package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.RegraPontuacaoRequestDTO;
import com.amarildo.campeonato.dto.RegraPontuacaoResponseDTO;
import com.amarildo.campeonato.service.RegraPontuacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/regrapontuacao")
public class RegraPontuacaoController {

    private final RegraPontuacaoService regraPontuacaoService;

    public RegraPontuacaoController(RegraPontuacaoService regraPontuacaoService) {
        this.regraPontuacaoService = regraPontuacaoService;
    }

    @GetMapping
    public ResponseEntity<List<RegraPontuacaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(regraPontuacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegraPontuacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return regraPontuacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegraPontuacaoResponseDTO> salvar(@RequestBody RegraPontuacaoRequestDTO regraPontuacao) {
        RegraPontuacaoResponseDTO regraPontuacaoSalva = regraPontuacaoService.salvar(regraPontuacao);

        URI location = URI.create(
                "/api/regrapontuacao/" + regraPontuacaoSalva.getIdenRegraPontuacao()
        );

        return ResponseEntity
                .created(location)
                .body(regraPontuacaoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegraPontuacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody RegraPontuacaoRequestDTO regraPontuacao) {
        return regraPontuacaoService.atualizar(id, regraPontuacao)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!regraPontuacaoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        regraPontuacaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
