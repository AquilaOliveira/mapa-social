package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Favorito;
import com.mapa.social.demo.service.FavoritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }
    @PostMapping
    public ResponseEntity<Favorito> adicionarFavorito(@RequestBody Favorito favorito) {
        try {
            Favorito novoFavorito = favoritoService.adicionarFavorito(favorito);
            return new ResponseEntity<>(novoFavorito, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Favorito>> listarFavoritosPorUsuario(@PathVariable Integer usuarioId) {
        List<Favorito> favoritos = favoritoService.listarFavoritosPorUsuario(usuarioId);
        if (favoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favoritos);
    }
    @DeleteMapping("/usuario/{usuarioId}/servico/{servicoId}")
    @Transactional
    public ResponseEntity<Void> removerFavorito(
            @PathVariable Integer usuarioId, 
            @PathVariable Integer servicoId) {
        favoritoService.removerFavorito(usuarioId, servicoId);
        return ResponseEntity.noContent().build();
    }
}
    

