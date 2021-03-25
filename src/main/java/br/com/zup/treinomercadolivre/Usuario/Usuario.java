package br.com.zup.treinomercadolivre.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario implements UserDetails {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Length(min=6)
	private String senha;
	

	@PastOrPresent
	private LocalDateTime instante = LocalDateTime.now();
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	

	@Deprecated
	public Usuario() {
		
	}
	
	
	public Usuario(@NotBlank @Email String email, @Length(min = 6) String senha) {
		this.email = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}



	public Long getId() {
		return id;
	}



	public String getEmail() {
		return email;
	}



	public String getSenha() {
		return senha;
	}



	public List<Perfil> getPerfis() {
		return perfis;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		
		return this.perfis;
	}

	@Override
	public String getPassword() {
		
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	
}
