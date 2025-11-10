
package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de Repositório para a entidade Categoria.
 * O Spring Data JPA cria automaticamente a implementação em tempo de execução.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // O JpaRepository já herda métodos como save(), findById(), findAll(), delete(), etc.

    // Opcional: Exemplo de um método de consulta personalizado
    // O Spring Data JPA gera a consulta automaticamente pelo nome do método.
    // List<Categoria> findByNome(String nome); 
}