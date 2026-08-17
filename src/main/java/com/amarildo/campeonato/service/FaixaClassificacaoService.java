package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.FaixaClassificacaoRequestDTO;
import com.amarildo.campeonato.dto.FaixaClassificacaoResponseDTO;
import com.amarildo.campeonato.entity.FaixaClassificacao;
import com.amarildo.campeonato.entity.FaseCampeonato;
import com.amarildo.campeonato.repository.FaixaClassificacaoRepository;
import com.amarildo.campeonato.repository.FaseCampeonatoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FaixaClassificacaoService {

    private final FaixaClassificacaoRepository faixaClassificacaoRepository;
    private final FaseCampeonatoRepository faseCampeonatoRepository;

    public FaixaClassificacaoService(FaixaClassificacaoRepository faixaClassificacaoRepository,
                                     FaseCampeonatoRepository faseCampeonatoRepository) {
        this.faixaClassificacaoRepository = faixaClassificacaoRepository;
        this.faseCampeonatoRepository = faseCampeonatoRepository;
    }

    public List<FaixaClassificacaoResponseDTO> listarTodos() {
        return faixaClassificacaoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<FaixaClassificacaoResponseDTO> buscarPorId(Long id) {
        return faixaClassificacaoRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public FaixaClassificacaoResponseDTO salvar(FaixaClassificacaoRequestDTO dto) {
        FaixaClassificacao faixaClassificacao = new FaixaClassificacao();
        preencherFaixaClassificacao(faixaClassificacao, dto);
        return converterParaResponseDTO(faixaClassificacaoRepository.save(faixaClassificacao));
    }

    public Optional<FaixaClassificacaoResponseDTO> atualizar(Long id, FaixaClassificacaoRequestDTO dto) {
        return faixaClassificacaoRepository.findById(id)
                .map(faixaClassificacao -> {
                    preencherFaixaClassificacao(faixaClassificacao, dto);
                    return converterParaResponseDTO(faixaClassificacaoRepository.save(faixaClassificacao));
                });
    }

    public void excluir(Long id) {
        faixaClassificacaoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return faixaClassificacaoRepository.existsById(id);
    }

    private void preencherFaixaClassificacao(FaixaClassificacao faixaClassificacao, FaixaClassificacaoRequestDTO dto) {
        validarCamposObrigatorios(dto);
        validarTamanhos(dto);

        FaseCampeonato faseCampeonato = faseCampeonatoRepository.findById(dto.getIdenFaseCampeonato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Fase do campeonato nao encontrada: " + dto.getIdenFaseCampeonato()
                ));

        validarFaixaDuplicada(faixaClassificacao, dto);

        faixaClassificacao.setFaseCampeonato(faseCampeonato);
        faixaClassificacao.setNomeFaixa(dto.getNomeFaixa());
        faixaClassificacao.setCodigoCor(dto.getCodigoCor());
    }

    private void validarCamposObrigatorios(FaixaClassificacaoRequestDTO dto) {
        if (dto.getIdenFaseCampeonato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fase do campeonato deve ser informada");
        }

        if (dto.getNomeFaixa() == null || dto.getNomeFaixa().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome da faixa deve ser informado");
        }
    }

    private void validarTamanhos(FaixaClassificacaoRequestDTO dto) {
        if (dto.getNomeFaixa() != null && dto.getNomeFaixa().length() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome da faixa deve ter no maximo 100 caracteres");
        }

        if (dto.getCodigoCor() != null && dto.getCodigoCor().length() > 20) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Codigo da cor deve ter no maximo 20 caracteres");
        }
    }

    private void validarFaixaDuplicada(FaixaClassificacao faixaClassificacao, FaixaClassificacaoRequestDTO dto) {
        boolean faixaDuplicada;

        if (faixaClassificacao.getIdenFaixaClassificacao() == null) {
            faixaDuplicada = faixaClassificacaoRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndNomeFaixa(
                            dto.getIdenFaseCampeonato(),
                            dto.getNomeFaixa()
                    );
        } else {
            faixaDuplicada = faixaClassificacaoRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndNomeFaixaAndIdenFaixaClassificacaoNot(
                            dto.getIdenFaseCampeonato(),
                            dto.getNomeFaixa(),
                            faixaClassificacao.getIdenFaixaClassificacao()
                    );
        }

        if (faixaDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe faixa de classificacao com este nome para a fase informada"
            );
        }
    }

    private FaixaClassificacaoResponseDTO converterParaResponseDTO(FaixaClassificacao faixaClassificacao) {
        FaixaClassificacaoResponseDTO response = new FaixaClassificacaoResponseDTO();

        response.setIdenFaixaClassificacao(faixaClassificacao.getIdenFaixaClassificacao());
        response.setIdenFaseCampeonato(faixaClassificacao.getFaseCampeonato().getIdenFaseCampeonato());
        response.setNomeFaseCampeonato(faixaClassificacao.getFaseCampeonato().getNomeFaseCampeonato());
        response.setNomeFaixa(faixaClassificacao.getNomeFaixa());
        response.setCodigoCor(faixaClassificacao.getCodigoCor());
        return response;
    }
}
