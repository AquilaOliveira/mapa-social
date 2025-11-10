package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Categoria;
import com.mapa.social.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Define que esta classe é um controlador REST e que todos os endpoints
// dentro dela começarão com o caminho base /categorias
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    // Injeção de Dependência do Service
    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // 1. POST: Criar uma nova categoria
    // Endpoint: POST /categorias
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        // O corpo da requisição JSON é automaticamente convertido em um objeto Categoria
        Categoria novaCategoria = categoriaService.salvar(categoria);
        
        // Retorna o objeto criado com o status 201 (CREATED)
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    // 2. GET: Buscar todas as categorias
    // Endpoint: GET /categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> buscarTodas() {
        List<Categoria> categorias = categoriaService.buscarTodas();
        
        // Retorna a lista com o status 200 (OK)
        return ResponseEntity.ok(categorias);
    }

    // 3. GET: Buscar categoria por ID
    // Endpoint: GET /categorias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        // @PathVariable extrai o 'id' da URL
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 (OK) com o corpo
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 (NOT FOUND)
    }

    // 4. PUT: Atualizar uma categoria
    // Endpoint: PUT /categorias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaDetalhes) {
        // Primeiro, verifica se a categoria existe
        return categoriaService.buscarPorId(id)
                .map(categoriaExistente -> {
                    // Atualiza os campos do objeto existente com os dados da requisição
                    categoriaExistente.setNome(categoriaDetalhes.getNome());
                    categoriaExistente.setDescricao(categoriaDetalhes.getDescricao());
                    
                    // Salva a categoria atualizada (o ID garante a atualização)
                    Categoria atualizada = categoriaService.salvar(categoriaExistente);
                    
                    // Retorna o objeto atualizado com status 200 (OK)
                    return ResponseEntity.ok(atualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

    // 5. DELETE: Deletar uma categoria
    // Endpoint: DELETE /categorias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
        // Verifica se existe antes de tentar deletar (opcional, mas boa prática)
        if (categoriaService.buscarPorId(id).isPresent()) {
            categoriaService.deletar(id);
            // Retorna status 204 (NO CONTENT) indicando sucesso sem corpo de resposta
            return ResponseEntity.noContent().build();
        } else {
            // Retorna 404 (NOT FOUND) se a categoria não existir
            return ResponseEntity.notFound().build();
        }
    }
}
