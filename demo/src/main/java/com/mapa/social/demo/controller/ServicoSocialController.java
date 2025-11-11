package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.ServicoSocial;
import com.mapa.social.demo.service.ServicoSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define que esta classe é um controlador REST e o caminho base é /servicos
@RestController
@RequestMapping("/servicos") // Usamos "servicos" para o endpoint
public class ServicoSocialController {

    private final ServicoSocialService servicoSocialService;

    // Injeção de Dependência do Service
    @Autowired
    public ServicoSocialController(ServicoSocialService servicoSocialService) {
        this.servicoSocialService = servicoSocialService;
    }

    // 1. POST: Criar um novo serviço social
    // Endpoint: POST /servicos
    @PostMapping
    public ResponseEntity<ServicoSocial> criarServico(@RequestBody ServicoSocial servicoSocial) {
        // O corpo da requisição JSON deve incluir a Categoria e o Endereco aninhados
        ServicoSocial novoServico = servicoSocialService.salvar(servicoSocial);
        
        // Retorna o objeto criado com o status 201 (CREATED)
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    // 2. GET: Buscar todos os serviços sociais
    // Endpoint: GET /servicos
    @GetMapping
    public ResponseEntity<List<ServicoSocial>> buscarTodos() {
        List<ServicoSocial> servicos = servicoSocialService.buscarTodos();
        
        // Retorna a lista com o status 200 (OK)
        return ResponseEntity.ok(servicos);
    }

    // 3. GET: Buscar serviço social por ID
    // Endpoint: GET /servicos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ServicoSocial> buscarPorId(@PathVariable Integer id) {
        return servicoSocialService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 (OK)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

    // 4. PUT: Atualizar um serviço social
    // Endpoint: PUT /servicos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ServicoSocial> atualizarServico(@PathVariable Integer id, @RequestBody ServicoSocial detalhes) {
        return servicoSocialService.buscarPorId(id)
                .map(servicoExistente -> {
                    // Atualiza os campos básicos
                    servicoExistente.setNome(detalhes.getNome());
                    servicoExistente.setTipo(detalhes.getTipo());
                    servicoExistente.setTelefone(detalhes.getTelefone());
                    
                    // ATENÇÃO: Se Endereco e Categoria forem enviados, eles também são atualizados ou substituídos
                    if (detalhes.getEndereco() != null) {
                        servicoExistente.setEndereco(detalhes.getEndereco());
                    }
                    if (detalhes.getCategoria() != null) {
                        servicoExistente.setCategoria(detalhes.getCategoria());
                    }
                    
                    ServicoSocial atualizado = servicoSocialService.salvar(servicoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. DELETE: Deletar um serviço social
    // Endpoint: DELETE /servicos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Integer id) {
        if (servicoSocialService.buscarPorId(id).isPresent()) {
            servicoSocialService.deletar(id);
            // Retorna status 204 (NO CONTENT) indicando sucesso
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
