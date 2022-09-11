package PortalEmpleo.App.Controladores;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PortalEmpleo.App.Entidades.Empresa;
import PortalEmpleo.App.Servicios.EmpresaServicio;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
public class RegistroEmpresaController {

	@Autowired
	UsuarioServicio usuarioRepositorio;

	@Autowired
	EmpresaServicio empresaRepositorio;

	// FORMULARIO REGIDTRO EMPRESA
	@GetMapping("/registroEmpresa")
	public String registroEmpresa() {
		return "registroEmpresa";
	}

	// FORMULARIO ERROR REGISTRO EMPRESA
	@GetMapping("/registroEmpresaError")
	public String registroEmpresaError(Model model) {
		model.addAttribute("mensaje",
				" Por favor introduzca un email y telefono diferente, ya que su email y telefono han coincidido con alguno que ya esta\r\n"
						+ "	                en la base de datos de la aplicacion");
		return "registroEmpresaError";
	}

	// REGISTRO NUEVA EMPRESA
	@PostMapping(value = "/nuevaEmpresa")
	public String registroEmpresa(Model model, @RequestParam String nombre, @RequestParam String email,
			@RequestParam String contraseya, @RequestParam String telefonoEmpresa, @RequestParam String ayoFundo,
			@RequestParam String sedeEmpresa, @RequestParam String descripcionEmpresa) {
		if (this.usuarioRepositorio.findByEmail(email) == null) {
			if (this.empresaRepositorio.findByTelefonoEmpresa(telefonoEmpresa) == null) {
				Empresa empresa = new Empresa(nombre, email, contraseya, telefonoEmpresa, ayoFundo, sedeEmpresa,
						descripcionEmpresa, 0, "ROLE_EMPRESA");
				if (nombre.isEmpty() == false) {
					if (email.isEmpty() == false) {
						if (contraseya.isEmpty() == false && contraseya.length() >= 8
								&& this.tieneNumero(contraseya) == true && this.tieneMayuscula(contraseya) == true
								&& this.tieneMinusculas(contraseya) == true
								&& this.tieneEspacios(contraseya) == false) {
							if (telefonoEmpresa.isEmpty() == false && telefonoEmpresa.length() == 9
									&& this.tieneNumero(telefonoEmpresa) == true
									&& this.tieneMayuscula(telefonoEmpresa) == false
									&& this.tieneMinusculas(telefonoEmpresa) == false
									&& this.tieneEspacios(telefonoEmpresa) == false) {
								if (ayoFundo.length() == 4 && ayoFundo.isEmpty() == false
										&& this.tieneNumero(ayoFundo) == true && this.tieneMayuscula(ayoFundo) == false
										&& this.tieneMinusculas(ayoFundo) == false
										&& this.tieneEspacios(ayoFundo) == false) {
									this.usuarioRepositorio.save(empresa);
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
				}
			}
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/registroEmpresaError";
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
	private boolean tieneMinusculas(String contraseya) {

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
