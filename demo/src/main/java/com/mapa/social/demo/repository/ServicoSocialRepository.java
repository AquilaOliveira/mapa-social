package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.ServicoSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de Repositório para a entidade ServicoSocial.
 * Estende JpaRepository para herdar operações CRUD prontas.
 */
@Repository
public interface ServicoSocialRepository extends JpaRepository<ServicoSocial, Integer> {

    // Opcional: Métodos de consulta personalizados baseados nos campos da entidade.
    
    // Exemplo: Buscar serviços por nome
    // List<ServicoSocial> findByNomeContaining(String nome);

    // Exemplo: Buscar serviços por ID de Categoria
    // List<ServicoSocial> findByCategoriaId(Integer categoriaId);
}
