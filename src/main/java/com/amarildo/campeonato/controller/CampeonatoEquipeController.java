package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.CampeonatoEquipeRequestDTO;
import com.amarildo.campeonato.dto.CampeonatoEquipeResponseDTO;
import com.amarildo.campeonato.service.CampeonatoEquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/campeonatoequipe")
public class CampeonatoEquipeController {

    private final CampeonatoEquipeService campeonatoEquipeService;

    public CampeonatoEquipeController(CampeonatoEquipeService campeonatoEquipeService) {
        this.campeonatoEquipeService = campeonatoEquipeService;
    }

    @GetMapping
    public ResponseEntity<List<CampeonatoEquipeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(campeonatoEquipeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoEquipeResponseDTO> buscarPorId(@PathVariable Long id) {
        return campeonatoEquipeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CampeonatoEquipeResponseDTO> salvar(@RequestBody CampeonatoEquipeRequestDTO campeonatoEquipe) {
        CampeonatoEquipeResponseDTO campeonatoEquipeSalva = campeonatoEquipeService.salvar(campeonatoEquipe);

        URI location = URI.create(
                "/api/campeonatoequipe/" + campeonatoEquipeSalva.getIdenCampeonatoEquipe()
        );

        return ResponseEntity
                .created(location)
                .body(campeonatoEquipeSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoEquipeResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody CampeonatoEquipeRequestDTO campeonatoEquipe) {
        return campeonatoEquipeService.atualizar(id, campeonatoEquipe)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!campeonatoEquipeService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        campeonatoEquipeService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
