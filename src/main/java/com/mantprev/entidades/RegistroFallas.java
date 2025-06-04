
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
@Table(name = "lista_fallas")
public class RegistroFallas {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int idFalla;
	
    private String nombreFalla;
    private String tipoFalla;
    private int idEmpresa;

    
    //GETTERS AND SETTERS

    public int getIdFalla() {
        return idFalla;
    }

    public void setIdFalla(int idFalla) {
        this.idFalla = idFalla;
    }

    public String getNombreFalla() {
        return nombreFalla;
    }

    public void setNombreFalla(String nombreFalla) {
        this.nombreFalla = nombreFalla;
    }

    public String getTipoFalla() {
        return tipoFalla;
    }

    public void setTipoFalla(String tipoFalla) {
        this.tipoFalla = tipoFalla;
    }

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
    
    
    
    
	
}
