package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.Solicitacao;
import com.mapa.social.demo.model.ServicoSocial;
import com.mapa.social.demo.model.Endereco;
import com.mapa.social.demo.repository.SolicitacaoRepository;
import com.mapa.social.demo.repository.ServicoSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/solicitacoes")
@CrossOrigin(origins = "http://localhost:5173")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private ServicoSocialRepository servicoSocialRepository;

    // Criar nova solicitação
    @PostMapping
    public ResponseEntity<?> criarSolicitacao(@RequestBody Solicitacao solicitacao) {
        try {
            if (solicitacao.getUsuarioId() == null || solicitacao.getServicoSocialId() == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Usuário e serviço são obrigatórios"));
            }

            solicitacao.setStatus("PENDENTE");
            solicitacao.setDataSolicitacao(LocalDateTime.now());
            Solicitacao novaSolicitacao = solicitacaoRepository.save(solicitacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaSolicitacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao criar solicitação: " + e.getMessage()));
        }
    }

    // Listar solicitações do usuário com informações do serviço
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Integer usuarioId) {
        try {
            List<Solicitacao> solicitacoes = solicitacaoRepository.findByUsuarioId(usuarioId);
            
            // Enriquecer com informações do serviço
            List<Map<String, Object>> solicitacoesCompletas = solicitacoes.stream()
                .map(solicitacao -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", solicitacao.getId());
                    map.put("usuarioId", solicitacao.getUsuarioId());
                    map.put("servicoSocialId", solicitacao.getServicoSocialId());
                    map.put("status", solicitacao.getStatus());
                    map.put("dataSolicitacao", solicitacao.getDataSolicitacao());
                    map.put("observacao", solicitacao.getObservacao());
                    map.put("dataResposta", solicitacao.getDataResposta());
                    map.put("respostaAdmin", solicitacao.getRespostaAdmin());
                    
                    // Buscar informações do serviço
                    servicoSocialRepository.findById(solicitacao.getServicoSocialId())
                        .ifPresent(servico -> {
                            Map<String, Object> servicoInfo = new HashMap<>();
                            servicoInfo.put("nome", servico.getNome());
                            
                            // Construir endereço completo
                            if (servico.getEndereco() != null) {
                                Endereco end = servico.getEndereco();
                                String enderecoCompleto = end.getRua() + ", " + end.getNumero() + 
                                                         " - " + end.getBairro() + ", " + 
                                                         end.getCidade() + " - " + end.getUf();
                                servicoInfo.put("endereco", enderecoCompleto);
                                servicoInfo.put("latitude", end.getLatitude());
                                servicoInfo.put("longitude", end.getLongitude());
                            }
                            
                            servicoInfo.put("telefone", servico.getTelefone());
                            map.put("servico", servicoInfo);
                        });
                    
                    return map;
                })
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(solicitacoesCompletas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao buscar solicitações: " + e.getMessage()));
        }
    }

    // Listar todas as solicitações (admin)
    @GetMapping
    public ResponseEntity<List<Solicitacao>> listarTodas() {
        return ResponseEntity.ok(solicitacaoRepository.findAll());
    }

    // Listar por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Solicitacao>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(solicitacaoRepository.findByStatus(status));
    }

    // Atualizar status da solicitação
    @PutMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request) {
        try {
            return solicitacaoRepository.findById(id)
                .map(solicitacao -> {
                    String novoStatus = request.get("status");
                    String respostaAdmin = request.get("respostaAdmin");
                    
                    solicitacao.setStatus(novoStatus);
                    solicitacao.setDataResposta(LocalDateTime.now());
                    if (respostaAdmin != null) {
                        solicitacao.setRespostaAdmin(respostaAdmin);
                    }
                    
                    solicitacaoRepository.save(solicitacao);
                    return ResponseEntity.ok(solicitacao);
                })
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao atualizar status: " + e.getMessage()));
        }
    }

    // Cancelar solicitação (usuário)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarSolicitacao(@PathVariable Integer id) {
        try {
            return solicitacaoRepository.findById(id)
                .map(solicitacao -> {
                    if (solicitacao.getStatus().equals("PENDENTE")) {
                        solicitacao.setStatus("CANCELADA");
                        solicitacaoRepository.save(solicitacao);
                        return ResponseEntity.ok(Map.of("message", "Solicitação cancelada com sucesso"));
                    } else {
                        return ResponseEntity.badRequest()
                                .body(Map.of("message", "Apenas solicitações pendentes podem ser canceladas"));
                    }
                })
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao cancelar solicitação: " + e.getMessage()));
        }
    }
}
