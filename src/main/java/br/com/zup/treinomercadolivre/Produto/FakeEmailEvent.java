package br.com.zup.treinomercadolivre.Produto;

import br.com.zup.treinomercadolivre.Pergunta.Pergunta;
import org.springframework.context.ApplicationEvent;

public class FakeEmailEvent  extends ApplicationEvent{


	private static final long serialVersionUID = 1L;
	
	private final Pergunta pergunta;

	public FakeEmailEvent(Object source, Pergunta pergunta) {
		super(source);
		this.pergunta = pergunta;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}
	
}
