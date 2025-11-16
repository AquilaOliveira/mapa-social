package com.mapa.social.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "servico_social_id", nullable = false)
    private Integer servicoSocialId;

    @Column(name = "data_acesso", nullable = false)
    private LocalDateTime dataAcesso = LocalDateTime.now();

    public Historico() {
    }

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

    public LocalDateTime getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(LocalDateTime dataAcesso) {
        this.dataAcesso = dataAcesso;
    }
}
