package br.com.zup.treinomercadolivre.Usuario;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import br.com.zup.treinomercadolivre.Validation.Password;


public class UsuarioForm {
	
	
	@NotEmpty
	@NotBlank
	@Email
	private String login;

	@Length(min=6)
	@Password
	private String senha;
	
	
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}


	public Usuario toModel() {
		
		return new Usuario(login, senha);
	}

}
