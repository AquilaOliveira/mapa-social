package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.*;
import com.mapa.social.demo.service.AdminService;
import com.mapa.social.demo.service.CategoriaService;
import com.mapa.social.demo.service.ServicoSocialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class AdminController {

    private final AdminService adminService;
    private final ServicoSocialService servicoService;
    private final CategoriaService categoriaService;

    public AdminController(AdminService adminService,
                          ServicoSocialService servicoService,
                          CategoriaService categoriaService) {
        this.adminService = adminService;
        this.servicoService = servicoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/sugestoes/pendentes")
    public ResponseEntity<List<SugestaoServico>> listarSugestoesPendentes() {
        List<SugestaoServico> sugestoes = adminService.listarSugestoesPendentes();
        return ResponseEntity.ok(sugestoes);
    }

    @PostMapping("/sugestoes/{id}/aprovar")
    public ResponseEntity<?> aprovarSugestao(@PathVariable Integer id) {
        try {
            SugestaoServico sugestao = adminService.aprovarSugestao(id);
            return ResponseEntity.ok(Map.of(
                "message", "Sugestão aprovada com sucesso",
                "sugestao", sugestao
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/sugestoes/{id}/rejeitar")
    public ResponseEntity<?> rejeitarSugestao(@PathVariable Integer id) {
        try {
            SugestaoServico sugestao = adminService.rejeitarSugestao(id);
            return ResponseEntity.ok(Map.of(
                "message", "Sugestão rejeitada",
                "sugestao", sugestao
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = adminService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id, @RequestParam Integer adminId) {
        try {
            adminService.excluirUsuario(id, adminId);
            return ResponseEntity.ok(Map.of("message", "Usuário excluído com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/usuarios/{id}/promover")
    public ResponseEntity<?> promoverParaAdmin(@PathVariable Integer id, @RequestParam Integer adminId) {
        try {
            Usuario usuario = adminService.promoverParaAdmin(id, adminId);
            return ResponseEntity.ok(Map.of(
                "message", "Usuário promovido para ADMIN",
                "usuario", usuario
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/usuarios/{id}/rebaixar")
    public ResponseEntity<?> rebaixarParaUser(@PathVariable Integer id, @RequestParam Integer adminId) {
        try {
            Usuario usuario = adminService.rebaixarParaUser(id, adminId);
            return ResponseEntity.ok(Map.of(
                "message", "Usuário rebaixado para USER",
                "usuario", usuario
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/usuarios/{id}/bloquear")
    public ResponseEntity<?> bloquearUsuario(@PathVariable Integer id, @RequestParam Integer adminId) {
        try {
            Usuario usuario = adminService.bloquearUsuario(id, adminId);
            return ResponseEntity.ok(Map.of(
                "message", "Usuário bloqueado",
                "usuario", usuario
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/usuarios/{id}/desbloquear")
    public ResponseEntity<?> desbloquearUsuario(@PathVariable Integer id) {
        try {
            Usuario usuario = adminService.desbloquearUsuario(id);
            return ResponseEntity.ok(Map.of(
                "message", "Usuário desbloqueado",
                "usuario", usuario
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/usuarios/admins")
    public ResponseEntity<List<Usuario>> listarAdmins() {
        List<Usuario> admins = adminService.listarAdmins();
        return ResponseEntity.ok(admins);
    }

    @PostMapping("/usuarios/criar")
    public ResponseEntity<?> criarUsuario(@RequestBody Map<String, String> dados, @RequestParam Integer adminId) {
        try {
            Usuario usuario = adminService.criarUsuario(
                dados.get("nome"),
                dados.get("email"),
                dados.get("senha"),
                dados.get("tipo"),
                adminId
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Usuário criado com sucesso",
                "usuario", usuario
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/usuarios/{id}/editar")
    public ResponseEntity<?> editarUsuario(@PathVariable Integer id, @RequestBody Map<String, String> dados, @RequestParam Integer adminId) {
        try {
            Usuario usuario = adminService.editarUsuario(
                id,
                dados.get("nome"),
                dados.get("email"),
                dados.get("tipo"),
                adminId
            );
            return ResponseEntity.ok(Map.of(
                "message", "Usuário atualizado com sucesso",
                "usuario", usuario
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/dashboard/estatisticas")
    public ResponseEntity<Map<String, Object>> obterEstatisticas() {
        Map<String, Object> stats = adminService.obterEstatisticas();
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/servicos")
    public ResponseEntity<?> criarServico(@RequestBody ServicoSocial servico) {
        try {
            ServicoSocial novoServico = servicoService.salvar(servico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Erro ao criar serviço: " + e.getMessage()));
        }
    }

    @PutMapping("/servicos/{id}")
    public ResponseEntity<?> atualizarServico(@PathVariable Integer id, @RequestBody ServicoSocial servico) {
        try {
            servico.setId(id);
            ServicoSocial atualizado = servicoService.salvar(servico);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Erro ao atualizar serviço: " + e.getMessage()));
        }
    }

    @DeleteMapping("/servicos/{id}")
    public ResponseEntity<?> excluirServico(@PathVariable Integer id) {
        try {
            servicoService.deletar(id);
            return ResponseEntity.ok(Map.of("message", "Serviço excluído com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Serviço não encontrado"));
        }
    }

    @PostMapping("/categorias")
    public ResponseEntity<?> criarCategoria(@RequestBody Categoria categoria) {
        try {
            Categoria novaCategoria = categoriaService.salvar(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Erro ao criar categoria: " + e.getMessage()));
        }
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<?> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        try {
            categoria.setId(id);
            Categoria atualizada = categoriaService.salvar(categoria);
            return ResponseEntity.ok(atualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Erro ao atualizar categoria: " + e.getMessage()));
        }
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> excluirCategoria(@PathVariable Integer id) {
        try {
            categoriaService.deletar(id);
            return ResponseEntity.ok(Map.of("message", "Categoria excluída com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Categoria não encontrada"));
        }
    }
}
