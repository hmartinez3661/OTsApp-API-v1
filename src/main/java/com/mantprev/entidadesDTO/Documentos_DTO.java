
package com.mantprev.entidadesDTO;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
public class Documentos_DTO{
//**************************** 
	
	
    private int idDoc;
    private String  nombreDoc;
    private String  nombreSinEsp;   //nombre del documento fisico en el folder donde se guarda
    private Integer idOrdTrab;
    private String  comodin;
    private MultipartFile multipartDoc;
    
    
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

	public String getNombreSinEsp() {
		return nombreSinEsp;
	}

	public void setNombreSinEsp(String nombreSinEsp) {
		this.nombreSinEsp = nombreSinEsp;
	}

	public String getComodin() {
		return comodin;
	}

	public void setComodin(String comodin) {
		this.comodin = comodin;
	}

	public MultipartFile getMultipartDoc() {
		return multipartDoc;
	}

	public void setMultipartDoc(MultipartFile multipartDoc) {
		this.multipartDoc = multipartDoc;
	}
    
    
    
    
	
   

	
    
 

    
  
    
    
    


    
    
    
    
}
