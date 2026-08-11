package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.CriterioDesempateRequestDTO;
import com.amarildo.campeonato.dto.CriterioDesempateResponseDTO;
import com.amarildo.campeonato.service.CriterioDesempateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/criteriosdesempate")
public class CriterioDesempateController {

    private final CriterioDesempateService criterioDesempateService;

    public CriterioDesempateController(CriterioDesempateService criterioDesempateService) {
        this.criterioDesempateService = criterioDesempateService;
    }

    @GetMapping
    public ResponseEntity<List<CriterioDesempateResponseDTO>> listarTodos() {
        return ResponseEntity.ok(criterioDesempateService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriterioDesempateResponseDTO> buscarPorId(@PathVariable Long id) {
        return criterioDesempateService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CriterioDesempateResponseDTO> salvar(
            @RequestBody CriterioDesempateRequestDTO criterioDesempate) {
        CriterioDesempateResponseDTO criterioDesempateSalvo =
                criterioDesempateService.salvar(criterioDesempate);

        URI location = URI.create(
                "/api/criteriosdesempate/" + criterioDesempateSalvo.getIdenCriterioDesempate()
        );

        return ResponseEntity.created(location).body(criterioDesempateSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriterioDesempateResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody CriterioDesempateRequestDTO criterioDesempate) {
        return criterioDesempateService.atualizar(id, criterioDesempate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!criterioDesempateService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        criterioDesempateService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
