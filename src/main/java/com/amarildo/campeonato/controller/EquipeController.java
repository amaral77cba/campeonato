package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.EquipeRequestDTO;
import com.amarildo.campeonato.dto.EquipeResponseDTO;
import com.amarildo.campeonato.service.EquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping
    public ResponseEntity<List<EquipeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(equipeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> buscarPorId(@PathVariable Long id) {
        return equipeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EquipeResponseDTO> salvar(@RequestBody EquipeRequestDTO equipe) {

        EquipeResponseDTO equipeSalva = equipeService.salvar(equipe);

        URI location = URI.create(
                "/api/equipes/" + equipeSalva.getIdenEquipe()
        );

        return ResponseEntity
                .created(location)
                .body(equipeSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody EquipeRequestDTO equipe) {
        return equipeService.atualizar(id, equipe)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!equipeService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        equipeService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
