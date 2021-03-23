package br.com.zup.treinomercadolivre.Secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zup.treinomercadolivre.Usuario.UsuarioRepository;


@EnableWebSecurity
@Configuration
@Profile(value = {"prod", "test"})
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	
		private final UsuarioRepository usuarioRepository;
	
		
		private final AutenticacaoService autenticaoService;
		

		 private final TokenService tokenService;

		 @Autowired
		  public SecurityConfigurations( TokenService tokenService,
				   UsuarioRepository usuarioRepository,
				   AutenticacaoService autenticaoService
				) {
		    this.tokenService = tokenService;
		    this.usuarioRepository = usuarioRepository;
		    this.autenticaoService = autenticaoService;
		  }
		
		  
		@Override
		@Bean
		protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
		}
	
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
			auth.userDetailsService(autenticaoService).passwordEncoder(new BCryptPasswordEncoder());
		}
		
	
		//Configuracoes de Autorizações endpoints
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers(HttpMethod.POST ,"/usuarios").permitAll()
			.antMatchers(HttpMethod.POST ,"/auth").permitAll()
			.anyRequest().authenticated().and().csrf().disable().
			sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService,usuarioRepository), UsernamePasswordAuthenticationFilter.class);
			
			
		}
		
		//Configurações de recursos estaticos(js, css,imagens, etc.
		@Override
		public void configure(WebSecurity web) throws Exception {
		    web.ignoring()
		        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/h2-console/**");
		}
}
