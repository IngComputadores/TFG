package PortalEmpleo.App.Controladores;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PortalEmpleo.App.Entidades.Candidato;
import PortalEmpleo.App.Entidades.Curriculum;
import PortalEmpleo.App.Entidades.Experiencias;
import PortalEmpleo.App.Servicios.CurriculumServicio;
import PortalEmpleo.App.Servicios.ExperienciasServicio;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
public class ExperienciaController {

	@Autowired
	UsuarioServicio usuarioRepositorio;

	@Autowired
	CurriculumServicio curriculumRepositorio;

	@Autowired
	ExperienciasServicio experienciasRepositorio;

	/*******************************************************************************************************
	 * AÑADIR EXPERIENCIAS AL CURRICULUM
	 *******************************************************************************************************/

	// FORMULARIO PARA RELLENAR LA EXPERIENCIA
	@GetMapping("/añadir-experiencia")
	public String añadirExperiencia(Model model, HttpServletRequest request) {
		model.addAttribute("añadirExperiencia", true);
		model.addAttribute("mensajeAñadirExperiencia",
				"Experiencia guardada correctamente. A continuación le redirigiremos al curriculum para que siga rellenando. Gracias.");
		return "experienciaFormulario";
	}

	// RELLENAR LOS DATOS DE LA EXPERIENCIA (CREAR CURRICULUM)
	@RequestMapping("/rellenar-datos-experiencia")
	public String rellenarDatosExperiencia(Model model, HttpServletRequest request,
			@RequestParam String descripcionExperiencia) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		Curriculum curriculum = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		Experiencias experiencia = new Experiencias(curriculum, descripcionExperiencia);
		candidato.setAñadidaExperiencia(1);

		this.experienciasRepositorio.save(experiencia);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/curriculumPasoPrevio";
	}

	/*******************************************************************************************************
	 * EDITAR EXPERIENCIAS DEL CURRICULUM
	 *******************************************************************************************************/

	// EXPERIENCIA PASO PREVIO
	@GetMapping("/experienciaPasoPrevio")
	public String experienciaPasoPrevio(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		model.addAttribute("mensajeAtrasSeguirEditandoCurriculum",
				"A continuación le redirigiremos a la página de editar el curriculum para que sigas editandolo. Gracias.");

		if (candidato.getAñadidaExperiencia() == 1) {
			model.addAttribute("mostrarEdicionExperiencia", true);
			return "experienciaPasoPrevio";
		} else {
			model.addAttribute("noMostrarEdicionExperiencia", true);
			return "experienciaPasoPrevio";

		}
	}

	// EXPERIENCIA PASO PREVIO EDITAR
	@GetMapping("/pasoPrevio-editar-experiencia")
	public String editarExperiencia(Model model, HttpServletRequest request) {

		model.addAttribute("prueba", true);
		return "redirect:/experienciaPasoPrevio";
	}

	// VOLVER A ATRAS PARA SEGUIR EDITANDO LOS DATOS DEL CURRIUCLUM O GUARDAR EL
	// CURRICULUM
	@GetMapping("/atras")
	public String atras(Model model, HttpServletRequest reques) {
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/curriculumPasoPrevioEditar";
	}

	// FORMULARIO PARA AÑADIR EXPERIENCIA NUEVA AL CURRICULUM UNA VEZ QUE EL CURRICULUM YA ESTA CREADO
	@GetMapping("/añadir-experiencia-edicion")
	public String añadirExperienciaEdicion(Model model, HttpServletRequest request) {
		model.addAttribute("añadirExperienciaEdicion", true);
		model.addAttribute("mensajeAñadirExperienciaEditar",
				"Experiencia guarda corectamente. A continuación te redirigiremos a la pagina anterior para decidir si quiere editar \r\n"
						+ "						            o añadir mas experiencias a su curriculum. Gracias.");
		return "experienciaFormulario";
	}

	// RELLENAR LOS DATOS DE LA EXPERIENCIA (EDITAR CURRICULUM)
	@RequestMapping("/rellenar-datos-experiencia-edicion")
	public String rellenarDatosExperienciaEdicion(Model model, HttpServletRequest request,
			@RequestParam String descripcionExperiencia) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		Curriculum curriculum = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		Experiencias experiencia = new Experiencias(curriculum, descripcionExperiencia);
		candidato.setAñadidaExperiencia(1);

		this.experienciasRepositorio.save(experiencia);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/experienciaPasoPrevio";
	}

	// MOSTRAS LAS EXPERIENCIAS DEL CURRICULUM
	@GetMapping("/lista-experiencias")
	public String listaExperiencias(Model model, HttpServletRequest request) {
		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		Curriculum c1 = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());
		List<Experiencias> listaExperiencias = c1.getExperiencias();

		if (listaExperiencias.size() > 0) {
			model.addAttribute("listaExperiencia", listaExperiencias);
		}
		return "experienciasTablaCurriculum";

	}

	// FORMULARIO DONDE SE VA HA EDITAR LA EXPERIENCIA
	@GetMapping("/editar-experiencias")
	public String editarExperiencias(Model model, @RequestParam String id) {

		long longID = Long.parseLong(id);
		Experiencias experiencia = this.experienciasRepositorio.findById(longID);
		model.addAttribute("mostarContenidoExperiencia", true);
		model.addAttribute("descripcion", experiencia.getDescripcionExperiencia());
		model.addAttribute("id", id);

		model.addAttribute("mensajeEditarExperiencia",
				"Experiencia editada correctamente. A continuación le redirigiremos a la pagina anterior"
						+ "para que decida si quiere volver a editar una experiencia o añadir a su curriculum alguna experiencia nueva. Gracias");

		return "experienciaFormularioEditar";
	}

	// EDITAR LA EXPERIENCIA
	@RequestMapping("/editar-datos-experiencia/{id}")
	public String editarDatosExperiencia(Model model, @RequestParam String descripcionExperiencia,
			@PathVariable String id) {

		long textoId;

		textoId = new Long(Long.parseLong(id));
		Experiencias experiencia = this.experienciasRepositorio.findById(textoId);
		experiencia.setDescripcionExperiencia(descripcionExperiencia);
		this.experienciasRepositorio.save(experiencia);

		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/experienciaPasoPrevio";

	}

}
