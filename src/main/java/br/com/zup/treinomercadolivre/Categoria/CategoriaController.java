package br.com.zup.treinomercadolivre.Categoria;

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
@RequestMapping("/categorias")
public class CategoriaController {
	
	private CategoriaRepository categoriaRepository;
	private VerificaSeCategoriaEstaCorretaValidator verificaSeCategoriaEstaCorretaValidator;
	
	public CategoriaController(CategoriaRepository categoriaRepository,
			VerificaSeCategoriaEstaCorretaValidator verificaSeCategoriaEstaCorretaValidator) {
		this.categoriaRepository = categoriaRepository;
		this.verificaSeCategoriaEstaCorretaValidator = verificaSeCategoriaEstaCorretaValidator;
	}
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(verificaSeCategoriaEstaCorretaValidator);
	}
	

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarCategoria(@RequestBody @Valid CategoriaRequest request) {
		
		Categoria categoria = request.toModel();
		categoriaRepository.save(categoria);
		return ResponseEntity.ok(1);	
		
	}

}
