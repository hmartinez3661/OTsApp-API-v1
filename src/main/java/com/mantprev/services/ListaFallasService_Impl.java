package com.mantprev.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.RegistroFallas;
import com.mantprev.entidadesDTO.RegistroFallasDTO;
import com.mantprev.repositorios.RegistroFallas_Repository;



@Service
public class ListaFallasService_Impl implements ListaFallasService {
	
	
	@Autowired
	RegistroFallas_Repository registroFallas_Reposit;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	
	@Transactional(readOnly = true)
	@Override
	public List<RegistroFallasDTO> getListaDeFallas(int idEmpresa) {
	/**************************************************************/
		List<RegistroFallasDTO> registroFallasDTO = new ArrayList<RegistroFallasDTO>();
		
		List<RegistroFallas> listaFallas = registroFallas_Reposit.getListaDeFallas(idEmpresa);
		
		for(int i=0; i<listaFallas.size(); i++) {
			
			RegistroFallas detalleFall = listaFallas.get(i);
			RegistroFallasDTO detalleFallaDTO   = modelMapper.map(detalleFall, RegistroFallasDTO.class); 
			registroFallasDTO.add(detalleFallaDTO);
		}
		
		return registroFallasDTO;
	}


	@Transactional
	@Override
	public String registrarNuevaFalla(String nombrFalla, String tipoFalla, int idEmpresa) {
	/***********************************************************************/
		RegistroFallas nuevaFalla = new RegistroFallas();
    	nuevaFalla.setNombreFalla(nombrFalla);
    	nuevaFalla.setTipoFalla(tipoFalla); 
    	nuevaFalla.setIdEmpresa(idEmpresa); 
    	
    	try {
    		registroFallas_Reposit.save(nuevaFalla);
    		return "EXITO";
    		
    	} catch (Exception exp) {
    		return "FALLO REGISTRAR FALLA";
    	}
	}


	@Transactional
	@Override        //Eliminar Falla en Androi
	public String eliminarRegistroFalla(String nombreFalla, int idEmpresa) {
	/********************************************************/
		List<RegistroFallas> registroFallaList = registroFallas_Reposit.getRegistroFallas(nombreFalla, idEmpresa);
		RegistroFallas registroFalla = registroFallaList.get(0);
    	
    	try {
    		registroFallas_Reposit.delete(registroFalla);
    		return "Eliminado";
    		
    	} catch (Exception exp) {
    		return "FALLO ELIMINAR FALLA";
    	}
	}


	@Transactional
	@Override
	public int guardarDescripcFalla(RegistroFallasDTO descripFalla, int idEmpresa) {
	/****************************************************************************/
		RegistroFallas nuevaFalla = modelMapper.map(descripFalla, RegistroFallas.class);
		nuevaFalla.setIdEmpresa(idEmpresa); 
    	
    	try {
    		registroFallas_Reposit.save(nuevaFalla);
    		return nuevaFalla.getIdFalla();
    		
    	} catch (Exception exp) {
    		return 0;
    	}
	}


	@Transactional
	@Override         //Eliminar falla en opcion Web
	public String eliminarRegistroFalla2(int idFalla, int idEmpresa) {
	/***************************************************************/	
		try {
			registroFallas_Reposit.deleteById(idFalla); 
    		return "Exito";
    		
    	} catch (Exception exp) {
    		return "FALLO ELIMINAR FALLA";
    	}
	}


	@Override
	public String saveListaInicFallas(List<RegistroFallas> listaInicFallas) {
	/**********************************************************************/
		registroFallas_Reposit.saveAll(listaInicFallas);
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
