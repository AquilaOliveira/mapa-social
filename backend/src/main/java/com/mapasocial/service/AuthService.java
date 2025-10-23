package com.mapasocial.service;

import com.mapasocial.dto.AuthResponse;
import com.mapasocial.dto.LoginRequest;
import com.mapasocial.dto.RegisterRequest;
import com.mapasocial.dto.UsuarioDTO;
import com.mapasocial.model.Usuario;
import com.mapasocial.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        UsuarioDTO usuario = usuarioService.criarUsuario(request);
        String token = jwtUtil.generateToken(usuario.getEmail());
        return new AuthResponse(token, usuario);
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioService.buscarPorEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenhaHash())) {
            throw new RuntimeException("Senha inválida");
        }

        if (!usuario.getAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());
        UsuarioDTO usuarioDTO = usuarioService.convertToDTO(usuario);

        return new AuthResponse(token, usuarioDTO);
    }
}
