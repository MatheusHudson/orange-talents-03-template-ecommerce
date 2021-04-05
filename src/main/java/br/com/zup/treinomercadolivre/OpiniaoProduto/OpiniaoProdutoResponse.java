package br.com.zup.treinomercadolivre.OpiniaoProduto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;


public class OpiniaoProdutoResponse {


	@Max(5)
	@Min(1)
	private int nota;

	@NotBlank
	private String titulo;

	@NotBlank
	private String descricao;

	@Deprecated
	public OpiniaoProdutoResponse() {

	}

	public OpiniaoProdutoResponse(OpiniaoProduto opiniaoProduto) {
		this.nota = opiniaoProduto.getNota();
		this.descricao = opiniaoProduto.getDescricao();
		this.titulo = opiniaoProduto.getTitulo();
	}


	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
}
