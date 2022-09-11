package PortalEmpleo.App.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Curriculum {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String perfil;
	private String idiomas;
	private String educacionUniversitaria;
	private String eduacionSecundaria;
	private String grado;
	private String cursos;
	private String voluntariados;
	private String habilidades;
	private String aptitudes;

	@OneToMany(mappedBy = "curriculum")
	private List<Experiencias> experiencias;

	private @OneToOne(mappedBy = "curriculum") Candidato candidato;

	public Curriculum() {
		this.candidato = null;
		this.experiencias = new ArrayList<>();
	}

	public Curriculum(String perfil, String idiomas, String educacionUniversitaria, String eduacionSecundaria,
			String grado, String cursos, String voluntariado, String habilidades, String aptitudes) {

		this.setPerfil(perfil);
		this.idiomas = idiomas;
		this.setEducacionUniversitaria(educacionUniversitaria);
		this.setEduacionSecundaria(eduacionSecundaria);
		this.setGrado(grado);
		this.setCursos(cursos);
		this.setVoluntariados(voluntariado);
		this.setHabilidades(habilidades);
		this.setAptitudes(aptitudes);
		this.experiencias = new ArrayList<>();
		this.candidato = null;
	}

	public List<Experiencias> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencias> experiencias) {
		this.experiencias = experiencias;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public String getEducacionUniversitaria() {
		return educacionUniversitaria;
	}

	public void setEducacionUniversitaria(String educacionUniversitaria) {
		this.educacionUniversitaria = educacionUniversitaria;
	}

	public String getEduacionSecundaria() {
		return eduacionSecundaria;
	}

	public void setEduacionSecundaria(String eduacionSecundaria) {
		this.eduacionSecundaria = eduacionSecundaria;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public String getCursos() {
		return cursos;
	}

	public void setCursos(String cursos) {
		this.cursos = cursos;
	}

	public String getVoluntariados() {
		return voluntariados;
	}

	public void setVoluntariados(String voluntariados) {
		this.voluntariados = voluntariados;
	}

	public String getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}

	public String getAptitudes() {
		return aptitudes;
	}

	public void setAptitudes(String aptitudes) {
		this.aptitudes = aptitudes;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

}
