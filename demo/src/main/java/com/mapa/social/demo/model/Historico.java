
package com.mapa.social.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_social_id", nullable = false)
    private ServicoSocial servicoSocial;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ServicoSocial getServicoSocial() {
        return servicoSocial;
    }

    public void setServicoSocial(ServicoSocial servicoSocial) {
        this.servicoSocial = servicoSocial;
    }

    public LocalDateTime getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(LocalDateTime dataAcesso) {
        this.dataAcesso = dataAcesso;
    }
}
