
package com.mantprev.services;

import java.util.List;

import com.mantprev.entidadesDTO.RegistroFallasDTO;


public interface ListaFallasService {

	
	
	public List<RegistroFallasDTO> getListaDeFallas(String idioma); 
	
	public String registrarNuevaFalla (String nombrFalla, String tipoFalla, String idioma);
	
	public String eliminarRegistroFalla (String nombreFalla, String idioma);
	
	public int guardarDescripcFalla(RegistroFallasDTO descripFalla, String idioma);
	
	public String eliminarRegistroFalla2(int idFalla, String idioma);
	
	
	
	
	
}
