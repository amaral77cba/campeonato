package com.amarildo.campeonato.controller;

import com.amarildo.campeonato.dto.ConfiguracaoCriterioDesempateRequestDTO;
import com.amarildo.campeonato.dto.ConfiguracaoCriterioDesempateResponseDTO;
import com.amarildo.campeonato.service.ConfiguracaoCriterioDesempateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/configuracaocriteriodesempate")
public class ConfiguracaoCriterioDesempateController {

    private final ConfiguracaoCriterioDesempateService configuracaoCriterioDesempateService;

    public ConfiguracaoCriterioDesempateController(
            ConfiguracaoCriterioDesempateService configuracaoCriterioDesempateService) {
        this.configuracaoCriterioDesempateService = configuracaoCriterioDesempateService;
    }

    @GetMapping
    public ResponseEntity<List<ConfiguracaoCriterioDesempateResponseDTO>> listarTodos() {
        return ResponseEntity.ok(configuracaoCriterioDesempateService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracaoCriterioDesempateResponseDTO> buscarPorId(@PathVariable Long id) {
        return configuracaoCriterioDesempateService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConfiguracaoCriterioDesempateResponseDTO> salvar(
            @RequestBody ConfiguracaoCriterioDesempateRequestDTO configuracaoCriterioDesempate) {
        ConfiguracaoCriterioDesempateResponseDTO configuracaoSalva =
                configuracaoCriterioDesempateService.salvar(configuracaoCriterioDesempate);

        URI location = URI.create(
                "/api/configuracaocriteriodesempate/"
                        + configuracaoSalva.getIdenConfigCriterioDesempate()
        );

        return ResponseEntity
                .created(location)
                .body(configuracaoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracaoCriterioDesempateResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ConfiguracaoCriterioDesempateRequestDTO configuracaoCriterioDesempate) {
        return configuracaoCriterioDesempateService.atualizar(id, configuracaoCriterioDesempate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!configuracaoCriterioDesempateService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }

        configuracaoCriterioDesempateService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
