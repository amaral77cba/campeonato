package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.CampeonatoRequestDTO;
import com.amarildo.campeonato.dto.CampeonatoResponseDTO;
import com.amarildo.campeonato.entity.Campeonato;
import com.amarildo.campeonato.repository.CampeonatoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository) {
        this.campeonatoRepository = campeonatoRepository;
    }

    public List<CampeonatoResponseDTO> listarTodos() {
        return campeonatoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<CampeonatoResponseDTO> buscarPorId(Long id) {
        return campeonatoRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public CampeonatoResponseDTO salvar(CampeonatoRequestDTO dto) {
        Campeonato campeonato = new Campeonato();

        preencherCampeonato(campeonato, dto);

        campeonato = campeonatoRepository.save(campeonato);

        return converterParaResponseDTO(campeonato);
    }

    public Optional<CampeonatoResponseDTO> atualizar(Long id, CampeonatoRequestDTO dto) {
        return campeonatoRepository.findById(id)
                .map(campeonato -> {
                    preencherCampeonato(campeonato, dto);
                    return converterParaResponseDTO(campeonatoRepository.save(campeonato));
                });
    }

    public void excluir(Long id) {
        campeonatoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return campeonatoRepository.existsById(id);
    }

    private void preencherCampeonato(Campeonato campeonato, CampeonatoRequestDTO dto) {
        if (dto.getNomeCampeonato() == null || dto.getNomeCampeonato().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do campeonato deve ser informado");
        }

        if (dto.getAno() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ano do campeonato deve ser informado");
        }

        if (dto.getDataFim() != null
                && dto.getDataInicio() != null
                && dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data fim nao pode ser anterior a data inicio");
        }

        campeonato.setNomeCampeonato(dto.getNomeCampeonato());
        campeonato.setAno(dto.getAno());
        campeonato.setDataInicio(dto.getDataInicio());
        campeonato.setDataFim(dto.getDataFim());
    }

    private CampeonatoResponseDTO converterParaResponseDTO(Campeonato campeonato) {
        CampeonatoResponseDTO response = new CampeonatoResponseDTO();

        response.setIdenCampeonato(campeonato.getIdenCampeonato());
        response.setNomeCampeonato(campeonato.getNomeCampeonato());
        response.setAno(campeonato.getAno());
        response.setDataInicio(campeonato.getDataInicio());
        response.setDataFim(campeonato.getDataFim());
        return response;
    }
}
