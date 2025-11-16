package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Categoria;
import com.mapa.social.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    @Transactional
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }
    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }
    @Transactional
    public void deletar(Integer id) {
        categoriaRepository.deleteById(id);
    }
}