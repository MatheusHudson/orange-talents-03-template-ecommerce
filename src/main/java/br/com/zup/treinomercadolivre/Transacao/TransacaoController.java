package br.com.zup.treinomercadolivre.Transacao;


import br.com.zup.treinomercadolivre.Compra.Compra;
import br.com.zup.treinomercadolivre.Compra.Gateway;
import br.com.zup.treinomercadolivre.Validation.CompraStatusIsValid;
import br.com.zup.treinomercadolivre.Validation.IdIsPresent;
import br.com.zup.treinomercadolivre.Validation.ValidarTransacao;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Validated
public class TransacaoController {
    //1
    private final ValidarTransacao validarTransacao;
    @PersistenceContext
    private EntityManager em;

    //1
    private final FinalizaCompraService finalizaCompraService;

    public TransacaoController(ValidarTransacao validarTransacao, FinalizaCompraService finalizaCompraService) {
        this.validarTransacao = validarTransacao;
        this.finalizaCompraService = finalizaCompraService;
    }

    @InitBinder
    //1
    public void  init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validarTransacao);
    }


    @PostMapping("/paypal.com/buyerId={id}")
    @Transactional
    public void processaTransacaoPayPal(@RequestBody @Valid TransacaoRequest request,
                                  @PathVariable @IdIsPresent(domainClass = Compra.class) @CompraStatusIsValid Long id) {
        //1
        Compra compra = em.find(Compra.class, id);

        //1
        if(!compra.getTipoPagamento().equals(Gateway.PAYPAL)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Somente pode processar transacao do tipo paypal!");
        }
        finalizaCompraService.finalizaCompra(compra, request);
        em.merge(compra);

    }

    @PostMapping("/pagseguro.com/returnId={id}")
    @Transactional
    public void processaTransacaoPagSeguro(@RequestBody @Valid TransacaoRequest request,
                                                      @PathVariable @IdIsPresent(domainClass = Compra.class) @CompraStatusIsValid Long id) {

        Compra compra = em.find(Compra.class, id);

        //1
        if(!compra.getTipoPagamento().equals(Gateway.PAGSEGURO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Somente pode processar transacao do tipo paypal!");
        }

        finalizaCompraService.finalizaCompra(compra, request);
        em.merge(compra);

    }
}
