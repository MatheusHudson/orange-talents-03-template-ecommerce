package br.com.zup.treinomercadolivre.Eventos;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailEventListener implements ApplicationListener<EmailEvent> {

	@Override
	public void onApplicationEvent(EmailEvent event) {

		System.out.println("Para:" + event.getEmail().getPara());
		System.out.println("De: " + event.getEmail().getDe());
		System.out.println("Assunto: " + event.getEmail().getAssunto());
		System.out.println("Corpo: " +event.getEmail().getCorpo());

	}

}
