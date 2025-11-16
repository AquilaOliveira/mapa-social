package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Historico;
import com.mapa.social.demo.model.ServicoSocial;
import com.mapa.social.demo.model.Usuario;
import com.mapa.social.demo.repository.HistoricoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {

    private final HistoricoRepository historicoRepository;

    public HistoricoService(HistoricoRepository historicoRepository /*, UsuarioService usuarioService, ServicoSocialService servicoSocialService */) {
        this.historicoRepository = historicoRepository;
    }
    @Transactional
    public Historico registrarAcesso(Historico historico) {
        return historicoRepository.save(historico);
    }
    public List<Historico> buscarTodos() {
        return historicoRepository.findAll();
    }
    public List<Historico> buscarPorUsuarioId(Integer usuarioId) {
        return historicoRepository.findByUsuarioId(usuarioId);
    }
    public Optional<Historico> buscarPorId(Integer id) {
        return historicoRepository.findById(id);
    }
    @Transactional
    public void deletar(Integer id) {
        historicoRepository.deleteById(id);
    }
}
