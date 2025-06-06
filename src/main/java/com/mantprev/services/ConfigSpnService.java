package com.mantprev.services;

import java.util.List;

import com.mantprev.entidades.ConfigSpinners;
import com.mantprev.entidadesDTO.ConfigSpinners_DTO;



public interface ConfigSpnService {
	
	
	public List<String> getItemsStatusDeOTs(int idEmpresa);
	
	public List<ConfigSpinners_DTO> getConfiguracSpinners(int idEmpresa);
	
	public String actualizarEjecutorOTs(int idItemConf, String nombreEjecut, int idEmpresa);
	
	public String actualizarClasificOTs(int idItemConf, String nombreClasif, String idioma);
	
	public String actualizarPrioridadOTs(int idItemConf, String nombrePriorid, String idioma);
	
	public String actualizarClasificFallasOTs(int idItemConf, String nombrClasificFalla, String idioma);
	
	public String actualizarConfigEmails(String configEmails, int idEmpresa);
	
	
	//Utilizado por Web App
	public String actualizarListaEjectOTs(List<String> listEjecOTs, int idEmpresa);
	
	public String actualizarListaClasificOTs(List<String> listClasificOTs, int idEmpresa);
	
	public String actualizarListPrioridadsOTs(List<String> listPrioridsOTs, int idEmpresa);
	
	public String actualizarListEstadosEquips(List<String> listEstadosEqu, int idEmpresa);
	
	public String actualizarListClasificFallas(List<String> listClasificFall, int idEmpresa);
	
	
	//Realizado al eleminar las config de spinner por idioma
	public String setConfigInicSpinners(List<ConfigSpinners> listConfigSpinn);
	
	
	
}
