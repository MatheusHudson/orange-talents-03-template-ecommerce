package br.com.zup.treinomercadolivre.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.treinomercadolivre.Usuario.Usuario;

public class PerguntaRequest {

	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max=500)
	private String descricao;
	
	
		

	public String getTitulo() {
		return titulo;
	}




	public String getDescricao() {
		return descricao;
	}




	public Pergunta toModel(Produto produto, Usuario usuario) {
	
		return new Pergunta(titulo, descricao, produto, usuario);
	}


	
}
