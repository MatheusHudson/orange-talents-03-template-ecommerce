package br.com.zup.treinomercadolivre.Produto;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ImagemProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String fotos; 

	private Long produtoId;
	
	

	public ImagemProduto(String fotos, Long produtoId) {
		this.fotos = fotos;
		this.produtoId = produtoId;
	}
	
	
	
	

}
