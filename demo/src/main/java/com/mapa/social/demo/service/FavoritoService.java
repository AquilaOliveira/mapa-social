package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Favorito;
import com.mapa.social.demo.repository.FavoritoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Marca a classe como um Service Component
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    // Injetar outros services (UsuarioService, ServicoSocialService) é comum aqui
    // para garantir que os IDs fornecidos existam no BD antes de criar o Favorito.

    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    // --- Métodos de Lógica de Negócio ---

    /**
     * Adiciona um serviço social à lista de favoritos de um usuário.
     * Verifica se o favorito já existe antes de salvar.
     * @param favorito O registro de Favorito (com Usuario e ServicoSocial preenchidos).
     * @return O registro de Favorito salvo.
     */
    @Transactional
    public Favorito adicionarFavorito(Favorito favorito) {
        Integer usuarioId = favorito.getUsuario().getId();
        Integer servicoId = favorito.getServicoSocial().getId();

        // 1. Verificar se o registro de favorito já existe para evitar duplicatas
        Optional<Favorito> existente = favoritoRepository.findByUsuarioIdAndServicoSocialId(usuarioId, servicoId);

        if (existente.isPresent()) {
            // Se já existe, apenas retorna o existente (ou pode lançar uma exceção de negócio)
            return existente.get(); 
        }

        // 2. Se não existe, salva o novo favorito
        return favoritoRepository.save(favorito);
    }
    
    /**
     * Remove um serviço da lista de favoritos de um usuário.
     * @param usuarioId ID do usuário.
     * @param servicoSocialId ID do serviço social.
     */
    @Transactional
    public void removerFavorito(Integer usuarioId, Integer servicoSocialId) {
        // Usa o método personalizado do repositório para deletar diretamente
        favoritoRepository.deleteByUsuarioIdAndServicoSocialId(usuarioId, servicoSocialId);
    }

    /**
     * Lista todos os favoritos de um usuário.
     * @param usuarioId ID do usuário.
     * @return Lista de registros de Favorito.
     */
   
    /**
     * Busca todos os registros de favorito. (Geralmente restrito a ADMIN)
     */
    public List<Favorito> buscarTodos() {
        return favoritoRepository.findAll();
    }

    /**
     * Lista todos os favoritos de um usuário.
     * @param usuarioId ID do usuário.
     * @return Lista de registros de Favorito.
     */
    public List<Favorito> listarFavoritosPorUsuario(Integer usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }
    
    // ... (Outros métodos como buscarPorId, deletar, etc., podem ser adicionados)
}
