package br.com.zup.treinomercadolivre.Pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.treinomercadolivre.Produto.Produto;
import br.com.zup.treinomercadolivre.Usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max=500)
	private String titulo;

	private LocalDateTime instante = LocalDateTime.now();
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario usuario;

	public Pergunta(@NotBlank String titulo, Produto produto,
			Usuario usuario) {
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	@Deprecated
	public Pergunta() {
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}


	public Produto getProduto() {
		return produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	
	
}
