package br.com.zup.treinomercadolivre.Produto;

import br.com.zup.treinomercadolivre.Categoria.Categoria;
import br.com.zup.treinomercadolivre.ImagemProduto.ImagemResponse;
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

    //3
    private Set<OpiniaoProdutoResponse> opiniaoProdutoResponses;

    //4
    private Set<PerguntaResponse> perguntaResponses;



    public ProdutoResponse(Produto produto, Categoria categoria, Double notaMedia,
                           long totalAvaliacoes, Set<OpiniaoProduto> opiniaoProduto, Set<Pergunta> pergunta) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        //5
        this.caracteristicasList = produto.getCaracteristicas().stream().map(CaracteristicasResponse::new).collect(Collectors.toList());
        this.categoria =  categoria.getNome();
        //6
        this.imagemProdutos = produto.getImagens().stream().map(ImagemResponse::new).collect(Collectors.toSet());
        this.notaMedia = notaMedia;
        this.totalAvaliacoes = totalAvaliacoes;
        //7
        this.opiniaoProdutoResponses = opiniaoProduto.stream().map(OpiniaoProdutoResponse::new).collect(Collectors.toSet());
        //8
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
