package com.mapasocial.controller;

import com.mapasocial.dto.ServicoSocialDTO;
import com.mapasocial.service.ServicoSocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoSocialController {
    private final ServicoSocialService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoSocialDTO>> listar() {
        return ResponseEntity.ok(servicoService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoSocialDTO> buscar(@PathVariable UUID id) {
        return servicoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ServicoSocialDTO>> listarPorCategoria(@PathVariable UUID categoriaId) {
        return ResponseEntity.ok(servicoService.listarPorCategoria(categoriaId));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ServicoSocialDTO>> buscar(@RequestParam String termo) {
        return ResponseEntity.ok(servicoService.buscarPorTermo(termo));
    }
}
