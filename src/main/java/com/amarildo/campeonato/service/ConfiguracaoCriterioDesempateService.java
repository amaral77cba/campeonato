package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.ConfiguracaoCriterioDesempateRequestDTO;
import com.amarildo.campeonato.dto.ConfiguracaoCriterioDesempateResponseDTO;
import com.amarildo.campeonato.entity.ConfiguracaoCriterioDesempate;
import com.amarildo.campeonato.entity.CriterioDesempate;
import com.amarildo.campeonato.entity.FaseCampeonato;
import com.amarildo.campeonato.repository.ConfiguracaoCriterioDesempateRepository;
import com.amarildo.campeonato.repository.CriterioDesempateRepository;
import com.amarildo.campeonato.repository.FaseCampeonatoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoCriterioDesempateService {

    private final ConfiguracaoCriterioDesempateRepository configuracaoCriterioDesempateRepository;
    private final FaseCampeonatoRepository faseCampeonatoRepository;
    private final CriterioDesempateRepository criterioDesempateRepository;

    public ConfiguracaoCriterioDesempateService(
            ConfiguracaoCriterioDesempateRepository configuracaoCriterioDesempateRepository,
            FaseCampeonatoRepository faseCampeonatoRepository,
            CriterioDesempateRepository criterioDesempateRepository) {
        this.configuracaoCriterioDesempateRepository = configuracaoCriterioDesempateRepository;
        this.faseCampeonatoRepository = faseCampeonatoRepository;
        this.criterioDesempateRepository = criterioDesempateRepository;
    }

    public List<ConfiguracaoCriterioDesempateResponseDTO> listarTodos() {
        return configuracaoCriterioDesempateRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<ConfiguracaoCriterioDesempateResponseDTO> buscarPorId(Long id) {
        return configuracaoCriterioDesempateRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public ConfiguracaoCriterioDesempateResponseDTO salvar(ConfiguracaoCriterioDesempateRequestDTO dto) {
        ConfiguracaoCriterioDesempate configuracao = new ConfiguracaoCriterioDesempate();
        preencherConfiguracao(configuracao, dto);
        return converterParaResponseDTO(configuracaoCriterioDesempateRepository.save(configuracao));
    }

    public Optional<ConfiguracaoCriterioDesempateResponseDTO> atualizar(
            Long id,
            ConfiguracaoCriterioDesempateRequestDTO dto) {
        return configuracaoCriterioDesempateRepository.findById(id)
                .map(configuracao -> {
                    preencherConfiguracao(configuracao, dto);
                    return converterParaResponseDTO(configuracaoCriterioDesempateRepository.save(configuracao));
                });
    }

    public void excluir(Long id) {
        configuracaoCriterioDesempateRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return configuracaoCriterioDesempateRepository.existsById(id);
    }

    private void preencherConfiguracao(
            ConfiguracaoCriterioDesempate configuracao,
            ConfiguracaoCriterioDesempateRequestDTO dto) {
        validarCamposObrigatorios(dto);
        validarOrdem(dto);

        FaseCampeonato faseCampeonato = faseCampeonatoRepository.findById(dto.getIdenFaseCampeonato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Fase do campeonato nao encontrada: " + dto.getIdenFaseCampeonato()
                ));

        CriterioDesempate criterioDesempate = criterioDesempateRepository.findById(dto.getIdenCriterioDesempate())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Criterio de desempate nao encontrado: " + dto.getIdenCriterioDesempate()
                ));

        validarDuplicidade(configuracao, dto);

        configuracao.setFaseCampeonato(faseCampeonato);
        configuracao.setCriterioDesempate(criterioDesempate);
        configuracao.setOrdem(dto.getOrdem());
    }

    private void validarCamposObrigatorios(ConfiguracaoCriterioDesempateRequestDTO dto) {
        if (dto.getIdenFaseCampeonato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fase do campeonato deve ser informada");
        }

        if (dto.getIdenCriterioDesempate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Criterio de desempate deve ser informado");
        }

        if (dto.getOrdem() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem deve ser informada");
        }
    }

    private void validarOrdem(ConfiguracaoCriterioDesempateRequestDTO dto) {
        if (dto.getOrdem() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem deve ser maior que zero");
        }
    }

    private void validarDuplicidade(
            ConfiguracaoCriterioDesempate configuracao,
            ConfiguracaoCriterioDesempateRequestDTO dto) {
        boolean criterioDuplicado;
        boolean ordemDuplicada;

        if (configuracao.getIdenConfigCriterioDesempate() == null) {
            criterioDuplicado = configuracaoCriterioDesempateRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndCriterioDesempate_IdenCriterioDesempate(
                            dto.getIdenFaseCampeonato(),
                            dto.getIdenCriterioDesempate()
                    );
            ordemDuplicada = configuracaoCriterioDesempateRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndOrdem(
                            dto.getIdenFaseCampeonato(),
                            dto.getOrdem()
                    );
        } else {
            criterioDuplicado = configuracaoCriterioDesempateRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndCriterioDesempate_IdenCriterioDesempateAndIdenConfigCriterioDesempateNot(
                            dto.getIdenFaseCampeonato(),
                            dto.getIdenCriterioDesempate(),
                            configuracao.getIdenConfigCriterioDesempate()
                    );
            ordemDuplicada = configuracaoCriterioDesempateRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndOrdemAndIdenConfigCriterioDesempateNot(
                            dto.getIdenFaseCampeonato(),
                            dto.getOrdem(),
                            configuracao.getIdenConfigCriterioDesempate()
                    );
        }

        if (criterioDuplicado) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe configuracao para este criterio na fase informada"
            );
        }

        if (ordemDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe configuracao com esta ordem para a fase informada"
            );
        }
    }

    private ConfiguracaoCriterioDesempateResponseDTO converterParaResponseDTO(
            ConfiguracaoCriterioDesempate configuracao) {
        ConfiguracaoCriterioDesempateResponseDTO response = new ConfiguracaoCriterioDesempateResponseDTO();

        response.setIdenConfigCriterioDesempate(configuracao.getIdenConfigCriterioDesempate());
        response.setIdenFaseCampeonato(configuracao.getFaseCampeonato().getIdenFaseCampeonato());
        response.setNomeFaseCampeonato(configuracao.getFaseCampeonato().getNomeFaseCampeonato());
        response.setIdenCriterioDesempate(configuracao.getCriterioDesempate().getIdenCriterioDesempate());
        response.setNomeCriterioDesempate(configuracao.getCriterioDesempate().getNomeCriterioDesempate());
        response.setCodigoCriterioDesempate(configuracao.getCriterioDesempate().getCodigoCriterioDesempate());
        response.setSentidoOrdenacao(configuracao.getCriterioDesempate().getSentidoOrdenacao().name());
        response.setCalculoAutomatico(configuracao.getCriterioDesempate().getCalculoAutomatico());
        response.setOrdem(configuracao.getOrdem());
        return response;
    }
}
