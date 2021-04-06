package br.com.zup.treinomercadolivre.Eventos;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailEventQuestionListener implements ApplicationListener<EmailEventQuestion> {

	@Override
	public void onApplicationEvent(EmailEventQuestion event) {
		
		System.out.println("Enviando email para o vendendor, pergunta do usuario: " + event.getPergunta().getUsuario().getEmail());
		System.out.println("Pergunta: " + event.getPergunta().getTitulo());
	}
}
