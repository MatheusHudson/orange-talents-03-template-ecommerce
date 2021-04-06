package br.com.zup.treinomercadolivre.Compra;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public enum Gateways {

    PAYPAL("paypal.com/buyerId="),PAGSEGURO("pagseguro.com/returnId=");

    String descricao;

    Gateways(String descricao) {
        this.descricao = descricao;
    }


    public URI criarUri(Compra compra, UriComponentsBuilder uriBuilder){
       return uriBuilder.path(compra.getTipoPagamento().descricao +
                compra.getId() + "&redirectUrl=Compra").buildAndExpand().toUri();
    }

}
