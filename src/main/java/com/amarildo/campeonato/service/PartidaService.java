package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.PartidaRequestDTO;
import com.amarildo.campeonato.dto.PartidaResponseDTO;
import com.amarildo.campeonato.entity.CampeonatoEquipe;
import com.amarildo.campeonato.entity.Estadio;
import com.amarildo.campeonato.entity.Partida;
import com.amarildo.campeonato.entity.Rodada;
import com.amarildo.campeonato.entity.enums.StatusPartida;
import com.amarildo.campeonato.repository.CampeonatoEquipeRepository;
import com.amarildo.campeonato.repository.EstadioRepository;
import com.amarildo.campeonato.repository.PartidaRepository;
import com.amarildo.campeonato.repository.RodadaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final RodadaRepository rodadaRepository;
    private final CampeonatoEquipeRepository campeonatoEquipeRepository;
    private final EstadioRepository estadioRepository;

    public PartidaService(PartidaRepository partidaRepository,
                          RodadaRepository rodadaRepository,
                          CampeonatoEquipeRepository campeonatoEquipeRepository,
                          EstadioRepository estadioRepository) {
        this.partidaRepository = partidaRepository;
        this.rodadaRepository = rodadaRepository;
        this.campeonatoEquipeRepository = campeonatoEquipeRepository;
        this.estadioRepository = estadioRepository;
    }

    public List<PartidaResponseDTO> listarTodos() {
        return partidaRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<PartidaResponseDTO> buscarPorId(Long id) {
        return partidaRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public PartidaResponseDTO salvar(PartidaRequestDTO dto) {
        Partida partida = new Partida();
        preencherPartida(partida, dto);
        return converterParaResponseDTO(partidaRepository.save(partida));
    }

    public Optional<PartidaResponseDTO> atualizar(Long id, PartidaRequestDTO dto) {
        return partidaRepository.findById(id)
                .map(partida -> {
                    preencherPartida(partida, dto);
                    return converterParaResponseDTO(partidaRepository.save(partida));
                });
    }

    public void excluir(Long id) {
        partidaRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return partidaRepository.existsById(id);
    }

    private void preencherPartida(Partida partida, PartidaRequestDTO dto) {
        validarCamposObrigatorios(dto);
        validarEquipesDiferentes(dto);

        Rodada rodada = rodadaRepository.findById(dto.getIdenRodada())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Rodada nao encontrada: " + dto.getIdenRodada()
                ));

        CampeonatoEquipe mandante = campeonatoEquipeRepository.findById(dto.getIdenCampeonatoEquipeMandante())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Equipe mandante do campeonato nao encontrada: " + dto.getIdenCampeonatoEquipeMandante()
                ));

        CampeonatoEquipe visitante = campeonatoEquipeRepository.findById(dto.getIdenCampeonatoEquipeVisitante())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Equipe visitante do campeonato nao encontrada: " + dto.getIdenCampeonatoEquipeVisitante()
                ));

        Estadio estadio = buscarEstadio(dto.getIdenEstadio());

        validarPartidaDuplicada(partida, dto);

        partida.setRodada(rodada);
        partida.setCampeonatoEquipeMandante(mandante);
        partida.setCampeonatoEquipeVisitante(visitante);
        partida.setDataHoraPartida(dto.getDataHoraPartida());
        partida.setEstadio(estadio);
        partida.setStatusPartida(converterStatusPartida(dto.getStatusPartida()));
    }

    private void validarCamposObrigatorios(PartidaRequestDTO dto) {
        if (dto.getIdenRodada() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rodada deve ser informada");
        }

        if (dto.getIdenCampeonatoEquipeMandante() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipe mandante deve ser informada");
        }

        if (dto.getIdenCampeonatoEquipeVisitante() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipe visitante deve ser informada");
        }
    }

    private void validarEquipesDiferentes(PartidaRequestDTO dto) {
        if (dto.getIdenCampeonatoEquipeMandante().equals(dto.getIdenCampeonatoEquipeVisitante())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Equipe mandante deve ser diferente da equipe visitante"
            );
        }
    }

    private Estadio buscarEstadio(Long idenEstadio) {
        if (idenEstadio == null) {
            return null;
        }

        return estadioRepository.findById(idenEstadio)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Estadio nao encontrado: " + idenEstadio
                ));
    }

    private void validarPartidaDuplicada(Partida partida, PartidaRequestDTO dto) {
        boolean partidaDuplicada;

        if (partida.getIdenPartida() == null) {
            partidaDuplicada = partidaRepository
                    .existsByRodada_IdenRodadaAndCampeonatoEquipeMandante_IdenCampeonatoEquipeAndCampeonatoEquipeVisitante_IdenCampeonatoEquipe(
                            dto.getIdenRodada(),
                            dto.getIdenCampeonatoEquipeMandante(),
                            dto.getIdenCampeonatoEquipeVisitante()
                    );
        } else {
            partidaDuplicada = partidaRepository
                    .existsByRodada_IdenRodadaAndCampeonatoEquipeMandante_IdenCampeonatoEquipeAndCampeonatoEquipeVisitante_IdenCampeonatoEquipeAndIdenPartidaNot(
                            dto.getIdenRodada(),
                            dto.getIdenCampeonatoEquipeMandante(),
                            dto.getIdenCampeonatoEquipeVisitante(),
                            partida.getIdenPartida()
                    );
        }

        if (partidaDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe partida para esta rodada com as equipes informadas"
            );
        }
    }

    private StatusPartida converterStatusPartida(String status) {
        if (status == null || status.isBlank()) {
            return StatusPartida.PROGRAMADA;
        }

        try {
            return StatusPartida.valueOf(status.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status da partida invalido: " + status, ex);
        }
    }

    private PartidaResponseDTO converterParaResponseDTO(Partida partida) {
        PartidaResponseDTO response = new PartidaResponseDTO();

        response.setIdenPartida(partida.getIdenPartida());
        response.setIdenRodada(partida.getRodada().getIdenRodada());
        response.setNomeRodada(partida.getRodada().getNomeRodada());
        response.setOrdemRodada(partida.getRodada().getOrdemRodada());
        response.setIdenCampeonatoEquipeMandante(partida.getCampeonatoEquipeMandante().getIdenCampeonatoEquipe());
        response.setNomeEquipeMandante(partida.getCampeonatoEquipeMandante().getEquipe().getNomeEquipe());
        response.setIdenCampeonatoEquipeVisitante(partida.getCampeonatoEquipeVisitante().getIdenCampeonatoEquipe());
        response.setNomeEquipeVisitante(partida.getCampeonatoEquipeVisitante().getEquipe().getNomeEquipe());
        response.setDataHoraPartida(partida.getDataHoraPartida());

        if (partida.getEstadio() != null) {
            response.setIdenEstadio(partida.getEstadio().getIdenEstadio());
            response.setNomeEstadio(partida.getEstadio().getNomeEstadio());
        }

        response.setStatusPartida(partida.getStatusPartida().name());
        return response;
    }
}
