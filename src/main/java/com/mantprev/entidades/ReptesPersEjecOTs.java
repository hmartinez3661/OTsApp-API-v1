
package com.mantprev.entidades;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
@Entity
@Table(name = "reptes_pers_ejec_ots")
public class ReptesPersEjecOTs { 
/*******************************/
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int    idReporte;
    
    private Double  cantHrs;
    private Double  calidadTrab;
    private Date    fechaEjec;
    private Integer idOT;
    private Integer idEmpleado;
    private Integer idEmpresa;
	
 
   /* ******************************************************* */   
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idEmpleado", insertable=false, updatable=false)
    private Personal_Tecnico personal_tecn;
    
    public Personal_Tecnico getPersonal_tecn() {
		return personal_tecn;
	}

	public void setPersonal_tecn(Personal_Tecnico personal_tecn) {
		this.personal_tecn = personal_tecn;
	}

	/******************************************************** */
    
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "idOT", insertable=false, updatable=false)
    private OrdenesTrabajo OrdenTrab;
    
    
    public OrdenesTrabajo getOrdenTrab() {
		return OrdenTrab;
	}
    
	public void setOrdenTrab(OrdenesTrabajo ordenTrab) {
		OrdenTrab = ordenTrab;
	}
	
    /********************************************************/
	
	
	
	
	
    //GETTERS AND SETTERS
    
    public int getIdReporte() {
		return idReporte;
	}
    
	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}
	
	public Double getCantHrs() {
		return cantHrs;
	}
	
	public void setCantHrs(Double cantHrs) {
		this.cantHrs = cantHrs;
	}
	
	public Double getCalidadTrab() {
		return calidadTrab;
	}
	
	public void setCalidadTrab(Double calidadTrab) {
		this.calidadTrab = calidadTrab;
	}
	
	public Date getFechaEjec() {
		return fechaEjec;
	}
	
	public void setFechaEjec(Date fechaEjec) {
		this.fechaEjec = fechaEjec;
	}
	
	public Integer getIdOT() {
		return idOT;
	}
	
	public void setIdOT(Integer idOT) {
		this.idOT = idOT;
	}
	
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	
    
    
    
    
    
    
    
}
