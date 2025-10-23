package com.mapasocial.service;

import com.mapasocial.dto.ServicoSocialDTO;
import com.mapasocial.model.Favorito;
import com.mapasocial.model.ServicoSocial;
import com.mapasocial.model.Usuario;
import com.mapasocial.repository.FavoritoRepository;
import com.mapasocial.repository.ServicoSocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final ServicoSocialRepository servicoRepository;
    private final ServicoSocialService servicoSocialService;

    @Transactional
    public Favorito adicionarFavorito(Usuario usuario, UUID servicoId) {
        if (favoritoRepository.existsByUsuarioIdAndServicoId(usuario.getId(), servicoId)) {
            throw new RuntimeException("Serviço já está nos favoritos");
        }

        ServicoSocial servico = servicoRepository.findById(servicoId)
            .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setServico(servico);

        return favoritoRepository.save(favorito);
    }

    @Transactional
    public void removerFavorito(Usuario usuario, UUID servicoId) {
        Favorito favorito = favoritoRepository.findByUsuarioIdAndServicoId(usuario.getId(), servicoId)
            .orElseThrow(() -> new RuntimeException("Favorito não encontrado"));

        favoritoRepository.delete(favorito);
    }

    public List<ServicoSocialDTO> listarFavoritos(UUID usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId)
            .stream()
            .map(favorito -> servicoSocialService.buscarPorId(favorito.getServico().getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }
}
