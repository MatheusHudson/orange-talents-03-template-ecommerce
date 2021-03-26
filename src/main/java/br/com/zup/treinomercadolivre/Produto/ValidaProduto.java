package br.com.zup.treinomercadolivre.Produto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaProduto {
	
	@PersistenceContext
	EntityManager em;
	
	public Produto devolveProdutoValido(Long id) {
		
		List<Produto> possivelProduto = em.createQuery("SELECT p FROM Produto p WHERE p.id = :pId").setParameter("pId", id).getResultList();
		if (possivelProduto.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id informado é invalido!");

		return possivelProduto.get(0);
		
	}
	

}
