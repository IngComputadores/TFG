package PortalEmpleo.App.Controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import PortalEmpleo.App.Entidades.Anuncio;
import PortalEmpleo.App.Entidades.Usuario;
import PortalEmpleo.App.Servicios.AnuncioServicio;
import PortalEmpleo.App.Servicios.UsuarioServicio;

@Controller
public class IndexController {
	
	@Autowired
	UsuarioServicio usuarioRepositorio;
	
	@Autowired
	AnuncioServicio anuncioRespositorio;

	// INDEX
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// PAGINA PREVIA PARA REGISTRARSE
	@GetMapping("/registro")
	public String subRegistro() {
		return "subRegistro";
	}
	
	// MOSTRAR LOS ANUNCIOS PUBLICADOS TABLON INDEX
	@GetMapping("/anunciosTablon")
	public String tablonAnuncio(Model model, HttpServletRequest request) {
		
		List<Anuncio> listaAnuncio = this.anuncioRespositorio.findAll();
		if(listaAnuncio.size()>0) {
			model.addAttribute("listaAnunciosTablon", listaAnuncio);
			model.addAttribute("hayAnunciosTablon", true);
			model.addAttribute("mostrarEmpresaTablon", true);
			model.addAttribute("mostrarInicio", true);
			return"/anunciosTablon";
		}else {
			model.addAttribute("noHayAnunciosTablon", true);
			model.addAttribute("mostrarInicio", true);
			return"/anunciosTablon";
		}
	}
	
	// MOSTRAR LAS EMPRESAS REGISTRADAS
	@GetMapping("/lista_empresas_registradas")
	public String empresasRegistradas(Model model) {
		
		List<Usuario> listaEmpresasRegistradas = this.usuarioRepositorio.findByRoles("ROLE_EMPRESA");
		
		if(listaEmpresasRegistradas.size()>0) {
			model.addAttribute("listaEmpresasRegistradas", listaEmpresasRegistradas);
			model.addAttribute("hayEmpresa", true);
			return "listaEmpresasRegistradas";
		}else {
			model.addAttribute("noHayEmpresa", true);
			return "listaEmpresasRegistradas";
		}
	}
	
	// CERRAR SESION
	@RequestMapping("/logout")
	public String logout() {
		return "index";
	}
	
}
