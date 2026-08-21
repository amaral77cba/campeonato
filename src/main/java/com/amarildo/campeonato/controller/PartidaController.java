package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.PartidaRequestDTO;
import com.amarildo.campeonato.dto.PartidaResponseDTO;
import com.amarildo.campeonato.service.PartidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/partida")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @GetMapping
    public ResponseEntity<List<PartidaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(partidaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidaResponseDTO> buscarPorId(@PathVariable Long id) {
        return partidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PartidaResponseDTO> salvar(@RequestBody PartidaRequestDTO partida) {
        PartidaResponseDTO partidaSalva = partidaService.salvar(partida);

        URI location = URI.create(
                "/api/partida/" + partidaSalva.getIdenPartida()
        );

        return ResponseEntity
                .created(location)
                .body(partidaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody PartidaRequestDTO partida) {
        return partidaService.atualizar(id, partida)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!partidaService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        partidaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
