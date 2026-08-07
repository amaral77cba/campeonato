package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.EstadioRequestDTO;
import com.amarildo.campeonato.dto.EstadioResponseDTO;
import com.amarildo.campeonato.service.EstadioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/estadios")
public class EstadioController {

    private final EstadioService estadioService;

    public EstadioController(EstadioService estadioService) {
        this.estadioService = estadioService;
    }

    @GetMapping
    public ResponseEntity<List<EstadioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(estadioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadioResponseDTO> buscarPorId(@PathVariable Long id) {
        return estadioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstadioResponseDTO> salvar(@RequestBody EstadioRequestDTO estadio) {

        EstadioResponseDTO estadioSalvo = estadioService.salvar(estadio);

        URI location = URI.create(
                "/api/estadios/" + estadioSalvo.getIdenEstadio()
        );

        return ResponseEntity
                .created(location)
                .body(estadioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody EstadioRequestDTO estadio) {
        return estadioService.atualizar(id, estadio)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!estadioService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        estadioService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
