package com.io.github.vendas.service.impl;

import com.io.github.vendas.domain.entity.Cliente;
import com.io.github.vendas.domain.entity.ItemPedido;
import com.io.github.vendas.domain.entity.Pedido;
import com.io.github.vendas.domain.entity.Produto;
import com.io.github.vendas.domain.enums.StatusPedido;
import com.io.github.vendas.domain.repository.ClienteRepository;
import com.io.github.vendas.domain.repository.ItemPedidoRepository;
import com.io.github.vendas.domain.repository.PedidoRepository;
import com.io.github.vendas.domain.repository.ProdutoRepository;
import com.io.github.vendas.exception.PedidoNaoEncontradoException;
import com.io.github.vendas.exception.RegraNegocioException;
import com.io.github.vendas.rest.dto.ItemPedidoDTO;
import com.io.github.vendas.rest.dto.PedidoDTO;
import com.io.github.vendas.service.PedidoService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;




    @Override
    public Pedido salvar(PedidoDTO dto) {
        Integer clienteId = dto.getCliente();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RegraNegocioException("Código de Cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(dto.getTotal());
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);


        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() );
    }


    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items");
        }
        return items.stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de Produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setProduto(produto);
                    itemPedido.setPedido(pedido);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
