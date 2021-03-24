package br.com.zup.treinomercadolivre.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotNull
	@Min(1)
	private BigDecimal valor;
	
	@NotNull
	@Min(1)
	private Integer quantidade;
	
	@OneToMany
	private List<Caracteristicas> caracteristicas;
	
	@NotNull
	private Long categoriaId;
	
	private LocalDateTime instante = LocalDateTime.now();
	
	
	public Produto(@NotEmpty String nome, @NotEmpty @Min(1) BigDecimal valor, @NotEmpty @Min(1) Integer quantidade,
			List<Caracteristicas> caracteristicas, @NotEmpty Long categoriaId) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.categoriaId = categoriaId;
	}
	
	
	
}
