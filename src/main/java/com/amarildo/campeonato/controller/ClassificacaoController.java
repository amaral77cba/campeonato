package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.ClassificacaoRequestDTO;
import com.amarildo.campeonato.dto.ClassificacaoResponseDTO;
import com.amarildo.campeonato.service.ClassificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/classificacao")
public class ClassificacaoController {

    private final ClassificacaoService classificacaoService;

    public ClassificacaoController(ClassificacaoService classificacaoService) {
        this.classificacaoService = classificacaoService;
    }

    @GetMapping
    public ResponseEntity<List<ClassificacaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(classificacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassificacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return classificacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClassificacaoResponseDTO> salvar(@RequestBody ClassificacaoRequestDTO classificacao) {
        ClassificacaoResponseDTO classificacaoSalva = classificacaoService.salvar(classificacao);

        URI location = URI.create(
                "/api/classificacao/" + classificacaoSalva.getIdenClassificacao()
        );

        return ResponseEntity
                .created(location)
                .body(classificacaoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassificacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ClassificacaoRequestDTO classificacao) {
        return classificacaoService.atualizar(id, classificacao)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!classificacaoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        classificacaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
