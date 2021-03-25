package br.com.zup.treinomercadolivre.Produto;


import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	private final ProdutoRepository produtoRepository;
	
		
	public ImagemController(UploudFake uploudFake,ProdutoRepository produtoRepository) {
		this.uploudFake = uploudFake;
		this.produtoRepository = produtoRepository;
	}

	
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarImagemProduto(@Valid ImagemRequest request, @AuthenticationPrincipal Usuario logado,
			@PathVariable @Valid Long id) {
		
		Optional<Produto> possivelProduto = produtoRepository.findById(id);
		if(possivelProduto.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id informado é invalido!");
		
		Produto produto = possivelProduto.get();
		
		if(produto.userIsValid(logado))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado!");
		
		Set<String> links = uploudFake.send(request.getFotos());
		produto.associarLinks(links);
		
		produtoRepository.save(produto);
		
		return ResponseEntity.ok(1);
	}

}
