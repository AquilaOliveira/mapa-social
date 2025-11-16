package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Historico;
import com.mapa.social.demo.service.HistoricoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @PostMapping
    public ResponseEntity<Historico> registrarAcesso(@RequestBody Historico historico) {
        Historico novoRegistro = historicoService.registrarAcesso(historico);
        return new ResponseEntity<>(novoRegistro, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Historico>> buscarHistoricoPorUsuario(@PathVariable Integer usuarioId) {
        List<Historico> historico = historicoService.buscarPorUsuarioId(usuarioId);
        if (historico.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historico> buscarPorId(@PathVariable Integer id) {
        return historicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable Integer id) {
        try {
            historicoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); 
        }
    }
}