package com.mantprev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.ConfigSpinners;
import com.mantprev.entidades.ConfigSpn_Espa;
import com.mantprev.entidades.ConfigSpn_Ingl;
import com.mantprev.entidades.ConfigSpn_Port;
import com.mantprev.entidadesDTO.ConfigSpinners_DTO;
import com.mantprev.repositorios.ConfigSpinner_Repository;
import com.mantprev.repositorios.ConfigSpnEspa_Repository;
import com.mantprev.repositorios.ConfigSpnIngl_Repository;
import com.mantprev.repositorios.ConfigSpnPort_Repository;


@Service
public class ConfigSpnService_Impl implements ConfigSpnService{
	
	
	@Autowired
	private ConfigSpinner_Repository configSpinner_Reposit;
	
	@Autowired
	private ConfigSpnEspa_Repository configSpnEspa_Reposit;
	
	@Autowired
	private ConfigSpnIngl_Repository configSpnIngl_Reposit;
	
	@Autowired
	private ConfigSpnPort_Repository configSpnPort_Reposit;   
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Transactional(readOnly = true) 
	@Override
	public List<String> getItemsStatusDeOTs(int idEmpresa) {
	/*****************************************************/
		List<String> listaStatusOTs = new ArrayList<String>();
		
		listaStatusOTs = configSpinner_Reposit.getItemsDeStatusDeOTs(idEmpresa);
		
		return listaStatusOTs;
	}

	
	@Transactional(readOnly = true) 
	@Override
	public List<ConfigSpinners_DTO> getConfiguracSpinners(int idEmpresa) { 
	/*****************************************************************/
		List<ConfigSpinners_DTO> listaConfigSpnDTO = new ArrayList<ConfigSpinners_DTO>();
		List<ConfigSpinners> listConfigSpnn = configSpinner_Reposit.getListConfigSpinners(idEmpresa);
		
		for(int i=0; i<listConfigSpnn.size(); i++) {
			
			ConfigSpinners confSpn = listConfigSpnn.get(i);
			ConfigSpinners_DTO configSpnDTO = modelMapper.map(confSpn, ConfigSpinners_DTO.class); 
			listaConfigSpnDTO.add(configSpnDTO);
		}
		
		return listaConfigSpnDTO;
	} 
	

	@Transactional
	@Override
	public String actualizarEjecutorOTs(int idItemConf, String nombreEjecut, int idEmpresa) {
	/***********************************************************************/
		
		String strReturn = "";
		
		if (nombreEjecut.equals("-")){
			nombreEjecut = "";
		}  
		
		Optional<ConfigSpinners> configOpt = configSpinner_Reposit.findById(idItemConf); 
    	
    	if (configOpt != null) {
    		
    		ConfigSpinners configSpn = configOpt.get();
    		configSpn.setEjecutoresOTs(nombreEjecut);
    		
    		configSpinner_Reposit.save(configSpn);
    		strReturn = "EXITO";
    		
    	} else {
    		strReturn = "FALLO EN ACTUALIZACION";
    	}
		
		return strReturn;
	}

	
	@Transactional
	@Override
	public String actualizarClasificOTs(int idItemConf, String nombreClasif, String idioma) {
	/***********************************************************************/
		String idiomaSpinners = idioma;
		
		if (nombreClasif.equals("-")){
			nombreClasif = "";
		}  
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	Optional<ConfigSpn_Espa> configOptEs = configSpnEspa_Reposit.findById(idItemConf); 
	        	
	        	if (configOptEs != null) {
	        		
	        		ConfigSpn_Espa configSpn = configOptEs.get();
	        		configSpn.setClasificTrabOTs(nombreClasif);
	        		
	        		configSpnEspa_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	            
	
	        case "pt":  //Portuguez
	        	Optional<ConfigSpn_Port> configPtOpt = configSpnPort_Reposit.findById(idItemConf);
	        	
	        	if (configPtOpt != null) {
	        		
	        		ConfigSpn_Port configSpn = configPtOpt.get();
	        		configSpn.setClasificTrabOTs(nombreClasif);
	        		
	        		configSpnPort_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	
	        default:  //es idioma Ingles (en)
	        	Optional<ConfigSpn_Ingl> configInglOpt = configSpnIngl_Reposit.findById(idItemConf);
	        	
	        	if (configInglOpt != null) {
	        		
	        		ConfigSpn_Ingl configSpn = configInglOpt.get();
	        		configSpn.setClasificTrabOTs(nombreClasif);
	        		
	        		configSpnIngl_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	    }
	}


	@Transactional
	@Override
	public String actualizarPrioridadOTs(int idItemConf, String nombrePriorid, String idioma) {
	/*************************************************************************/
		String idiomaSpinners = idioma;
		
		if (nombrePriorid.equals("-")){
			nombrePriorid = "";
		}  
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	Optional<ConfigSpn_Espa> configOptEs = configSpnEspa_Reposit.findById(idItemConf); 
	        	
	        	if (configOptEs != null) {
	        		
	        		ConfigSpn_Espa configSpn = configOptEs.get();
	        		configSpn.setPrioridTrabOTs(nombrePriorid);
	        		
	        		configSpnEspa_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	            
	
	        case "pt":  //Portuguez
	        	Optional<ConfigSpn_Port> configPtOpt = configSpnPort_Reposit.findById(idItemConf);
	        	
	        	if (configPtOpt != null) {
	        		
	        		ConfigSpn_Port configSpn = configPtOpt.get();
	        		configSpn.setPrioridTrabOTs(nombrePriorid);
	        		
	        		configSpnPort_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	
	        default:  //es idioma Ingles (en)
	        	Optional<ConfigSpn_Ingl> configInglOpt = configSpnIngl_Reposit.findById(idItemConf);
	        	
	        	if (configInglOpt != null) {
	        		
	        		ConfigSpn_Ingl configSpn = configInglOpt.get();
	        		configSpn.setPrioridTrabOTs(nombrePriorid);
	        		
	        		configSpnIngl_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	    }
	}


	@Transactional
	@Override
	public String actualizarClasificFallasOTs(int idItemConf, String nombrClasificFalla, String idioma) {
	/*************************************************************************************/
		String idiomaSpinners = idioma;
		
		if (nombrClasificFalla.equals("-")){
			nombrClasificFalla = "";
		}  
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	Optional<ConfigSpn_Espa> configOptEs = configSpnEspa_Reposit.findById(idItemConf); 
	        	
	        	if (configOptEs != null) {
	        		
	        		ConfigSpn_Espa configSpn = configOptEs.get();
	        		configSpn.setClasificFallas(nombrClasificFalla);
	        		
	        		configSpnEspa_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	            
	
	        case "pt":  //Portuguez
	        	Optional<ConfigSpn_Port> configPtOpt = configSpnPort_Reposit.findById(idItemConf);
	        	
	        	if (configPtOpt != null) {
	        		
	        		ConfigSpn_Port configSpn = configPtOpt.get();
	        		configSpn.setClasificFallas(nombrClasificFalla);
	        		
	        		configSpnPort_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	
	        default:  //es idioma Ingles (en)
	        	Optional<ConfigSpn_Ingl> configInglOpt = configSpnIngl_Reposit.findById(idItemConf);
	        	
	        	if (configInglOpt != null) {
	        		
	        		ConfigSpn_Ingl configSpn = configInglOpt.get();
	        		configSpn.setClasificFallas(nombrClasificFalla);
	        		
	        		configSpnIngl_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	    }
	}


	@Transactional
	@Override
	public String actualizarConfigEmails(String configEmails, int idEmpresa) {
	/************************************************************************/
		ConfigSpinners configSpin = configSpinner_Reposit.getConfigSpinners(idEmpresa);
		
		configSpin.setConfigCorreos(configEmails);
		configSpinner_Reposit.save(configSpin);
		return "EXITO";
	}


	@Transactional
	@Override
	public String actualizarListaEjectOTs(List<String> listEjecOTs, int idEmpresa) {
	/*****************************************************************************/
		List<ConfigSpinners> listaConfGen = configSpinner_Reposit.getListConfigSpinners(idEmpresa);
		
		for(int i=0; i<listaConfGen.size(); i++) {
			
			if (i < listEjecOTs.size()) {
				
				String config = listEjecOTs.get(i);
				listaConfGen.get(i).setEjecutoresOTs(config);
				
			} else {
				listaConfGen.get(i).setEjecutoresOTs("");
			}
		}
		
		configSpinner_Reposit.saveAll(listaConfGen);
		return "OK";
	}


	@Transactional
	@Override
	public String actualizarListaClasificOTs(List<String> listClasificOTs, int idEmpresa) {
	/*************************************************************************************/
		List<ConfigSpinners> listaConfGen = configSpinner_Reposit.getListConfigSpinners(idEmpresa);
 		
		
		for(int i=1; i<listaConfGen.size(); i++) {
			
			if (i < listClasificOTs.size()) {
				
				String clasific = listClasificOTs.get(i);
				listaConfGen.get(i).setClasificTrabOTs(clasific);
				
			} else {
				listaConfGen.get(i).setClasificTrabOTs("");
			}
		}
		
		configSpinner_Reposit.saveAll(listaConfGen);
		return "OK";
	}


	@Transactional
	@Override
	public String actualizarListPrioridadsOTs(List<String> listPrioridsOTs, int idEmpresa) {
	/*******************************************************************************/
		List<ConfigSpinners> listaConfGen = configSpinner_Reposit.getListConfigSpinners(idEmpresa);
		
		for(int i=0; i<listaConfGen.size(); i++) {
			
			if (i < listPrioridsOTs.size()) {
				
				String config = listPrioridsOTs.get(i);
				listaConfGen.get(i).setPrioridTrabOTs(config);
				
			} else {
				listaConfGen.get(i).setPrioridTrabOTs("");
			}
		}
		
		configSpinner_Reposit.saveAll(listaConfGen);
		return "OK";
	}


	@Transactional
	@Override
	public String actualizarListEstadosEquips(List<String> listEstadosEqu, int idEmpresa) {
	/***********************************************************************************/
		List<ConfigSpinners> listaConfGen = configSpinner_Reposit.getListConfigSpinners(idEmpresa);
		
		for(int i=0; i<listaConfGen.size(); i++) {
			
			if (i < listEstadosEqu.size()) {
				
				String config = listEstadosEqu.get(i);
				listaConfGen.get(i).setEstadoEquipo(config);
				
			} else {
				listaConfGen.get(i).setEstadoEquipo("");
			}
		}
		
		configSpinner_Reposit.saveAll(listaConfGen);
		return "OK";
	}

	
	@Transactional
	@Override
	public String actualizarListClasificFallas(List<String> listClasificFall, int idEmpresa) {
	/**************************************************************************************/
		List<ConfigSpinners> listaConfGen = configSpinner_Reposit.getListConfigSpinners(idEmpresa);
		
		for(int i=0; i<listaConfGen.size(); i++) {
			
			if (i < listClasificFall.size()) {
				
				String config = listClasificFall.get(i);
				listaConfGen.get(i).setClasificFallas(config);
				
			} else {
				listaConfGen.get(i).setClasificFallas("");
			}
		}
		
		configSpinner_Reposit.saveAll(listaConfGen);
		return "OK";
	}


	@Override
	public String setConfigInicSpinners(List<ConfigSpinners> listConfigSpinn) {
	/****************************************************************/
		configSpinner_Reposit.saveAll(listConfigSpinn);
		return "Exito";
	}


	

	
	
	
	
	
	
	

	
	
	
	
	
	
	
}
