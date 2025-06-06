
package com.mantprev.services;

import java.util.List;

import com.mantprev.entidades.RegistroFallas;
import com.mantprev.entidadesDTO.RegistroFallasDTO;


public interface ListaFallasService {

	
	
	public List<RegistroFallasDTO> getListaDeFallas(int idEmpresa); 
	
	public String registrarNuevaFalla (String nombrFalla, String tipoFalla, int idEmpresa);
	
	public String eliminarRegistroFalla (String nombreFalla, int idEmpresa);
	
	public int guardarDescripcFalla(RegistroFallasDTO descripFalla, int idEmpresa);
	
	public String eliminarRegistroFalla2(int idFalla, int idEmpresa);
	
	public String saveListaInicFallas(List<RegistroFallas> listaInicFallas);
	
	
	
	
	
}
