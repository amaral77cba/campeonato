package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.ResultadoPartidaRequestDTO;
import com.amarildo.campeonato.dto.ResultadoPartidaResponseDTO;
import com.amarildo.campeonato.entity.Partida;
import com.amarildo.campeonato.entity.ResultadoPartida;
import com.amarildo.campeonato.repository.PartidaRepository;
import com.amarildo.campeonato.repository.ResultadoPartidaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResultadoPartidaService {

    private final ResultadoPartidaRepository resultadoPartidaRepository;
    private final PartidaRepository partidaRepository;

    public ResultadoPartidaService(ResultadoPartidaRepository resultadoPartidaRepository,
                                   PartidaRepository partidaRepository) {
        this.resultadoPartidaRepository = resultadoPartidaRepository;
        this.partidaRepository = partidaRepository;
    }

    public List<ResultadoPartidaResponseDTO> listarTodos() {
        return resultadoPartidaRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<ResultadoPartidaResponseDTO> buscarPorId(Long id) {
        return resultadoPartidaRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public ResultadoPartidaResponseDTO salvar(ResultadoPartidaRequestDTO dto) {
        ResultadoPartida resultadoPartida = new ResultadoPartida();
        preencherResultadoPartida(resultadoPartida, dto);
        return converterParaResponseDTO(resultadoPartidaRepository.save(resultadoPartida));
    }

    public Optional<ResultadoPartidaResponseDTO> atualizar(Long id, ResultadoPartidaRequestDTO dto) {
        return resultadoPartidaRepository.findById(id)
                .map(resultadoPartida -> {
                    preencherResultadoPartida(resultadoPartida, dto);
                    return converterParaResponseDTO(resultadoPartidaRepository.save(resultadoPartida));
                });
    }

    public void excluir(Long id) {
        resultadoPartidaRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return resultadoPartidaRepository.existsById(id);
    }

    private void preencherResultadoPartida(ResultadoPartida resultadoPartida, ResultadoPartidaRequestDTO dto) {
        validarCamposObrigatorios(dto);
        validarGols(dto);

        Partida partida = partidaRepository.findById(dto.getIdenPartida())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Partida nao encontrada: " + dto.getIdenPartida()
                ));

        validarPartidaDuplicada(resultadoPartida, dto);

        resultadoPartida.setPartida(partida);
        resultadoPartida.setGolsEquipeMandante(dto.getGolsEquipeMandante());
        resultadoPartida.setGolsEquipeVisitante(dto.getGolsEquipeVisitante());
        resultadoPartida.setDataRegistro(
                dto.getDataRegistro() != null ? dto.getDataRegistro() : LocalDateTime.now()
        );
    }

    private void validarCamposObrigatorios(ResultadoPartidaRequestDTO dto) {
        if (dto.getIdenPartida() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Partida deve ser informada");
        }

        if (dto.getGolsEquipeMandante() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gols da equipe mandante deve ser informado");
        }

        if (dto.getGolsEquipeVisitante() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gols da equipe visitante deve ser informado");
        }
    }

    private void validarGols(ResultadoPartidaRequestDTO dto) {
        if (dto.getGolsEquipeMandante() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gols da equipe mandante deve ser maior ou igual a zero");
        }

        if (dto.getGolsEquipeVisitante() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gols da equipe visitante deve ser maior ou igual a zero");
        }
    }

    private void validarPartidaDuplicada(ResultadoPartida resultadoPartida, ResultadoPartidaRequestDTO dto) {
        boolean partidaDuplicada;

        if (resultadoPartida.getIdenResultadoPartida() == null) {
            partidaDuplicada = resultadoPartidaRepository.existsByPartida_IdenPartida(dto.getIdenPartida());
        } else {
            partidaDuplicada = resultadoPartidaRepository.existsByPartida_IdenPartidaAndIdenResultadoPartidaNot(
                    dto.getIdenPartida(),
                    resultadoPartida.getIdenResultadoPartida()
            );
        }

        if (partidaDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe resultado para a partida informada"
            );
        }
    }

    private ResultadoPartidaResponseDTO converterParaResponseDTO(ResultadoPartida resultadoPartida) {
        ResultadoPartidaResponseDTO response = new ResultadoPartidaResponseDTO();
        Partida partida = resultadoPartida.getPartida();

        response.setIdenResultadoPartida(resultadoPartida.getIdenResultadoPartida());
        response.setIdenPartida(partida.getIdenPartida());
        response.setIdenCampeonatoEquipeMandante(partida.getCampeonatoEquipeMandante().getIdenCampeonatoEquipe());
        response.setNomeEquipeMandante(partida.getCampeonatoEquipeMandante().getEquipe().getNomeEquipe());
        response.setIdenCampeonatoEquipeVisitante(partida.getCampeonatoEquipeVisitante().getIdenCampeonatoEquipe());
        response.setNomeEquipeVisitante(partida.getCampeonatoEquipeVisitante().getEquipe().getNomeEquipe());
        response.setGolsEquipeMandante(resultadoPartida.getGolsEquipeMandante());
        response.setGolsEquipeVisitante(resultadoPartida.getGolsEquipeVisitante());
        response.setDataRegistro(resultadoPartida.getDataRegistro());
        return response;
    }
}
