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
        
        // IMPORTANTE: Cadastro público sempre cria USER
        // Apenas SUPER_ADMIN pode criar ADMIN via painel admin
        if (usuario.getId() == null) {
            usuario.setRole(com.mapa.social.demo.model.UserRole.USER);
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
    
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public boolean verificarSenha(String email, String senhaEmTextoSimples) {
        try {
            System.out.println("=== VERIFICAR SENHA ===");
            System.out.println("Email: " + email);
            System.out.println("Senha texto: " + senhaEmTextoSimples);
            
            if (email == null || senhaEmTextoSimples == null) {
                System.out.println("Email ou senha null");
                return false;
            }
            
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
            
            if (usuarioOpt.isEmpty()) {
                System.out.println("Usuário não encontrado");
                return false;
            }
            
            Usuario usuario = usuarioOpt.get();
            String hashSalvo = usuario.getSenhaHash();
            
            System.out.println("Hash salvo: " + hashSalvo);
            System.out.println("Bloqueado: " + usuario.getBloqueado());
            
            if (hashSalvo == null || hashSalvo.isEmpty()) {
                System.out.println("Hash vazio");
                return false;
            }
            
            boolean matches = passwordEncoder.matches(senhaEmTextoSimples, hashSalvo);
            System.out.println("Matches: " + matches);
            System.out.println("=======================");
            
            return matches;
        } catch (Exception e) {
            System.err.println("Erro ao verificar senha: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public String gerarHash(String senha) {
        return passwordEncoder.encode(senha);
    }
    
    public boolean testarHash(String senha, String hash) {
        return passwordEncoder.matches(senha, hash);
    }
}
