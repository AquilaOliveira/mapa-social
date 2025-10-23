package com.mapasocial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoSocialDTO {
    private UUID id;
    private UUID categoriaId;
    private String categoriaNome;
    private String nome;
    private String descricao;
    private String endereco;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String telefone;
    private String email;
    private String horarioFuncionamento;
    private String siteUrl;
    private String requisitos;
    private String documentosNecessarios;
    private Boolean verificado;
    private Double mediaAvaliacoes;
}
