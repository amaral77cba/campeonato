package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.CidadeRequestDTO;
import com.amarildo.campeonato.dto.CidadeResponseDTO;
import com.amarildo.campeonato.service.CidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<CidadeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(cidadeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> buscarPorId(@PathVariable Long id) {
        return cidadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CidadeResponseDTO> salvar(@RequestBody CidadeRequestDTO cidade) {

        CidadeResponseDTO cidadeSalva = cidadeService.salvar(cidade);

        URI location = URI.create(
                "/api/cidades/" + cidadeSalva.getIdenCidade()
        );

        return ResponseEntity
                .created(location)
                .body(cidadeSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody CidadeRequestDTO cidade) {
        return cidadeService.atualizar(id, cidade)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!cidadeService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        cidadeService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
