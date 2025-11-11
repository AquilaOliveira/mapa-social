package com.mapa.social.demo.model;

import jakarta.persistence.*;

@Entity
public class ServicoSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 50)
    private String tipo; // Ex: "ONG", "Governamental", "Privado", etc.

    @Column(length = 20)
    private String telefone;

    @Column(length = 500)
    private String descricao; // Descrição do serviço social

    // Relacionamento OneToOne com Endereco
    // Um ServicoSocial tem um Endereco, e um Endereco pertence a um ServicoSocial.
    // Usamos @JoinColumn para criar a chave estrangeira 'endereco_id' nesta tabela.
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false)
    private Endereco endereco; // int endereco_id

    // Relacionamento ManyToOne com Categoria
    // Muitos Servicos podem ter a mesma Categoria.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria; // int categoria_id

    // --- Construtor Padrão (necessário para JPA) ---
    public ServicoSocial() {
    }

    // --- Getters e Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
