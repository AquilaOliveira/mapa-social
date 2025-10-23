package com.mapasocial.repository;

import com.mapasocial.model.ServicoSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServicoSocialRepository extends JpaRepository<ServicoSocial, UUID> {
    List<ServicoSocial> findByAtivoTrue();

    List<ServicoSocial> findByCategoriaIdAndAtivoTrue(UUID categoriaId);

    @Query("SELECT s FROM ServicoSocial s WHERE s.ativo = true AND " +
           "(LOWER(s.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(s.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(s.endereco) LIKE LOWER(CONCAT('%', :termo, '%')))")
    List<ServicoSocial> buscarPorTermo(@Param("termo") String termo);
}
