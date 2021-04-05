package br.com.zup.treinomercadolivre.Pergunta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.treinomercadolivre.Produto.Produto;
import br.com.zup.treinomercadolivre.Usuario.Usuario;

public class PerguntaRequest {

	@NotBlank
	@Size(max=500)
	private String titulo;

	private String descricao;
	
	
		

	public String getTitulo() {
		return titulo;
	}




	public Pergunta toModel(Produto produto, Usuario usuario) {
	
		return new Pergunta(titulo, produto, usuario);
	}


	
}
