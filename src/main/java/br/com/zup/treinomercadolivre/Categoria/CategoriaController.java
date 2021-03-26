package br.com.zup.treinomercadolivre.Categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository
		) {
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarCategoria(@RequestBody @Valid CategoriaRequest request) {

		Categoria categoria = request.toModel();
		categoriaRepository.save(categoria);
		return ResponseEntity.ok(1);

	}

}
