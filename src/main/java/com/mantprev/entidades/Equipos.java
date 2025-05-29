
package com.mantprev.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
@Entity
@Table(name = "equipos")
public class Equipos{
//******************* 
    
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int idEquipo;
    
	private int idEquipoPadre;
    private String nombEquipo;
    private String numHijo;
    private String nivelArbol;
    private String correlativo;
    private Double costoHoraParo;
    private String caractTecnicas;
    private String nombFotoEquip;
    
    
    //* RELACIONES ENTRE TABLAS ******************************************************************
     
    @JsonManagedReference
    @OneToMany(mappedBy="equipo", fetch = FetchType.LAZY)  // EAGER  Funciona   // , cascade = CascadeType.ALL
    private List<OrdenesTrabajo> listaOrdsTrab;
    
    
    public List<OrdenesTrabajo> getListaOrdsTrab() {
		return listaOrdsTrab;
	}

	public void setListaOrdsTrab(List<OrdenesTrabajo> listaOrdsTrab) {
		this.listaOrdsTrab = listaOrdsTrab;
	}
	
    //**********************************************************************************************
   	
       
    /***METODOS SETTERS AND GETTERS***/
    
	public int getIdEquipo() {
		return idEquipo;
	}
	
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	
	public int getIdEquipoPadre() {
		return idEquipoPadre;
	}

	public void setIdEquipoPadre(int idEquipoPadre) {
		this.idEquipoPadre = idEquipoPadre;
	}
	
	public String getNombEquipo() {
		return nombEquipo;
	}
	
	public void setNombEquipo(String nombEquipo) {
		this.nombEquipo = nombEquipo;
	}
	
	public String getNumHijo() {
		return numHijo;
	}
	
	public void setNumHijo(String numHijo) {
		this.numHijo = numHijo;
	}
	
	public String getNivelArbol() {
		return nivelArbol;
	}
	
	public void setNivelArbol(String nivelArbol) {
		this.nivelArbol = nivelArbol;
	}
	
	public String getCorrelativo() {
		return correlativo;
	}
	
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	
	public Double getCostoHoraParo() {
		return costoHoraParo;
	}
	
	public void setCostoHoraParo(Double costoHoraParo) {
		this.costoHoraParo = costoHoraParo;
	}
	
	public String getCaractTecnicas() {
		return caractTecnicas;
	}
	
	public void setCaractTecnicas(String caractTecnicas) {
		this.caractTecnicas = caractTecnicas;
	}
	
	public String getNombFotoEquip() {
		return nombFotoEquip;
	}
	
	public void setNombFotoEquip(String nombFotoEquip) {
		this.nombFotoEquip = nombFotoEquip;
	}

	


	

	
    
 

    
  
    
    
    


    
    
    
    
}
