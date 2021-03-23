package br.com.zup.treinomercadolivre.Categoria;

import javax.validation.constraints.NotEmpty;

import br.com.zup.treinomercadolivre.Validation.UniqueValue;

public class CategoriaRequest {

	
	@NotEmpty
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	private Long categoriaMae;
	
	

	public String getNome() {
		return nome;
	}


	public Long getCategoriaMae() {
		return categoriaMae;
	}



	public Categoria toModel() {
		
		return new Categoria(nome, categoriaMae);
	}
	
	

}

