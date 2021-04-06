package br.com.zup.treinomercadolivre.Eventos;

import br.com.zup.treinomercadolivre.Compra.Compra;
import org.springframework.context.ApplicationEvent;

public class EmailEventPurchase extends ApplicationEvent{


	private static final long serialVersionUID = 1L;

	private final Compra compra;

	public EmailEventPurchase(Object source, Compra compra) {
		super(source);
		this.compra = compra;
	}

	public Compra getCompra() {
		return compra;
	}
}
