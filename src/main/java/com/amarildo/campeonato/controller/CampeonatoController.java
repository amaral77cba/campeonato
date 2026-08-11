package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.CampeonatoRequestDTO;
import com.amarildo.campeonato.dto.CampeonatoResponseDTO;
import com.amarildo.campeonato.service.CampeonatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoController {

    private final CampeonatoService campeonatoService;

    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @GetMapping
    public ResponseEntity<List<CampeonatoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(campeonatoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return campeonatoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CampeonatoResponseDTO> salvar(@RequestBody CampeonatoRequestDTO campeonato) {

        CampeonatoResponseDTO campeonatoSalvo = campeonatoService.salvar(campeonato);

        URI location = URI.create(
                "/api/campeonatos/" + campeonatoSalvo.getIdenCampeonato()
        );

        return ResponseEntity
                .created(location)
                .body(campeonatoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody CampeonatoRequestDTO campeonato) {
        return campeonatoService.atualizar(id, campeonato)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!campeonatoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        campeonatoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
