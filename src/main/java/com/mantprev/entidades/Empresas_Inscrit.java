
package com.mantprev.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas_inscrit")
public class Empresas_Inscrit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int idEmpresa;
	
	private String nombEmpresa;
	private String paisEmpresa;
	private String idiomaGrupo;
	private String simbMoneda;
	private String codigoPais;
	private int cantMaxUsers;
	private Date fechaSuscrip;
	private Date fechaFnlSuscrip; 
	
	
	//Constructor
	public Empresas_Inscrit() {
	}


	public int getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public String getNombEmpresa() {
		return nombEmpresa;
	}


	public void setNombEmpresa(String nombEmpresa) {
		this.nombEmpresa = nombEmpresa;
	}


	public String getPaisEmpresa() {
		return paisEmpresa;
	}


	public void setPaisEmpresa(String paisEmpresa) {
		this.paisEmpresa = paisEmpresa;
	}


	public String getIdiomaGrupo() {
		return idiomaGrupo;
	}


	public void setIdiomaGrupo(String idiomaGrupo) {
		this.idiomaGrupo = idiomaGrupo;
	}


	public String getSimbMoneda() {
		return simbMoneda;
	}


	public void setSimbMoneda(String simbMoneda) {
		this.simbMoneda = simbMoneda;
	}


	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public int getCantMaxUsers() {
		return cantMaxUsers;
	}


	public void setCantMaxUsers(int cantMaxUsers) {
		this.cantMaxUsers = cantMaxUsers;
	}


	public Date getFechaSuscrip() {
		return fechaSuscrip;
	}


	public void setFechaSuscrip(Date fechaSuscrip) {
		this.fechaSuscrip = fechaSuscrip;
	}


	public Date getFechaFnlSuscrip() {
		return fechaFnlSuscrip;
	}


	public void setFechaFnlSuscrip(Date fechaFnlSuscrip) {
		this.fechaFnlSuscrip = fechaFnlSuscrip;
	}


	
	
	
	
}
