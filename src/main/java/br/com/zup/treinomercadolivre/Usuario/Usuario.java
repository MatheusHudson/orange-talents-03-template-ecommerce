package br.com.zup.treinomercadolivre.Usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.treinomercadolivre.Validation.Password;

@Entity
public class Usuario  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	@Column(nullable = false)
	private String login;

	@Length(min=6)
	private String senha;
	

	@PastOrPresent
	private LocalDateTime instante = LocalDateTime.now();


	public Usuario(@NotBlank @Email String login, @Length(min = 6) String senha) {
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}


	
}
