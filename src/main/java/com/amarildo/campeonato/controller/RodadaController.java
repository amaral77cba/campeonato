package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.RodadaRequestDTO;
import com.amarildo.campeonato.dto.RodadaResponseDTO;
import com.amarildo.campeonato.service.RodadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rodada")
public class RodadaController {

    private final RodadaService rodadaService;

    public RodadaController(RodadaService rodadaService) {
        this.rodadaService = rodadaService;
    }

    @GetMapping
    public ResponseEntity<List<RodadaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(rodadaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RodadaResponseDTO> buscarPorId(@PathVariable Long id) {
        return rodadaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RodadaResponseDTO> salvar(@RequestBody RodadaRequestDTO rodada) {
        RodadaResponseDTO rodadaSalva = rodadaService.salvar(rodada);

        URI location = URI.create(
                "/api/rodada/" + rodadaSalva.getIdenRodada()
        );

        return ResponseEntity
                .created(location)
                .body(rodadaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RodadaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody RodadaRequestDTO rodada) {
        return rodadaService.atualizar(id, rodada)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!rodadaService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        rodadaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
