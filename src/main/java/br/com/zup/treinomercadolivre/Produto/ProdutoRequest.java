package br.com.zup.treinomercadolivre.Produto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.treinomercadolivre.Categoria.Categoria;
import br.com.zup.treinomercadolivre.Validation.IdIsPresent;

public class ProdutoRequest {

	@NotEmpty
	private String nome;

	@NotNull
	@Min(1)
	private BigDecimal valor;

	@NotNull
	@Min(1)
	private Integer quantidade;
	
	
	private List<Caracteristicas> caracteristicas;

	@NotNull
	@IdIsPresent(domainClass = Categoria.class)
	private Long categoriaId;

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public List<Caracteristicas> getCaracteristicas() {
		return caracteristicas;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public Produto toModel() {
		return new Produto(nome, valor, quantidade, caracteristicas, categoriaId);
	}

	public void  saveCaracteristicas(EntityManager em) {
		for (Caracteristicas caract : caracteristicas) {		
			em.persist(caract);
		}
	}
}
