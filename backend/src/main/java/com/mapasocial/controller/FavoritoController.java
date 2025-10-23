package com.mapasocial.controller;

import com.mapasocial.dto.ServicoSocialDTO;
import com.mapasocial.model.Favorito;
import com.mapasocial.service.FavoritoService;
import com.mapasocial.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {
    private final FavoritoService favoritoService;
    private final UsuarioService usuarioService;

    @PostMapping("/{servicoId}")
    public ResponseEntity<Favorito> adicionar(@PathVariable UUID servicoId, Authentication authentication) {
        return usuarioService.buscarPorEmail(authentication.getName())
            .map(usuario -> {
                try {
                    Favorito favorito = favoritoService.adicionarFavorito(usuario, servicoId);
                    return ResponseEntity.ok(favorito);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().<Favorito>build();
                }
            })
            .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{servicoId}")
    public ResponseEntity<Void> remover(@PathVariable UUID servicoId, Authentication authentication) {
        return usuarioService.buscarPorEmail(authentication.getName())
            .map(usuario -> {
                try {
                    favoritoService.removerFavorito(usuario, servicoId);
                    return ResponseEntity.ok().<Void>build();
                } catch (Exception e) {
                    return ResponseEntity.notFound().<Void>build();
                }
            })
            .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<ServicoSocialDTO>> listar(Authentication authentication) {
        return usuarioService.buscarPorEmail(authentication.getName())
            .map(usuario -> ResponseEntity.ok(favoritoService.listarFavoritos(usuario.getId())))
            .orElse(ResponseEntity.notFound().build());
    }
}
