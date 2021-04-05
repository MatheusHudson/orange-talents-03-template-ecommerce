package br.com.zup.treinomercadolivre.ImagemProduto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;


public class ImagemRequest {

	@Size(min=1)
	@NotNull
	private List<MultipartFile> fotos = new ArrayList<>();


	public List<MultipartFile> getFotos() {
		return fotos;
	}


	public void setFotos(List<MultipartFile> fotos) {
		this.fotos = fotos;
	}


	
	
}
