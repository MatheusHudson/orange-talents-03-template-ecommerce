package br.com.zup.treinomercadolivre.Produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Caracteristicas {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nome;
	
	
	private String descricao;
	
	
	
	public Long getId() {
		return id;
	}



	public String getNome() {
		return nome;
	}



	public String getDescricao() {
		return descricao;
	}



	public Caracteristicas(String nome, @Size(max = 1000) String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	
}
