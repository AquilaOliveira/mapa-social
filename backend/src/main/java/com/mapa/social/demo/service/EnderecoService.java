package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Endereco;
import com.mapa.social.demo.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
    @Transactional
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }
    public Optional<Endereco> buscarPorId(Integer id) {
        return enderecoRepository.findById(id);
    }
    @Transactional
    public void deletar(Integer id) {
        enderecoRepository.deleteById(id);
    }
}
