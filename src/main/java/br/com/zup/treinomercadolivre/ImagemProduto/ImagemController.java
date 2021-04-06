package br.com.zup.treinomercadolivre.ImagemProduto;


import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zup.treinomercadolivre.Produto.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.zup.treinomercadolivre.Usuario.Usuario;
import br.com.zup.treinomercadolivre.Validation.IdIsPresent;


@RestController
@RequestMapping("/produto/{id}/imagens")
@Validated
public class ImagemController {
	
	private final UploudFake uploudFake;
	
	public ImagemController(UploudFake uploudFake) {
		this.uploudFake = uploudFake;
	}


	@PersistenceContext
	private EntityManager em;
	
	
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarImagemProduto(@Valid ImagemRequest request, @AuthenticationPrincipal Usuario logado,
			@PathVariable @IdIsPresent(domainClass = Produto.class) Long id) {
	
		
		Produto produto = (Produto) em.createQuery("SELECT p FROM Produto p WHERE p.id = :pId").setParameter("pId", id).getSingleResult();
		
		if(produto.userIsValid(logado))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado!");
		
		Set<String> links = uploudFake.send(request.getFotos());
		produto.associarLinks(links);
		
		em.merge(produto);
		
		return ResponseEntity.ok(1);
	}

}
