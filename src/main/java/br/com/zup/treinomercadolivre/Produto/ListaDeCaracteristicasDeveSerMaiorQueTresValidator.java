package br.com.zup.treinomercadolivre.Produto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ListaDeCaracteristicasDeveSerMaiorQueTresValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		ProdutoRequest request = (ProdutoRequest) target;
		for (Caracteristicas caract : request.getCaracteristicas()) {
			if (caract.getDescricao().length() > 1000 || caract.getDescricao().isBlank() || caract.getNome().isBlank())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Nome e descrição não podem estar vazios.Descrição precisar ser menor que 1000.");
		}
		
		if (request.getCaracteristicas().size() < 3)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Deve ser inserido pelo menos 3 caracteristicas!");

	}
}
