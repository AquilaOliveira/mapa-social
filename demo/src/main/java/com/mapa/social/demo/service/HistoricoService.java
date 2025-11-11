package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Historico;
import com.mapa.social.demo.model.ServicoSocial;
import com.mapa.social.demo.model.Usuario;
import com.mapa.social.demo.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Marca a classe como um Service Component
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    
    // Injetar outros serviços para buscar Usuario ou ServicoSocial, se necessário
    // private final UsuarioService usuarioService;
    // private final ServicoSocialService servicoSocialService;

    @Autowired
    public HistoricoService(HistoricoRepository historicoRepository /*, UsuarioService usuarioService, ServicoSocialService servicoSocialService */) {
        this.historicoRepository = historicoRepository;
        // this.usuarioService = usuarioService;
        // this.servicoSocialService = servicoSocialService;
    }

    // --- Métodos de Lógica de Negócio ---

    /**
     * Registra um novo acesso de um usuário a um serviço social.
     * @param historico O registro de Histórico a ser salvo.
     * @return O registro de Histórico salvo.
     */
    @Transactional
    public Historico registrarAcesso(Historico historico) {
        // **Lógica de Negócio/Validação:**
        // Aqui você garantiria que Usuario e ServicoSocial existem no banco de dados
        // antes de salvar o registro de Histórico.
        
        // O campo dataAcesso é preenchido automaticamente na entidade, 
        // mas você pode forçar ou ajustar aqui se necessário.
        
        return historicoRepository.save(historico);
    }
    
    /**
     * Busca todos os registros de histórico. (Geralmente restrito a ADMIN)
     */
    public List<Historico> buscarTodos() {
        return historicoRepository.findAll();
    }

    /**
     * Busca o histórico de acessos de um usuário específico pelo ID do usuário.
     * @param usuarioId ID do usuário.
     * @return Lista de registros de Histórico.
     */
    public List<Historico> buscarPorUsuarioId(Integer usuarioId) {
        return historicoRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Busca um registro específico de histórico pelo ID.
     */
    public Optional<Historico> buscarPorId(Integer id) {
        return historicoRepository.findById(id);
    }

    /**
     * Deleta um registro de histórico.
     */
    @Transactional
    public void deletar(Integer id) {
        historicoRepository.deleteById(id);
    }
}
