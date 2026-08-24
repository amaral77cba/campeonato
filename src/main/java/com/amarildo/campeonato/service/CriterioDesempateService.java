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
        validarCamposObrigatorios(dto);
        validarDuplicidade(criterioDesempate, dto);

        criterioDesempate.setNomeCriterioDesempate(dto.getNomeCriterioDesempate().trim());
        criterioDesempate.setCodigoCriterioDesempate(dto.getCodigoCriterioDesempate().trim());
        criterioDesempate.setSentidoOrdenacao(converterSentidoOrdenacao(dto.getSentidoOrdenacao()));
        criterioDesempate.setCalculoAutomatico(
                dto.getCalculoAutomatico() != null ? dto.getCalculoAutomatico() : Boolean.TRUE
        );
    }

    private void validarCamposObrigatorios(CriterioDesempateRequestDTO dto) {
        if (dto.getNomeCriterioDesempate() == null || dto.getNomeCriterioDesempate().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do criterio de desempate deve ser informado");
        }

        if (dto.getCodigoCriterioDesempate() == null || dto.getCodigoCriterioDesempate().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Codigo do criterio de desempate deve ser informado");
        }
    }

    private void validarDuplicidade(CriterioDesempate criterioDesempate, CriterioDesempateRequestDTO dto) {
        boolean nomeDuplicado;
        boolean codigoDuplicado;

        String nome = dto.getNomeCriterioDesempate().trim();
        String codigo = dto.getCodigoCriterioDesempate().trim();

        if (criterioDesempate.getIdenCriterioDesempate() == null) {
            nomeDuplicado = criterioDesempateRepository.existsByNomeCriterioDesempateIgnoreCase(nome);
            codigoDuplicado = criterioDesempateRepository.existsByCodigoCriterioDesempateIgnoreCase(codigo);
        } else {
            nomeDuplicado = criterioDesempateRepository.existsByNomeCriterioDesempateIgnoreCaseAndIdenCriterioDesempateNot(
                    nome,
                    criterioDesempate.getIdenCriterioDesempate()
            );
            codigoDuplicado = criterioDesempateRepository.existsByCodigoCriterioDesempateIgnoreCaseAndIdenCriterioDesempateNot(
                    codigo,
                    criterioDesempate.getIdenCriterioDesempate()
            );
        }

        if (nomeDuplicado) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe criterio de desempate com este nome"
            );
        }

        if (codigoDuplicado) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe criterio de desempate com este codigo"
            );
        }
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
