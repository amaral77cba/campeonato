package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.FaixaClassificacaoVigenciaRequestDTO;
import com.amarildo.campeonato.dto.FaixaClassificacaoVigenciaResponseDTO;
import com.amarildo.campeonato.entity.FaixaClassificacao;
import com.amarildo.campeonato.entity.FaixaClassificacaoVigencia;
import com.amarildo.campeonato.repository.FaixaClassificacaoRepository;
import com.amarildo.campeonato.repository.FaixaClassificacaoVigenciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FaixaClassificacaoVigenciaService {

    private final FaixaClassificacaoVigenciaRepository faixaClassificacaoVigenciaRepository;
    private final FaixaClassificacaoRepository faixaClassificacaoRepository;

    public FaixaClassificacaoVigenciaService(
            FaixaClassificacaoVigenciaRepository faixaClassificacaoVigenciaRepository,
            FaixaClassificacaoRepository faixaClassificacaoRepository) {
        this.faixaClassificacaoVigenciaRepository = faixaClassificacaoVigenciaRepository;
        this.faixaClassificacaoRepository = faixaClassificacaoRepository;
    }

    public List<FaixaClassificacaoVigenciaResponseDTO> listarTodos() {
        return faixaClassificacaoVigenciaRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<FaixaClassificacaoVigenciaResponseDTO> buscarPorId(Long id) {
        return faixaClassificacaoVigenciaRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public FaixaClassificacaoVigenciaResponseDTO salvar(FaixaClassificacaoVigenciaRequestDTO dto) {
        FaixaClassificacaoVigencia vigencia = new FaixaClassificacaoVigencia();
        preencherFaixaClassificacaoVigencia(vigencia, dto);
        return converterParaResponseDTO(faixaClassificacaoVigenciaRepository.save(vigencia));
    }

    public Optional<FaixaClassificacaoVigenciaResponseDTO> atualizar(Long id, FaixaClassificacaoVigenciaRequestDTO dto) {
        return faixaClassificacaoVigenciaRepository.findById(id)
                .map(vigencia -> {
                    preencherFaixaClassificacaoVigencia(vigencia, dto);
                    return converterParaResponseDTO(faixaClassificacaoVigenciaRepository.save(vigencia));
                });
    }

    public void excluir(Long id) {
        faixaClassificacaoVigenciaRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return faixaClassificacaoVigenciaRepository.existsById(id);
    }

    private void preencherFaixaClassificacaoVigencia(
            FaixaClassificacaoVigencia vigencia,
            FaixaClassificacaoVigenciaRequestDTO dto) {
        validarCamposObrigatorios(dto);
        validarRegras(dto);

        FaixaClassificacao faixaClassificacao = faixaClassificacaoRepository.findById(dto.getIdenFaixaClassificacao())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Faixa de classificacao nao encontrada: " + dto.getIdenFaixaClassificacao()
                ));

        validarInicioDuplicado(vigencia, dto);

        vigencia.setFaixaClassificacao(faixaClassificacao);
        vigencia.setPosicaoInicial(dto.getPosicaoInicial());
        vigencia.setPosicaoFinal(dto.getPosicaoFinal());
        vigencia.setDataInicioVigencia(dto.getDataInicioVigencia());
        vigencia.setDataFimVigencia(dto.getDataFimVigencia());
        vigencia.setMotivoAlteracao(dto.getMotivoAlteracao());
    }

    private void validarCamposObrigatorios(FaixaClassificacaoVigenciaRequestDTO dto) {
        if (dto.getIdenFaixaClassificacao() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faixa de classificacao deve ser informada");
        }

        if (dto.getPosicaoInicial() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posicao inicial deve ser informada");
        }

        if (dto.getPosicaoFinal() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posicao final deve ser informada");
        }

        if (dto.getDataInicioVigencia() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de inicio da vigencia deve ser informada");
        }
    }

    private void validarRegras(FaixaClassificacaoVigenciaRequestDTO dto) {
        if (dto.getPosicaoInicial() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posicao inicial deve ser maior que zero");
        }

        if (dto.getPosicaoFinal() < dto.getPosicaoInicial()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posicao final deve ser maior ou igual a posicao inicial");
        }

        if (dto.getDataFimVigencia() != null && dto.getDataFimVigencia().isBefore(dto.getDataInicioVigencia())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de fim deve ser maior ou igual a data de inicio");
        }

        if (dto.getMotivoAlteracao() != null && dto.getMotivoAlteracao().length() > 500) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Motivo da alteracao deve ter no maximo 500 caracteres");
        }
    }

    private void validarInicioDuplicado(
            FaixaClassificacaoVigencia vigencia,
            FaixaClassificacaoVigenciaRequestDTO dto) {
        boolean inicioDuplicado;

        if (vigencia.getIdenFaixaClassificacaoVigencia() == null) {
            inicioDuplicado = faixaClassificacaoVigenciaRepository
                    .existsByFaixaClassificacao_IdenFaixaClassificacaoAndDataInicioVigencia(
                            dto.getIdenFaixaClassificacao(),
                            dto.getDataInicioVigencia()
                    );
        } else {
            inicioDuplicado = faixaClassificacaoVigenciaRepository
                    .existsByFaixaClassificacao_IdenFaixaClassificacaoAndDataInicioVigenciaAndIdenFaixaClassificacaoVigenciaNot(
                            dto.getIdenFaixaClassificacao(),
                            dto.getDataInicioVigencia(),
                            vigencia.getIdenFaixaClassificacaoVigencia()
                    );
        }

        if (inicioDuplicado) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe vigencia com esta data de inicio para a faixa informada"
            );
        }
    }

    private FaixaClassificacaoVigenciaResponseDTO converterParaResponseDTO(FaixaClassificacaoVigencia vigencia) {
        FaixaClassificacaoVigenciaResponseDTO response = new FaixaClassificacaoVigenciaResponseDTO();

        response.setIdenFaixaClassificacaoVigencia(vigencia.getIdenFaixaClassificacaoVigencia());
        response.setIdenFaixaClassificacao(vigencia.getFaixaClassificacao().getIdenFaixaClassificacao());
        response.setNomeFaixa(vigencia.getFaixaClassificacao().getNomeFaixa());
        response.setPosicaoInicial(vigencia.getPosicaoInicial());
        response.setPosicaoFinal(vigencia.getPosicaoFinal());
        response.setDataInicioVigencia(vigencia.getDataInicioVigencia());
        response.setDataFimVigencia(vigencia.getDataFimVigencia());
        response.setMotivoAlteracao(vigencia.getMotivoAlteracao());
        return response;
    }
}
