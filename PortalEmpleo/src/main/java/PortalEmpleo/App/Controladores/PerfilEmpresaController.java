package PortalEmpleo.App.Controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import PortalEmpleo.App.Entidades.Empresa;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
@RequestMapping("/perfilEmpresa")
public class PerfilEmpresaController {

	@Autowired
	UsuarioServicio usuarioRepositorio;

	// DATOS DE PERFIL EMPRESA
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String perfilEmpresa(Model model, HttpServletRequest request) {

		Empresa empresa = (Empresa) this.usuarioRepositorio.findByEmail(request.getUserPrincipal().getName());
		if (empresa != null) {
			model.addAttribute("nombreEmpresa", " " + empresa.getNombre());
			model.addAttribute("emailEmpresa", " " + empresa.getEmail());
			model.addAttribute("sedeEmpresa", " " + empresa.getSedeEmpresa());
			model.addAttribute("ayoFundo", " " + empresa.getAyoFundo());
			model.addAttribute("telefonoEmpresa", " " + empresa.getTelefonoEmpresa());
			model.addAttribute("descripcionEmpresa", " " + empresa.getDescripcionEmpresa());

			return "perfilEmpresa";
		}
		return ("redirect:/loginError");
	}
}
