
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
@Table(name = "documentos_ots")
public class Documentos_OTs{
//**************************** 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int idDoc;
    
    private String  nombreDoc;
    private String  nombreSinEsp;    //nombre del documento fisico en el folder donde se guarda
    private Integer idOrdTrab;
    private String  comodin;
	private int idEmpresa;
    
    
    //GETTERS AND SETTERS
    
	public int getIdDoc() {
		return idDoc;
	}
	
	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}
	
	public String getNombreDoc() {
		return nombreDoc;
	}
	
	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}
	
	public Integer getIdOrdTrab() {
		return idOrdTrab;
	}
	
	public void setIdOrdTrab(Integer idOrdTrab) {
		this.idOrdTrab = idOrdTrab;
	}

	public String getComodin() {
		return comodin;
	}

	public void setComodin(String comodin) {
		this.comodin = comodin;
	}

	public String getNombreSinEsp() {
		return nombreSinEsp;
	}

	public void setNombreSinEsp(String nombreSinEsp) {
		this.nombreSinEsp = nombreSinEsp;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
    
    
    
    
	
   

	
    
 

    
  
    
    
    


    
    
    
    
}
