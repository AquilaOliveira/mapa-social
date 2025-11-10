package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de Repositório para a entidade Endereco.
 * Estende JpaRepository para herdar operações CRUD prontas.
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    // O JpaRepository gerencia as operações básicas (salvar, buscar por ID, deletar, etc.)
    
    // Exemplo opcional: Você pode adicionar métodos de busca específicos se precisar.
    // Endereco findByCep(String cep);
}
