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
    public void excluirUsuario(Integer id, Integer adminId) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        Usuario admin = usuarioRepository.findById(adminId)
            .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado"));
        
        if (usuario.getRole() == UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Super Admin não pode ser excluído");
        }
        
        // ADMIN só pode excluir USER, SUPER_ADMIN pode excluir ADMIN e USER
        if (usuario.getRole() == UserRole.ADMIN && admin.getRole() != UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Apenas SUPER_ADMIN pode excluir ADMIN");
        }
        
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario promoverParaAdmin(Integer id, Integer adminId) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        Usuario admin = usuarioRepository.findById(adminId)
            .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado"));
        
        if (usuario.getRole() == UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Super Admin não pode ser alterado");
        }
        
        // Apenas SUPER_ADMIN pode promover para ADMIN
        if (admin.getRole() != UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Apenas SUPER_ADMIN pode promover usuários para ADMIN");
        }
        
        usuario.setRole(UserRole.ADMIN);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario rebaixarParaUser(Integer id, Integer adminId) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        Usuario admin = usuarioRepository.findById(adminId)
            .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado"));
        
        if (usuario.getRole() == UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Super Admin não pode ser alterado");
        }
        
        // Apenas SUPER_ADMIN pode rebaixar ADMIN
        if (usuario.getRole() == UserRole.ADMIN && admin.getRole() != UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Apenas SUPER_ADMIN pode rebaixar ADMIN");
        }
        
        usuario.setRole(UserRole.USER);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario bloquearUsuario(Integer id, Integer adminId) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        Usuario admin = usuarioRepository.findById(adminId)
            .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado"));
        
        if (usuario.getRole() == UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Super Admin não pode ser bloqueado");
        }
        
        // ADMIN só pode bloquear USER, SUPER_ADMIN pode bloquear ADMIN e USER
        if (usuario.getRole() == UserRole.ADMIN && admin.getRole() != UserRole.SUPER_ADMIN) {
            throw new IllegalArgumentException("Apenas SUPER_ADMIN pode bloquear ADMIN");
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
