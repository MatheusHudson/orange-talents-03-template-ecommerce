package br.com.zup.treinomercadolivre.Categoria;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(unique = true, nullable = false)
	private String nome;

	private Long categoriaMae;

	@Deprecated
	public Categoria() {
		
	}
	
	public Categoria(@NotEmpty String nome, Long categoriaMae) {
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Long getCategoriaMae() {
		return categoriaMae;
	}


	
}
