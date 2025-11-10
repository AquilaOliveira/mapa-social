package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Endereco;
import com.mapa.social.demo.service.EnderecoService; // Iremos criar este Service a seguir!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define que esta classe é um controlador REST e o caminho base é /enderecos
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    // Injeção de Dependência do Service (Usaremos um construtor, que é a melhor prática)
    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    // 1. POST: Criar um novo endereço
    // Endpoint: POST /enderecos
    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.salvar(endereco);
        
        // Retorna o objeto criado com o status 201 (CREATED)
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    // 2. GET: Buscar todos os endereços
    // Endpoint: GET /enderecos
    @GetMapping
    public ResponseEntity<List<Endereco>> buscarTodos() {
        List<Endereco> enderecos = enderecoService.buscarTodos();
        
        // Retorna a lista com o status 200 (OK)
        return ResponseEntity.ok(enderecos);
    }

    // 3. GET: Buscar endereço por ID
    // Endpoint: GET /enderecos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Integer id) {
        // Usa o .map para retornar 200 se encontrar, ou 404 se não encontrar
        return enderecoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    // 4. PUT: Atualizar um endereço
    // Endpoint: PUT /enderecos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco enderecoDetalhes) {
        return enderecoService.buscarPorId(id)
                .map(enderecoExistente -> {
                    // Atualiza os campos do objeto existente com os dados da requisição
                    enderecoExistente.setRua(enderecoDetalhes.getRua());
                    enderecoExistente.setNumero(enderecoDetalhes.getNumero());
                    enderecoExistente.setBairro(enderecoDetalhes.getBairro());
                    enderecoExistente.setCidade(enderecoDetalhes.getCidade());
                    enderecoExistente.setUf(enderecoDetalhes.getUf());
                    enderecoExistente.setCep(enderecoDetalhes.getCep());
                    enderecoExistente.setComplemento(enderecoDetalhes.getComplemento());
                    
                    Endereco atualizado = enderecoService.salvar(enderecoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. DELETE: Deletar um endereço
    // Endpoint: DELETE /enderecos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        if (enderecoService.buscarPorId(id).isPresent()) {
            enderecoService.deletar(id);
            // Retorna status 204 (NO CONTENT) indicando sucesso
            return ResponseEntity.noContent().build();
        } else {
            // Retorna 404 (NOT FOUND) se não existir
            return ResponseEntity.notFound().build();
        }
    }
}