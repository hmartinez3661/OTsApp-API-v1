
package com.mantprev.entidades;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
//import lombok.Data;
import lombok.NoArgsConstructor;


//@Data
@Builder
@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
@Entity
@Table(name = "usuarios")  //, uniqueConstraints = {@UniqueConstraint(columnNames = {"emailUsuario"})}  
public class Usuarios implements UserDetails{
//******************************************* 
    
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int idUsuario;
    
    private String nombreUsuario;
    private String emailUsuario;
    private String passwordEncrip;
    private String passwordNormal;
    private String userRol;
    private String telefonoUser;
    private String nombreEmpresa;
    private int    idEmpresa;
    private String paisEmpresa;
    private String idiomaGrupo;
    private String simbMoneda;
    private String codigoPais;
    private int    cantMaxUsers;
    private Date   fechaSuscrip;
    private Date   fechaFnlSuscrip;
    
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList(); 
	}
	
	@Override
	public String getPassword() {
		return passwordEncrip;
	}
	
	@Override
	public String getUsername() {
		return emailUsuario; 
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	
	//*******************************************
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getPasswordEncrip() {
		return passwordEncrip;
	}

	public void setPasswordEncrip(String passwordEncrip) {
		this.passwordEncrip = passwordEncrip;
	}

	public String getPasswordNormal() {
		return passwordNormal;
	}

	public void setPasswordNormal(String passwordNormal) {
		this.passwordNormal = passwordNormal;
	}

	public String getUserRol() {
		return userRol;
	}

	public void setUserRol(String userRol) {
		this.userRol = userRol;
	}

	public String getTelefonoUser() {
		return telefonoUser;
	}

	public void setTelefonoUser(String telefonoUser) {
		this.telefonoUser = telefonoUser;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdiomaGrupo() {
		return idiomaGrupo;
	}

	public void setIdiomaGrupo(String idiomaGrupo) {
		this.idiomaGrupo = idiomaGrupo;
	}

	public int getCantMaxUsers() {
		return cantMaxUsers;
	}

	public void setCantMaxUsers(int cantMaxUsers) {
		this.cantMaxUsers = cantMaxUsers;
	}

	public Date getFechaSuscrip() {
		return fechaSuscrip;
	}

	public void setFechaSuscrip(Date fechaSuscrip) {
		this.fechaSuscrip = fechaSuscrip;
	}

	public Date getFechaFnlSuscrip() {
		return fechaFnlSuscrip;
	}

	public void setFechaFnlSuscrip(Date fechaFnlSuscrip) {
		this.fechaFnlSuscrip = fechaFnlSuscrip;
	}

	public String getPaisEmpresa() {
		return paisEmpresa;
	}

	public void setPaisEmpresa(String paisEmpresa) {
		this.paisEmpresa = paisEmpresa;
	}

	public String getSimbMoneda() {
		return simbMoneda;
	}

	public void setSimbMoneda(String simbMoneda) {
		this.simbMoneda = simbMoneda;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	

	
    
    
    
	
	
	
	
	
	
	
	
	
   
    
    
    
    
    
}
