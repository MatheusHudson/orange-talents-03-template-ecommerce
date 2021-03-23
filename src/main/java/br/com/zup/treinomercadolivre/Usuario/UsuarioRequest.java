package br.com.zup.treinomercadolivre.Usuario;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import br.com.zup.treinomercadolivre.Validation.Password;
import br.com.zup.treinomercadolivre.Validation.UniqueValue;


public class UsuarioRequest {
	
	
	@NotEmpty
	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;

	@Length(min=6)
	@Password
	private String senha;
	
	
	

	public String getEmail() {
		return email;
	}



	public String getSenha() {
		return senha;
	}



	public Usuario toModel() {
		
		return new Usuario(email, senha);
	}

}
