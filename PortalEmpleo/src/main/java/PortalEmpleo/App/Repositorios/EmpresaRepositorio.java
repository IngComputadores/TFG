package PortalEmpleo.App.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import PortalEmpleo.App.Entidades.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {

	Empresa findByTelefonoEmpresa(String telefonoEmpresa);

}
