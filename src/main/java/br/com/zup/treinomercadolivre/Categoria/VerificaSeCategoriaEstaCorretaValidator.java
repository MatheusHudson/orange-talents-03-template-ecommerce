package br.com.zup.treinomercadolivre.Categoria;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VerificaSeCategoriaEstaCorretaValidator implements Validator {

	private CategoriaRepository repository;
	
	public VerificaSeCategoriaEstaCorretaValidator(CategoriaRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoriaRequest request = (CategoriaRequest) target;
		if(errors.hasErrors() || request.getCategoriaMae() == null ) {
			return;
		}
		
		Optional<Categoria> categoria = repository.findById(request.getCategoriaMae());
		if(categoria.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"É preciso passar o id de uma categoria já existente em nosso banco de dados");
		}
		
	}

}
