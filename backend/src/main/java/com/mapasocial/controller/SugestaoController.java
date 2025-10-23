package com.mapasocial.controller;

import com.mapasocial.dto.SugestaoRequest;
import com.mapasocial.model.Sugestao;
import com.mapasocial.service.SugestaoService;
import com.mapasocial.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sugestoes")
@RequiredArgsConstructor
public class SugestaoController {
    private final SugestaoService sugestaoService;
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Sugestao> criar(@Valid @RequestBody SugestaoRequest request, Authentication authentication) {
        return usuarioService.buscarPorEmail(authentication.getName())
            .map(usuario -> {
                Sugestao sugestao = sugestaoService.criarSugestao(request, usuario);
                return ResponseEntity.ok(sugestao);
            })
            .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/anonima")
    public ResponseEntity<Sugestao> criarAnonima(@Valid @RequestBody SugestaoRequest request) {
        Sugestao sugestao = sugestaoService.criarSugestaoAnonima(request);
        return ResponseEntity.ok(sugestao);
    }

    @GetMapping("/minhas")
    public ResponseEntity<List<Sugestao>> listarMinhas(Authentication authentication) {
        return usuarioService.buscarPorEmail(authentication.getName())
            .map(usuario -> ResponseEntity.ok(sugestaoService.listarPorUsuario(usuario.getId())))
            .orElse(ResponseEntity.notFound().build());
    }
}
