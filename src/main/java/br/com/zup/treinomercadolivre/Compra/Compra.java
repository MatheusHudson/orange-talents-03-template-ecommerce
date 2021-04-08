package br.com.zup.treinomercadolivre.Compra;

import br.com.zup.treinomercadolivre.Eventos.Email;
import br.com.zup.treinomercadolivre.Transacao.Transacao;
import br.com.zup.treinomercadolivre.Transacao.TransacaoRequest;
import br.com.zup.treinomercadolivre.Produto.Produto;
import br.com.zup.treinomercadolivre.Usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;


@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Min(1)
    private Integer quantidade;
    //1
    @ManyToOne
    private Produto produto;
    //1
    @ManyToOne
    private Usuario usuario;
    //1
    @NotNull
    private Gateway tipoPagamento;
    //1
    @NotNull
    private StatusCompra status = StatusCompra.INICIADA;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes;

    private BigDecimal valorProduto;

    @Deprecated
    public Compra () {

    }


    public Compra(@NotNull @Positive @Min(1) Integer quantidade, Produto produto, Usuario usuario, Gateway tipoPagamento) {
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

    public Gateway getTipoPagamento() {
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

    public void adicionaTransacao(TransacaoRequest request) {
        this.transacoes.add(request.obterTransacao(this));
    }

    public void finalizarCompra() {
        this.status = StatusCompra.CONCLUIDA;
    }

    //1
    public Email emailAvisoCompra() {
        return new Email("Vendedor do Produto",
                usuario.getEmail(),
                "Compra do produto "
                        + produto.getNome(),
                "Você recebeu uma nova solicitação de compra."
                );
    }
    public Email emailFalhaTransacao() {
        return new Email(usuario.getEmail(),
                "Gateway de pagamento",
                "Erro ao realizar pagamento do produto: "
                        + produto.getNome(),
                "Ocorreu um erro ao tentar finalizar o pagamento, você pode tentar denovo acessando este link:" +
                        tipoPagamento.descricao + produto.getId()
        );
    }

    public Email emailSucessoTransacao() {
        return new Email(
                usuario.getEmail(),
                "Vendedor",
                "Transação confirmada: "
                        + produto.getNome(),
                "Transação efetuada com sucesso, agora é somente aguarda o seu produto!"
        );
    }
}
