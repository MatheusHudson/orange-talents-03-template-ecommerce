package br.com.zup.treinomercadolivre.Produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.treinomercadolivre.Usuario.Usuario;
import br.com.zup.treinomercadolivre.Validation.IdIsPresent;

@RestController
@RequestMapping("produto/{id}/perguntas")
@Validated
public class PerguntaController {
	
	ApplicationContext applicationContext;

	public PerguntaController(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public void cadastrarPergunta(@PathVariable @IdIsPresent(domainClass = Produto.class) Long id,@RequestBody @Valid  PerguntaRequest request, @AuthenticationPrincipal Usuario usuario) {
		
		Produto produto = (Produto) em.createQuery("SELECT p FROM Produto p WHERE p.id = :pId").setParameter("pId", id).getSingleResult();
		if(produto.userIsValid(usuario))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inválido");
		Pergunta pergunta = request.toModel(produto, usuario);
		em.persist(pergunta);

		applicationContext.publishEvent(new FakeEmailEvent(this, pergunta));
	
		
	}
}
