package br.com.zup.treinomercadolivre.Produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
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
@RequestMapping("/produto/{id}/opiniao")
@Validated
public class OpiniaoProdutoController {


	@PersistenceContext
	EntityManager em;


	@PostMapping
	@Transactional
	public void cadastrarOpiniaoProduto(@RequestBody @Valid OpinaoProdutoRequest request,
			@PathVariable @IdIsPresent(domainClass = Produto.class) Long id ,
			@AuthenticationPrincipal Usuario logado) {

		Produto produto = (Produto) em.createQuery("SELECT p FROM Produto p WHERE p.id = :pId").setParameter("pId", id).getSingleResult();
		if (produto.userIsValid(logado))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado!");

		OpiniaoProduto opiniaoProduto = request.toModel(produto, logado);
		
		em.persist(opiniaoProduto);
		
	}

}
