package br.com.zup.treinomercadolivre.Produto;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.treinomercadolivre.Usuario.Usuario;

@RestController
@RequestMapping("/imagens")
public class ImagemController {
	

	
	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarImagemProduto(@RequestBody @Valid ImagemRequest request, @AuthenticationPrincipal Usuario logado) {
		
		Produto produto = (Produto) em.createQuery("Select a From Produto a WHERE a.id = :pId ").setParameter("pId", request.getProdutoId()).getSingleResult();
		if(logado.getId() != produto.getUsuarioId())
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado!");
		
		List<ImagemProduto> imagens = request.toModel();
		produto.getImagens().addAll(imagens);
		
		return ResponseEntity.ok(1);
	}

}
