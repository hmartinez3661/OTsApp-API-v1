
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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
@Entity
@Table(name = "personal_tecn")
public class Personal_Tecnico{
//****************************
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int idEmpleado;
    
    private String nombreEmpl;
    private String tipoEjecutor;
    private String email;
    private String fotoEmpl;
    private String informAdic;
    private String statusPers;  // Activo -- Inactivo
    
    
    /* RELACION ENTRE TABLAS *********************************/
    
    @JsonIgnore
    @OneToMany (mappedBy = "personal_tecn", fetch = FetchType.LAZY) 
    private List<ReptesPersEjecOTs> listRtesPersEjecOT;
    
    
    public List<ReptesPersEjecOTs> getListRtesPersEjecOT() {
		return listRtesPersEjecOT;
	}
    
	public void setListRtesPersEjecOT(List<ReptesPersEjecOTs> listRtesPersEjecOT) {
		this.listRtesPersEjecOT = listRtesPersEjecOT;
	}
    
    
   /* RELACION ENTRE TABLAS *********************************
    
    @JsonIgnore
    @OneToMany (mappedBy = "empleado", fetch = FetchType.LAZY) 
    private List<ReptesPersEjecOTs> listRtesPersEjecOT;
    
    
    public List<ReptesPersEjecOTs> getListRtesPersEjecOT() {
		return listRtesPersEjecOT;
	}
	public void setListRtesPersEjecOT(List<ReptesPersEjecOTs> listRtesPersEjecOT) {
		this.listRtesPersEjecOT = listRtesPersEjecOT;
	}

	//*********************************************************
	
	@JsonIgnore
	@OneToMany (mappedBy = "empleado", fetch = FetchType.LAZY)
	private List<ReptesPersEjecRuts> listRtesPersEjecRut;
	
	
	public List<ReptesPersEjecRuts> getListRtesPersEjecRut() {
		return listRtesPersEjecRut;
	}
	
	public void setListRtesPersEjecRut(List<ReptesPersEjecRuts> listRtesPersEjecRut) {
		this.listRtesPersEjecRut = listRtesPersEjecRut;
	}
	
	********************************************************/ 
    
    
    
    
    //GETTERS AND SETTERS
    
	public int getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public String getNombreEmpl() {
		return nombreEmpl;
	}
	
	public void setNombreEmpl(String nombreEmpl) {
		this.nombreEmpl = nombreEmpl;
	}
	
	public String getTipoEjecutor() {
		return tipoEjecutor;
	}
	
	public void setTipoEjecutor(String tipoEjecutor) {
		this.tipoEjecutor = tipoEjecutor;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFotoEmpl() {
		return fotoEmpl;
	}
	
	public void setFotoEmpl(String fotoEmpl) {
		this.fotoEmpl = fotoEmpl;
	}
	
	public String getInformAdic() {
		return informAdic;
	}
	
	public void setInformAdic(String informAdic) {
		this.informAdic = informAdic;
	}

	public String getStatusPers() {
		return statusPers;
	}

	public void setStatusPers(String statusPers) {
		this.statusPers = statusPers;
	}

	
    
    
    
   
    
	
	
    
    
   

	
    
 

    
  
    
    
    


    
    
    
    
}
