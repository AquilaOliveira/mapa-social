package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Favorito;
import com.mapa.social.demo.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    @Autowired
    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    // 1. POST: Adicionar um serviço à lista de favoritos do usuário
    // Endpoint: POST /favoritos
    // O corpo espera os IDs do Usuario e ServicoSocial
    @PostMapping
    public ResponseEntity<Favorito> adicionarFavorito(@RequestBody Favorito favorito) {
        try {
            Favorito novoFavorito = favoritoService.adicionarFavorito(favorito);
            return new ResponseEntity<>(novoFavorito, HttpStatus.CREATED);
        } catch (Exception e) {
            // Capturar erros como IDs de usuário/serviço inexistentes ou problemas de BD
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 2. GET: Listar todos os favoritos de um usuário específico
    // Endpoint: GET /favoritos/usuario/{usuarioId}
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Favorito>> listarFavoritosPorUsuario(@PathVariable Integer usuarioId) {
        List<Favorito> favoritos = favoritoService.listarFavoritosPorUsuario(usuarioId);
        
        if (favoritos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 se a lista estiver vazia
        }
        
        return ResponseEntity.ok(favoritos);
    }

    // 3. DELETE: Remover um serviço dos favoritos
    // Endpoint: DELETE /favoritos/usuario/{usuarioId}/servico/{servicoId}
    @DeleteMapping("/usuario/{usuarioId}/servico/{servicoId}")
    @Transactional // Usamos @Transactional aqui pois o service utiliza um delete customizado
    public ResponseEntity<Void> removerFavorito(
            @PathVariable Integer usuarioId, 
            @PathVariable Integer servicoId) {
        
        favoritoService.removerFavorito(usuarioId, servicoId);
        
        // Retorna 204 No Content, indicando que a remoção foi processada
        return ResponseEntity.noContent().build();
    }
}
    

