package br.com.zup.treinomercadolivre.Produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CaracteristicasResponse {


	@NotBlank
	private String nome;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	public CaracteristicasResponse(Caracteristicas caracteristicas) {

		this.nome = caracteristicas.getNome();
		this.descricao = caracteristicas.getDescricao();
	}

	@Deprecated
	public CaracteristicasResponse() {
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
