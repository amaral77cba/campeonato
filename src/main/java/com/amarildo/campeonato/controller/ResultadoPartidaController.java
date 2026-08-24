package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.ResultadoPartidaRequestDTO;
import com.amarildo.campeonato.dto.ResultadoPartidaResponseDTO;
import com.amarildo.campeonato.service.ResultadoPartidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/resultadopartida")
public class ResultadoPartidaController {

    private final ResultadoPartidaService resultadoPartidaService;

    public ResultadoPartidaController(ResultadoPartidaService resultadoPartidaService) {
        this.resultadoPartidaService = resultadoPartidaService;
    }

    @GetMapping
    public ResponseEntity<List<ResultadoPartidaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(resultadoPartidaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoPartidaResponseDTO> buscarPorId(@PathVariable Long id) {
        return resultadoPartidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResultadoPartidaResponseDTO> salvar(@RequestBody ResultadoPartidaRequestDTO resultadoPartida) {
        ResultadoPartidaResponseDTO resultadoPartidaSalvo = resultadoPartidaService.salvar(resultadoPartida);

        URI location = URI.create(
                "/api/resultadopartida/" + resultadoPartidaSalvo.getIdenResultadoPartida()
        );

        return ResponseEntity
                .created(location)
                .body(resultadoPartidaSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultadoPartidaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ResultadoPartidaRequestDTO resultadoPartida) {
        return resultadoPartidaService.atualizar(id, resultadoPartida)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!resultadoPartidaService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        resultadoPartidaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
