package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Endereco;
import com.mapa.social.demo.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.salvar(endereco);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Endereco>> buscarTodos() {
        List<Endereco> enderecos = enderecoService.buscarTodos();
        return ResponseEntity.ok(enderecos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Integer id) {
        return enderecoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); 
    }
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco enderecoDetalhes) {
        return enderecoService.buscarPorId(id)
                .map(enderecoExistente -> {
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        if (enderecoService.buscarPorId(id).isPresent()) {
            enderecoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}