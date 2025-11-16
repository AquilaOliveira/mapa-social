package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

    List<Favorito> findByUsuarioId(Integer usuarioId);
    
    Optional<Favorito> findByUsuarioIdAndServicoSocialId(Integer usuarioId, Integer servicoSocialId);

    void deleteByUsuarioIdAndServicoSocialId(Integer usuarioId, Integer servicoSocialId);
}
