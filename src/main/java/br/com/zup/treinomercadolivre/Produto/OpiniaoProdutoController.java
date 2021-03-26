package br.com.zup.treinomercadolivre.Produto;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.treinomercadolivre.Usuario.Usuario;

@RestController
@RequestMapping("/produto/{id}/opiniao")
public class OpiniaoProdutoController {

	private final ValidaProduto validaProduto;

	@PersistenceContext
	EntityManager em;
	
	public OpiniaoProdutoController(ValidaProduto validaProduto) {
		this.validaProduto = validaProduto;
	}

	@PostMapping
	@Transactional
	public void cadastrarOpiniaoProduto(@RequestBody @Valid OpinaoProdutoRequest request, @PathVariable Long id,
			@AuthenticationPrincipal Usuario logado) {

		Produto produto = validaProduto.devolveProdutoValido(id);
		if (produto.userIsValid(logado))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado!");

		OpiniaoProduto opiniaoProduto = request.toModel(produto, logado);
		
		produto.adicionarOpiniao(opiniaoProduto);
		
		em.merge(produto);
		
	}

}
