package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.EquipeRequestDTO;
import com.amarildo.campeonato.dto.EquipeResponseDTO;
import com.amarildo.campeonato.entity.Cidade;
import com.amarildo.campeonato.entity.Equipe;
import com.amarildo.campeonato.entity.Estadio;
import com.amarildo.campeonato.repository.CidadeRepository;
import com.amarildo.campeonato.repository.EquipeRepository;
import com.amarildo.campeonato.repository.EstadioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadioRepository estadioRepository;

    public EquipeService(EquipeRepository equipeRepository,
                         CidadeRepository cidadeRepository,
                         EstadioRepository estadioRepository) {
        this.equipeRepository = equipeRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadioRepository = estadioRepository;
    }

    public List<EquipeResponseDTO> listarTodos() {
        return equipeRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<EquipeResponseDTO> buscarPorId(Long id) {
        return equipeRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public EquipeResponseDTO salvar(EquipeRequestDTO dto) {
        Equipe equipe = new Equipe();
        preencherEquipe(equipe, dto);
        return converterParaResponseDTO(equipeRepository.save(equipe));
    }

    public Optional<EquipeResponseDTO> atualizar(Long id, EquipeRequestDTO dto) {
        return equipeRepository.findById(id)
                .map(equipe -> {
                    preencherEquipe(equipe, dto);
                    return converterParaResponseDTO(equipeRepository.save(equipe));
                });
    }

    public void excluir(Long id) {
        equipeRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return equipeRepository.existsById(id);
    }

    private void preencherEquipe(Equipe equipe, EquipeRequestDTO dto) {
        if (dto.getIdenCidade() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cidade deve ser informada");
        }

        Cidade cidade = cidadeRepository.findById(dto.getIdenCidade())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Cidade nao encontrada: " + dto.getIdenCidade()
                ));

        Estadio estadioPadrao = null;
        if (dto.getIdenEstadioPadrao() != null) {
            estadioPadrao = estadioRepository.findById(dto.getIdenEstadioPadrao())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Estadio padrao nao encontrado: " + dto.getIdenEstadioPadrao()
                    ));
        }

        equipe.setNomeEquipe(dto.getNomeEquipe());
        equipe.setSiglaEquipe(dto.getSiglaEquipe());
        equipe.setCidade(cidade);
        equipe.setCaminhoEscudo(dto.getCaminhoEscudo());
        equipe.setEstadioPadrao(estadioPadrao);
    }

    private EquipeResponseDTO converterParaResponseDTO(Equipe equipe) {
        EquipeResponseDTO response = new EquipeResponseDTO();
        response.setIdenEquipe(equipe.getIdenEquipe());
        response.setNomeEquipe(equipe.getNomeEquipe());
        response.setSiglaEquipe(equipe.getSiglaEquipe());
        response.setIdenCidade(equipe.getCidade().getIdenCidade());
        response.setNomeCidade(equipe.getCidade().getNomeCidade());
        response.setCaminhoEscudo(equipe.getCaminhoEscudo());

        if (equipe.getEstadioPadrao() != null) {
            response.setIdenEstadioPadrao(equipe.getEstadioPadrao().getIdenEstadio());
            response.setNomeEstadioPadrao(equipe.getEstadioPadrao().getNomeEstadio());
        }

        return response;
    }
}
