package com.mantprev.services;

import java.util.List;

import com.mantprev.entidadesDTO.ConfigSpinners_DTO;



public interface ConfigSpnService {
	
	
	public List<String> getItemsStatusDeOTs(String idioma);
	
	public List<ConfigSpinners_DTO> getConfiguracSpinners(String idioma);
	
	public String actualizarEjecutorOTs(int idItemConf, String nombreEjecut, String idioma);
	
	public String actualizarClasificOTs(int idItemConf, String nombreClasif, String idioma);
	
	public String actualizarPrioridadOTs(int idItemConf, String nombrePriorid, String idioma);
	
	public String actualizarClasificFallasOTs(int idItemConf, String nombrClasificFalla, String idioma);
	
	public String actualizarConfigEmails(int idConfig, String configEmails, String idioma);
	
	
	//Utilizado por Web App
	public String actualizarListaEjectOTs(List<String> listEjecOTs, String idioma);
	
	public String actualizarListaClasificOTs(List<String> listClasificOTs, String idioma);
	
	public String actualizarListPrioridadsOTs(List<String> listPrioridsOTs, String idioma);
	
	public String actualizarListEstadosEquips(List<String> listEstadosEqu, String idioma);
	
	public String actualizarListClasificFallas(List<String> listClasificFall, String idioma);
	
	
	
}
