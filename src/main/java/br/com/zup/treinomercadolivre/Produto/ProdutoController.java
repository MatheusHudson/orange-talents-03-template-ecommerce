package br.com.zup.treinomercadolivre.Produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;
	
	private ListaDeCaracteristicasDeveSerMaiorQueTresValidator listaDeCaracteristicasDeveSerMaiorQueTresValidator;
	
	
	
	public ProdutoController(ListaDeCaracteristicasDeveSerMaiorQueTresValidator listaDeCaracteristicasDeveSerMaiorQueTresValidator) {
		this.listaDeCaracteristicasDeveSerMaiorQueTresValidator = listaDeCaracteristicasDeveSerMaiorQueTresValidator;
	}


	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(listaDeCaracteristicasDeveSerMaiorQueTresValidator);
	}
	

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequest request) {
		Produto produto = request.toModel();
		request.saveCaracteristicas(em);
		em.persist(produto);
		return ResponseEntity.ok(1);
	}
}
