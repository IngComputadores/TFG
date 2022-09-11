package PortalEmpleo.App.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import PortalEmpleo.App.Entidades.Anuncio;
import PortalEmpleo.App.Repositorios.AnuncioRepositorio;

@Component
public class AnuncioServicio {

	@Autowired
	AnuncioRepositorio repositorio;

	public Anuncio save(Anuncio anuncio) {
		return this.repositorio.save(anuncio);
	}

	public Anuncio findById(long id) {
		return this.repositorio.findOne(id);
	}

	public List<Anuncio> findByFecha(String fecha) {
		return this.repositorio.findByFecha(fecha);
	}

	public List<Anuncio> findByEmailEmpresa(String emailEmpresa) {
		return this.repositorio.findByEmailEmpresa(emailEmpresa);
	}

	public List<Anuncio> findAll() {
		return this.repositorio.findAll();
	}

	void delete(long id) {
		this.repositorio.delete(id);
	}

	void delete(Anuncio anuncio) {
		this.repositorio.delete(anuncio);
	}

}
