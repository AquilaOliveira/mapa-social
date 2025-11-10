package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Usuario;
import com.mapa.social.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Nova dependência!
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Injetaremos o encoder aqui

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // --- Métodos de Lógica de Negócio ---

    /**
     * Salva um novo Usuário após criptografar a senha.
     * @param usuario A entidade Usuario (com a senha em texto simples).
     * @return O Usuario salvo (com a senha hash).
     */
    @Transactional
    public Usuario salvar(Usuario usuario) {
        // 1. **Lógica de Segurança:** Criptografa a senha antes de salvar no BD
        // Assumimos que a entidade Usuario vem com a senha em texto simples no campo senhaHash temporariamente.
        String senhaEmTextoSimples = usuario.getSenhaHash(); 
        
        // Criptografa a senha usando o Bcrypt (ou outro algoritmo configurado)
        String senhaCriptografada = passwordEncoder.encode(senhaEmTextoSimples);
        
        // Define o hash criptografado na entidade antes de salvar
        usuario.setSenhaHash(senhaCriptografada);

        // 2. **Validação de Negócio (ex: email único)**
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent() && usuario.getId() == null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca todos os usuários.
     */
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca um usuário pelo ID.
     */
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }
    
    // **Método essencial para login/autenticação (verifica a senha)**
    public boolean verificarSenha(String email, String senhaEmTextoSimples) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Compara a senha em texto simples fornecida com o hash salvo no BD
            return passwordEncoder.matches(senhaEmTextoSimples, usuario.getSenhaHash());
        }
        return false;
    }

    // ... (Métodos de deletar e atualizar omitidos para brevidade, mas devem seguir o padrão)
}
