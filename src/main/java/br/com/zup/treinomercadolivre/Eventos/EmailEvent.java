package br.com.zup.treinomercadolivre.Eventos;

import br.com.zup.treinomercadolivre.Compra.Compra;
import br.com.zup.treinomercadolivre.Pergunta.Pergunta;
import org.springframework.context.ApplicationEvent;

public class EmailEvent extends ApplicationEvent{


	private static final long serialVersionUID = 1L;

	private Email email;

	public EmailEvent(Object source, Email email) {
		super(source);
		this.email = email;
	}


	public Email getEmail() {
		return email;
	}
}
