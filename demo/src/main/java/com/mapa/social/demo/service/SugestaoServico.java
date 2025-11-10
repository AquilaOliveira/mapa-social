package com.mapa.social.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Tabela para armazenar sugestões de novos serviços
@Entity
@Table(name = "sugestao_servico")
public class SugestaoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Campos solicitados no diagrama (adaptados):
    @Column(name = "nome_sugerido", nullable = false, length = 150)
    private String nomeSugerido; // string nome_sugerido

    @Column(name = "endereco_sugerido", length = 255)
    private String enderecoSugerido; // string endereco_sugerido

    @Column(name = "descricao_sugerida", length = 500)
    private String descricaoSugerida; // string descricao_sugerida

    // Status da sugestão (ex: PENDENTE, APROVADO, REJEITADO)
    @Enumerated(EnumType.STRING) // enum status
    @Column(nullable = false)
    private StatusSugestao status = StatusSugestao.PENDENTE;

    // Relacionamento ManyToOne: Muitas sugestões podem vir do mesmo Usuário
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // int usuario_id (o usuário que sugeriu)
    
    @Column(name = "data_sugestao", nullable = false)
    private LocalDateTime dataSugestao = LocalDateTime.now();


    // Enum para o campo status
    public enum StatusSugestao {
        PENDENTE, APROVADO, REJEITADO
    }

    // --- Construtor Padrão (necessário para JPA) ---
    public SugestaoServico() {
    }

    // --- Getters e Setters ---
    
    // ... (Métodos Getters e Setters omitidos para brevidade, mas devem ser incluídos)
    // ...
    // ...
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomeSugerido() { return nomeSugerido; }
    public void setNomeSugerido(String nomeSugerido) { this.nomeSugerido = nomeSugerido; }
    public String getEnderecoSugerido() { return enderecoSugerido; }
    public void setEnderecoSugerido(String enderecoSugerido) { this.enderecoSugerido = enderecoSugerido; }
    public String getDescricaoSugerida() { return descricaoSugerida; }
    public void setDescricaoSugerida(String descricaoSugerida) { this.descricaoSugerida = descricaoSugerida; }
    public StatusSugestao getStatus() { return status; }
    public void setStatus(StatusSugestao status) { this.status = status; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public LocalDateTime getDataSugestao() { return dataSugestao; }
    public void setDataSugestao(LocalDateTime dataSugestao) { this.dataSugestao = dataSugestao; }
}
