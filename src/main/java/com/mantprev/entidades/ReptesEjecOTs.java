package com.mantprev.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
@Builder 
@Entity
@Table(name = "reportes_ejec_ots")
public class ReptesEjecOTs  {
/****************************/    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
    @Column(unique = true, nullable = false)
    private int    idReporte;
    
    private Integer idOT;
    private Date    fechaEjec;
    private Double  calidadTrab;
    private Double  tpoParoProduc;
    private Double  tpoRealReparac;
    private String  nombreSuperv;
    private String  nombreFalla;
    private String  persRecivTrab;
    private String  reporteHistor;
    private Integer cantFotosCierre;
    private Integer cantRptosUtiliz;
    private Integer cantServExter;
    
    
    /***************************************************/
    
    @OneToOne
    @JoinColumn (name = "idOT", insertable=false, updatable=false)  // , insertable=false, updatable=false
    private OrdenesTrabajo ordenTrab;
    

	public OrdenesTrabajo getOrdenTrab() {
		return ordenTrab;
	}

	public void setOrdenTrab(OrdenesTrabajo ordenTrab) {
		this.ordenTrab = ordenTrab;
	}
	

	/***************************************************/
    
    
    //GETERS AND SETTERS
    
	public int getIdReporte() {
		return idReporte;
	}
	
	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}
	
	public Integer getIdOT() {
		return idOT;
	}
	
	public void setIdOT(Integer idOT) {
		this.idOT = idOT;
	}
	
	public Date getFechaEjec() {
		return fechaEjec;
	}
	
	public void setFechaEjec(Date fechaEjec) {
		this.fechaEjec = fechaEjec;
	}
	
	public Double getCalidadTrab() {
		return calidadTrab;
	}
	
	public void setCalidadTrab(Double calidadTrab) {
		this.calidadTrab = calidadTrab;
	}
	
	public Double getTpoParoProduc() {
		return tpoParoProduc;
	}
	
	public void setTpoParoProduc(Double tpoParoProduc) {
		this.tpoParoProduc = tpoParoProduc;
	}
	
	public Double getTpoRealReparac() {
		return tpoRealReparac;
	}
	
	public void setTpoRealReparac(Double tpoRealReparac) {
		this.tpoRealReparac = tpoRealReparac;
	}
	
	public String getNombreSuperv() {
		return nombreSuperv;
	}
	
	public void setNombreSuperv(String nombreSuperv) {
		this.nombreSuperv = nombreSuperv;
	}
	
	public String getNombreFalla() {
		return nombreFalla;
	}
	
	public void setNombreFalla(String nombreFalla) {
		this.nombreFalla = nombreFalla;
	}
	
	public String getPersRecivTrab() {
		return persRecivTrab;
	}
	
	public void setPersRecivTrab(String persRecivTrab) {
		this.persRecivTrab = persRecivTrab;
	}
	
	public String getReporteHistor() {
		return reporteHistor;
	}
	
	public void setReporteHistor(String reporteHistor) {
		this.reporteHistor = reporteHistor;
	}
	
	public Integer getCantFotosCierre() {
		return cantFotosCierre;
	}
	
	public void setCantFotosCierre(Integer cantFotosCierre) {
		this.cantFotosCierre = cantFotosCierre;
	}
	
	public Integer getCantRptosUtiliz() {
		return cantRptosUtiliz;
	}
	
	public void setCantRptosUtiliz(Integer cantRptosUtiliz) {
		this.cantRptosUtiliz = cantRptosUtiliz;
	}
	
	public Integer getCantServExter() {
		return cantServExter;
	}
	
	public void setCantServExter(Integer cantServExter) {
		this.cantServExter = cantServExter;
	}
    
    
    

	
    
    
    
}
