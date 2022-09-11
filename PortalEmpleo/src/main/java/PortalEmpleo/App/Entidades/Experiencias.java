package PortalEmpleo.App.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Experiencias {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String descripcionExperiencia;

	@ManyToOne
	private Curriculum curriculum;

	public Experiencias() {
		curriculum = null;
	}

	public Experiencias(Curriculum curriculum) {
		this.setCurriculum(curriculum);
	}

	public Experiencias(Curriculum curriculum, String experiencias) {
		this.setCurriculum(curriculum);
		this.setDescripcionExperiencia(experiencias);
	}

	public Experiencias(String descripcionExperiencia) {
		this.setDescripcionExperiencia(descripcionExperiencia);
	}

	public String getDescripcionExperiencia() {
		return descripcionExperiencia;
	}

	public void setDescripcionExperiencia(String descripcionExperiencia) {
		this.descripcionExperiencia = descripcionExperiencia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	 * public String getDescripcionExperiencia() { return descripcionExperiencia; }
	 * 
	 * public void setDescripcionExperiencia(String descripcionExperiencia) {
	 * this.descripcionExperiencia = descripcionExperiencia; }
	 */

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
