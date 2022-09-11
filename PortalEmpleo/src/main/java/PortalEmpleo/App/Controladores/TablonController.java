package PortalEmpleo.App.Controladores;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import PortalEmpleo.App.Entidades.Anuncio;
import PortalEmpleo.App.Entidades.Empresa;
import PortalEmpleo.App.Servicios.AnuncioServicio;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
public class TablonController {

	@Autowired
	AnuncioServicio anunciosRepositorio;

	@Autowired
	UsuarioServicio usuarioRepositrorio;

	// MOSTRAR INFORMACION DE LA EMPRESA (TABLON ANUNCIO INDEX)
	@GetMapping("/mostrar-empresa")
	public String mostrarEmpresa(Model model, @RequestParam String id) {
		long longID = Long.parseLong(id);
		Anuncio anuncio = this.anunciosRepositorio.findById(longID);
		Empresa empresa = (Empresa) this.usuarioRepositrorio.findByEmail(anuncio.getEmailEmpresa());
		model.addAttribute("nombreEmpresa", empresa.getNombre());
		model.addAttribute("emailEmpresa", empresa.getEmail());

		model.addAttribute("telefonoEmpresa", empresa.getTelefonoEmpresa());
		model.addAttribute("sedeEmpresa", empresa.getSedeEmpresa());
		model.addAttribute("descripcionEmpresa", empresa.getDescripcionEmpresa());
		model.addAttribute("ayoEmpresa", empresa.getAyoFundo());

		model.addAttribute("infoTablonAnuncios", true);

		return "/infoEmpresa";
	}

	// LOGIN
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logeado", true);
		} else {
			model.addAttribute("logeado", false);
		}
	}

}
