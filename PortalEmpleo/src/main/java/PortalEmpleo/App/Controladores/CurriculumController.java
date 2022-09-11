package PortalEmpleo.App.Controladores;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PortalEmpleo.App.Entidades.Candidato;
import PortalEmpleo.App.Entidades.Curriculum;
import PortalEmpleo.App.Entidades.Experiencias;
import PortalEmpleo.App.Servicios.CurriculumServicio;
import PortalEmpleo.App.Servicios.ExperienciasServicio;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
public class CurriculumController {

	@Autowired
	UsuarioServicio usuarioRepositorio;

	@Autowired
	CurriculumServicio curriculumRepositorio;

	@Autowired
	ExperienciasServicio experienciasRepositorio;

	/********************************************************************************************************************
	 * CREAR CURRICULUM
	 ********************************************************************************************************************/

	// PAGINA WEB PASO PREVIO PARA CREAR CURRICULUM
	@GetMapping("/curriculumPasoPrevio")
	public String curriculumPasoPrevio(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		model.addAttribute("mensajeCrearCurriculum",
				"Curriculum creado correctamente. A continuación te redirigiremos a la pagina de perfil. Gracias.");
		if (candidato.getDatosRellenados() == 1) {
			model.addAttribute("desaparecerboton", true);
			return "curriculumPasoPrevio";
		} else {
			model.addAttribute("norellenadoDatos", true);
			return "curriculumPasoPrevio";
		}
	}

	// PAGINA WEB DONDE VER EL CURRICULUM
	@GetMapping("/curriculumCandidato")
	public String curriculumCandidato(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		Curriculum curriculum = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		List<Experiencias> listaExperiencias = curriculum.getExperiencias();

		// Curriculum rellenado
		if (candidato.getHayCurriculum() == 1) {
			// comprobamos en el html si el campo esta rellenado
			model.addAttribute("curriculumEcho", true);

			model.addAttribute("mostrarTodo", true);

			// comprobamos se curriculum esta creado
			model.addAttribute("curriculumYaCreado", true);
			model.addAttribute("mensajeCurriculumYaCreado", "Curriculum ya rellenado y creado. Gracias.");

			// datos personales
			model.addAttribute("nombreCandidato", candidato.getNombre());
			model.addAttribute("apellidosCandidato", candidato.getApellidos());
			model.addAttribute("emailCandidato", candidato.getEmail());

			model.addAttribute("curriculumSobreMi", curriculum.getPerfil());
			model.addAttribute("curriculumId", curriculum.getId());
			model.addAttribute("curriculumIdiomas", curriculum.getIdiomas());
			model.addAttribute("curriculumHabilidades", curriculum.getHabilidades());
			model.addAttribute("curriculumAptitudes", curriculum.getAptitudes());
			model.addAttribute("curriculumUniversidad", curriculum.getEducacionUniversitaria());
			model.addAttribute("curriculumSecundaria", curriculum.getEduacionSecundaria());
			model.addAttribute("curriculumGrado", curriculum.getGrado());
			model.addAttribute("curriculumCursos", curriculum.getCursos());
			model.addAttribute("curriculumVoluntariado", curriculum.getVoluntariados());

			if (listaExperiencias.size() > 0) {
				model.addAttribute("listaLasExperiencias", listaExperiencias);
			} else {
				model.addAttribute("noRellenadaExperiencias", true);
			}

			return "curriculumCandidato";
		} else {
			model.addAttribute("curriculumNoEditar", true);
			model.addAttribute("mensajeCurriculumNoEditar", "Curriculum no creado. No puedes editar el curriculum.");
			model.addAttribute("mostrarTodo", true);

			model.addAttribute("curriculumNoEcho", true);

			return "curriculumCandidato";
		}
	}

	// VER EL CURRICULUM DEL CANDIDATO (BOTON MI CURRICULUM)
	@GetMapping("/mi-curriculum")
	public String verCurriculum(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		Curriculum curriculum = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		List<Experiencias> listaExperiencias = curriculum.getExperiencias();

		// Curriculum hecho
		if (candidato.getHayCurriculum() == 1) {

			model.addAttribute("curriculumEcho", true);

			model.addAttribute("mostrarTodo2", true);

			// datos personales
			model.addAttribute("nombreCandidato", candidato.getNombre());
			model.addAttribute("apellidosCandidato", candidato.getApellidos());
			model.addAttribute("emailCandidato", candidato.getEmail());

			model.addAttribute("curriculumSobreMi", curriculum.getPerfil());
			model.addAttribute("curriculumId", curriculum.getId());
			model.addAttribute("curriculumIdiomas", curriculum.getIdiomas());
			model.addAttribute("curriculumHabilidades", curriculum.getHabilidades());
			model.addAttribute("curriculumAptitudes", curriculum.getAptitudes());
			model.addAttribute("curriculumUniversidad", curriculum.getEducacionUniversitaria());
			model.addAttribute("curriculumSecundaria", curriculum.getEduacionSecundaria());
			model.addAttribute("curriculumGrado", curriculum.getGrado());
			model.addAttribute("curriculumCursos", curriculum.getCursos());
			model.addAttribute("curriculumVoluntariado", curriculum.getVoluntariados());

			if (listaExperiencias.size() > 0) {
				model.addAttribute("listaLasExperiencias", listaExperiencias);
			} else {
				model.addAttribute("noRellenadaExperiencias", true);
			}

			return "curriculumCandidato";
		} else {
			model.addAttribute("mostrarTodo2", true);
			model.addAttribute("curriculumNoEcho", true);
			return "curriculumCandidato";
		}
	}

	// PASO PREVIO PARA CREAR CURRICULUM
	@GetMapping("/pasoPrevio")
	public String pasoPrevio(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		// Curriculum rellenado
		if (candidato.getHayCurriculum() == 1) {

			return "redirect:/curriculumCandidato";
		} else {
			return "redirect:/curriculumPasoPrevio";
		}
	}

	// CREAR CURRICULUM
	@GetMapping("/crear-curriculum")
	public String crearCurriculum(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		model.addAttribute("nombrecandidato", candidato.getNombre());
		model.addAttribute("apellidoscandidato", candidato.getApellidos());
		model.addAttribute("emailcandidato", candidato.getEmail());

		Curriculum c1 = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		// Rellenado los datos del curriculum
		if (candidato.getDatosRellenados() == 1) {
			c1.setAptitudes(c1.getAptitudes());
			c1.setCursos(c1.getCursos());
			c1.setEduacionSecundaria(c1.getEduacionSecundaria());
			c1.setEducacionUniversitaria(c1.getEducacionUniversitaria());
			c1.setGrado(c1.getGrado());
			c1.setHabilidades(c1.getHabilidades());
			c1.setIdiomas(c1.getIdiomas());
			c1.setPerfil(c1.getPerfil());
			c1.setVoluntariados(c1.getVoluntariados());
		} else {
			// Crear curriculum vacio
			c1.setAptitudes(" ");
			c1.setCursos(" ");
			c1.setEduacionSecundaria(" ");
			c1.setEducacionUniversitaria(" ");
			c1.setGrado(" ");
			c1.setHabilidades(" ");
			c1.setIdiomas(" ");
			c1.setPerfil(" ");
			c1.setVoluntariados(" ");
			candidato.setDatosRellenados(1);
		}

		candidato.setHayCurriculum(1);
		candidato.setCreadoCurriculum(1);
		this.curriculumRepositorio.save(c1);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/perfilCandidato";
	}

	// FORMULARIO RELLENAR DATOS DEL CURRICULUM
	@GetMapping("/rellenar-datos")
	public String rellenarDatos(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		if (candidato.getHayCurriculum() == 1) {
			return "redirect:/curriculumCandidato";
		} else {
			model.addAttribute("mostrarBotonGuardar", true);
			model.addAttribute("mensajeGuardaDatosCurriculum",
					"Datos del curriculum guardados correctamente. A continuación le redirigiremos a la pagina anterior para que pueda seguir \r\n"
							+ "						            rellenado los datos de su curriculum. Gracias.");
			return "curriculumFormulario";
		}
	}

	// RELLENAR DATOS DEL CURRICULUM EN EL FORMULARIO
	@RequestMapping("/rellenar-datos-curriculum")
	public String introducirDatosCuriculum(Model model, HttpServletRequest request, @RequestParam String perfil,
			@RequestParam String idiomas, @RequestParam String educacionUniversitaria,
			@RequestParam String eduacionSecundaria, @RequestParam String grado, @RequestParam String cursos,
			@RequestParam String voluntariado, @RequestParam String habilidades, @RequestParam String aptitudes) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		Curriculum c1 = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		c1.setPerfil(perfil);
		c1.setIdiomas(idiomas);
		c1.setEducacionUniversitaria(educacionUniversitaria);
		c1.setEduacionSecundaria(eduacionSecundaria);
		c1.setGrado(grado);
		c1.setCursos(cursos);
		c1.setVoluntariados(voluntariado);
		c1.setHabilidades(habilidades);
		c1.setAptitudes(aptitudes);

		candidato.setDatosRellenados(1);
		candidato.setHayCurriculum(1);
		candidato.setCreadoCurriculum(1);

		this.curriculumRepositorio.save(c1);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/curriculumPasoPrevio";
	}

	/********************************************************************************************************************
	 * EDITAR CURRICULUM
	 ********************************************************************************************************************/

	// PAGINA WEB EDITAR PASO PREVIO
	@GetMapping("/curriculumPasoPrevioEditar")
	public String curriculumPasoPrevioEditar(Model model, HttpServletRequest request) {
		model.addAttribute("mensajeCurriculumGuardarDatosEditados",
				"Curriculum editado y guardado correctamente. A continuación te redirigiremos a la pagina de perfil. Gracias.");
		
		return "curriculumPasoPrevioEditar";
	}

	// EDITAR PASO PREVIO
	@GetMapping("/pasoPrevioEditar")
	public String pasoPrevioEditar(Model model, HttpServletRequest request) {
		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		// Hay curriculum
		if (candidato.getHayCurriculum() == 1) {
			return "redirect:/curriculumPasoPrevioEditar";
		} else {
			return "redirect:/curriculumCandidato";
		}
	}

	// GUARDAR EL CURRICULUM YA EDITADO
	@GetMapping("/guardar-editar-curriculum")
	public String guardarEditarCurriculum(Model model, HttpServletRequest request) {
		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		model.addAttribute("nombrecandidato", candidato.getNombre());
		model.addAttribute("apellidoscandidato", candidato.getApellidos());
		model.addAttribute("emailcandidato", candidato.getEmail());

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/perfilCandidato";
	}

	// FORMULARIO DONDE EDITAR DATOS
	@GetMapping("/editar-datos")
	public String editarDatos(Model model, HttpServletRequest request) {
		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		Curriculum c1 = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		if (candidato.getHayCurriculum() == 1) {
			model.addAttribute("mostarContenidoCurriculum", true);

			model.addAttribute("curriculumSobreMi", c1.getPerfil());
			model.addAttribute("curriculumId", c1.getId());
			model.addAttribute("curriculumIdiomas", c1.getIdiomas());
			model.addAttribute("curriculumHabilidades", c1.getHabilidades());
			model.addAttribute("curriculumAptitudes", c1.getAptitudes());
			model.addAttribute("curriculumUniversidad", c1.getEducacionUniversitaria());
			model.addAttribute("curriculumSecundaria", c1.getEduacionSecundaria());
			model.addAttribute("curriculumGrado", c1.getGrado());
			model.addAttribute("curriculumCursos", c1.getCursos());
			model.addAttribute("curriculumVoluntariado", c1.getVoluntariados());

		}

		model.addAttribute("mostrarBotonEditar", true);
		model.addAttribute("mensajeCurriculumEditar", "Datos editados del curriculum guardados correctamente.\r\n"
				+ "						            A continuación le redirigiremos al pagina anterior para que siga editando curriculum. Gracias.");
		return "curriculumFormularioEditar";
	}

	// EDITAR LOS DATOS DEL CURRICULIM
	@RequestMapping("/editar-datos-curriculum")
	public String editarDatosCurriculum(Model model, HttpServletRequest request, @RequestParam String perfil,
			@RequestParam String idiomas, @RequestParam String educacionUniversitaria,
			@RequestParam String eduacionSecundaria, @RequestParam String grado, @RequestParam String cursos,
			@RequestParam String voluntariado, @RequestParam String habilidades, @RequestParam String aptitudes) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		Curriculum c1 = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		c1.setPerfil(perfil);
		c1.setIdiomas(idiomas);
		c1.setEducacionUniversitaria(educacionUniversitaria);
		c1.setEduacionSecundaria(eduacionSecundaria);
		c1.setGrado(grado);
		c1.setCursos(cursos);
		c1.setVoluntariados(voluntariado);
		c1.setHabilidades(habilidades);
		c1.setAptitudes(aptitudes);

		candidato.setDatosRellenados(1);
		candidato.setHayCurriculum(1);
		candidato.setCreadoCurriculum(1);
		candidato.setEditadoDatosCurriculum(1);

		
		this.curriculumRepositorio.save(c1);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/curriculumPasoPrevioEditar";
	}

}
