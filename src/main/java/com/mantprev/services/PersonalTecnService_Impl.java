package com.mantprev.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.Personal_Tecnico;
import com.mantprev.entidadesDTO.PersonalTecn_DTO;
import com.mantprev.repositorios.PersonaTecnico_Repository;


@Service
public class PersonalTecnService_Impl implements PersonalTecnService {
	
	
	@Autowired
	PersonaTecnico_Repository persTecnico_Reposit;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Transactional(readOnly = true)
	@Override
	public List<PersonalTecn_DTO> getLstaDePersonalTecn(int idEmpresa) {
	/*****************************************************************/
		List<Personal_Tecnico> listaPersTecn = persTecnico_Reposit.getListaPersonalTecnico(idEmpresa);
		List<PersonalTecn_DTO> listaPersTecnDTO = new ArrayList<PersonalTecn_DTO>();
		
		for(int i=0; i<listaPersTecn.size(); i++) {
			
			Personal_Tecnico persTecn = listaPersTecn.get(i);
			PersonalTecn_DTO persTecnDTO = modelMapper.map(persTecn, PersonalTecn_DTO.class);
			listaPersTecnDTO.add(persTecnDTO);
		}
		
		return listaPersTecnDTO;
	}


	@Transactional
	@Override
	public String registrarNuevoTecnico(String nombrTecnico, String tipoEjecutor) {
	/*****************************************************************************/
		Personal_Tecnico nvoPersTecn = new Personal_Tecnico();
		nvoPersTecn.setNombreEmpl(nombrTecnico);
		nvoPersTecn.setTipoEjecutor(tipoEjecutor);
		
		try {
			persTecnico_Reposit.save(nvoPersTecn);
			return "EXITO";
			
		} catch (Exception ex) {
			return "Error al ingresar Datos";
		}
	}
	
	
	@Transactional
	@Override
	public String registrarNuevoTecnico2(PersonalTecn_DTO persTecn) {
	/***************************************************************/
		int idPersTec = persTecn.getIdEmpleado();
		
		if (idPersTec == 0) {
			Personal_Tecnico nvoPersTecn = new Personal_Tecnico();
			nvoPersTecn.setNombreEmpl(persTecn.getNombreEmpl());
			nvoPersTecn.setTipoEjecutor(persTecn.getTipoEjecutor());
			nvoPersTecn.setInformAdic(persTecn.getInformAdic());
			nvoPersTecn.setStatusPers("Activo");
			
			persTecnico_Reposit.save(nvoPersTecn);
			return "EXITO";
			
		} else {
			Personal_Tecnico persTecnico = persTecnico_Reposit.getPersonalTecnicoById(idPersTec);
			persTecnico.setNombreEmpl(persTecn.getNombreEmpl());
			persTecnico.setTipoEjecutor(persTecn.getTipoEjecutor());
			persTecnico.setInformAdic(persTecn.getInformAdic());
			
			persTecnico_Reposit.save(persTecnico);
			return "EXITO";
		}
	}


	@Transactional
	@Override
	public String actualizarDatosTecnico(String nombreTecn, String tipoEjecut, String nombrOrign) {
	/************************************************************************************/
		List<Personal_Tecnico> listPersTecn = persTecnico_Reposit.getPersonalTecnicoByName(nombrOrign);
		Personal_Tecnico persTecn = listPersTecn.get(0);
		
		persTecn.setNombreEmpl(nombreTecn);
		persTecn.setTipoEjecutor(tipoEjecut);
		
		try {
			persTecnico_Reposit.save(persTecn);
			return "EXITO";
			
		} catch (Exception ex) {
			return "FALLO EN ACTUALIZAR DATOS";
		}
	}


	@Transactional
	@Override
	public String eliminarPersTecnico(String nombreTecn) {
	//****************************************************
		List<Personal_Tecnico> listPersTecn = persTecnico_Reposit.getPersonalTecnicoByName(nombreTecn);
		Personal_Tecnico persTecn = listPersTecn.get(0);
		persTecn.setStatusPers("Inactivo");   //Inabilita al personal para que ya no aparesca en el listado de personal
		
		try {
			persTecnico_Reposit.save(persTecn);
			return "Eliminado";
			
		} catch (Exception ex) {
			return "NoEliminado";
		}
	}
	
	
	@Transactional
	@Override
	public String eliminarPersTecnico2(int idPersTec) {
	//************************************************
		Personal_Tecnico persTecn = persTecnico_Reposit.getPersonalTecnicoById(idPersTec);
		persTecn.setStatusPers("Inactivo");   //Inabilita al personal para que ya no aparesca en el listado de personal
		
		try {
			persTecnico_Reposit.save(persTecn);
			return "Eliminado";
			
		} catch (Exception ex) {
			return "NoEliminado";
		}
	}
	

	
	
	
	
	
}
