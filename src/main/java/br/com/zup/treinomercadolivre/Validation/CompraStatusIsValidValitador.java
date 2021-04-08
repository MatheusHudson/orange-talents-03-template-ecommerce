package br.com.zup.treinomercadolivre.Validation;

import br.com.zup.treinomercadolivre.Compra.Compra;
import br.com.zup.treinomercadolivre.Compra.StatusCompra;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class CompraStatusIsValidValitador implements ConstraintValidator<CompraStatusIsValid, Object>{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(CompraStatusIsValid params) {

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null)
			return true;

		Compra compra = manager.createQuery("select c from Compra c where c.id =:value", Compra.class)
				.setParameter("value", value).getSingleResult();

		if(compra.getStatus().equals(StatusCompra.CONCLUIDA))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Está compra já possui uma transação concluida!");
		return true;
	}

}