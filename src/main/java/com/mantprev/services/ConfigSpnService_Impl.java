package com.mantprev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.ConfigSpinners;
import com.mantprev.entidadesDTO.ConfigSpinners_DTO;
import com.mantprev.repositorios.ConfigSpinner_Repository;


@Service
public class ConfigSpnService_Impl implements ConfigSpnService{
	
	
	@Autowired
	private ConfigSpinner_Repository configSpinner_Reposit;
	
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
