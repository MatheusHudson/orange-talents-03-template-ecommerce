package br.com.zup.treinomercadolivre.Compra;

import br.com.zup.treinomercadolivre.Produto.Produto;
import br.com.zup.treinomercadolivre.Usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class CompraRequest {

    @NotNull
    @Positive
    @Min(1)
    private Integer quantidade;

    private Gateways tipoPagamento;


    public Compra toModel(Usuario usuario, Produto produto) {

        return new Compra(quantidade, produto, usuario,tipoPagamento);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Gateways getTipoPagamento() {
        return tipoPagamento;
    }
}
