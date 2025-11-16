package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Favorito;
import com.mapa.social.demo.model.Usuario;
import com.mapa.social.demo.model.ServicoSocial;
import com.mapa.social.demo.repository.FavoritoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    @Transactional
    public Favorito adicionarFavorito(Favorito favorito) {
        Integer usuarioId = favorito.getUsuario().getId();
        Integer servicoId = favorito.getServicoSocial().getId();
        Optional<Favorito> existente = favoritoRepository.findByUsuarioIdAndServicoSocialId(usuarioId, servicoId);
        if (existente.isPresent()) {
            return existente.get(); 
        }
        return favoritoRepository.save(favorito);
    }

    @Transactional
    public void removerFavorito(Integer usuarioId, Integer servicoSocialId) {
        favoritoRepository.deleteByUsuarioIdAndServicoSocialId(usuarioId, servicoSocialId);
    }
    public List<Favorito> listarFavoritosPorUsuario(Integer usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }
    public List<Favorito> buscarTodos() {
        return favoritoRepository.findAll();
    }
}
