package br.com.zup.treinomercadolivre.Usuario;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	UsuarioRepository UsuarioRepository;
	
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		UsuarioRepository = usuarioRepository;
	}


	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {
		
		Usuario usuario = usuarioForm.toModel();
		UsuarioRepository.save(usuario);
		return ResponseEntity.ok(1);
	}

}
