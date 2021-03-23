package br.com.zup.treinomercadolivre.Secutiry;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.treinomercadolivre.Usuario.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<br.com.zup.treinomercadolivre.Usuario.Usuario> usuario = usuarioRepository.findByEmail(username);
		if(usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new UsernameNotFoundException("Dados invalidos!");
		}
		
	}

	
}
