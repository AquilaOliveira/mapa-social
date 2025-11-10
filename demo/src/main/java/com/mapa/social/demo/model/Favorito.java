package com.mapa.social.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorito")
public class Favorito {

    // Adotamos um ID próprio (Chave Primária Composta é mais complexa)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relacionamento ManyToOne: Muitos favoritos para um único Usuário
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; // int usuario_id

    // Relacionamento ManyToOne: Muitos favoritos podem apontar para o mesmo ServicoSocial
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_social_id", nullable = false)
    private ServicoSocial servicoSocial; // int servicosocial_id

    // Campo que armazena a data e hora em que foi marcado como favorito
    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao = LocalDateTime.now(); // datetime data_inclusao

    // --- Construtor Padrão (necessário para JPA) ---
    public Favorito() {
    }

    // --- Construtor para facilitar a criação ---
    public Favorito(Usuario usuario, ServicoSocial servicoSocial) {
        this.usuario = usuario;
        this.servicoSocial = servicoSocial;
        this.dataInclusao = LocalDateTime.now();
    }
    
    // --- Getters e Setters ---

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
