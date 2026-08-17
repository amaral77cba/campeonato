package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.FaixaClassificacaoVigenciaRequestDTO;
import com.amarildo.campeonato.dto.FaixaClassificacaoVigenciaResponseDTO;
import com.amarildo.campeonato.service.FaixaClassificacaoVigenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/faixaclassificacaovigencia")
public class FaixaClassificacaoVigenciaController {

    private final FaixaClassificacaoVigenciaService faixaClassificacaoVigenciaService;

    public FaixaClassificacaoVigenciaController(FaixaClassificacaoVigenciaService faixaClassificacaoVigenciaService) {
        this.faixaClassificacaoVigenciaService = faixaClassificacaoVigenciaService;
    }

    @GetMapping
    public ResponseEntity<List<FaixaClassificacaoVigenciaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(faixaClassificacaoVigenciaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaixaClassificacaoVigenciaResponseDTO> buscarPorId(@PathVariable Long id) {
        return faixaClassificacaoVigenciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FaixaClassificacaoVigenciaResponseDTO> salvar(
            @RequestBody FaixaClassificacaoVigenciaRequestDTO vigencia) {
        FaixaClassificacaoVigenciaResponseDTO vigenciaSalva = faixaClassificacaoVigenciaService.salvar(vigencia);

        URI location = URI.create(
                "/api/faixaclassificacaovigencia/" + vigenciaSalva.getIdenFaixaClassificacaoVigencia()
        );

        return ResponseEntity
                .created(location)
                .body(vigenciaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaixaClassificacaoVigenciaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody FaixaClassificacaoVigenciaRequestDTO vigencia) {
        return faixaClassificacaoVigenciaService.atualizar(id, vigencia)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!faixaClassificacaoVigenciaService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        faixaClassificacaoVigenciaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
