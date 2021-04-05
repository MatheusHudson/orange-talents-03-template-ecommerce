	package br.com.zup.treinomercadolivre.OpiniaoProduto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.treinomercadolivre.Produto.Produto;
import br.com.zup.treinomercadolivre.Usuario.Usuario;

public class OpinaoProdutoRequest {

	@Max(5)
	@Min(1)
	private int nota;
	
	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max=500)
	private String descricao;
	
	


	public int getNota() {
		return nota;
	}




	public String getTitulo() {
		return titulo;
	}




	public String getDescricao() {
		return descricao;
	}




	public OpiniaoProduto toModel(Produto produto, Usuario logado) {

		return new OpiniaoProduto(nota, titulo, descricao, logado, produto);
	}

}
