package com.mapa.social.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorito")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_social_id", nullable = false)
    private ServicoSocial servicoSocial;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao = LocalDateTime.now();

    public Favorito() {
    }

    public Favorito(Usuario usuario, ServicoSocial servicoSocial) {
        this.usuario = usuario;
        this.servicoSocial = servicoSocial;
        this.dataInclusao = LocalDateTime.now();
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

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
