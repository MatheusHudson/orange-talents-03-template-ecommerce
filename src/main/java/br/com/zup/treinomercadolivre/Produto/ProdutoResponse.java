package br.com.zup.treinomercadolivre.Produto;

import br.com.zup.treinomercadolivre.Categoria.Categoria;
import br.com.zup.treinomercadolivre.ImagemProduto.ImagemResponse;
import br.com.zup.treinomercadolivre.OpiniaoProduto.OpiniaoCalculo;
import br.com.zup.treinomercadolivre.OpiniaoProduto.OpiniaoProduto;
import br.com.zup.treinomercadolivre.OpiniaoProduto.OpiniaoProdutoResponse;
import br.com.zup.treinomercadolivre.Pergunta.Pergunta;
import br.com.zup.treinomercadolivre.Pergunta.PerguntaResponse;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//9
public class ProdutoResponse {

    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer quantidade;

    @NotBlank
    private String categoria;

    @NotNull
    //1
    private List<CaracteristicasResponse> caracteristicasList;

    @NotNull
    //1
    private Set<ImagemResponse> imagemProdutos;

    @NotNull
    private Double notaMedia;

    private Long totalAvaliacoes;

    //1
    private Set<OpiniaoProdutoResponse> opiniaoProdutoResponses;

    //1
    private Set<PerguntaResponse> perguntaResponses;

    //1
    private OpiniaoCalculo opiniao = new OpiniaoCalculo();



    public ProdutoResponse(Produto produto, Categoria categoria, Set<OpiniaoProduto> opiniaoProduto, Set<Pergunta> pergunta) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        //1
        this.caracteristicasList = produto.getCaracteristicas().stream().map(CaracteristicasResponse::new).collect(Collectors.toList());
        this.categoria =  categoria.getNome();
        //1
        this.imagemProdutos = produto.getImagens().stream().map(ImagemResponse::new).collect(Collectors.toSet());
        this.notaMedia = opiniao.calcularMedia(opiniaoProduto);
        this.totalAvaliacoes = opiniao.contarAvaliacoes(opiniaoProduto);
        //1
        this.opiniaoProdutoResponses = opiniaoProduto.stream().map(OpiniaoProdutoResponse::new).collect(Collectors.toSet());
        //1
        this.perguntaResponses = pergunta.stream().map(PerguntaResponse::new).collect(Collectors.toSet());

    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<CaracteristicasResponse> getCaracteristicasList() {
        return caracteristicasList;
    }

    public String getCategoria() {
        return categoria;
    }

    public Set<ImagemResponse> getImagemProdutos() {
        return imagemProdutos;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public Long getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public Set<OpiniaoProdutoResponse> getOpiniaoProdutoResponses() {
        return opiniaoProdutoResponses;
    }

    public Set<PerguntaResponse> getPerguntaResponses() {
        return perguntaResponses;
    }
}
