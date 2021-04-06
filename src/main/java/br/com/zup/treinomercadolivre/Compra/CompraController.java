package br.com.zup.treinomercadolivre.Compra;

import br.com.zup.treinomercadolivre.Eventos.EmailEventPurchase;
import br.com.zup.treinomercadolivre.Produto.Produto;
import br.com.zup.treinomercadolivre.Usuario.Usuario;
import br.com.zup.treinomercadolivre.Validation.IdIsPresent;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;


@RestController
@Validated
public class CompraController {

    @PersistenceContext
    private EntityManager em;

    ApplicationContext applicationContext;

    public CompraController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostMapping("/produtos/{id}/compra")
    @Transactional
    public ResponseEntity<String> realizarCompra(@RequestBody @Valid CompraRequest request,
                                                 @PathVariable @IdIsPresent(domainClass = Produto.class
                                              ) Long id, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriBuilder) {

        Produto produto = em.find(Produto.class, id);
        Integer quantidade = request.getQuantidade();
        if(produto.isValidQuantidade(quantidade)) {
            Compra compra = request.toModel(usuario, produto);
            em.persist(compra);
            applicationContext.publishEvent(new EmailEventPurchase(this, compra));
            URI uri = request.getTipoPagamento().criarUri(compra, uriBuilder);
           return ResponseEntity.created(uri).body(uri.toString());

        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não há estoque para a quantidade selecionada");

    }
}
