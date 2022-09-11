package PortalEmpleo.App.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import PortalEmpleo.App.Entidades.Empresa;
import PortalEmpleo.App.Repositorios.EmpresaRepositorio;

@Component
public class EmpresaServicio {

	@Autowired
	EmpresaRepositorio repositorioE;

	public Empresa findByTelefonoEmpresa(String telefonoEmpresa) {
		return this.repositorioE.findByTelefonoEmpresa(telefonoEmpresa);
	}

}
