package PortalEmpleo.App.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import PortalEmpleo.App.Entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	Usuario findById(long id);

	Usuario findByEmail(String email);

	List<Usuario> findAll();

	Usuario findByEmailAndContraseya(String email, String contraseya, String rol);

	List<Usuario> findByRoles(String rol);

	Usuario findByEmailAndRoles(String email, String roles);

	Usuario deleteByEmail(String email);

	Usuario deleteById(long id);

	Usuario save(Usuario usuario);

}
