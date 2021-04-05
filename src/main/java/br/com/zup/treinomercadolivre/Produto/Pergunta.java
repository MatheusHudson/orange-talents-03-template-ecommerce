package br.com.zup.treinomercadolivre.Produto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.treinomercadolivre.Usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max=500)
	private String descricao;
	
	private LocalDateTime instante = LocalDateTime.now();
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario usuario;

	public Pergunta(@NotBlank String titulo, @NotBlank @Size(max = 500) String descricao, Produto produto,
			Usuario usuario) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	
	
}
