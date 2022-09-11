package PortalEmpleo.App.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PortalEmpleo.App.Entidades.Anuncio;

public interface AnuncioRepositorio extends JpaRepository<Anuncio, Long> {

	Anuncio save(Anuncio anuncio);

	Anuncio findById(long id);

	List<Anuncio> findByFecha(String fecha);

	List<Anuncio> findByEmailEmpresa(String emailEmpresa);

	List<Anuncio> findAll();

	void delete(long id);

	void delete(Anuncio anuncio);
}
