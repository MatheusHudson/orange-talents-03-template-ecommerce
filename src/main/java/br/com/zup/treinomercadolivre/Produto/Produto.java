package br.com.zup.treinomercadolivre.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.treinomercadolivre.ImagemProduto.ImagemProduto;
import br.com.zup.treinomercadolivre.Usuario.Usuario;

@Entity
public class Produto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotNull
	@Min(1)
	private BigDecimal valor;
	
	@NotNull
	@Min(1)
	private Integer quantidade;
	//1
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Caracteristicas> caracteristicas;
	
	@NotNull
	private Long categoriaId;
	
	@NotNull
	private Long usuarioId;
	//1
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "produtoId")
	private Set<ImagemProduto> imagens;
	
	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Produto( ) {
		
	}
	
	public Produto(@NotEmpty String nome, @NotNull @Min(1) BigDecimal valor, @NotNull @Min(1) Integer quantidade,
			List<Caracteristicas> caracteristicas, @NotNull Long categoriaId, @NotNull Long usuarioId) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.categoriaId = categoriaId;
		this.usuarioId = usuarioId;

		System.out.println(caracteristicas.toString());
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public void associarLinks(Set<String> links) {
		
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(link, this)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}
    //1
	public boolean userIsValid(Usuario logado) {
		
		return usuarioId != logado.getId();
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public List<Caracteristicas> getCaracteristicas() {
		return caracteristicas;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public Long getId() {
		return id;
	}

	public boolean isValidQuantidade(Integer quantidade) {
		if(this.quantidade > 0 && this.quantidade > quantidade) {
			this.quantidade -= quantidade;
			return true;
		}
		return false;
	}

}
