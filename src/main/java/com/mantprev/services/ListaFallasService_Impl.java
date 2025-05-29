package com.mantprev.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.RegistroFallas_Espa;
import com.mantprev.entidades.RegistroFallas_Ingl;
import com.mantprev.entidades.RegistroFallas_Port;
import com.mantprev.entidadesDTO.RegistroFallasDTO;
import com.mantprev.repositorios.RegistroFallasEspa_Repository;
import com.mantprev.repositorios.RegistroFallasIngl_Repository;
import com.mantprev.repositorios.RegistroFallasPort_Repository;



@Service
public class ListaFallasService_Impl implements ListaFallasService {

	
	@Autowired
	RegistroFallasEspa_Repository listaFallasEspa_Reposit;
	
	@Autowired
	RegistroFallasIngl_Repository listaFallasIngl_Reposit;
	
	@Autowired
	RegistroFallasPort_Repository listaFallasPort_Reposit;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	
	@Transactional(readOnly = true)
	@Override
	public List<RegistroFallasDTO> getListaDeFallas(String idioma) {
	/***********************************************/
		String idiomaSpinners = idioma;
		List<RegistroFallasDTO> registroFallasDTO = new ArrayList<RegistroFallasDTO>();
		
		if(idiomaSpinners.equals("es")) {  //Español
			
			List<RegistroFallas_Espa> listaFallasEspa = (List<RegistroFallas_Espa>) listaFallasEspa_Reposit.getListaDeFallasEspa();
    		
    		for(int i=0; i<listaFallasEspa.size(); i++) {
    			
    			RegistroFallas_Espa detalleFall_esp = listaFallasEspa.get(i);
    			RegistroFallasDTO detalleFallaDTO   = modelMapper.map(detalleFall_esp, RegistroFallasDTO.class); 
    			registroFallasDTO.add(detalleFallaDTO);
    		}
			
		} else if(idiomaSpinners.equals("pt")) { //Portuguez
			
			List<RegistroFallas_Port> listaFallasPort = (List<RegistroFallas_Port>) listaFallasPort_Reposit.getListaDeFallasPort();
    		
    		for(int i=0; i<listaFallasPort.size(); i++) {
    			
    			RegistroFallas_Port detalleFall_port = listaFallasPort.get(i);
    			RegistroFallasDTO detalleFallaDTO    = modelMapper.map(detalleFall_port, RegistroFallasDTO.class); 
    			registroFallasDTO.add(detalleFallaDTO);
    		}
			
		} else if(idiomaSpinners.equals("en")) { //Ingles
			
			List<RegistroFallas_Ingl> listaFallasIngl = (List<RegistroFallas_Ingl>) listaFallasIngl_Reposit.getListaDeFallasIngl();
    		
    		for(int i=0; i<listaFallasIngl.size(); i++) {
    			
    			RegistroFallas_Ingl detalleFall_esp = listaFallasIngl.get(i);
    			RegistroFallasDTO detalleFallaDTO   = modelMapper.map(detalleFall_esp, RegistroFallasDTO.class); 
    			registroFallasDTO.add(detalleFallaDTO);
    		}
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
	public int guardarDescripcFalla(RegistroFallasDTO descripFalla, String idioma) {
	/****************************************************************************/
		String idiomaSpinners = idioma;
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	RegistroFallas_Espa nuevaFallaEspa = modelMapper.map(descripFalla, RegistroFallas_Espa.class); 
	        	
	        	try {
	        		listaFallasEspa_Reposit.save(nuevaFallaEspa);
	        		return nuevaFallaEspa.getIdFalla();
	        		
	        	} catch (Exception exp) {
	        		return 0;
	        	}
	
	        	
	        case "pt":  //Portuguez
	        	RegistroFallas_Port nuevaFallaPort = modelMapper.map(descripFalla, RegistroFallas_Port.class); 
	        	
	        	try {
	        		listaFallasPort_Reposit.save(nuevaFallaPort);
	        		return nuevaFallaPort.getIdFalla();
	        		
	        	} catch (Exception exp) {
	        		return 0;
	        	}
	
	        	
	        default:  //es idioma Ingles (en)
	        	RegistroFallas_Ingl nuevaFallaIngl = modelMapper.map(descripFalla, RegistroFallas_Ingl.class); 
	        	
	        	try {
	        		listaFallasIngl_Reposit.save(nuevaFallaIngl);
	        		return nuevaFallaIngl.getIdFalla();
	        		
	        	} catch (Exception exp) {
	        		return 0;
	        	}
	    }
	}


	@Transactional
	@Override         //Eliminar falla en opcion Web
	public String eliminarRegistroFalla2(int idFalla, String idioma) {
	/*****************************************************************/	
		String idiomaSpinners = idioma;
		
		switch(idiomaSpinners) {
		
	        case "es":  //Español
	        	try {
	        		listaFallasEspa_Reposit.deleteById(idFalla); 
	        		return "Exito";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO ELIMINAR FALLA";
	        	}
	        	
	        case "pt":  //Portuguez
	        	try {
	        		listaFallasPort_Reposit.deleteById(idFalla); 
	        		return "Exito";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO ELIMINAR FALLA";
	        	}
	        	
	        default:  //es idioma Ingles (en)
	        	try {
	        		listaFallasIngl_Reposit.deleteById(idFalla); 
	        		return "Exito";
	        		
	        	} catch (Exception exp) {
	        		return "FALLO ELIMINAR FALLA";
	        	}
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
