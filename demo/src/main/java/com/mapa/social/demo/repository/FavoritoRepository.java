package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Interface de Repositório para a entidade Favorito.
 * Gerencia os registros de serviços favoritos de cada usuário.
 */
@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

    // 1. Buscar todos os favoritos de um usuário específico
    List<Favorito> findByUsuarioId(Integer usuarioId);
    
    // 2. Verificar se um serviço já é favorito de um usuário
    Optional<Favorito> findByUsuarioIdAndServicoSocialId(Integer usuarioId, Integer servicoSocialId);

    // 3. Deletar um favorito específico (útil para o usuário "desfavoritar")
    void deleteByUsuarioIdAndServicoSocialId(Integer usuarioId, Integer servicoSocialId);
}
