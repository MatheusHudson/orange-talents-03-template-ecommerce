package br.com.zup.treinomercadolivre.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String username);
	
}
