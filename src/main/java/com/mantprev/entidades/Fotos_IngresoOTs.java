
package com.mantprev.entidades;

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
@Table(name = "fotos_ingreso_ots")
public class Fotos_IngresoOTs{
//**************************** 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int idFoto;
    
    private Integer idEquipo;
    private String  nombreFoto;
    private String  correlatEquip;
    private Integer idOrdTrab;
    
    
    //GETTERS AND SETTERS
    
	public int getIdFoto() {
		return idFoto;
	}
	
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
	
	public Integer getIdEquipo() {
		return idEquipo;
	}
	
	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}
	
	public String getNombreFoto() {
		return nombreFoto;
	}
	
	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}
	
	public String getCorrelatEquip() {
		return correlatEquip;
	}
	
	public void setCorrelatEquip(String correlatEquip) {
		this.correlatEquip = correlatEquip;
	}
	
	public Integer getIdOrdTrab() {
		return idOrdTrab;
	}
	
	public void setIdOrdTrab(Integer idOrdTrab) {
		this.idOrdTrab = idOrdTrab;
	}
    
   

	
    
 

    
  
    
    
    


    
    
    
    
}
