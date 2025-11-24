package com.mapa.social.demo.service;

import com.mapa.social.demo.model.*;
import com.mapa.social.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class AdminService {

    private final SugestaoServicoRepository sugestaoRepository;
    private final ServicoSocialRepository servicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistoricoRepository historicoRepository;
    private final FavoritoRepository favoritoRepository;

    public AdminService(SugestaoServicoRepository sugestaoRepository,
                       ServicoSocialRepository servicoRepository,
                       UsuarioRepository usuarioRepository,
                       HistoricoRepository historicoRepository,
                       FavoritoRepository favoritoRepository) {
        this.sugestaoRepository = sugestaoRepository;
        this.servicoRepository = servicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.historicoRepository = historicoRepository;
        this.favoritoRepository = favoritoRepository;
    }

    public List<SugestaoServico> listarSugestoesPendentes() {
        return sugestaoRepository.findByStatus(SugestaoServico.StatusSugestao.PENDENTE);
    }

    @Transactional
    public SugestaoServico aprovarSugestao(Integer id) {
        SugestaoServico sugestao = sugestaoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sugestão não encontrada"));
        
        sugestao.setStatus(SugestaoServico.StatusSugestao.APROVADO);
        return sugestaoRepository.save(sugestao);
    }

    @Transactional
    public SugestaoServico rejeitarSugestao(Integer id) {
        SugestaoServico sugestao = sugestaoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sugestão não encontrada"));
        
        sugestao.setStatus(SugestaoServico.StatusSugestao.REJEITADO);
        return sugestaoRepository.save(sugestao);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public void excluirUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario promoverParaAdmin(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        if (usuario.getRole() == UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Super Admin não pode ser alterado");
        }
        
        usuario.setRole(UserRole.ADMIN);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario rebaixarParaUser(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        if (usuario.getRole() == UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Super Admin não pode ser alterado");
        }
        
        usuario.setRole(UserRole.USER);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario bloquearUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        if (usuario.getRole() == UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Super Admin não pode ser bloqueado");
        }
        
        usuario.setBloqueado(true);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario desbloquearUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        usuario.setBloqueado(false);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarAdmins() {
        return usuarioRepository.findAll().stream()
            .filter(u -> u.getRole() == UserRole.ADMIN || u.getRole() == UserRole.SUPER_ADMIN)
            .toList();
    }

    public Map<String, Object> obterEstatisticas() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalUsuarios", usuarioRepository.count());
        stats.put("totalServicos", servicoRepository.count());
        stats.put("totalSugestoesPendentes", sugestaoRepository.findByStatus(SugestaoServico.StatusSugestao.PENDENTE).size());
        stats.put("totalHistoricos", historicoRepository.count());
        stats.put("totalFavoritos", favoritoRepository.count());
        
        return stats;
    }
}
