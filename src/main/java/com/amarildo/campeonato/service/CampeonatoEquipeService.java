package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.CampeonatoEquipeRequestDTO;
import com.amarildo.campeonato.dto.CampeonatoEquipeResponseDTO;
import com.amarildo.campeonato.entity.Campeonato;
import com.amarildo.campeonato.entity.CampeonatoEquipe;
import com.amarildo.campeonato.entity.Equipe;
import com.amarildo.campeonato.entity.enums.StatusParticipacao;
import com.amarildo.campeonato.repository.CampeonatoEquipeRepository;
import com.amarildo.campeonato.repository.CampeonatoRepository;
import com.amarildo.campeonato.repository.EquipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CampeonatoEquipeService {

    private final CampeonatoEquipeRepository campeonatoEquipeRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final EquipeRepository equipeRepository;

    public CampeonatoEquipeService(CampeonatoEquipeRepository campeonatoEquipeRepository,
                                   CampeonatoRepository campeonatoRepository,
                                   EquipeRepository equipeRepository) {
        this.campeonatoEquipeRepository = campeonatoEquipeRepository;
        this.campeonatoRepository = campeonatoRepository;
        this.equipeRepository = equipeRepository;
    }

    public List<CampeonatoEquipeResponseDTO> listarTodos() {
        return campeonatoEquipeRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<CampeonatoEquipeResponseDTO> buscarPorId(Long id) {
        return campeonatoEquipeRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public CampeonatoEquipeResponseDTO salvar(CampeonatoEquipeRequestDTO dto) {
        CampeonatoEquipe campeonatoEquipe = new CampeonatoEquipe();
        preencherCampeonatoEquipe(campeonatoEquipe, dto);
        return converterParaResponseDTO(campeonatoEquipeRepository.save(campeonatoEquipe));
    }

    public Optional<CampeonatoEquipeResponseDTO> atualizar(Long id, CampeonatoEquipeRequestDTO dto) {
        return campeonatoEquipeRepository.findById(id)
                .map(campeonatoEquipe -> {
                    preencherCampeonatoEquipe(campeonatoEquipe, dto);
                    return converterParaResponseDTO(campeonatoEquipeRepository.save(campeonatoEquipe));
                });
    }

    public void excluir(Long id) {
        campeonatoEquipeRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return campeonatoEquipeRepository.existsById(id);
    }

    private void preencherCampeonatoEquipe(CampeonatoEquipe campeonatoEquipe, CampeonatoEquipeRequestDTO dto) {
        if (dto.getIdenCampeonato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campeonato deve ser informado");
        }

        if (dto.getIdenEquipe() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipe deve ser informada");
        }

        Campeonato campeonato = campeonatoRepository.findById(dto.getIdenCampeonato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Campeonato nao encontrado: " + dto.getIdenCampeonato()
                ));

        Equipe equipe = equipeRepository.findById(dto.getIdenEquipe())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Equipe nao encontrada: " + dto.getIdenEquipe()
                ));

        validarInscricaoDuplicada(campeonatoEquipe, dto);

        campeonatoEquipe.setCampeonato(campeonato);
        campeonatoEquipe.setEquipe(equipe);
        campeonatoEquipe.setDataInscricao(
                dto.getDataInscricao() != null ? dto.getDataInscricao() : LocalDate.now()
        );
        campeonatoEquipe.setCabecaChave(dto.getCabecaChave() != null ? dto.getCabecaChave() : Boolean.FALSE);
        campeonatoEquipe.setStatusParticipacao(converterStatusParticipacao(dto.getStatusParticipacao()));
    }

    private void validarInscricaoDuplicada(CampeonatoEquipe campeonatoEquipe, CampeonatoEquipeRequestDTO dto) {
        boolean inscricaoDuplicada;

        if (campeonatoEquipe.getIdenCampeonatoEquipe() == null) {
            inscricaoDuplicada = campeonatoEquipeRepository
                    .existsByCampeonato_IdenCampeonatoAndEquipe_IdenEquipe(
                            dto.getIdenCampeonato(),
                            dto.getIdenEquipe()
                    );
        } else {
            inscricaoDuplicada = campeonatoEquipeRepository
                    .existsByCampeonato_IdenCampeonatoAndEquipe_IdenEquipeAndIdenCampeonatoEquipeNot(
                            dto.getIdenCampeonato(),
                            dto.getIdenEquipe(),
                            campeonatoEquipe.getIdenCampeonatoEquipe()
                    );
        }

        if (inscricaoDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Equipe ja inscrita neste campeonato"
            );
        }
    }

    private StatusParticipacao converterStatusParticipacao(String status) {
        if (status == null || status.isBlank()) {
            return StatusParticipacao.ATIVA;
        }

        try {
            return StatusParticipacao.valueOf(status.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status de participacao invalido: " + status, ex);
        }
    }

    private CampeonatoEquipeResponseDTO converterParaResponseDTO(CampeonatoEquipe campeonatoEquipe) {
        CampeonatoEquipeResponseDTO response = new CampeonatoEquipeResponseDTO();

        response.setIdenCampeonatoEquipe(campeonatoEquipe.getIdenCampeonatoEquipe());
        response.setIdenCampeonato(campeonatoEquipe.getCampeonato().getIdenCampeonato());
        response.setNomeCampeonato(campeonatoEquipe.getCampeonato().getNomeCampeonato());
        response.setIdenEquipe(campeonatoEquipe.getEquipe().getIdenEquipe());
        response.setNomeEquipe(campeonatoEquipe.getEquipe().getNomeEquipe());
        response.setDataInscricao(campeonatoEquipe.getDataInscricao());
        response.setCabecaChave(campeonatoEquipe.getCabecaChave());
        response.setStatusParticipacao(campeonatoEquipe.getStatusParticipacao().name());
        return response;
    }
}
