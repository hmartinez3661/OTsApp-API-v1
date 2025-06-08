package com.mantprev.entidades;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
@Builder 
@Entity
@Table(name = "ordenes_trabajo")
public class OrdenesTrabajo { 

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int idOT;
	
	private Integer idEquipo;
	private String  persEjecutor;
	private String  clasificTrabajo;
	private String  prioridadOT;
	private String  trabajoSolicit;
	private String  nombSolicitante;
	private Date    fechaIngresoOT;
	private String  horaIngresoOT;
	private Integer idReporteEjecuc;
	private String  nombreReviso;
	private Date    fechaRevision;
	private String  horaRevision;
	private Double  tiempoEstim;
	private Integer personalEstim;
	private String  tecnAsignado;
	private String  indicacPreviasTrab;
	private String  explicRechazo;
	private String  estatusOT;
	private Integer cantFotosAnex;
	private String  comodinCorrelat;
	private Integer idRpteEjecOT;
	private Integer idEmpresa;
	
	
	//* RELACIONES ENTRE TABLAS ********************************************
	
	@OneToOne
    @JoinColumn(name = "idRpteEjecOT", insertable=false, updatable=false)
    private ReptesEjecOTs repteEjecOT;
 
    
    public ReptesEjecOTs getRepteEjecOT() {
		return repteEjecOT;
	}
	
	public void setRepteEjecOT(ReptesEjecOTs repteEjecOT) {
		this.repteEjecOT = repteEjecOT;
	}
	
	
	/********************************/
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "idEquipo", insertable=false, updatable=false)
	private Equipos equipo;
	
	public Equipos getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}
	
	
	//**********************************************************************  
	
	@JsonManagedReference
	@JsonIgnore
    @OneToMany (mappedBy = "OrdenTrab", fetch = FetchType.LAZY)  
    private List<ReptesPersEjecOTs> listRtesPerTecnEjecOT;
    
    
	public List<ReptesPersEjecOTs> getListRtesPerTecnEjecOT() {
		return listRtesPerTecnEjecOT;
	}

	public void setListRtesPerTecnEjecOT(List<ReptesPersEjecOTs> listRtesPerTecnEjecOT) {
		this.listRtesPerTecnEjecOT = listRtesPerTecnEjecOT;
	}

	
	
	/********************************************************************/

    
	@JsonIgnore
    @OneToMany (mappedBy = "ordenTrab", fetch = FetchType.LAZY)
    private List<RptesReptosEjecOTs> listRtesReptosEjecOT;
    
    
	public List<RptesReptosEjecOTs> getListRtesReptosEjecOT() {
		return listRtesReptosEjecOT;
	}

	public void setListRtesReptosEjecOT(List<RptesReptosEjecOTs> listRtesReptosEjecOT) {
		this.listRtesReptosEjecOT = listRtesReptosEjecOT;
	}
	
	
	/********************************************************************/
	
	
	
	
	//GETTERS AND SETTERS
	
	public int getIdOT() {
		return idOT;
	}
	
	public void setIdOT(int idOT) {
		this.idOT = idOT;
	}
	
	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}
	
	public String getPersEjecutor() {
		return persEjecutor;
	}
	
	public void setPersEjecutor(String persEjecutor) {
		this.persEjecutor = persEjecutor;
	}
	
	public String getClasificTrabajo() {
		return clasificTrabajo;
	}
	
	public void setClasificTrabajo(String clasificTrabajo) {
		this.clasificTrabajo = clasificTrabajo;
	}
	
	public String getPrioridadOT() {
		return prioridadOT;
	}
	
	public void setPrioridadOT(String prioridadOT) {
		this.prioridadOT = prioridadOT;
	}
	
	public String getTrabajoSolicit() {
		return trabajoSolicit;
	}
	public void setTrabajoSolicit(String trabajoSolicit) {
		this.trabajoSolicit = trabajoSolicit;
	}
	
	public String getNombSolicitante() {
		return nombSolicitante;
	}
	
	public void setNombSolicitante(String nombSolicitante) {
		this.nombSolicitante = nombSolicitante;
	}
	
	public Date getFechaIngresoOT() {
		return fechaIngresoOT;
	}
	
	public void setFechaIngresoOT(Date fechaIngresoOT) {
		this.fechaIngresoOT = fechaIngresoOT;
	}
	
	public String getHoraIngresoOT() {
		return horaIngresoOT;
	}
	
	public void setHoraIngresoOT(String horaIngresoOT) {
		this.horaIngresoOT = horaIngresoOT;
	}
	
	public Integer getIdReporteEjecuc() {
		return idReporteEjecuc;
	}
	
	public void setIdReporteEjecuc(Integer idReporteEjecuc) {
		this.idReporteEjecuc = idReporteEjecuc;
	}
	
	public String getNombreReviso() {
		return nombreReviso;
	}
	
	public void setNombreReviso(String nombreReviso) {
		this.nombreReviso = nombreReviso;
	}
	
	public Date getFechaRevision() {
		return fechaRevision;
	}
	
	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}
	
	public String getHoraRevision() {
		return horaRevision;
	}
	
	public void setHoraRevision(String horaRevision) {
		this.horaRevision = horaRevision;
	}
	
	public Double getTiempoEstim() {
		return tiempoEstim;
	}
	
	public void setTiempoEstim(Double tiempoEstim) {
		this.tiempoEstim = tiempoEstim;
	}
	
	public Integer getPersonalEstim() {
		return personalEstim;
	}
	
	public void setPersonalEstim(Integer personalEstim) {
		this.personalEstim = personalEstim;
	}
	
	public String getTecnAsignado() {
		return tecnAsignado;
	}
	
	public void setTecnAsignado(String tecnAsignado) {
		this.tecnAsignado = tecnAsignado;
	}
	
	public String getIndicacPreviasTrab() {
		return indicacPreviasTrab;
	}
	
	public void setIndicacPreviasTrab(String indicacPreviasTrab) {
		this.indicacPreviasTrab = indicacPreviasTrab;
	}
	
	public String getExplicRechazo() {
		return explicRechazo;
	}
	
	public void setExplicRechazo(String explicRechazo) {
		this.explicRechazo = explicRechazo;
	}
	
	public String getEstatusOT() {
		return estatusOT;
	}
	
	public void setEstatusOT(String estatusOT) {
		this.estatusOT = estatusOT;
	}
	
	public Integer getCantFotosAnex() {
		return cantFotosAnex;
	}
	
	public void setCantFotosAnex(Integer cantFotosAnex) {
		this.cantFotosAnex = cantFotosAnex;
	}
	
	public String getComodinCorrelat() {
		return comodinCorrelat;
	}
	
	public void setComodinCorrelat(String comodinCorrelat) {
		this.comodinCorrelat = comodinCorrelat;
	}
	
	public Integer getIdRpteEjecOT() {
		return idRpteEjecOT;
	}
	
	public void setIdRpteEjecOT(Integer idRpteEjecOT) {
		this.idRpteEjecOT = idRpteEjecOT;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	
	

	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
