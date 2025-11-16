package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Usuario;
import com.mapa.social.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().length() < 3) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        if (usuario.getSenhaHash() == null || usuario.getSenhaHash().length() < 6) {
            throw new IllegalArgumentException("Senha deve ter ao menos 6 caracteres.");
        }
        if (usuario.getTipo() == null || usuario.getTipo().isBlank()) {
            usuario.setTipo("COMUM");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent() && usuario.getId() == null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        String senhaEmTextoSimples = usuario.getSenhaHash();
        String senhaCriptografada = passwordEncoder.encode(senhaEmTextoSimples);
        usuario.setSenhaHash(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }
    public boolean verificarSenha(String email, String senhaEmTextoSimples) {
        try {
            if (email == null || senhaEmTextoSimples == null) {
                return false;
            }
            
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
            
            if (usuarioOpt.isEmpty()) {
                return false;
            }
            
            Usuario usuario = usuarioOpt.get();
            String hashSalvo = usuario.getSenhaHash();
            
            if (hashSalvo == null || hashSalvo.isEmpty()) {
                return false;
            }
            
            return passwordEncoder.matches(senhaEmTextoSimples, hashSalvo);
        } catch (Exception e) {
            System.err.println("Erro ao verificar senha: " + e.getMessage());
            return false;
        }
    }
}
