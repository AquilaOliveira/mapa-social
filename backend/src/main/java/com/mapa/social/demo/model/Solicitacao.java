package com.mapa.social.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacoes")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "servico_social_id", nullable = false)
    private Integer servicoSocialId;

    @Column(nullable = false, length = 20)
    private String status = "PENDENTE"; // PENDENTE, APROVADA, REJEITADA, CANCELADA

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    @Column(length = 1000)
    private String observacao;

    @Column(name = "data_resposta")
    private LocalDateTime dataResposta;

    @Column(name = "resposta_admin", length = 1000)
    private String respostaAdmin;

    public Solicitacao() {
    }

    public Solicitacao(Integer usuarioId, Integer servicoSocialId, String observacao) {
        this.usuarioId = usuarioId;
        this.servicoSocialId = servicoSocialId;
        this.observacao = observacao;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getServicoSocialId() {
        return servicoSocialId;
    }

    public void setServicoSocialId(Integer servicoSocialId) {
        this.servicoSocialId = servicoSocialId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDateTime dataResposta) {
        this.dataResposta = dataResposta;
    }

    public String getRespostaAdmin() {
        return respostaAdmin;
    }

    public void setRespostaAdmin(String respostaAdmin) {
        this.respostaAdmin = respostaAdmin;
    }
}
