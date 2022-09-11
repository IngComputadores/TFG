package PortalEmpleo.App;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import PortalEmpleo.App.Entidades.Anuncio;
import PortalEmpleo.App.Entidades.Candidato;
import PortalEmpleo.App.Entidades.Empresa;
import PortalEmpleo.App.Entidades.Experiencias;
import PortalEmpleo.App.Entidades.Curriculum;
import PortalEmpleo.App.Servicios.AnuncioServicio;
import PortalEmpleo.App.Servicios.CurriculumServicio;
import PortalEmpleo.App.Servicios.ExperienciasServicio;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Component
public class DatabaseUsersLoader {

	@Autowired
	UsuarioServicio usuarioRepositorio;

	@Autowired
	CurriculumServicio curriculumRepositorio;

	@Autowired
	ExperienciasServicio experienciaRepositorio;

	@Autowired
	AnuncioServicio anuncioRepositorio;

	@PostConstruct
	public void init() {

		/*
		 * Empresa empresa1 = new Empresa("Telefonica", "telefonica@gmail.com","t",
		 * "123456789", "1996", "Madrid", " ",0, "ROLE_EMPRESA");
		 * this.usuarioRepositorio.save(empresa1);
		 * 
		 * Curriculum c1 = new Curriculum("no hay nada", "Español, Frances",
		 * "Universidad Rey Juan Carlos", "Ramiro de Maeztu",
		 * "Grado de Ingenieria de Computadores", "Cursos de ofimatica",
		 * "voluntario del Banco de alimentos", "no hay habilidades", "no hay aptitudes"
		 * ); this.curriculumRepositorio.save(c1);
		 * 
		 * Experiencias exp1 = new Experiencias(c1, "bbva 2014");
		 * this.experienciaRepositorio.save(exp1);
		 * 
		 * Experiencias exp2 = new Experiencias(c1, "bbva 2012");
		 * this.experienciaRepositorio.save(exp2);
		 * 
		 * Curriculum c2 = new Curriculum("no hay nada", "Español, ingles",
		 * "Universidad Rey Juan Carlos", "Ramiro de Maeztu",
		 * "Grado de Ingenieria de informatica", "Cursos de ofimatica",
		 * "voluntario del Banco de alimentos", "no hay habilidades", "no hay aptitudes"
		 * ); this.curriculumRepositorio.save(c2);
		 * 
		 * 
		 * Candidato cliente1 = new Candidato("Luis", "luis@gmail.com", "luis",
		 * "hernandez", c1, 1,1, 1,0, "ROLE_CANDIDATO");
		 * usuarioRepositorio.save(cliente1);
		 * 
		 * Candidato cliente2 = new Candidato("Miguel", "miguel@gmail.com", "miguel",
		 * "hernandez", c2, 1, 1, 0,0,"ROLE_CANDIDATO");
		 * usuarioRepositorio.save(cliente2);
		 * 
		 * Empresa empresa2 = new Empresa("Telefonicas", "telefonica1@gmail.com", "t",
		 * "123456788", "1996", "Barcelona", " ",0, "ROLE_EMPRESA");
		 * this.usuarioRepositorio.save(empresa2);
		 * 
		 * Empresa empresa3 = new Empresa("bbva", "bbva@gmail.com", "b", "123456782",
		 * "1996", "Sevilla", " ",0, "ROLE_EMPRESA");
		 * this.usuarioRepositorio.save(empresa3);
		 * 
		 * 
		 * Anuncio anuncio = new Anuncio(empresa1, "oferta de trabajo", "2022-11-06",
		 * "oferta dedicada recien universitarios");
		 * this.anuncioRepositorio.save(anuncio);
		 */
	}
}
