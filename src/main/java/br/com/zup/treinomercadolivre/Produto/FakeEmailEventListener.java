package br.com.zup.treinomercadolivre.Produto;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class FakeEmailEventListener implements ApplicationListener<FakeEmailEvent> {

	@Override
	public void onApplicationEvent(FakeEmailEvent event) {
		
		System.out.println("Enviando email para o vendendor, pergunta do usuario: " + event.getPergunta().getUsuario().getEmail());
		System.out.println("Pergunta: " + event.getPergunta().getDescricao());
	}
	


}
