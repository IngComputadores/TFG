package PortalEmpleo.App.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Candidato extends Usuario {

	private String apellidos;

	private @OneToOne Curriculum curriculum;

	private int hayCurriculum;
	private int creadoCurriculum;
	private int datosRellenados;
	private int editadoDatosCurriculum;
	private int añadidaExperiencia;

	private @ManyToMany(mappedBy = "listaCandidatos") List<Anuncio> listaAnuncios;

	public Candidato() {
	}

	public Candidato(String nombre, String email, String contraseya, String apellidos, String... roles) {
		super(nombre, email, contraseya, roles);
		this.apellidos = apellidos;
		this.listaAnuncios = new ArrayList<Anuncio>();
	}

	public Candidato(String nombre, String email, String contraseya, String apellidos, Curriculum curriculum,
			int hayCurriculum, int datosRellenados, int añadidaExperiencia, int editadoDatosCurriculum,
			String... roles) {
		super(nombre, email, contraseya, roles);
		this.apellidos = apellidos;
		this.curriculum = curriculum;
		this.hayCurriculum = hayCurriculum;
		this.datosRellenados = datosRellenados;
		this.añadidaExperiencia = añadidaExperiencia;
		this.creadoCurriculum = 0;
		this.editadoDatosCurriculum = editadoDatosCurriculum;
		this.listaAnuncios = new ArrayList<Anuncio>();
	}

	public List<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}

	public void setListaAnuncios(List<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}

	public int getEditadoDatosCurriculum() {
		return editadoDatosCurriculum;
	}

	public void setEditadoDatosCurriculum(int editadoDatosCurriculum) {
		this.editadoDatosCurriculum = editadoDatosCurriculum;
	}

	public int getCreadoCurriculum() {
		return creadoCurriculum;
	}

	public void setCreadoCurriculum(int creadoCurriculum) {
		this.creadoCurriculum = creadoCurriculum;
	}

	public int getAñadidaExperiencia() {
		return añadidaExperiencia;
	}

	public void setAñadidaExperiencia(int añadidaExperiencia) {
		this.añadidaExperiencia = añadidaExperiencia;
	}

	public int getDatosRellenados() {
		return datosRellenados;
	}

	public void setDatosRellenados(int datosRellenados) {
		this.datosRellenados = datosRellenados;
	}

	public int getHayCurriculum() {
		return hayCurriculum;
	}

	public void setHayCurriculum(int hayCurriculum) {
		this.hayCurriculum = hayCurriculum;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

}
