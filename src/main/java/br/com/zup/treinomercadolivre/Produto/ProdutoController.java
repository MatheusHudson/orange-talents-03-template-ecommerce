package br.com.zup.treinomercadolivre.Produto;


import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	
	private final  ProdutoRepository produtosRepository;
	

	public ProdutoController(ProdutoRepository produtosRepository) {
		this.produtosRepository = produtosRepository;
	}


	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequest request) {
		Produto produto = request.toModel();
		produtosRepository.save(produto);
		return ResponseEntity.ok(1);
	}
}
