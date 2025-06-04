
package com.mantprev.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
@Entity
@Table(name = "reptes_servext_ejec_ots")
public class RptesServExtEjecOTs {
/*******************************/
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int    idReporte;
    
	private Integer idOT;
	private String  nombreServic;
    private Double  costoServic;
    private Date    fechaServic;
    private Integer idEmpresa;
    
    
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

	public String getNombreServic() {
		return nombreServic;
	}

	public void setNombreServic(String nombreServic) {
		this.nombreServic = nombreServic;
	}

	public Double getCostoServic() {
		return costoServic;
	}

	public void setCostoServic(Double costoServic) {
		this.costoServic = costoServic;
	}

	public Date getFechaServic() {
		return fechaServic;
	}

	public void setFechaServic(Date fechaServic) {
		this.fechaServic = fechaServic;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
 
  
	
    
    
    
    
    
    
    
}
