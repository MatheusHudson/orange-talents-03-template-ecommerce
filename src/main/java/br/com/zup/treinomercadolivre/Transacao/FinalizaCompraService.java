package br.com.zup.treinomercadolivre.Transacao;

import br.com.zup.treinomercadolivre.Compra.Compra;
import br.com.zup.treinomercadolivre.Eventos.EmailEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class FinalizaCompraService {

    private final ApplicationContext applicationContext;
    private  final Evento evento;

    public FinalizaCompraService(ApplicationContext applicationContext, Evento evento) {
        this.applicationContext = applicationContext;
        this.evento = evento;
    }

    public void finalizaCompra(Compra compra, TransacaoRequest request) {
        compra.adicionaTransacao(request);
        if(request.getStatus().equals(StatusTransacao.SUCESSO)) {
            compra.finalizarCompra();
            evento.processaNota(compra);
            evento.processaRanking(compra);
            applicationContext.publishEvent(new EmailEvent(this, compra.emailSucessoTransacao()));

        }
        else {
            applicationContext.publishEvent(new EmailEvent(this, compra.emailFalhaTransacao()));
        }
    }
}
