package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.ServicoSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoSocialRepository extends JpaRepository<ServicoSocial, Integer> {
}
