package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Usuario;
import com.mapa.social.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1. POST: Cadastro de novo usuário (Senha em texto simples é criptografada no Service)
    // Endpoint: POST /usuarios/cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        try {
            // O Service cuida da criptografia da senha
            Usuario novoUsuario = usuarioService.salvar(usuario);
            
            // Retorna o objeto criado (sem o hash da senha, por segurança, em um sistema real)
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Captura a exceção de email já cadastrado do Service
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 2. POST: Endpoint simples de Login (mock)
    // Endpoint: POST /usuarios/login
    // Espera um JSON com email e senha
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senhaHash"); // Note: o body virá com a senha no campo 'senhaHash'
        
        if (email == null || senha == null) {
             return ResponseEntity.badRequest().body("Email e senha são obrigatórios.");
        }

        Usuario usuario = usuarioService.buscarPorEmail(email);
        if (usuario != null && usuarioService.verificarSenha(email, senha)) {
            // Retorna dados do usuário (sem a senha)
            return ResponseEntity.ok(Map.of(
                "message", "Login bem-sucedido!",
                "usuario", Map.of(
                    "id", usuario.getId(),
                    "nome", usuario.getNome(),
                    "email", usuario.getEmail(),
                    "tipo", usuario.getTipo()
                )
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Credenciais inválidas."));
        }
    }
    
    // 3. GET: Buscar todos os usuários (Normalmente restrito a ADMIN)
    // Endpoint: GET /usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // 4. GET: Buscar usuário por ID
    // Endpoint: GET /usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ... (Métodos PUT e DELETE podem ser adicionados seguindo o padrão)
}
