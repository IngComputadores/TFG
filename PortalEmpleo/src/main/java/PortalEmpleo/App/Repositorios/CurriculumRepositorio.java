package PortalEmpleo.App.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import PortalEmpleo.App.Entidades.Curriculum;

public interface CurriculumRepositorio extends JpaRepository<Curriculum, Long> {

	Curriculum save(Curriculum curriculum);

	Curriculum findById(long id);

	List<Curriculum> findAll();

	void deleteById(long id);

	void delete(Curriculum curriculum);
}
