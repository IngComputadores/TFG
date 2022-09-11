package PortalEmpleo.App.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] staticResources = { "/css/**", "/js/**", "/assets/**", };

		// PAGINAS PUBLICAS
		http.authorizeRequests().antMatchers(staticResources).permitAll();
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/registro").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/login_Error").permitAll();
		http.authorizeRequests().antMatchers("/registroCandidato").permitAll();
		http.authorizeRequests().antMatchers("/nuevoCandidato").permitAll();
		http.authorizeRequests().antMatchers("/registroCandidatoError").permitAll();
		http.authorizeRequests().antMatchers("/registroEmpresa").permitAll();
		http.authorizeRequests().antMatchers("/nuevaEmpresa").permitAll();
		http.authorizeRequests().antMatchers("/registroEmpresaError").permitAll();
		http.authorizeRequests().antMatchers("/anunciosTablon").permitAll();
		http.authorizeRequests().antMatchers("/lista_empresas_registradas").permitAll();
		http.authorizeRequests().antMatchers("/mostrar-empresa").permitAll();

		// PAGINAS PRIVADAS
		http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/perfilCandidato").hasAnyRole("CANDIDATO");
		http.authorizeRequests().antMatchers("/perfilEmpresa").hasAnyRole("EMPRESA");

		// LOGIN
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("contraseya");
		http.formLogin().defaultSuccessUrl("/perfil", true);
		http.formLogin().failureUrl("/login_Error");

		// LOGOUT
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		// DISABLE CSRF
		// http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);

	}

}
