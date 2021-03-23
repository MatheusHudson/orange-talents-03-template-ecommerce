package br.com.zup.treinomercadolivre.Secutiry;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zup.treinomercadolivre.Usuario.UsuarioRepository;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{

	private final TokenService tokenService;
	private final br.com.zup.treinomercadolivre.Usuario.UsuarioRepository repository;
	
	@Autowired
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token =  recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		
		if(valido) {
			autenticarCliente(token);
		}
		filterChain.doFilter(request, response);
		
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		br.com.zup.treinomercadolivre.Usuario.Usuario usuario = repository.findById(idUsuario).get();
		
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuperarToken(HttpServletRequest request) {
			
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
