package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.RodadaRequestDTO;
import com.amarildo.campeonato.dto.RodadaResponseDTO;
import com.amarildo.campeonato.entity.FaseCampeonato;
import com.amarildo.campeonato.entity.Rodada;
import com.amarildo.campeonato.entity.enums.StatusRodada;
import com.amarildo.campeonato.repository.FaseCampeonatoRepository;
import com.amarildo.campeonato.repository.RodadaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class RodadaService {

    private final RodadaRepository rodadaRepository;
    private final FaseCampeonatoRepository faseCampeonatoRepository;

    public RodadaService(RodadaRepository rodadaRepository,
                         FaseCampeonatoRepository faseCampeonatoRepository) {
        this.rodadaRepository = rodadaRepository;
        this.faseCampeonatoRepository = faseCampeonatoRepository;
    }

    public List<RodadaResponseDTO> listarTodos() {
        return rodadaRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<RodadaResponseDTO> buscarPorId(Long id) {
        return rodadaRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public RodadaResponseDTO salvar(RodadaRequestDTO dto) {
        Rodada rodada = new Rodada();
        preencherRodada(rodada, dto);
        return converterParaResponseDTO(rodadaRepository.save(rodada));
    }

    public Optional<RodadaResponseDTO> atualizar(Long id, RodadaRequestDTO dto) {
        return rodadaRepository.findById(id)
                .map(rodada -> {
                    preencherRodada(rodada, dto);
                    return converterParaResponseDTO(rodadaRepository.save(rodada));
                });
    }

    public void excluir(Long id) {
        rodadaRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return rodadaRepository.existsById(id);
    }

    private void preencherRodada(Rodada rodada, RodadaRequestDTO dto) {
        validarCamposObrigatorios(dto);
        validarOrdem(dto);
        validarDatas(dto);

        FaseCampeonato faseCampeonato = faseCampeonatoRepository.findById(dto.getIdenFaseCampeonato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Fase do campeonato nao encontrada: " + dto.getIdenFaseCampeonato()
                ));

        validarOrdemDuplicada(rodada, dto);

        rodada.setFaseCampeonato(faseCampeonato);
        rodada.setOrdemRodada(dto.getOrdemRodada());
        rodada.setNomeRodada(dto.getNomeRodada());
        rodada.setDataInicio(dto.getDataInicio());
        rodada.setDataFim(dto.getDataFim());
        rodada.setStatusRodada(converterStatusRodada(dto.getStatusRodada()));
    }

    private void validarCamposObrigatorios(RodadaRequestDTO dto) {
        if (dto.getIdenFaseCampeonato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fase do campeonato deve ser informada");
        }

        if (dto.getOrdemRodada() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem da rodada deve ser informada");
        }
    }

    private void validarOrdem(RodadaRequestDTO dto) {
        if (dto.getOrdemRodada() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem da rodada deve ser maior que zero");
        }
    }

    private void validarDatas(RodadaRequestDTO dto) {
        if (dto.getDataFim() != null
                && dto.getDataInicio() != null
                && dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Data fim deve ser maior ou igual a data inicio"
            );
        }
    }

    private void validarOrdemDuplicada(Rodada rodada, RodadaRequestDTO dto) {
        boolean ordemDuplicada;

        if (rodada.getIdenRodada() == null) {
            ordemDuplicada = rodadaRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndOrdemRodada(
                            dto.getIdenFaseCampeonato(),
                            dto.getOrdemRodada()
                    );
        } else {
            ordemDuplicada = rodadaRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndOrdemRodadaAndIdenRodadaNot(
                            dto.getIdenFaseCampeonato(),
                            dto.getOrdemRodada(),
                            rodada.getIdenRodada()
                    );
        }

        if (ordemDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe rodada com esta ordem para a fase informada"
            );
        }
    }

    private StatusRodada converterStatusRodada(String status) {
        if (status == null || status.isBlank()) {
            return StatusRodada.PROGRAMADA;
        }

        try {
            return StatusRodada.valueOf(status.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status da rodada invalido: " + status, ex);
        }
    }

    private RodadaResponseDTO converterParaResponseDTO(Rodada rodada) {
        RodadaResponseDTO response = new RodadaResponseDTO();

        response.setIdenRodada(rodada.getIdenRodada());
        response.setIdenFaseCampeonato(rodada.getFaseCampeonato().getIdenFaseCampeonato());
        response.setNomeFaseCampeonato(rodada.getFaseCampeonato().getNomeFaseCampeonato());
        response.setOrdemRodada(rodada.getOrdemRodada());
        response.setNomeRodada(rodada.getNomeRodada());
        response.setDataInicio(rodada.getDataInicio());
        response.setDataFim(rodada.getDataFim());
        response.setStatusRodada(rodada.getStatusRodada().name());
        return response;
    }
}
