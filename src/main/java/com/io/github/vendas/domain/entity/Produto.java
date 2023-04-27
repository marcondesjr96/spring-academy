package com.io.github.vendas.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty(message = "Campo descrição é Obrigatório")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "Campo preço é obrigatório")
    private BigDecimal preco;

}