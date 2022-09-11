package PortalEmpleo.App.Controladores;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PortalEmpleo.App.Entidades.Anuncio;
import PortalEmpleo.App.Entidades.Candidato;
import PortalEmpleo.App.Entidades.Curriculum;
import PortalEmpleo.App.Entidades.Empresa;
import PortalEmpleo.App.Entidades.Experiencias;
import PortalEmpleo.App.Servicios.AnuncioServicio;
import PortalEmpleo.App.Servicios.CurriculumServicio;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
public class AnuncioController {

	@Autowired
	UsuarioServicio usuarioRepositorio;

	@Autowired
	AnuncioServicio anunciosRepositorio;

	@Autowired
	CurriculumServicio curriculumRepositorio;

	/************************************************************************************************************************
	 * PERFIL EMPRESA (ANUNCIOS)
	 ***************************************************************************************************************************/

	// INFORMACION DE LA EMPRESA
	@GetMapping("/infoEmpresa")
	public String infoEmpresa(Model model, HttpServletRequest request) {

		Empresa empresa = (Empresa) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		model.addAttribute("nombreEmpresa", empresa.getNombre());
		model.addAttribute("emailEmpresa", empresa.getEmail());

		model.addAttribute("telefonoEmpresa", empresa.getTelefonoEmpresa());
		model.addAttribute("sedeEmpresa", empresa.getSedeEmpresa());
		model.addAttribute("descripcionEmpresa", empresa.getDescripcionEmpresa());
		model.addAttribute("ayoEmpresa", empresa.getAyoFundo());

		model.addAttribute("infoEmpresaPerfilEmpresa", true);

		return "/infoEmpresa";
	}

	// ANUNCIOS PUBLICADOS POR LA EMPRESA
	@GetMapping("/anunciosPublicados")
	public String anunciosPublicados(Model model, HttpServletRequest request) {
		Empresa empresa = (Empresa) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		List<Anuncio> listaAnunciosPublicados = this.anunciosRepositorio.findByEmailEmpresa(empresa.getEmail());

		if (listaAnunciosPublicados.size() > 0) {
			model.addAttribute("listaAnunciosPublicados", listaAnunciosPublicados);
			model.addAttribute("hayAnunciosPublicados", true);
			model.addAttribute("editarAnuncio", true);
			return "anunciosPublicados";
		} else {
			model.addAttribute("noHayAnunciosPublicados", true);
			model.addAttribute("noEditarAnuncio", true);
			model.addAttribute("mensajeNoEditarAnuncio",
					"No se puede editar ningún anuncio por que todavía no has publicado ningun anuncio. Gracias.");
			return "anunciosPublicados";
		}
	}

	@GetMapping("/mis-anuncios-publicados")
	public String misAnunciosPublicados(Model model, HttpServletRequest request) {
		Empresa empresa = (Empresa) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		List<Anuncio> listaAnunciosPublicados = this.anunciosRepositorio.findByEmailEmpresa(empresa.getEmail());

		if (listaAnunciosPublicados.size() > 0) {
			model.addAttribute("listaAnunciosPublicados", listaAnunciosPublicados);
			model.addAttribute("hayAnunciosPublicados", true);
			model.addAttribute("candidatoApuntados", true);
			return "anunciosPublicados";
		} else {
			model.addAttribute("noHayAnunciosPublicados", true);
			return "anunciosPublicados";
		}
	}

	@GetMapping("/anuncioListaCandidatosApuntados")
	public String anuncioListaCandidatosApuntados(Model model, HttpServletRequest request) {
		return "anuncioListaCandidatosApuntados";

	}

	// NUMERO DE CANDIDATOS APUNTADOS
	@GetMapping("/NCandidatosApuntados")
	public String NCandidatosApuntados(Model model, HttpServletRequest request, @RequestParam String id) {

		long longID = Long.parseLong(id);
		Anuncio anuncio = this.anunciosRepositorio.findById(longID);
		model.addAttribute("numeroCandidatosApuntados", anuncio.getListaCandidatos().size());

		List<Candidato> listaCandidatosApuntadosAnuncio = anuncio.getListaCandidatos();

		Empresa empresa = (Empresa) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		model.addAttribute("nombreEmpresa", empresa.getNombre());

		if (listaCandidatosApuntadosAnuncio.size() > 0) {
			model.addAttribute("listaCandidatosApuntadosAnuncio", listaCandidatosApuntadosAnuncio);
			model.addAttribute("hayCandidatosApuntadosAnuncio", true);
			return "anuncioListaCandidatosApuntados";
		} else {
			model.addAttribute("noHayCandidatosApuntadosAnuncio", true);
			return "anuncioListaCandidatosApuntados";
		}
	}

	// CURRICULUM DEL CANDIDATO QUE SE HA APUNTADO AL ANUNCIO QUE HA ESCRITO LA EMPRESA
	@GetMapping("/infoCandidato")
	public String infoCandidato(Model model, HttpServletRequest request, @RequestParam String id) {
		long longID = Long.parseLong(id);
		Candidato candidato = (Candidato) this.usuarioRepositorio.findById(longID);

		Curriculum curriculum = this.curriculumRepositorio.findById(candidato.getCurriculum().getId());

		List<Experiencias> listaExperiencias = curriculum.getExperiencias();
		if (candidato.getHayCurriculum() == 1) {
			// comprobamos en el html si el campo esta rellenado
			model.addAttribute("curriculumEcho", true);
			model.addAttribute("cambiarPerfilVolver", true);

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
			model.addAttribute("curriculumNoEcho", true);
			return "curriculumCandidato";
		}
	}

	// FORMULARIO PARA CREAR ANUNCIO
	@GetMapping("/crear-anuncio")
	public String crearAnuncio(Model model, HttpServletRequest request) {
		model.addAttribute("mensajePublicarAnuncio",
				"Anuncio publicado correctamente. A continuación le redirigiremos a su pagina de perfil. Gracias");
		return "anuncioFormulario";
	}

	// RELLENAR LOS DATOS PARA CREAR EL ANUNCIO
	@RequestMapping("/rellenar-datos-anuncio")
	public String rellenarDtosAnuncio(Model model, HttpServletRequest request, @RequestParam String asunto,
			@RequestParam String fecha, @RequestParam String descripcionOferta) {

		Empresa empresa = (Empresa) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		Anuncio anuncio = new Anuncio(empresa, asunto, fecha, descripcionOferta);
		empresa.setHayAnuncios(1);
		this.anunciosRepositorio.save(anuncio);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/perfilEmpresa";
	}

	@GetMapping("/lista-anuncios")
	public String listaAnuncios(Model model, HttpServletRequest request) {

		return "redirect:/anunciosPublicados";
	}

	// FORMULARIO EDITAR ANUNCIO
	@GetMapping("/editar-anuncio")
	public String editarAnuncio(Model model, @RequestParam String id) {

		long longID = Long.parseLong(id);
		Anuncio anuncio = this.anunciosRepositorio.findById(longID);
		model.addAttribute("mostarContenidoAnuncio", true);
		model.addAttribute("descripcionAnuncio", anuncio.getDescripcionOferta());
		model.addAttribute("asuntoAnuncio", anuncio.getAsunto());
		model.addAttribute("fechaAnuncio", anuncio.getFecha());
		// model.addAttribute("fechaAnuncioToString", anuncio.getFecha().toString());

		model.addAttribute("id", id);

		model.addAttribute("mensajeEditarAnuncio",
				"Anuncio editado correctamente. A continuación le redirigiremos a la pagina para ver todos los anuncios publicados. Gracias.");

		return "anuncioFormularioEditar";

	}

	// RELLENAR LOS DATOS PARA EDITAR EL ANUNCIO
	@RequestMapping("/rellenar-anuncio-editado/{id}")
	public String editarDatosExperiencia(Model model, @RequestParam String asunto, @RequestParam String fecha,
			@RequestParam String descripcionOferta, @PathVariable String id) {

		long textoId;

		textoId = new Long(Long.parseLong(id));
		Anuncio anuncio = this.anunciosRepositorio.findById(textoId);
		anuncio.setAsunto(asunto);
		anuncio.setDescripcionOferta(descripcionOferta);
		anuncio.setFecha(fecha);
		this.anunciosRepositorio.save(anuncio);

		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/anunciosPublicados";

	}

	/************************************************************************************************************************
	 * PERFIL CANDIDATO (ANUNCIOS)
	 ***************************************************************************************************************************/

	//LOGIN
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logeado", true);
		} else {
			model.addAttribute("logeado", false);
		}
	}

	// ANUNCIOS PUBLICADO POR LAS EMPRESAS
	@GetMapping("/anunciosTablonPerfilCandidato")
	public String tablonAnuncio(Model model, HttpServletRequest request) {

		List<Anuncio> listaAnuncio = this.anunciosRepositorio.findAll();
		if (listaAnuncio.size() > 0) {
			model.addAttribute("listaAnunciosTablon", listaAnuncio);
			model.addAttribute("hayAnunciosTablon", true);
			model.addAttribute("ocultarInicio", true);
			model.addAttribute("otraformaVerEmpresa", true);
			return "/anunciosTablon";
		} else {
			model.addAttribute("noHayAnunciosTablon", true);
			model.addAttribute("ocultarInicio", true);
			return "/anunciosTablon";
		}
	}

	// MOSTRAR INFORMACION DE LA EMPRESA
	@GetMapping("/mostrar-empresa-perfilCandidato")
	public String mostrarEmpresa(Model model, @RequestParam String id) {
		long longID = Long.parseLong(id);
		Anuncio anuncio = this.anunciosRepositorio.findById(longID);
		Empresa empresa = (Empresa) this.usuarioRepositorio.findByEmail(anuncio.getEmailEmpresa());
		model.addAttribute("nombreEmpresa", empresa.getNombre());
		model.addAttribute("emailEmpresa", empresa.getEmail());

		model.addAttribute("telefonoEmpresa", empresa.getTelefonoEmpresa());
		model.addAttribute("sedeEmpresa", empresa.getSedeEmpresa());
		model.addAttribute("descripcionEmpresa", empresa.getDescripcionEmpresa());
		model.addAttribute("ayoEmpresa", empresa.getAyoFundo());

		model.addAttribute("mostrarPerfilCandidato", true);

		return "/infoEmpresa";
	}

	// ANUNCIOS A LOS QUE EL CANDIDATO SE HA APUNTADO
	@GetMapping("/anunciosApuntados")
	public String anunciosApuntados(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());

		model.addAttribute("nombreCandidato", candidato.getNombre());
		model.addAttribute("apellidoCandidato", candidato.getApellidos());
		model.addAttribute("numeroAnunciosApuntados", candidato.getListaAnuncios().size());

		List<Anuncio> listaAnunciosApuntados = candidato.getListaAnuncios();

		if (listaAnunciosApuntados.size() > 0) {
			model.addAttribute("listaAnunciosApuntados", listaAnunciosApuntados);
			model.addAttribute("hayAnunciosApuntados", true);
			return "anunciosApuntadosCandidato";
		} else {
			model.addAttribute("noHayAnunciosApuntados", true);
			return "anunciosApuntadosCandidato";
		}
	}

	// APUNTARSE A UN ANUNCIO
	@RequestMapping(value = "/apuntarseAnuncioCandidato")
	public String apuntarseAnuncioCandidato(Model model, @RequestParam String id, HttpServletRequest request) {
		long longID = Long.parseLong(id);

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		Anuncio anuncio = this.anunciosRepositorio.findById(longID);
		model.addAttribute("id", anuncio.getId());

		List<Anuncio> listaAnuncio = this.anunciosRepositorio.findAll();
		if (listaAnuncio.size() > 0) {
			model.addAttribute("listaAnunciosTablon", listaAnuncio);
			model.addAttribute("hayAnunciosTablon", true);
			model.addAttribute("ocultarInicio", true);
			model.addAttribute("otraformaVerEmpresa", true);
		} else {
			model.addAttribute("noHayAnunciosTablon", true);
			model.addAttribute("ocultarInicio", true);
		}

		if (anuncio.getApuntadoCandidato() == 0) {
			anuncio.setApuntadoCandidato(1);
			anuncio.getListaCandidatos().add(candidato);
			this.anunciosRepositorio.save(anuncio);
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			model.addAttribute("verCandidatoApuntado", true);
			model.addAttribute("mensajeCandidatoApuntado", "Te has apuntado correctamente al anuncio. Gracias");
			return "anunciosTablon";

		} else {
			if (anuncio.getListaCandidatos().contains(candidato) == false) {
				if (anuncio.getApuntadoCandidato() == 0) {
					anuncio.setApuntadoCandidato(1);
					anuncio.getListaCandidatos().add(candidato);
					this.anunciosRepositorio.save(anuncio);
					try {
						TimeUnit.SECONDS.sleep(4);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					model.addAttribute("ververCandidatoApuntado", true);
					model.addAttribute("mensajeCandidatoApuntado", "Te has apuntado correctamente al anuncio. Gracias");

					return "anunciosTablon";
				} else {
					anuncio.setApuntadoCandidato(anuncio.getApuntadoCandidato() + 1);
					anuncio.getListaCandidatos().add(candidato);
					this.anunciosRepositorio.save(anuncio);
					model.addAttribute("ververCandidatoApuntado", true);
					model.addAttribute("mensajeCandidatoApuntado", "Te has apuntado correctamente al anuncio. Gracias");

					try {
						TimeUnit.SECONDS.sleep(4);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return "anunciosTablon";
				}
			} else {
				model.addAttribute("CandidatoYaApuntado", true);
				model.addAttribute("mensajeCandidatoYaApuntado", "No puedes apuntarte al anuncio, ya estas apuntado.");

				return "anunciosTablon";
			}
		}

	}

}
