package br.com.zup.treinomercadolivre.ImagemProduto;

import javax.validation.constraints.NotBlank;


public class ImagemResponse {

	@NotBlank
	private String fotos;

	@Deprecated
	public ImagemResponse() {

	}

	public ImagemResponse(ImagemProduto imagemProduto) {
		this.fotos = imagemProduto.getFotos();

	}

	public String getFotos() {
		return fotos;
	}
}
