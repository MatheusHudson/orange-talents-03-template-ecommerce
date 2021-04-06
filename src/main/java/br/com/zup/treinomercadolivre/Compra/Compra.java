package br.com.zup.treinomercadolivre.Compra;

import br.com.zup.treinomercadolivre.Produto.Produto;
import br.com.zup.treinomercadolivre.Usuario.Usuario;
import br.com.zup.treinomercadolivre.Validation.UniqueValue;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Min(1)
    private Integer quantidade;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gateways tipoPagamento;

    @Enumerated(EnumType.STRING)
    @NotNull
    @CreationTimestamp
    private StatusCompra status = StatusCompra.INICIADA;

    private BigDecimal valorProduto;

    public Compra(@NotNull @Positive @Min(1) Integer quantidade, Produto produto, Usuario usuario, Gateways tipoPagamento) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.usuario = usuario;
        this.tipoPagamento = tipoPagamento;
        this.valorProduto = produto.getValor();

    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Gateways getTipoPagamento() {
        return tipoPagamento;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public Long getId() {
        return id;
    }
}
