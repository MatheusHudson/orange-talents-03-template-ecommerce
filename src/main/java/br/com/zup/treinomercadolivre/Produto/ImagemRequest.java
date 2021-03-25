package br.com.zup.treinomercadolivre.Produto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.treinomercadolivre.Validation.IdIsPresent;

public class ImagemRequest {

	@Size(min=1)
	private List<String> fotos; 
	
	@NotNull
	@IdIsPresent(domainClass = Produto.class)
	private Long produtoId;

	public List<String> getFotos() {
		return fotos;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public List<ImagemProduto> toModel() {
			
		List<ImagemProduto> imagens = fotos.stream().map(x -> new ImagemProduto(x, 1L)).collect(Collectors.toList());
		return imagens;
	}
	
	
	
	
}
