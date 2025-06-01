
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
@Table(name = "config_spinners")
public class ConfigSpinners {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	
	private String estatusOTs;
	private String ejecutoresOTs;
	private String clasificTrabOTs;
	private String prioridTrabOTs;
	private String estadoEquipo;
	private String clasificFallas;
	private String rolesUsers;
	private String configCorreos;
	private String msgsWhatsApp;
	private int idEmpresa;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEstatusOTs() {
		return estatusOTs;
	}
	
	public void setEstatusOTs(String estatusOTs) {
		this.estatusOTs = estatusOTs;
	}
	
	public String getEjecutoresOTs() {
		return ejecutoresOTs;
	}
	
	public void setEjecutoresOTs(String ejecutoresOTs) {
		this.ejecutoresOTs = ejecutoresOTs;
	}
	
	public String getClasificTrabOTs() {
		return clasificTrabOTs;
	}
	
	public void setClasificTrabOTs(String clasificTrabOTs) {
		this.clasificTrabOTs = clasificTrabOTs;
	}
	
	public String getPrioridTrabOTs() {
		return prioridTrabOTs;
	}
	
	public void setPrioridTrabOTs(String prioridTrabOTs) {
		this.prioridTrabOTs = prioridTrabOTs;
	}
	
	public String getEstadoEquipo() {
		return estadoEquipo;
	}
	
	public void setEstadoEquipo(String estadoEquipo) {
		this.estadoEquipo = estadoEquipo;
	}
	
	public String getClasificFallas() {
		return clasificFallas;
	}
	
	public void setClasificFallas(String clasificFallas) {
		this.clasificFallas = clasificFallas;
	}
	
	public String getRolesUsers() {
		return rolesUsers;
	}
	
	public void setRolesUsers(String rolesUsers) {
		this.rolesUsers = rolesUsers;
	}
	
	public String getConfigCorreos() {
		return configCorreos;
	}
	
	public void setConfigCorreos(String configCorreos) {
		this.configCorreos = configCorreos;
	}
	
	public String getMsgsWhatsApp() {
		return msgsWhatsApp;
	}
	
	public void setMsgsWhatsApp(String msgsWhatsApp) {
		this.msgsWhatsApp = msgsWhatsApp;
	}
	
	public int getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
	
	
	
	
}
