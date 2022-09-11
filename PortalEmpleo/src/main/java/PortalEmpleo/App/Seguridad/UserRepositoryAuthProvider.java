package PortalEmpleo.App.Seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import PortalEmpleo.App.Entidades.Usuario;
import PortalEmpleo.App.Servicios.UsuarioServicio;;

@Component
public class UserRepositoryAuthProvider implements AuthenticationProvider {

	@Autowired
	private UsuarioServicio usuarioRepository;

	// AUTENTIFICACION DE USUARIOS
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		String contraseya = (String) authentication.getCredentials();

		Usuario user = usuarioRepository.findByEmail(email);

		if (user == null) {
			System.out.println("Usuario incorrecto");
			throw new BadCredentialsException("Usuario  incorrecto");

		}
		if (!new BCryptPasswordEncoder().matches(contraseya, user.getContraseya())) {
			System.out.println("Contraseña incorrecta");
			throw new BadCredentialsException("Contraseña incorrecta");

		} else {

			List<GrantedAuthority> roles = new ArrayList<>();
			for (String role : user.getRoles()) {
				roles.add(new SimpleGrantedAuthority(role));
			}
			return new UsernamePasswordAuthenticationToken(user.getEmail(), contraseya, roles);

		}
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
