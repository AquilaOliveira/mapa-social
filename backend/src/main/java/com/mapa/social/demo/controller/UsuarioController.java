package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Usuario;
import com.mapa.social.demo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        try {
            if (usuario.getNome() == null || usuario.getNome().trim().length() < 3) {
                return ResponseEntity.badRequest().body(Map.of("message", "Nome inválido."));
            }
            if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
                return ResponseEntity.badRequest().body(Map.of("message", "E-mail inválido."));
            }
            if (usuario.getSenhaHash() == null || usuario.getSenhaHash().length() < 6) {
                return ResponseEntity.badRequest().body(Map.of("message", "Senha deve ter ao menos 6 caracteres."));
            }
            if (usuario.getTipo() == null || usuario.getTipo().isBlank()) {
                usuario.setTipo("COMUM");
            }
            Usuario novoUsuario = usuarioService.salvar(usuario);

            // Retorna dados sem senha/hash para segurança
            Map<String, Object> body = Map.of(
                "id", novoUsuario.getId(),
                "nome", novoUsuario.getNome(),
                "email", novoUsuario.getEmail(),
                "tipo", novoUsuario.getTipo(),
                "dataCadastro", novoUsuario.getDataCadastro()
            );
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        try {
            String email = credenciais.get("email");
            String senha = credenciais.get("senhaHash");
            
            System.out.println("=== DEBUG LOGIN ===");
            System.out.println("Email recebido: " + email);
            System.out.println("Senha recebida: " + senha);
            System.out.println("==================");
            
            if (email == null || senha == null) {
                 return ResponseEntity.badRequest().body(Map.of("message", "Email e senha são obrigatórios."));
            }

            boolean valid = usuarioService.verificarSenha(email, senha);
            
            System.out.println("Senha válida? " + valid);
            
            if (valid) {
                return ResponseEntity.ok(Map.of("message", "Login bem-sucedido!", "email", email));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Credenciais inválidas."));
            }
        } catch (Exception e) {
            System.err.println("Erro no login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Erro no servidor: " + e.getMessage()));
        }
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/test-bcrypt/{senha}")
    public ResponseEntity<?> testBCrypt(@PathVariable String senha) {
        String novoHash = usuarioService.gerarHash(senha);
        var usuario = usuarioService.buscarPorId(1);
        String hashBanco = usuario.map(u -> u.getSenhaHash()).orElse("N/A");
        boolean matches = usuario.map(u -> usuarioService.testarHash(senha, u.getSenhaHash())).orElse(false);
        return ResponseEntity.ok(Map.of(
            "senha", senha,
            "novoHashGerado", novoHash,
            "hashNoBanco", hashBanco,
            "matchesComBanco", matches,
            "emailBanco", usuario.map(u -> u.getEmail()).orElse("N/A")
        ));
    }
}
