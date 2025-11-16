package com.mapa.social.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sugestao_servico")
public class SugestaoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_sugerido", nullable = false, length = 150)
    private String nomeSugerido;

    @Column(name = "endereco_sugerido", length = 255)
    private String enderecoSugerido;

    @Column(name = "descricao_sugerida", length = 500)
    private String descricaoSugerida;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSugestao status = StatusSugestao.PENDENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @Column(name = "data_sugestao", nullable = false)
    private LocalDateTime dataSugestao = LocalDateTime.now();

    public enum StatusSugestao {
        PENDENTE, APROVADO, REJEITADO
    }

    public SugestaoServico() {
    }
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
