package PortalEmpleo.App.Controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import PortalEmpleo.App.Entidades.Candidato;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
@RequestMapping("/perfilCandidato")
public class PerfilCandidatoController {

	@Autowired
	UsuarioServicio usuarioRepositorio;

	// DATOS DE PERFIL CANDIDATO
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String perfilCandidato(Model model, HttpServletRequest request) {

		Candidato candidato = (Candidato) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		if (candidato != null) {
			model.addAttribute("nombrecandidato", " " + candidato.getNombre());
			model.addAttribute("emailcandidato", " " + candidato.getEmail());
			model.addAttribute("apellidoscandidato", " " + candidato.getApellidos());
			return "perfilCandidato";
		}
		return ("redirect:/loginError");
	}
}
