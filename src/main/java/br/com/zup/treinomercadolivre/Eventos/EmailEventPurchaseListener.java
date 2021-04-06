package br.com.zup.treinomercadolivre.Eventos;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailEventPurchaseListener implements ApplicationListener<EmailEventPurchase> {

	@Override
	public void onApplicationEvent(EmailEventPurchase event) {
		System.out.println("Assunto: Nova compra " );
		System.out.println("Corpo: O usuario " + event.getCompra().getUsuario().getEmail() + "realizou uma compra");
		System.out.println("Status da compra: " + event.getCompra().getStatus());
	}
}
