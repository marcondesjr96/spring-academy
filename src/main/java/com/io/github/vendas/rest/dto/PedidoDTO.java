package com.io.github.vendas.rest.dto;

import com.io.github.vendas.validation.NotEmptyList;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Codigo do Cliente obrigatório")
    private Integer cliente;

    @NotNull(message = "Campo Preço total obrigatório")
    private BigDecimal total;

    @NotEmptyList(message = "Pedido não pode ser realizado sem itens")
    private List<ItemPedidoDTO> items;
}