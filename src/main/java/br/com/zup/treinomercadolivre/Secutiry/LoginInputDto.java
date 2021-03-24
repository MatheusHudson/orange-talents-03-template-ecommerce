package br.com.zup.treinomercadolivre.Secutiry;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDto {

	private String email;
	private String senha;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(email,
				senha);
	}
}
