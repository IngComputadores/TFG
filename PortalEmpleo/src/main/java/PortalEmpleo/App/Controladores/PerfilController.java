package PortalEmpleo.App.Controladores;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PerfilController {

	// FORMULARIO LOGIN
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// FORMULARIO ERROR DE LOGIN
	@GetMapping("/login_Error")
	public String loginError(Model model) {
		model.addAttribute("mensaje", "Contraseña o email no validos. Intentelo de nuevo para iniciar sesión");
		return "loginError";
	}

	// REDIRRECION A LA PAGINA DE PERFIL SEGUN EL ROL DEL USUARIO
	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	public String redireccion(Model model, HttpServletRequest request) {
		if (request.isUserInRole("CANDIDATO")) {
			return ("redirect:/perfilCandidato");
		} else if (request.isUserInRole("EMPRESA")) {
			return ("redirect:/perfilEmpresa");
		} else {
			try {
				TimeUnit.SECONDS.sleep(6);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ("redirect:/loginError");
		}
	}
}
