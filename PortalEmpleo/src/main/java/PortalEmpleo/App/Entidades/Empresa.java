package PortalEmpleo.App.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Empresa extends Usuario {

	private String telefonoEmpresa;
	private String ayoFundo;
	private String sedeEmpresa;

	@Column(columnDefinition = "text")
	private String descripcionEmpresa;

	private int hayAnuncios;

	@OneToMany(mappedBy = "empresa")
	private List<Anuncio> listaAnuncios;

	public Empresa() {

	}

	public Empresa(String nombre, String email, String contraseya, String telefonoEmpresa, String ayoFundo,
			String sedeEmpresa, String descripcionEmpresa, int hayAnuncios, String... roles) {
		super(nombre, email, contraseya, roles);
		this.ayoFundo = ayoFundo;
		this.sedeEmpresa = sedeEmpresa;
		this.descripcionEmpresa = descripcionEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
		this.listaAnuncios = new ArrayList<>();
		this.setHayAnuncios(hayAnuncios);
	}

	public int getHayAnuncios() {
		return hayAnuncios;
	}

	public void setHayAnuncios(int hayAnuncios) {
		this.hayAnuncios = hayAnuncios;
	}

	public List<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}

	public void setListaAnuncios(List<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public String getAyoFundo() {
		return ayoFundo;
	}

	public void setAyoFundo(String ayoFundo) {
		this.ayoFundo = ayoFundo;
	}

	public String getSedeEmpresa() {
		return sedeEmpresa;
	}

	public void setSedeEmpresa(String sedeEmpresa) {
		this.sedeEmpresa = sedeEmpresa;
	}

	public String getDescripcionEmpresa() {
		return descripcionEmpresa;
	}

	public void setDescripcionEmpresa(String descripcionEmpresa) {
		this.descripcionEmpresa = descripcionEmpresa;
	}

}
