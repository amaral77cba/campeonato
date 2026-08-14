package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.FaseCampeonatoRequestDTO;
import com.amarildo.campeonato.dto.FaseCampeonatoResponseDTO;
import com.amarildo.campeonato.service.FaseCampeonatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fasecampeonato")
public class FaseCampeonatoController {

    private final FaseCampeonatoService faseCampeonatoService;

    public FaseCampeonatoController(FaseCampeonatoService faseCampeonatoService) {
        this.faseCampeonatoService = faseCampeonatoService;
    }

    @GetMapping
    public ResponseEntity<List<FaseCampeonatoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(faseCampeonatoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaseCampeonatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return faseCampeonatoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FaseCampeonatoResponseDTO> salvar(@RequestBody FaseCampeonatoRequestDTO faseCampeonato) {
        FaseCampeonatoResponseDTO faseCampeonatoSalva = faseCampeonatoService.salvar(faseCampeonato);

        URI location = URI.create(
                "/api/fasecampeonato/" + faseCampeonatoSalva.getIdenFaseCampeonato()
        );

        return ResponseEntity
                .created(location)
                .body(faseCampeonatoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaseCampeonatoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody FaseCampeonatoRequestDTO faseCampeonato) {
        return faseCampeonatoService.atualizar(id, faseCampeonato)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!faseCampeonatoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        faseCampeonatoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
