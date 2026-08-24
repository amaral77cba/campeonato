package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.RegraPontuacaoRequestDTO;
import com.amarildo.campeonato.dto.RegraPontuacaoResponseDTO;
import com.amarildo.campeonato.entity.FaseCampeonato;
import com.amarildo.campeonato.entity.RegraPontuacao;
import com.amarildo.campeonato.repository.FaseCampeonatoRepository;
import com.amarildo.campeonato.repository.RegraPontuacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RegraPontuacaoService {

    private final RegraPontuacaoRepository regraPontuacaoRepository;
    private final FaseCampeonatoRepository faseCampeonatoRepository;

    public RegraPontuacaoService(RegraPontuacaoRepository regraPontuacaoRepository,
                                 FaseCampeonatoRepository faseCampeonatoRepository) {
        this.regraPontuacaoRepository = regraPontuacaoRepository;
        this.faseCampeonatoRepository = faseCampeonatoRepository;
    }

    public List<RegraPontuacaoResponseDTO> listarTodos() {
        return regraPontuacaoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<RegraPontuacaoResponseDTO> buscarPorId(Long id) {
        return regraPontuacaoRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public RegraPontuacaoResponseDTO salvar(RegraPontuacaoRequestDTO dto) {
        RegraPontuacao regraPontuacao = new RegraPontuacao();
        preencherRegraPontuacao(regraPontuacao, dto);
        return converterParaResponseDTO(regraPontuacaoRepository.save(regraPontuacao));
    }

    public Optional<RegraPontuacaoResponseDTO> atualizar(Long id, RegraPontuacaoRequestDTO dto) {
        return regraPontuacaoRepository.findById(id)
                .map(regraPontuacao -> {
                    preencherRegraPontuacao(regraPontuacao, dto);
                    return converterParaResponseDTO(regraPontuacaoRepository.save(regraPontuacao));
                });
    }

    public void excluir(Long id) {
        regraPontuacaoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return regraPontuacaoRepository.existsById(id);
    }

    private void preencherRegraPontuacao(RegraPontuacao regraPontuacao, RegraPontuacaoRequestDTO dto) {
        validarCamposObrigatorios(dto);

        Integer pontosVitoria = dto.getPontosVitoria() != null ? dto.getPontosVitoria() : 3;
        Integer pontosEmpate = dto.getPontosEmpate() != null ? dto.getPontosEmpate() : 1;
        Integer pontosDerrota = dto.getPontosDerrota() != null ? dto.getPontosDerrota() : 0;

        validarPontuacao(pontosVitoria, pontosEmpate, pontosDerrota);

        FaseCampeonato faseCampeonato = faseCampeonatoRepository.findById(dto.getIdenFaseCampeonato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Fase do campeonato nao encontrada: " + dto.getIdenFaseCampeonato()
                ));

        validarFaseDuplicada(regraPontuacao, dto);

        regraPontuacao.setFaseCampeonato(faseCampeonato);
        regraPontuacao.setPontosVitoria(pontosVitoria);
        regraPontuacao.setPontosEmpate(pontosEmpate);
        regraPontuacao.setPontosDerrota(pontosDerrota);
    }

    private void validarCamposObrigatorios(RegraPontuacaoRequestDTO dto) {
        if (dto.getIdenFaseCampeonato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fase do campeonato deve ser informada");
        }
    }

    private void validarPontuacao(Integer pontosVitoria, Integer pontosEmpate, Integer pontosDerrota) {
        if (pontosVitoria < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pontos por vitoria deve ser maior ou igual a zero");
        }

        if (pontosEmpate < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pontos por empate deve ser maior ou igual a zero");
        }

        if (pontosDerrota < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pontos por derrota deve ser maior ou igual a zero");
        }
    }

    private void validarFaseDuplicada(RegraPontuacao regraPontuacao, RegraPontuacaoRequestDTO dto) {
        boolean faseDuplicada;

        if (regraPontuacao.getIdenRegraPontuacao() == null) {
            faseDuplicada = regraPontuacaoRepository
                    .existsByFaseCampeonato_IdenFaseCampeonato(dto.getIdenFaseCampeonato());
        } else {
            faseDuplicada = regraPontuacaoRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndIdenRegraPontuacaoNot(
                            dto.getIdenFaseCampeonato(),
                            regraPontuacao.getIdenRegraPontuacao()
                    );
        }

        if (faseDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe regra de pontuacao para a fase informada"
            );
        }
    }

    private RegraPontuacaoResponseDTO converterParaResponseDTO(RegraPontuacao regraPontuacao) {
        RegraPontuacaoResponseDTO response = new RegraPontuacaoResponseDTO();

        response.setIdenRegraPontuacao(regraPontuacao.getIdenRegraPontuacao());
        response.setIdenFaseCampeonato(regraPontuacao.getFaseCampeonato().getIdenFaseCampeonato());
        response.setNomeFaseCampeonato(regraPontuacao.getFaseCampeonato().getNomeFaseCampeonato());
        response.setPontosVitoria(regraPontuacao.getPontosVitoria());
        response.setPontosEmpate(regraPontuacao.getPontosEmpate());
        response.setPontosDerrota(regraPontuacao.getPontosDerrota());
        return response;
    }
}
