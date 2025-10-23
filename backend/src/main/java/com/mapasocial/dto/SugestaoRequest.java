package com.mapasocial.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoRequest {
    @NotBlank(message = "Nome do serviço é obrigatório")
    private String nomeServico;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoriaSugerida;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    private String endereco;
    private String contato;
}
