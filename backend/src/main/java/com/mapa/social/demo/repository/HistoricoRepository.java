package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Integer> {

    List<Historico> findByUsuarioId(Integer usuarioId);
    
    List<Historico> findByServicoSocialId(Integer servicoSocialId);
}
