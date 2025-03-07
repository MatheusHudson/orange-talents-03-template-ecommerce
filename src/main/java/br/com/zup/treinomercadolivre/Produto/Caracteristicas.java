package br.com.zup.treinomercadolivre.Produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Caracteristicas {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Size(max = 1000)
	private String descricao;

	public Caracteristicas(String nome, @Size(max = 1000) String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	@Deprecated
	public Caracteristicas() {
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}


	@Override
	public String toString() {
		return "Caracteristicas{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				'}';
	}
}
