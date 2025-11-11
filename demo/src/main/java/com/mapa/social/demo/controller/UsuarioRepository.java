


package com.mapa.social.demo.controller; 

import com.mapa.social.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Interface de Repositório para a entidade Usuario.
 * Estende JpaRepository para herdar operações CRUD prontas.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // O campo 'email' é unique no BD e é essencial para o login/autenticação.
    // O Spring Data JPA cria este método de busca automaticamente.
    Optional<Usuario> findByEmail(String email);

    // Opcional: Buscar usuários por tipo (ex: ADMIN, COMUM)
    // List<Usuario> findByTipo(String tipo);
}