package br.com.zup.treinomercadolivre.Secutiry;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.treinomercadolivre.Usuario.Usuario;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

	@Override
	public UserDetails map(Object shouldBeASystemUser) {						
		return (Usuario) shouldBeASystemUser;
	}

}