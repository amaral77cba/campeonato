package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.FaseCampeonatoRequestDTO;
import com.amarildo.campeonato.dto.FaseCampeonatoResponseDTO;
import com.amarildo.campeonato.entity.Campeonato;
import com.amarildo.campeonato.entity.FaseCampeonato;
import com.amarildo.campeonato.entity.TipoDisputa;
import com.amarildo.campeonato.repository.CampeonatoRepository;
import com.amarildo.campeonato.repository.FaseCampeonatoRepository;
import com.amarildo.campeonato.repository.TipoDisputaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FaseCampeonatoService {

    private final FaseCampeonatoRepository faseCampeonatoRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final TipoDisputaRepository tipoDisputaRepository;

    public FaseCampeonatoService(FaseCampeonatoRepository faseCampeonatoRepository,
                                 CampeonatoRepository campeonatoRepository,
                                 TipoDisputaRepository tipoDisputaRepository) {
        this.faseCampeonatoRepository = faseCampeonatoRepository;
        this.campeonatoRepository = campeonatoRepository;
        this.tipoDisputaRepository = tipoDisputaRepository;
    }

    public List<FaseCampeonatoResponseDTO> listarTodos() {
        return faseCampeonatoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<FaseCampeonatoResponseDTO> buscarPorId(Long id) {
        return faseCampeonatoRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public FaseCampeonatoResponseDTO salvar(FaseCampeonatoRequestDTO dto) {
        FaseCampeonato faseCampeonato = new FaseCampeonato();
        preencherFaseCampeonato(faseCampeonato, dto);
        return converterParaResponseDTO(faseCampeonatoRepository.save(faseCampeonato));
    }

    public Optional<FaseCampeonatoResponseDTO> atualizar(Long id, FaseCampeonatoRequestDTO dto) {
        return faseCampeonatoRepository.findById(id)
                .map(faseCampeonato -> {
                    preencherFaseCampeonato(faseCampeonato, dto);
                    return converterParaResponseDTO(faseCampeonatoRepository.save(faseCampeonato));
                });
    }

    public void excluir(Long id) {
        faseCampeonatoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return faseCampeonatoRepository.existsById(id);
    }

    private void preencherFaseCampeonato(FaseCampeonato faseCampeonato, FaseCampeonatoRequestDTO dto) {
        validarCamposObrigatorios(dto);
        validarNumeros(dto);

        Campeonato campeonato = campeonatoRepository.findById(dto.getIdenCampeonato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Campeonato nao encontrado: " + dto.getIdenCampeonato()
                ));

        TipoDisputa tipoDisputa = tipoDisputaRepository.findById(dto.getIdenTipoDisputa())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Tipo de disputa nao encontrado: " + dto.getIdenTipoDisputa()
                ));

        validarOrdemDuplicada(faseCampeonato, dto);

        faseCampeonato.setCampeonato(campeonato);
        faseCampeonato.setNomeFaseCampeonato(dto.getNomeFaseCampeonato());
        faseCampeonato.setOrdem(dto.getOrdem());
        faseCampeonato.setQtdClassificam(dto.getQtdClassificam());
        faseCampeonato.setPossuiIdaVolta(dto.getPossuiIdaVolta() != null ? dto.getPossuiIdaVolta() : Boolean.FALSE);
        faseCampeonato.setTipoDisputa(tipoDisputa);
    }

    private void validarCamposObrigatorios(FaseCampeonatoRequestDTO dto) {
        if (dto.getIdenCampeonato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campeonato deve ser informado");
        }

        if (dto.getNomeFaseCampeonato() == null || dto.getNomeFaseCampeonato().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome da fase deve ser informado");
        }

        if (dto.getOrdem() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem deve ser informada");
        }

        if (dto.getIdenTipoDisputa() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de disputa deve ser informado");
        }
    }

    private void validarNumeros(FaseCampeonatoRequestDTO dto) {
        if (dto.getOrdem() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem deve ser maior que zero");
        }

        if (dto.getQtdClassificam() != null && dto.getQtdClassificam() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de classificados deve ser maior que zero");
        }
    }

    private void validarOrdemDuplicada(FaseCampeonato faseCampeonato, FaseCampeonatoRequestDTO dto) {
        boolean ordemDuplicada;

        if (faseCampeonato.getIdenFaseCampeonato() == null) {
            ordemDuplicada = faseCampeonatoRepository
                    .existsByCampeonato_IdenCampeonatoAndOrdem(dto.getIdenCampeonato(), dto.getOrdem());
        } else {
            ordemDuplicada = faseCampeonatoRepository
                    .existsByCampeonato_IdenCampeonatoAndOrdemAndIdenFaseCampeonatoNot(
                            dto.getIdenCampeonato(),
                            dto.getOrdem(),
                            faseCampeonato.getIdenFaseCampeonato()
                    );
        }

        if (ordemDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe fase com esta ordem para o campeonato informado"
            );
        }
    }

    private FaseCampeonatoResponseDTO converterParaResponseDTO(FaseCampeonato faseCampeonato) {
        FaseCampeonatoResponseDTO response = new FaseCampeonatoResponseDTO();

        response.setIdenFaseCampeonato(faseCampeonato.getIdenFaseCampeonato());
        response.setIdenCampeonato(faseCampeonato.getCampeonato().getIdenCampeonato());
        response.setNomeCampeonato(faseCampeonato.getCampeonato().getNomeCampeonato());
        response.setNomeFaseCampeonato(faseCampeonato.getNomeFaseCampeonato());
        response.setOrdem(faseCampeonato.getOrdem());
        response.setQtdClassificam(faseCampeonato.getQtdClassificam());
        response.setPossuiIdaVolta(faseCampeonato.getPossuiIdaVolta());
        response.setIdenTipoDisputa(faseCampeonato.getTipoDisputa().getIdenTipoDisputa());
        response.setNomeTipoDisputa(faseCampeonato.getTipoDisputa().getNomeTipoDisputa());
        return response;
    }
}
