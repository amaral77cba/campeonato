package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.CidadeRequestDTO;
import com.amarildo.campeonato.dto.CidadeResponseDTO;
import com.amarildo.campeonato.entity.Cidade;
import com.amarildo.campeonato.entity.enums.Uf;
import com.amarildo.campeonato.repository.CidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public List<CidadeResponseDTO> listarTodos() {
        return cidadeRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<CidadeResponseDTO> buscarPorId(Long id) {
        return cidadeRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public CidadeResponseDTO salvar(CidadeRequestDTO dto) {
        Cidade cidade = new Cidade();

        preencherCidade(cidade, dto);

        cidade = cidadeRepository.save(cidade);

        return converterParaResponseDTO(cidade);
    }

    public Optional<CidadeResponseDTO> atualizar(Long id, CidadeRequestDTO dto) {
        return cidadeRepository.findById(id)
                .map(cidade -> {
                    preencherCidade(cidade, dto);
                    return converterParaResponseDTO(cidadeRepository.save(cidade));
                });
    }

    public void excluir(Long id) {
        cidadeRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return cidadeRepository.existsById(id);
    }

    private void preencherCidade(Cidade cidade, CidadeRequestDTO dto) {
        cidade.setNomeCidade(dto.getNomeCidade());
        cidade.setUf(converterUf(dto.getUf()));
    }

    private Uf converterUf(String uf) {
        if (uf == null || uf.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UF deve ser informada");
        }

        try {
            return Uf.valueOf(uf.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UF invalida: " + uf, ex);
        }
    }

    private CidadeResponseDTO converterParaResponseDTO(Cidade cidade) {
        CidadeResponseDTO response = new CidadeResponseDTO();
        response.setIdenCidade(cidade.getIdenCidade());
        response.setNomeCidade(cidade.getNomeCidade());
        response.setUf(cidade.getUf().name());
        return response;
    }
}
