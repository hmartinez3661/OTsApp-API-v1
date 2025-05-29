
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
@Table(name = "reptes_reptos_ejec_ots")
public class RptesReptosEjecOTs {
/*******************************/
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int    idReporte;
    
	private Integer idOT;
	private String  nombreRep;
	private Double  cantidad;
    private Double  costoTotal;
    private Date    fechaConsumo;
    private Integer idRepSum;
    
    
    /* RELACION ENTRE TABLAS ******************************/
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn (name = "idOT", insertable=false, updatable=false)
    private OrdenesTrabajo ordenTrab;
    
    public OrdenesTrabajo getOrdenTrab() {
		return ordenTrab;
	}

	public void setOrdenTrab(OrdenesTrabajo ordenTrab) {
		this.ordenTrab = ordenTrab;
	}
    
    /******************************************************/   
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn (name = "idRepSum", insertable=false, updatable=false)
    private Repuestos_Sum repuestoSumin;
	
	public Repuestos_Sum getRepuestoSumin() {
		return repuestoSumin;
	}
	 
	public void setRepuestoSumin(Repuestos_Sum repuestoSumin) {
		this.repuestoSumin = repuestoSumin;
	}
	
	
	
	/******************************************************/   
	
    
    
    //GETTERS AND SETTERS
    
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
	
	public String getNombreRep() {
		return nombreRep;
	}
	
	public void setNombreRep(String nombreRep) {
		this.nombreRep = nombreRep;
	}
	
	public Double getCostoTotal() {
		return costoTotal;
	}
	
	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}
	
	public Date getFechaConsumo() {
		return fechaConsumo;
	}
	
	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}

	public Integer getIdRepSum() {
		return idRepSum;
	}

	public void setIdRepSum(Integer idRepSum) {
		this.idRepSum = idRepSum;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	

	
	
 
  
	
    
    
    
    
    
    
    
}
