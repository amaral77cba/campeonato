package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.CidadeRequestDTO;
import com.amarildo.campeonato.dto.CidadeResponseDTO;
import com.amarildo.campeonato.dto.TipoDisputaRequestDTO;
import com.amarildo.campeonato.dto.TipoDisputaResponseDTO;
import com.amarildo.campeonato.service.TipoDisputaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tipodisputa")
public class TipoDisputaController {

    private final TipoDisputaService tipoDisputaService;

    public TipoDisputaController(TipoDisputaService tipoDisputaService) {
        this.tipoDisputaService = tipoDisputaService;
    }

    @GetMapping
    public ResponseEntity<List<TipoDisputaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(tipoDisputaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDisputaResponseDTO> buscarPorId(@PathVariable Long id) {
        return tipoDisputaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoDisputaResponseDTO> salvar(@RequestBody TipoDisputaRequestDTO tipoDisputa) {

        TipoDisputaResponseDTO tipoDisputaSalva = tipoDisputaService.salvar(tipoDisputa);

        URI location = URI.create(
                "/api/tipodisputa/" + tipoDisputaSalva.getIdenTipoDisputa()
        );

        return ResponseEntity
                .created(location)
                .body(tipoDisputaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDisputaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody TipoDisputaRequestDTO tipoDisputa) {
        return tipoDisputaService.atualizar(id, tipoDisputa)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!tipoDisputaService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        tipoDisputaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
