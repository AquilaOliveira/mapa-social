package com.mapa.social.demo.service;

import com.mapa.social.demo.model.ServicoSocial;
import com.mapa.social.demo.repository.ServicoSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Marca a classe como um Service Component no Spring
public class ServicoSocialService {

    private final ServicoSocialRepository servicoSocialRepository;

    // Injeção de Dependência do Repositório via construtor
    @Autowired
    public ServicoSocialService(ServicoSocialRepository servicoSocialRepository) {
        this.servicoSocialRepository = servicoSocialRepository;
    }

    // --- Métodos de Lógica de Negócio ---

    /**
     * Salva um novo ServicoSocial ou atualiza um existente.
     * @param servicoSocial A entidade ServicoSocial a ser salva.
     * @return O ServicoSocial salvo (com o ID gerado/atualizado).
     */
    @Transactional // Garante que a operação seja executada dentro de uma transação
    public ServicoSocial salvar(ServicoSocial servicoSocial) {
        // **Aqui você pode adicionar lógicas de validação de negócio, como:**
        // 1. Verificar se o Endereço e a Categoria estão preenchidos.
        // 2. Padronizar o telefone ou o tipo antes de salvar.

        // Por exemplo, garante que o nome não seja nulo.
        if (servicoSocial.getNome() == null || servicoSocial.getNome().trim().isEmpty()) {
             throw new IllegalArgumentException("O nome do serviço social é obrigatório.");
        }
        
        return servicoSocialRepository.save(servicoSocial);
    }

    /**
     * Busca todos os serviços sociais.
     * @return Uma lista de todos os ServicosSocial.
     */
    public List<ServicoSocial> buscarTodos() {
        return servicoSocialRepository.findAll();
    }

    /**
     * Busca um serviço social pelo ID.
     * @param id O ID do serviço social.
     * @return Um Optional contendo o ServicoSocial, se encontrado.
     */
    public Optional<ServicoSocial> buscarPorId(Integer id) {
        return servicoSocialRepository.findById(id);
    }

    /**
     * Deleta um serviço social pelo ID.
     * @param id O ID do serviço social a ser deletado.
     */
    @Transactional
    public void deletar(Integer id) {
        // **Lógica de segurança:** Você pode verificar se o serviço existe antes de deletar
        if (!servicoSocialRepository.existsById(id)) {
            throw new RuntimeException("Serviço Social com ID " + id + " não encontrado.");
        }
        servicoSocialRepository.deleteById(id);
    }
}
