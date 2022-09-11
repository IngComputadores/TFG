package PortalEmpleo.App.Servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import PortalEmpleo.App.Entidades.Usuario;
import PortalEmpleo.App.Repositorios.UsuarioRepositorio;

@Component
public class UsuarioServicio {

	@Autowired
	UsuarioRepositorio repositorio;

	public Usuario findById(long id) {
		return this.repositorio.findOne(id);
	}

	public Usuario findByEmail(String email) {
		return this.repositorio.findByEmail(email);
	}

	public List<Usuario> findAll() {
		return this.repositorio.findAll();
	}

	public Usuario findByEmailAndContraseya(String email, String contraseya, String rol) {
		return this.repositorio.findByEmailAndContraseya(email, contraseya, rol);
	}

	public List<Usuario> findByRoles(String rol) {
		return this.repositorio.findByRoles(rol);
	}

	public Usuario findByEmailAndRoles(String email, String roles) {
		return this.repositorio.findByEmailAndRoles(email, roles);
	}

	public Usuario deleteByEmail(String email) {
		return this.repositorio.deleteByEmail(email);
	}

	public Usuario deleteById(long id) {
		return this.repositorio.deleteById(id);
	}

	public void delete(Usuario usuario) {
		repositorio.delete(usuario);
	}

	public boolean exists(long id) {
		return this.repositorio.exists(id);
	}

	public boolean exists(String email) {
		return this.repositorio.findByEmail(email) != null;
	}

	public Usuario save(Usuario usuario) {
		return this.repositorio.save(usuario);
	}

}
