package com.io.github.vendas.service;

import com.io.github.vendas.domain.entity.Pedido;
import com.io.github.vendas.domain.enums.StatusPedido;
import com.io.github.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
