package PortalEmpleo.App.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import PortalEmpleo.App.Entidades.Curriculum;
import PortalEmpleo.App.Repositorios.CurriculumRepositorio;

@Component
public class CurriculumServicio {

	@Autowired
	CurriculumRepositorio repositorio;

	public Curriculum save(Curriculum curriculum) {
		return this.repositorio.save(curriculum);
	}

	public Curriculum findById(long id) {
		return this.repositorio.findOne(id);
	}

	public List<Curriculum> findAll() {
		return this.repositorio.findAll();
	}

	public void deleteById(long id) {
		this.repositorio.deleteById(id);
	}

	public void delete(Curriculum curriculum) {
		this.repositorio.delete(curriculum);
	}

}
