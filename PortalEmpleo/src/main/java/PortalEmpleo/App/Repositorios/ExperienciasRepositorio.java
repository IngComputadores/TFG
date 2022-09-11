package PortalEmpleo.App.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PortalEmpleo.App.Entidades.Experiencias;

public interface ExperienciasRepositorio extends JpaRepository<Experiencias, Long> {

	Experiencias save(Experiencias experiencias);

	List<Experiencias> findAll();

	Experiencias findById(long id);

}
