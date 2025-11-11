package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.SugestaoServico;
import com.mapa.social.demo.model.SugestaoServico.StatusSugestao;
import com.mapa.social.demo.service.SugestaoServicoService;
import com.mapa.social.demo.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sugestoes")
public class SugestaoServicoController {

    private final SugestaoServicoService service;
    private final UsuarioRepository usuarioRepository;

    public SugestaoServicoController(SugestaoServicoService service, UsuarioRepository usuarioRepository) {
        this.service = service;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<SugestaoServico> criar(@RequestBody SugestaoServico sugestao) {
        // Validar usuário associado (agora o frontend envia { usuario: { id: <userId> } })
        if (sugestao.getUsuario() == null || sugestao.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario (id) obrigatorio na sugestao.");
        }

        var usuario = usuarioRepository.findById(sugestao.getUsuario().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario nao encontrado."));
        sugestao.setUsuario(usuario); // garante entidade gerenciada

        // Status default se não vier
        if (sugestao.getStatus() == null) {
            sugestao.setStatus(StatusSugestao.PENDENTE);
        }

        SugestaoServico criada = service.criar(sugestao);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public List<SugestaoServico> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SugestaoServico> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<SugestaoServico> buscarPorUsuario(@PathVariable Integer usuarioId) {
        return service.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/pendentes")
    public List<SugestaoServico> buscarPendentes() {
        return service.buscarPendentes();
    }

    @GetMapping("/status/{status}")
    public List<SugestaoServico> buscarPorStatus(@PathVariable StatusSugestao status) {
        return service.buscarPorStatus(status);
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<SugestaoServico> aprovar(@PathVariable Integer id) {
        return service.aprovar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<SugestaoServico> rejeitar(@PathVariable Integer id) {
        return service.rejeitar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SugestaoServico> atualizar(@PathVariable Integer id,
                                                     @RequestBody SugestaoServico dados) {
        return service.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
