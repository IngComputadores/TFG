package PortalEmpleo.App.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import PortalEmpleo.App.Entidades.Experiencias;
import PortalEmpleo.App.Repositorios.ExperienciasRepositorio;

@Component
public class ExperienciasServicio {

	@Autowired
	ExperienciasRepositorio repository;

	public Experiencias save(Experiencias experiencias) {
		return this.repository.save(experiencias);
	}

	public List<Experiencias> findAll() {
		return this.repository.findAll();
	}

	public Experiencias findById(long id) {
		return repository.findOne(id);
	}

	public Experiencias delete(long id) {
		this.repository.delete(id);
		return null;
	}

	public void delete(Experiencias experiencias) {
		this.repository.delete(experiencias);
	}

}
