package br.com.zup.treinomercadolivre.Produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.treinomercadolivre.Usuario.Usuario;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Max(5)
	@Min(1)
	private int nota;

	@NotBlank
	private String titulo;

	@NotBlank
	private String descricao;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public OpiniaoProduto() {
		
	}

	public OpiniaoProduto(@Max(5) @Min(1) int nota, @NotBlank String titulo, @NotBlank String descricao,
			@NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	
	

}
