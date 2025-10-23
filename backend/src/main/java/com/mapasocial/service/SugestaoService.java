package com.mapasocial.service;

import com.mapasocial.dto.SugestaoRequest;
import com.mapasocial.model.Sugestao;
import com.mapasocial.model.Usuario;
import com.mapasocial.repository.SugestaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SugestaoService {
    private final SugestaoRepository sugestaoRepository;

    @Transactional
    public Sugestao criarSugestao(SugestaoRequest request, Usuario usuario) {
        Sugestao sugestao = new Sugestao();
        sugestao.setUsuario(usuario);
        sugestao.setNomeServico(request.getNomeServico());
        sugestao.setCategoriaSugerida(request.getCategoriaSugerida());
        sugestao.setDescricao(request.getDescricao());
        sugestao.setEndereco(request.getEndereco());
        sugestao.setContato(request.getContato());
        sugestao.setStatus("pendente");

        return sugestaoRepository.save(sugestao);
    }

    @Transactional
    public Sugestao criarSugestaoAnonima(SugestaoRequest request) {
        Sugestao sugestao = new Sugestao();
        sugestao.setNomeServico(request.getNomeServico());
        sugestao.setCategoriaSugerida(request.getCategoriaSugerida());
        sugestao.setDescricao(request.getDescricao());
        sugestao.setEndereco(request.getEndereco());
        sugestao.setContato(request.getContato());
        sugestao.setStatus("pendente");

        return sugestaoRepository.save(sugestao);
    }

    public List<Sugestao> listarPorUsuario(UUID usuarioId) {
        return sugestaoRepository.findByUsuarioId(usuarioId);
    }
}
