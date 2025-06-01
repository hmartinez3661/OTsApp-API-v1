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
	public List<String> getItemsStatusDeOTs(String idioma) {
	/****************************************/
		List<String> listaStatusOTs = new ArrayList<String>();
		String idiomaSpinners = idioma;
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	listaStatusOTs = configSpnEspa_Reposit.getItemsDeStatusDeOTs();;
	            break;
	
	        case "pt":  //Portuguez
	        	listaStatusOTs = configSpnPort_Reposit.getItemsDeStatusDeOTs();;
	            break;
	
	        default:  //es idioma Ingles
	        	listaStatusOTs = configSpnIngl_Reposit.getItemsDeStatusDeOTs();;
	    }
		
		return listaStatusOTs;
	}

	
	@Transactional(readOnly = true) 
	@Override
	public List<ConfigSpinners_DTO> getConfiguracSpinners(String idioma) { 
	/******************************************************/
		String idiomaSpinners = idioma;
		List<ConfigSpinners_DTO> listaConfigSpnDTO = new ArrayList<ConfigSpinners_DTO>();
		
		
		if (idiomaSpinners.equals("es")) {  //El idiona es Espanol
			List<ConfigSpn_Espa> configSpnEs = configSpnEspa_Reposit.getConfigSpinners();
    		
    		for(int i=0; i<configSpnEs.size(); i++) {
    			
    			ConfigSpn_Espa confSpn_esp = configSpnEs.get(i);
    			ConfigSpinners_DTO configSpnDTO = modelMapper.map(confSpn_esp, ConfigSpinners_DTO.class); 
    			listaConfigSpnDTO.add(configSpnDTO);
    		}
    		
		} else if (idiomaSpinners.equals("pt")) {  //El idiona es portugues
			
			List<ConfigSpn_Port> configSpnPort = configSpnPort_Reposit.getConfigSpinners();
        	
        	for(int i=0; i<configSpnPort.size(); i++) {
    			
        		ConfigSpn_Port confSpn_port = configSpnPort.get(i);
    			ConfigSpinners_DTO configSpnDTO = modelMapper.map(confSpn_port, ConfigSpinners_DTO.class); 
    			listaConfigSpnDTO.add(configSpnDTO);
    		}
        	
		} else if (idiomaSpinners.equals("en")) {  //El idiona es Ingles
			
			List<ConfigSpn_Ingl> configSpnIngl = configSpnIngl_Reposit.getConfigSpinners(); 
        	
        	for(int i=0; i<configSpnIngl.size(); i++) {
    			
        		ConfigSpn_Ingl confSpn_ingl = configSpnIngl.get(i);
    			ConfigSpinners_DTO configSpnDTO = modelMapper.map(confSpn_ingl, ConfigSpinners_DTO.class); 
    			listaConfigSpnDTO.add(configSpnDTO);
    		}
		}
		
		return listaConfigSpnDTO;
	} 
	

	@Transactional
	@Override
	public String actualizarEjecutorOTs(int idItemConf, String nombreEjecut, String idioma) {
	/***********************************************************************/
		String idiomaSpinners = idioma;
		String strReturn = "";
		
		if (nombreEjecut.equals("-")){
			nombreEjecut = "";
		}  
		
		if (idiomaSpinners.equals("es")) {
			
			Optional<ConfigSpn_Espa> configOptEs = configSpnEspa_Reposit.findById(idItemConf); 
        	
        	if (configOptEs != null) {
        		
        		ConfigSpn_Espa configSpn = configOptEs.get();
        		configSpn.setEjecutoresOTs(nombreEjecut);
        		
        		configSpnEspa_Reposit.save(configSpn);
        		strReturn = "EXITO";
        		
        	} else {
        		strReturn = "FALLO EN ACTUALIZACION";
        	}
			
		} else if (idiomaSpinners.equals("pt")) {
			Optional<ConfigSpn_Port> configPtOpt = configSpnPort_Reposit.findById(idItemConf);
        	
        	if (configPtOpt != null) {
        		
        		ConfigSpn_Port configSpn = configPtOpt.get();
        		configSpn.setEjecutoresOTs(nombreEjecut);
        		
        		configSpnPort_Reposit.save(configSpn);
        		strReturn = "EXITO";
        		
        	} else {
        		strReturn = "FALLO EN ACTUALIZACION";
        	}
			
		} else if (idiomaSpinners.equals("en")) {
			
			Optional<ConfigSpn_Ingl> configInglOpt = configSpnIngl_Reposit.findById(idItemConf);
        	
        	if (configInglOpt != null) {
        		
        		ConfigSpn_Ingl configSpn = configInglOpt.get();
        		configSpn.setEjecutoresOTs(nombreEjecut);
        		
        		configSpnIngl_Reposit.save(configSpn);
        		strReturn = "EXITO";
        		
        	} else {
        		strReturn = "FALLO EN ACTUALIZACION";
        	}
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
	public String actualizarConfigEmails(int idConfig, String configEmails, String idioma) {
	/***********************************************************************/
		String idiomaSpinners = idioma;
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	Optional<ConfigSpn_Espa> configOptEs = configSpnEspa_Reposit.findById(idConfig); //idConfi = 1
	        	
	        	if (configOptEs != null) {
	        		
	        		ConfigSpn_Espa configSpn = configOptEs.get();
	        		configSpn.setConfigCorreos(configEmails);
	        		
	        		configSpnEspa_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	            
	
	        case "pt":  //Portuguez
	        	Optional<ConfigSpn_Port> configPtOpt = configSpnPort_Reposit.findById(idConfig); //idConfi = 1
	        	
	        	if (configPtOpt != null) {
	        		
	        		ConfigSpn_Port configSpn = configPtOpt.get();
	        		configSpn.setConfigCorreos(configEmails);
	        		
	        		configSpnPort_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	
	        default:  //es idioma Ingles (en)
	        	Optional<ConfigSpn_Ingl> configInglOpt = configSpnIngl_Reposit.findById(idConfig); //idConfi = 1
	        	
	        	if (configInglOpt != null) {
	        		
	        		ConfigSpn_Ingl configSpn = configInglOpt.get();
	        		configSpn.setConfigCorreos(configEmails);
	        		
	        		configSpnIngl_Reposit.save(configSpn);
	        		return "EXITO";
	        		
	        	} else {
	        		return "FALLO EN ACTUALIZACION";
	        	}
	    }
	}


	@Transactional
	@Override
	public String actualizarListaEjectOTs(List<String> listEjecOTs, String idioma) {
	/*****************************************************************************/
		String idiomaSpinners = idioma;   
		
		if (idiomaSpinners.equals("es")) {
			List<ConfigSpn_Espa> listaConfGen = configSpnEspa_Reposit.getConfigSpinners();
			
			for(int i=0; i<listEjecOTs.size(); i++) {
				String ejecutor = listEjecOTs.get(i);
				listaConfGen.get(i).setEjecutoresOTs(ejecutor); 
			}
			configSpnEspa_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("pt")) {
			List<ConfigSpn_Port> listaConfGen = configSpnPort_Reposit.getConfigSpinners();

			for(int i=0; i<listEjecOTs.size(); i++) {
				String ejecutor = listEjecOTs.get(i);
				listaConfGen.get(i).setEjecutoresOTs(ejecutor); 
			}
			configSpnPort_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("en")) {
			List<ConfigSpn_Ingl> listaConfGen = configSpnIngl_Reposit.getConfigSpinners();

			for(int i=0; i<listEjecOTs.size(); i++) {
				String ejecutor = listEjecOTs.get(i);
				listaConfGen.get(i).setEjecutoresOTs(ejecutor); 
			}
			configSpnIngl_Reposit.saveAll(listaConfGen);
		}
		
		return "OK";
	}


	@Transactional
	@Override
	public String actualizarListaClasificOTs(List<String> listClasificOTs, String idioma) {
	/*************************************************************************************/
		String idiomaSpinners = idioma;   
		
		if (idiomaSpinners.equals("es")) {
			List<ConfigSpn_Espa> listaConfGen = configSpnEspa_Reposit.getConfigSpinners();
			
			for(int i=0; i<listClasificOTs.size(); i++) {
				String clasific = listClasificOTs.get(i);
				listaConfGen.get(i).setClasificTrabOTs(clasific); 
			}
			configSpnEspa_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("pt")) {
			List<ConfigSpn_Port> listaConfGen = configSpnPort_Reposit.getConfigSpinners();

			for(int i=0; i<listClasificOTs.size(); i++) {
				String clasific = listClasificOTs.get(i);
				listaConfGen.get(i).setClasificTrabOTs(clasific); 
			}
			configSpnPort_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("en")) {
			List<ConfigSpn_Ingl> listaConfGen = configSpnIngl_Reposit.getConfigSpinners();

			for(int i=0; i<listClasificOTs.size(); i++) {
				String clasific = listClasificOTs.get(i);
				listaConfGen.get(i).setClasificTrabOTs(clasific); 
			}
			configSpnIngl_Reposit.saveAll(listaConfGen);
		}
		
		return "OK";
	}


	@Transactional
	@Override
	public String actualizarListPrioridadsOTs(List<String> listPrioridsOTs, String idioma) {
	/*******************************************************************************/
		String idiomaSpinners = idioma;   
		
		if (idiomaSpinners.equals("es")) {
			List<ConfigSpn_Espa> listaConfGen = configSpnEspa_Reposit.getConfigSpinners();
			
			for(int i=0; i<listPrioridsOTs.size(); i++) {
				String prioridad = listPrioridsOTs.get(i);
				listaConfGen.get(i).setPrioridTrabOTs(prioridad); 
			}
			configSpnEspa_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("pt")) {
			List<ConfigSpn_Port> listaConfGen = configSpnPort_Reposit.getConfigSpinners();

			for(int i=0; i<listPrioridsOTs.size(); i++) {
				String prioridad = listPrioridsOTs.get(i);
				listaConfGen.get(i).setPrioridTrabOTs(prioridad); 
			}
			configSpnPort_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("en")) {
			List<ConfigSpn_Ingl> listaConfGen = configSpnIngl_Reposit.getConfigSpinners();

			for(int i=0; i<listPrioridsOTs.size(); i++) {
				String prioridad = listPrioridsOTs.get(i);
				listaConfGen.get(i).setPrioridTrabOTs(prioridad); 
			}
			configSpnIngl_Reposit.saveAll(listaConfGen);
		}
		
		return "OK";
	}


	@Transactional
	@Override
	public String actualizarListEstadosEquips(List<String> listEstadosEqu, String idioma) {
	/***********************************************************************************/
		String idiomaSpinners = idioma;   
		
		if (idiomaSpinners.equals("es")) {
			List<ConfigSpn_Espa> listaConfGen = configSpnEspa_Reposit.getConfigSpinners();
			
			for(int i=0; i<listEstadosEqu.size(); i++) {
				String estado = listEstadosEqu.get(i);
				listaConfGen.get(i).setEstadoEquipo(estado); 
			}
			configSpnEspa_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("pt")) {
			List<ConfigSpn_Port> listaConfGen = configSpnPort_Reposit.getConfigSpinners();

			for(int i=0; i<listEstadosEqu.size(); i++) {
				String estado = listEstadosEqu.get(i);
				listaConfGen.get(i).setEstadoEquipo(estado); 
			}
			configSpnPort_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("en")) {
			List<ConfigSpn_Ingl> listaConfGen = configSpnIngl_Reposit.getConfigSpinners();

			for(int i=0; i<listEstadosEqu.size(); i++) {
				String estado = listEstadosEqu.get(i);
				listaConfGen.get(i).setEstadoEquipo(estado); 
			}
			configSpnIngl_Reposit.saveAll(listaConfGen);
		}
		
		return "OK";
	}

	
	@Transactional
	@Override
	public String actualizarListClasificFallas(List<String> listClasificFall, String idioma) {
	/**************************************************************************************/
		String idiomaSpinners = idioma;   
		
		if (idiomaSpinners.equals("es")) {
			List<ConfigSpn_Espa> listaConfGen = configSpnEspa_Reposit.getConfigSpinners();
			
			for(int i=0; i<listClasificFall.size(); i++) {
				String clasific = listClasificFall.get(i);
				listaConfGen.get(i).setClasificFallas(clasific); 
			}
			configSpnEspa_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("pt")) {
			List<ConfigSpn_Port> listaConfGen = configSpnPort_Reposit.getConfigSpinners();

			for(int i=0; i<listClasificFall.size(); i++) {
				String clasific = listClasificFall.get(i);
				listaConfGen.get(i).setClasificFallas(clasific); 
			}
			configSpnPort_Reposit.saveAll(listaConfGen);
			
		} else if (idiomaSpinners.equals("en")) {
			List<ConfigSpn_Ingl> listaConfGen = configSpnIngl_Reposit.getConfigSpinners();

			for(int i=0; i<listClasificFall.size(); i++) {
				String clasific = listClasificFall.get(i);
				listaConfGen.get(i).setClasificFallas(clasific);
			}
			configSpnIngl_Reposit.saveAll(listaConfGen);
		}
		
		return "OK";
	}


	@Override
	public String setConfigInicSpinners(ConfigSpinners configSpinn) {
	/****************************************************************/
		configSpinner_Reposit.save(configSpinn);
		return "Exito";
	}


	

	
	
	
	
	
	
	

	
	
	
	
	
	
	
}
