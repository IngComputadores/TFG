package PortalEmpleo.App.Controladores;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import PortalEmpleo.App.Entidades.Candidato;
import PortalEmpleo.App.Entidades.Curriculum;
import PortalEmpleo.App.Servicios.CurriculumServicio;
import PortalEmpleo.App.Servicios.ExperienciasServicio;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
public class RegistroCandidatoController {

	@Autowired
	UsuarioServicio usuarioRepositorio;

	@Autowired
	CurriculumServicio curriculumRepositorio;

	@Autowired
	ExperienciasServicio experienciasRepositorio;

	// FORMULARIO REGIDTRO CANDIDATO
	@GetMapping("/registroCandidato")
	public String registroCandidato(Model model) {
		return "registroCandidato";
	}

	// FORMULARIO ERROR REGISTRO CANDIDATO
	@GetMapping("/registroCandidatoError")
	public String registroCandidatoError(Model model) {
		model.addAttribute("mensaje",
				" Por favor introduzca un email diferente, ya que su email ha coincidido con uno que ya esta\r\n"
						+ "	                en la base de datos de la aplicacion");
		return "registroCandidatoError";
	}

	// REGISTRO NUEVO CANDIDATO
	@PostMapping(value = "/nuevoCandidato")
	public String nuevoCandidato(HttpServletRequest request, RedirectAttributes redirectAttrs, Model model,
			@RequestParam String nombre, @RequestParam String email, @RequestParam String contraseya,
			@RequestParam String apellidos) {

		Curriculum curriculum = new Curriculum();
		this.curriculumRepositorio.save(curriculum);

		if (this.usuarioRepositorio.findByEmail(email) == null) {
			Candidato candidato = new Candidato(nombre, email, contraseya, apellidos, curriculum, 0, 0, 0, 0,
					"ROLE_CANDIDATO");
			if (nombre.isEmpty() == false) {
				if (email.isEmpty() == false) {
					if (contraseya.isEmpty() == false && contraseya.length() >= 8
							&& this.tieneNumero(contraseya) == true && this.tieneMayuscula(contraseya) == true
							&& this.tieneEspacios(contraseya) == false && this.tieneMinuculas(contraseya) == true) {
						this.usuarioRepositorio.save(candidato);
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return "redirect:/login";
					}

				}
			}
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/registroCandidatoError";
	}

	// CONTRASEÑA TIENE INCLUIDA ALGUN NUMERO
	private boolean tieneNumero(String contraseya) {

		for (int x = 0; x < contraseya.length(); x++) {
			char c = contraseya.charAt(x);
			if (((c >= '0' && c <= '9'))) {
				return true;
			}
		}
		return false;
	}

	// CONTRASEÑA TIENE INCLUIDA ALGUNA LETRA MAYUSCULA
	private boolean tieneMayuscula(String contraseya) {

		for (int x = 0; x < contraseya.length(); x++) {
			char c = contraseya.charAt(x);
			if (((c >= 'A' && c <= 'Z'))) {
				return true;
			}
		}
		return false;
	}

	// CONTRASEÑA TIENE INCLUIDA ALGUNA LETRA MINUSCULA
	private boolean tieneMinuculas(String contraseya) {

		for (int x = 0; x < contraseya.length(); x++) {
			char c = contraseya.charAt(x);
			if (((c >= 'a' && c <= 'z'))) {
				return true;
			}
		}
		return false;
	}

	// CONTRASEÑA TIENE INCLUIDA ALGUN ESPACIO EN BLANCO
	private boolean tieneEspacios(String contraseya) {

		for (int x = 0; x < contraseya.length(); x++) {
			char c = contraseya.charAt(x);
			if ((c == ' ')) {
				return true;
			}
		}
		return false;
	}
}
