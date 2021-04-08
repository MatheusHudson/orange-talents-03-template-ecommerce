package br.com.zup.treinomercadolivre.EndpointsFake;

import javax.validation.constraints.NotNull;

public class Request {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public Request(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}
