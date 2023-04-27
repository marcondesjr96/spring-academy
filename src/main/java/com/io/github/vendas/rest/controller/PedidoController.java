package com.io.github.vendas.rest.controller;

import com.io.github.vendas.domain.entity.ItemPedido;
import com.io.github.vendas.domain.entity.Pedido;
import com.io.github.vendas.domain.enums.StatusPedido;
import com.io.github.vendas.rest.dto.AtualizacaoStatusPedidoDTO;
import com.io.github.vendas.rest.dto.PedidoDTO;
import com.io.github.vendas.rest.dto.ResponseItemPedidoDTO;
import com.io.github.vendas.rest.dto.ResponsePedidoDTO;
import com.io.github.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;



    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public ResponsePedidoDTO getById(@PathVariable Integer id){
       return pedidoService.obterPedidoCompleto(id)
               .map(p -> converterPedido(p))
               .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private ResponsePedidoDTO converterPedido(Pedido pedido){
        return ResponsePedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converterItens(pedido.getItens()))
                .build();
    }

    private List<ResponseItemPedidoDTO> converterItens(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                itemPedido -> ResponseItemPedidoDTO.builder()
                        .descricaoProduto(itemPedido.getProduto().getDescricao())
                        .precoUnitario(itemPedido.getProduto().getPreco())
                        .quantidade(itemPedido.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
