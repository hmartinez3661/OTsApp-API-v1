package com.mantprev.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.RegistroFallas;
import com.mantprev.entidades.RegistroFallas_Espa;
import com.mantprev.entidades.RegistroFallas_Ingl;
import com.mantprev.entidades.RegistroFallas_Port;
import com.mantprev.entidadesDTO.RegistroFallasDTO;
import com.mantprev.repositorios.RegistroFallasEspa_Repository;
import com.mantprev.repositorios.RegistroFallasIngl_Repository;
import com.mantprev.repositorios.RegistroFallasPort_Repository;
import com.mantprev.repositorios.RegistroFallas_Repository;



@Service
public class ListaFallasService_Impl implements ListaFallasService {

	
	@Autowired
	RegistroFallasEspa_Repository listaFallasEspa_Reposit;
	
	@Autowired
	RegistroFallasIngl_Repository listaFallasIngl_Reposit;
	
	@Autowired
	RegistroFallasPort_Repository listaFallasPort_Reposit;
	
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
	public String registrarNuevaFalla(String nombrFalla, String tipoFalla, String idioma) {
	/***********************************************************************/
		String idiomaSpinners = idioma;
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	
	        	RegistroFallas_Espa nuevaFallaEspa = new RegistroFallas_Espa();
	        	nuevaFallaEspa.setNombreFalla(nombrFalla);
	        	nuevaFallaEspa.setTipoFalla(tipoFalla); 
	        	
	        	try {
	        		listaFallasEspa_Reposit.save(nuevaFallaEspa);
	        		return "EXITO";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO REGISTRAR FALLA";
	        	}
	
	        	
	        case "pt":  //Portuguez
	        	RegistroFallas_Port nuevaFallaPort = new RegistroFallas_Port();
	        	nuevaFallaPort.setNombreFalla(nombrFalla);
	        	nuevaFallaPort.setTipoFalla(tipoFalla); 
	        	
	        	try {
	        		listaFallasPort_Reposit.save(nuevaFallaPort);
	        		return "EXITO";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO REGISTRAR FALLA";
	        	}
	
	        	
	        default:  //es idioma Ingles (en)
	        	RegistroFallas_Ingl nuevaFallaIngl = new RegistroFallas_Ingl();
	        	nuevaFallaIngl.setNombreFalla(nombrFalla);
	        	nuevaFallaIngl.setTipoFalla(tipoFalla); 
	        	
	        	try {
	        		listaFallasIngl_Reposit.save(nuevaFallaIngl);
	        		return "EXITO";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO REGISTRAR FALLA";
	        	}
	    }
	}


	@Transactional
	@Override        //Eliminar Falla en Androi
	public String eliminarRegistroFalla(String nombreFalla, String idioma) {
	/********************************************************/
		String idiomaSpinners = idioma;
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	RegistroFallas_Espa registroFallaEsp = listaFallasEspa_Reposit.getRegistroFallasEspa(nombreFalla); 
	        	
	        	try {
	        		listaFallasEspa_Reposit.delete(registroFallaEsp);
	        		return "Eliminado";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO ELIMINAR FALLA";
	        	}
	        	
	        case "pt":  //Portuguez
	        	RegistroFallas_Port registroFallaPort = listaFallasPort_Reposit.getRegistroFallasPort(nombreFalla); 
	        	
	        	try {
	        		listaFallasPort_Reposit.delete(registroFallaPort);
	        		return "Eliminado";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO ELIMINAR FALLA";
	        	}
	        	
	        default:  //es idioma Ingles (en)
	        	RegistroFallas_Ingl registroFallaIngl = listaFallasIngl_Reposit.getRegistroFallasIngl(nombreFalla); 
	        	
	        	try {
	        		listaFallasIngl_Reposit.delete(registroFallaIngl);
	        		return "Eliminado";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO ELIMINAR FALLA";
	        	}
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
