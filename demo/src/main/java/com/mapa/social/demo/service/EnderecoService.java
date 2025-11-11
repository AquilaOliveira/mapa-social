package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Endereco;
import com.mapa.social.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Marca a classe como um Service Component do Spring
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    // Injeção de Dependência do Repositório via construtor
    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    // --- Métodos de Lógica de Negócio ---

    /**
     * Salva um novo Endereço ou atualiza um existente.
     * @param endereco A entidade Endereco a ser salva.
     * @return O Endereco salvo (com o ID gerado/atualizado).
     */
    @Transactional // Garante a atomicidade da operação no banco de dados
    public Endereco salvar(Endereco endereco) {
        // **Aqui você pode adicionar validações, como:**
        // if (endereco.getCep() == null || !endereco.getCep().matches("\\d{5}-\\d{3}")) {
        //     throw new IllegalArgumentException("CEP inválido.");
        // }
        return enderecoRepository.save(endereco);
    }

    /**
     * Busca todos os endereços.
     * @return Uma lista de todos os endereços.
     */
    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    /**
     * Busca um endereço pelo ID.
     * @param id O ID do endereço.
     * @return Um Optional contendo o Endereco, se encontrado.
     */
    public Optional<Endereco> buscarPorId(Integer id) {
        return enderecoRepository.findById(id);
    }

    /**
     * Deleta um endereço pelo ID.
     * @param id O ID do endereço a ser deletado.
     */
    @Transactional
    public void deletar(Integer id) {
        // Você pode adicionar verificação de existência aqui ou confiar na exceção do Spring.
        enderecoRepository.deleteById(id);
    }
}
