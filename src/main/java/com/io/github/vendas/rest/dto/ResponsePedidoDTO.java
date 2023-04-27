package com.io.github.vendas.rest.dto;

import lombok.*;


import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePedidoDTO {

    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private String dataPedido;
    private String status;
    private List<ResponseItemPedidoDTO> items;
}
