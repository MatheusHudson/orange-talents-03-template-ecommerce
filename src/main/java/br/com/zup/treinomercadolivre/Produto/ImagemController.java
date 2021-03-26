package br.com.zup.treinomercadolivre.Produto;


import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.zup.treinomercadolivre.Usuario.Usuario;


@RestController
@RequestMapping("/produto/{id}/imagens")
public class ImagemController {
	
	private final UploudFake uploudFake;
	private final ValidaProduto validaProduto;;
	
	public ImagemController(UploudFake uploudFake, ValidaProduto validaProduto) {
		this.uploudFake = uploudFake;
		this.validaProduto = validaProduto;
	}


	@PersistenceContext
	private EntityManager em;
	
	
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarImagemProduto(@Valid ImagemRequest request, @AuthenticationPrincipal Usuario logado,
			@PathVariable @Valid Long id) {
	
		
		Produto produto = validaProduto.devolveProdutoValido(id);
		
		if(produto.userIsValid(logado))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado!");
		
		Set<String> links = uploudFake.send(request.getFotos());
		produto.associarLinks(links);
		
		em.persist(produto);
		
		return ResponseEntity.ok(1);
	}

}
