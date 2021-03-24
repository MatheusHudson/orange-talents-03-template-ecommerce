package br.com.zup.treinomercadolivre.Produto;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.treinomercadolivre.Categoria.Categoria;

@Component
public class VerificaACategoriaValidator implements Validator{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors())
			return;
		ProdutoRequest request = (ProdutoRequest) target;
		
		List<Categoria> categoria = em.createQuery("Select c From Categoria c WHERE c.id = :id").setParameter("id", request.getCategoriaId()).getResultList();
		
		if(categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira o id de uma categoria valida em nosos banco de dados");
	}

}
