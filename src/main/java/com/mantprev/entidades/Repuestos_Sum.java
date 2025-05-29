
package com.mantprev.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
@Entity
@Table(name = "repuestos_sum", uniqueConstraints={@UniqueConstraint(columnNames={"codigoRep"})})
public class Repuestos_Sum{
//**************************
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int    idRepSum;
	
    private String nombreRep;
    
    @Column(unique=true)
    private String codigoRep;
    
    private String unidades;
    private String clasificac;
    private Double costoProm;
    private String fotoRep;
    private String especifTecn;
    
    
    /* RELACION ENTRE TABLAS *********************************/
    
    @JsonIgnore
    @OneToMany (mappedBy = "repuestoSumin", fetch = FetchType.LAZY)
    private List<RptesReptosEjecOTs> listReptesReptosEjecOT;
    
    
    public List<RptesReptosEjecOTs> getListReptesRepSumEjecOT() { 
		return listReptesReptosEjecOT;
	}
    
	public void setListReptesRepSumEjecOT(List<RptesReptosEjecOTs> listReptesReptosEjecOT) {
		this.listReptesReptosEjecOT = listReptesReptosEjecOT;
	}
	

	/*****************************************************************/
	
	
    
    //GETTERS AND SETTERS
	
	public int getIdRepSum() {
		return idRepSum;
	}

	public void setIdRepSum(int idRepSum) {
		this.idRepSum = idRepSum;
	}

	public String getNombreRep() {
		return nombreRep;
	}

	public void setNombreRep(String nombreRep) {
		this.nombreRep = nombreRep;
	}

	public String getCodigoRep() {
		return codigoRep;
	}

	public void setCodigoRep(String codigoRep) {
		this.codigoRep = codigoRep;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	public String getClasificac() {
		return clasificac;
	}

	public void setClasificac(String clasificac) {
		this.clasificac = clasificac;
	}

	public Double getCostoProm() {
		return costoProm;
	}

	public void setCostoProm(Double costoProm) {
		this.costoProm = costoProm;
	}

	public String getFotoRep() {
		return fotoRep;
	}

	public void setFotoRep(String fotoRep) {
		this.fotoRep = fotoRep;
	}

	public String getEspecifTecn() {
		return especifTecn;
	}

	public void setEspecifTecn(String especifTecn) {
		this.especifTecn = especifTecn;
	}
	
	
    
	
    
    
    
   
    
	
	
    
    
   

	
    
 

    
  
    
    
    


    
    
    
    
}
