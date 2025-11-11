package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface de Repositório para a entidade Historico.
 * Estende JpaRepository para gerenciar os registros de acesso.
 */
@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Integer> {

    // Método para buscar todos os registros de histórico de um usuário específico
    List<Historico> findByUsuarioId(Integer usuarioId);
    
    // Método para buscar o histórico de acessos a um serviço social específico
    List<Historico> findByServicoSocialId(Integer servicoSocialId);

    // Opcional: Buscar os 10 registros mais recentes de um usuário
    // List<Historico> findTop10ByUsuarioIdOrderByDataAcessoDesc(Integer usuarioId);
}
