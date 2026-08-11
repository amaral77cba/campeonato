package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.CriterioDesempateRequestDTO;
import com.amarildo.campeonato.dto.CriterioDesempateResponseDTO;
import com.amarildo.campeonato.entity.CriterioDesempate;
import com.amarildo.campeonato.entity.enums.SentidoOrdenacao;
import com.amarildo.campeonato.repository.CriterioDesempateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CriterioDesempateService {

    private final CriterioDesempateRepository criterioDesempateRepository;

    public CriterioDesempateService(CriterioDesempateRepository criterioDesempateRepository) {
        this.criterioDesempateRepository = criterioDesempateRepository;
    }

    public List<CriterioDesempateResponseDTO> listarTodos() {
        return criterioDesempateRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<CriterioDesempateResponseDTO> buscarPorId(Long id) {
        return criterioDesempateRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public CriterioDesempateResponseDTO salvar(CriterioDesempateRequestDTO dto) {
        CriterioDesempate criterioDesempate = new CriterioDesempate();
        preencherCriterioDesempate(criterioDesempate, dto);
        return converterParaResponseDTO(criterioDesempateRepository.save(criterioDesempate));
    }

    public Optional<CriterioDesempateResponseDTO> atualizar(Long id, CriterioDesempateRequestDTO dto) {
        return criterioDesempateRepository.findById(id)
                .map(criterioDesempate -> {
                    preencherCriterioDesempate(criterioDesempate, dto);
                    return converterParaResponseDTO(criterioDesempateRepository.save(criterioDesempate));
                });
    }

    public void excluir(Long id) {
        criterioDesempateRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return criterioDesempateRepository.existsById(id);
    }

    private void preencherCriterioDesempate(CriterioDesempate criterioDesempate,
                                             CriterioDesempateRequestDTO dto) {
        if (dto.getCalculoAutomatico() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calculo automatico deve ser informado");
        }

        criterioDesempate.setNomeCriterioDesempate(dto.getNomeCriterioDesempate());
        criterioDesempate.setCodigoCriterioDesempate(dto.getCodigoCriterioDesempate());
        criterioDesempate.setSentidoOrdenacao(converterSentidoOrdenacao(dto.getSentidoOrdenacao()));
        criterioDesempate.setCalculoAutomatico(dto.getCalculoAutomatico());
    }

    private SentidoOrdenacao converterSentidoOrdenacao(String sentido) {
        if (sentido == null || sentido.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sentido de ordenacao deve ser informado");
        }

        try {
            return SentidoOrdenacao.valueOf(sentido.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sentido de ordenacao invalido: " + sentido,
                    ex
            );
        }
    }

    private CriterioDesempateResponseDTO converterParaResponseDTO(CriterioDesempate criterioDesempate) {
        CriterioDesempateResponseDTO response = new CriterioDesempateResponseDTO();
        response.setIdenCriterioDesempate(criterioDesempate.getIdenCriterioDesempate());
        response.setNomeCriterioDesempate(criterioDesempate.getNomeCriterioDesempate());
        response.setCodigoCriterioDesempate(criterioDesempate.getCodigoCriterioDesempate());
        response.setSentidoOrdenacao(criterioDesempate.getSentidoOrdenacao().name());
        response.setCalculoAutomatico(criterioDesempate.getCalculoAutomatico());
        return response;
    }
}
