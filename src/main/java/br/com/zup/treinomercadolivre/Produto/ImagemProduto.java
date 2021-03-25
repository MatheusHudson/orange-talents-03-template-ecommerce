package br.com.zup.treinomercadolivre.Produto;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class ImagemProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String fotos; 

	@ManyToOne
	@NotNull
	private Produto produtoId;
	
	@Deprecated
	public ImagemProduto() {
		
	}
	

	public ImagemProduto(String fotos, Produto produtoId) {
		this.fotos = fotos;
		this.produtoId = produtoId;
	}
	
	
	
	

}
