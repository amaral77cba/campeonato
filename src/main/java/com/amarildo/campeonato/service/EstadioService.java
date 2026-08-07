package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.EstadioRequestDTO;
import com.amarildo.campeonato.dto.EstadioResponseDTO;
import com.amarildo.campeonato.entity.Cidade;
import com.amarildo.campeonato.entity.Estadio;
import com.amarildo.campeonato.repository.CidadeRepository;
import com.amarildo.campeonato.repository.EstadioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EstadioService {

    private final EstadioRepository estadioRepository;
    private final CidadeRepository cidadeRepository;

    public EstadioService(EstadioRepository estadioRepository, CidadeRepository cidadeRepository) {
        this.estadioRepository = estadioRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public List<EstadioResponseDTO> listarTodos() {
        return estadioRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<EstadioResponseDTO> buscarPorId(Long id) {
        return estadioRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public EstadioResponseDTO salvar(EstadioRequestDTO dto) {
        Estadio estadio = new Estadio();
        preencherEstadio(estadio, dto);
        return converterParaResponseDTO(estadioRepository.save(estadio));
    }

    public Optional<EstadioResponseDTO> atualizar(Long id, EstadioRequestDTO dto) {
        return estadioRepository.findById(id)
                .map(estadio -> {
                    preencherEstadio(estadio, dto);
                    return converterParaResponseDTO(estadioRepository.save(estadio));
                });
    }

    public void excluir(Long id) {
        estadioRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return estadioRepository.existsById(id);
    }

    private void preencherEstadio(Estadio estadio, EstadioRequestDTO dto) {
        if (dto.getIdenCidade() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cidade deve ser informada");
        }

        Cidade cidade = cidadeRepository.findById(dto.getIdenCidade())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Cidade nao encontrada: " + dto.getIdenCidade()
                ));

        estadio.setNomeEstadio(dto.getNomeEstadio());
        estadio.setApelidoEstadio(dto.getApelidoEstadio());
        estadio.setCidade(cidade);
    }

    private EstadioResponseDTO converterParaResponseDTO(Estadio estadio) {
        EstadioResponseDTO response = new EstadioResponseDTO();
        response.setIdenEstadio(estadio.getIdenEstadio());
        response.setNomeEstadio(estadio.getNomeEstadio());
        response.setApelidoEstadio(estadio.getApelidoEstadio());
        response.setIdenCidade(estadio.getCidade().getIdenCidade());
        response.setNomeCidade(estadio.getCidade().getNomeCidade());
        return response;
    }
}
