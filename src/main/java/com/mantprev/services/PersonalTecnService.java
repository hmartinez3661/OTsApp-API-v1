package com.mantprev.services;

import java.util.List;

import com.mantprev.entidadesDTO.PersonalTecn_DTO;


public interface PersonalTecnService {
	
	public List<PersonalTecn_DTO> getLstaDePersonalTecn(int idEmpresa); 
	
	public String registrarNuevoTecnico(String nombrTecnico, String tipoEjecutor);
	
	public String registrarNuevoTecnico2(PersonalTecn_DTO persTecn);
	
	public String actualizarDatosTecnico(String nombreTecn, String tipoEjecut, String nombrOrign); 
	
	public String eliminarPersTecnico(String nombreTecn); 
	
	public String eliminarPersTecnico2(int idPersTec); 
	
	
	
	
}
