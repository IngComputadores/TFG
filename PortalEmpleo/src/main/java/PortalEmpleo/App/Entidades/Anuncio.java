package PortalEmpleo.App.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Anuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String asunto;
	private String fecha;

	@Column(columnDefinition = "text")
	private String descripcionOferta;

	private int apuntadoCandidato;
	private String emailEmpresa;

	@ManyToOne
	private Empresa empresa;

	@ManyToMany
	@JoinTable(name = "anuncio_inscribir", joinColumns = @JoinColumn(name = "anuncio_id"), inverseJoinColumns = @JoinColumn(name = "candidato_id"))
	private List<Candidato> listaCandidatos;

	public Anuncio() {
	}

	public Anuncio(Empresa empresa, String asunto, String fecha, String descripcionOferta) {
		this.setEmpresa(empresa);
		this.setAsunto(asunto);
		this.setDescripcionOferta(descripcionOferta);
		this.setFecha(fecha);
		this.setEmailEmpresa(empresa.getEmail());
		this.listaCandidatos = new ArrayList<Candidato>();
	}

	public Anuncio(String asunto, String fecha, String descripcionOferta) {
		this.setEmpresa(null);
		this.setAsunto(asunto);
		this.setDescripcionOferta(descripcionOferta);
		this.setFecha(fecha);
		this.listaCandidatos = new ArrayList<Candidato>();
	}

	public int getApuntadoCandidato() {
		return apuntadoCandidato;
	}

	public void setApuntadoCandidato(int apuntadoCandidato) {
		this.apuntadoCandidato = apuntadoCandidato;
	}

	public void apuntarseAnuncio() {
		this.apuntadoCandidato++;
	}

	public void desapuntarseAnuncio() {
		this.apuntadoCandidato--;
	}

	public List<Candidato> getListaCandidatos() {
		return listaCandidatos;
	}

	public void setListaCandidatos(List<Candidato> listaCandidatos) {
		this.listaCandidatos = listaCandidatos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcionOferta() {
		return descripcionOferta;
	}

	public void setDescripcionOferta(String descripcionOferta) {
		this.descripcionOferta = descripcionOferta;
	}

	public String getEmailEmpresa() {
		return emailEmpresa;
	}

	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
