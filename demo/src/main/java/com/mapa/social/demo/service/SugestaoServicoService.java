package com.mapa.social.demo.service;

import com.mapa.social.demo.model.SugestaoServico;
import com.mapa.social.demo.model.SugestaoServico.StatusSugestao;
import com.mapa.social.demo.repository.SugestaoServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SugestaoServicoService {

    private final SugestaoServicoRepository repository;

    public SugestaoServicoService(SugestaoServicoRepository repository) {
        this.repository = repository;
    }

    public SugestaoServico criar(SugestaoServico sugestao) {
        // status padrão caso não venha do frontend
        if (sugestao.getStatus() == null) {
            sugestao.setStatus(StatusSugestao.PENDENTE);
        }
        return repository.save(sugestao);
    }

    public List<SugestaoServico> listarTodas() {
        return repository.findAll();
    }

    public Optional<SugestaoServico> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<SugestaoServico> buscarPorUsuario(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<SugestaoServico> buscarPorStatus(StatusSugestao status) {
        return repository.findByStatus(status);
    }

    public List<SugestaoServico> buscarPendentes() {
        return repository.findByStatus(StatusSugestao.PENDENTE);
    }

    public Optional<SugestaoServico> aprovar(Integer id) {
        return repository.findById(id).map(s -> {
            s.setStatus(StatusSugestao.APROVADO);
            return repository.save(s);
        });
    }

    public Optional<SugestaoServico> rejeitar(Integer id) {
        return repository.findById(id).map(s -> {
            s.setStatus(StatusSugestao.REJEITADO);
            return repository.save(s);
        });
    }

    public Optional<SugestaoServico> atualizar(Integer id, SugestaoServico dados) {
        return repository.findById(id).map(existente -> {
            existente.setNomeSugerido(dados.getNomeSugerido());
            existente.setEnderecoSugerido(dados.getEnderecoSugerido());
            existente.setDescricaoSugerida(dados.getDescricaoSugerida());
            if (dados.getStatus() != null) {
                existente.setStatus(dados.getStatus());
            }
            if (dados.getUsuario() != null) {
                existente.setUsuario(dados.getUsuario());
            }
            return repository.save(existente);
        });
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
