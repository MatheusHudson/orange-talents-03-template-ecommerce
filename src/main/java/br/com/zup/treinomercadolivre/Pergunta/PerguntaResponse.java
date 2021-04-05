package br.com.zup.treinomercadolivre.Pergunta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


public class PerguntaResponse {

	@NotBlank
	@Size(max=500)
	private String titulo;
	private LocalDateTime instante = LocalDateTime.now();

	public PerguntaResponse(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
	}


	public String getTitulo() {
		return titulo;
	}

	
}
