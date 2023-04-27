package com.io.github.vendas.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseItemPedidoDTO {

    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
