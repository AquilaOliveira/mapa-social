package com.mapasocial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sugestoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sugestao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nome_servico", nullable = false)
    private String nomeServico;

    @Column(name = "categoria_sugerida", nullable = false)
    private String categoriaSugerida;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    private String endereco;

    private String contato;

    @Column(nullable = false)
    private String status = "pendente";

    @Column(name = "observacao_admin", columnDefinition = "TEXT")
    private String observacaoAdmin;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
