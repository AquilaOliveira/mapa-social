
package com.mapa.social.demo.model;
import com.mapa.social.demo.model.Historico;
import com.mapa.social.demo.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/historico") // Endpoint base: http://localhost:8080/historico
public class Historico {

    private final HistoricoService historicoService;

    @Autowired
    public Historico(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    // 1. POST: Registrar um novo acesso (novo registro de histórico)
    // Endpoint: POST /historico
    @PostMapping
    public ResponseEntity<Historico> registrarAcesso(@RequestBody Historico historico) {
        Historico novoRegistro = historicoService.registrarAcesso(historico);
        return new ResponseEntity<>(novoRegistro, HttpStatus.CREATED);
    }

    // 2. GET: Buscar o histórico completo de um usuário
    // Endpoint: GET /historico/usuario/{usuarioId}
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Historico>> buscarHistoricoPorUsuario(@PathVariable Integer usuarioId) {
        List<Historico> historico = historicoService.buscarPorUsuarioId(usuarioId);
        
        if (historico.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(historico);
    }
    
    // 3. GET: Buscar um registro específico de histórico por ID
    // Endpoint: GET /historico/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Historico> buscarPorId(@PathVariable Integer id) {
        return historicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. DELETE: Deletar um registro de histórico específico
    // Endpoint: DELETE /historico/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable Integer id) {
        try {
            historicoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); 
        }
    }
}
