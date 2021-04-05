package br.com.zup.treinomercadolivre.Produto;


import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zup.treinomercadolivre.Categoria.Categoria;
import br.com.zup.treinomercadolivre.OpiniaoProduto.OpiniaoProduto;
import br.com.zup.treinomercadolivre.Pergunta.Pergunta;
import br.com.zup.treinomercadolivre.Validation.IdIsPresent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.zup.treinomercadolivre.Usuario.Usuario;

import java.util.OptionalDouble;
import java.util.Set;

@RestController
@Validated
public class ProdutoController {

	
	private final Repository repository;
	

	public ProdutoController(Repository produtosRepository) {
		this.repository = produtosRepository;
	}


	@PostMapping("/produtos")
	@Transactional
	public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario logado) {
		Produto produto = request.toModel(logado);
		repository.save(produto);
		return ResponseEntity.ok(1);
	}

	@GetMapping("produtos/{id}")
	@Transactional
	public ResponseEntity<ProdutoResponse> obterProduto(@PathVariable @IdIsPresent(domainClass = Produto.class) Long id) {

		Produto produto =  repository.findById(id).get();
		Categoria categoria = repository.categoria(produto.getCategoriaId());
		Set<OpiniaoProduto> opiniaoProduto = repository.opiniaoProduto(id);
		Double notaMedia = opiniaoProduto.stream().mapToDouble(opiniao -> opiniao.getNota()).average().orElse(0.0);
		Long totalAvaliacoes = opiniaoProduto.stream().mapToInt(opiniao -> opiniao.getNota()).count();
		Set<Pergunta> pergunta = repository.perguntas(id);
		ProdutoResponse response = new ProdutoResponse(produto, categoria, notaMedia, totalAvaliacoes, opiniaoProduto, pergunta);

		return ResponseEntity.ok(response);
	}
}
