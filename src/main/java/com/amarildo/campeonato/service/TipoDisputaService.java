package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.CidadeRequestDTO;
import com.amarildo.campeonato.dto.CidadeResponseDTO;
import com.amarildo.campeonato.dto.TipoDisputaRequestDTO;
import com.amarildo.campeonato.dto.TipoDisputaResponseDTO;
import com.amarildo.campeonato.entity.Cidade;
import com.amarildo.campeonato.entity.TipoDisputa;
import com.amarildo.campeonato.entity.enums.StatusTipoDisputa;
import com.amarildo.campeonato.entity.enums.Uf;
import com.amarildo.campeonato.repository.TipoDisputaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TipoDisputaService {

    private final TipoDisputaRepository tipoDisputaRepository;

    public TipoDisputaService(TipoDisputaRepository tipoDisputaRepository) {
        this.tipoDisputaRepository = tipoDisputaRepository;
    }

    public List<TipoDisputaResponseDTO> listarTodos(){
        return tipoDisputaRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<TipoDisputaResponseDTO> buscarPorId(Long id){
        return tipoDisputaRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public TipoDisputaResponseDTO salvar(TipoDisputaRequestDTO dto){
        TipoDisputa tipoDisputa = new TipoDisputa();

        preencherTipoDisputa(tipoDisputa, dto);

        tipoDisputa = tipoDisputaRepository.save(tipoDisputa);

        return converterParaResponseDTO(tipoDisputa);

    }

    public Optional<TipoDisputaResponseDTO> atualizar(Long id, TipoDisputaRequestDTO dto) {
        return tipoDisputaRepository.findById(id)
                .map(tipoDisputa -> {
                    preencherTipoDisputa(tipoDisputa, dto);
                    return converterParaResponseDTO(tipoDisputaRepository.save(tipoDisputa));
                });
    }

    public void excluir(Long id) {
        tipoDisputaRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return tipoDisputaRepository.existsById(id);
    }

    private void preencherTipoDisputa(TipoDisputa tipoDisputa, TipoDisputaRequestDTO dto) {
        tipoDisputa.setNomeTipoDisputa(dto.getNomeTipoDisputa());
        tipoDisputa.setStatusTipoDisputa(converterStatusTipoDisputa(dto.getStatusTipoDisputa()));
    }

    private StatusTipoDisputa converterStatusTipoDisputa(String status) {
        if (status == null || status.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status Tipo de Disputa deve ser informado");
        }

        try {
            return StatusTipoDisputa.valueOf(status.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status Tipo de Disputa invalido: " + status, ex);
        }
    }

    private TipoDisputaResponseDTO converterParaResponseDTO(TipoDisputa tipoDisputa) {
        TipoDisputaResponseDTO response = new TipoDisputaResponseDTO();

        response.setIdenTipoDisputa(tipoDisputa.getIdenTipoDisputa());
        response.setNomeTipoDisputa(tipoDisputa.getNomeTipoDisputa());
        response.setStatusTipoDisputa(tipoDisputa.getStatusTipoDisputa().name());
        return response;
    }
}
