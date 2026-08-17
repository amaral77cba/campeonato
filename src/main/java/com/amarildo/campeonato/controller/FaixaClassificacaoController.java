package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.FaixaClassificacaoRequestDTO;
import com.amarildo.campeonato.dto.FaixaClassificacaoResponseDTO;
import com.amarildo.campeonato.service.FaixaClassificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/faixaclassificacao")
public class FaixaClassificacaoController {

    private final FaixaClassificacaoService faixaClassificacaoService;

    public FaixaClassificacaoController(FaixaClassificacaoService faixaClassificacaoService) {
        this.faixaClassificacaoService = faixaClassificacaoService;
    }

    @GetMapping
    public ResponseEntity<List<FaixaClassificacaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(faixaClassificacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaixaClassificacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return faixaClassificacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FaixaClassificacaoResponseDTO> salvar(@RequestBody FaixaClassificacaoRequestDTO faixaClassificacao) {
        FaixaClassificacaoResponseDTO faixaClassificacaoSalva = faixaClassificacaoService.salvar(faixaClassificacao);

        URI location = URI.create(
                "/api/faixaclassificacao/" + faixaClassificacaoSalva.getIdenFaixaClassificacao()
        );

        return ResponseEntity
                .created(location)
                .body(faixaClassificacaoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaixaClassificacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody FaixaClassificacaoRequestDTO faixaClassificacao) {
        return faixaClassificacaoService.atualizar(id, faixaClassificacao)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!faixaClassificacaoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        faixaClassificacaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
