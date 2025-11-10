package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Categoria;
import com.mapa.social.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Marca a classe como um Service Component no Spring
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Injeção de Dependência do Repositório via construtor (forma recomendada)
    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // --- Métodos de Lógica de Negócio ---

    /**
     * Salva uma nova Categoria ou atualiza uma existente.
     * @param categoria A entidade Categoria a ser salva.
     * @return A Categoria salva (com o ID gerado/atualizado).
     */
    @Transactional // Garante que a operação seja executada dentro de uma transação de banco de dados
    public Categoria salvar(Categoria categoria) {
        // **Aqui você pode adicionar lógicas de validação de negócio, como:**
        // if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
        //     throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        // }
        return categoriaRepository.save(categoria);
    }

    /**
     * Busca todas as categorias.
     * @return Uma lista de todas as categorias.
     */
    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }

    /**
     * Busca uma categoria pelo ID.
     * @param id O ID da categoria.
     * @return Um Optional contendo a Categoria, se encontrada.
     */
    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    /**
     * Deleta uma categoria pelo ID.
     * @param id O ID da categoria a ser deletada.
     */
    @Transactional
    public void deletar(Integer id) {
        // **Aqui você pode adicionar lógicas de verificação antes de deletar, como:**
        // verificar se a categoria está sendo usada por outra entidade (ex: Produto)
        
        categoriaRepository.deleteById(id);
    }
}