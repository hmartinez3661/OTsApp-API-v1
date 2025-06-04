
package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mantprev.entidades.RegistroFallas;


public interface RegistroFallas_Repository extends CrudRepository <RegistroFallas, Integer> {

	
	@Query("select R from RegistroFallas R where R.nombreFalla = ?1")
    public RegistroFallas getRegistroFallas(String nombreFalla);
	
	
	@Query("select R from RegistroFallas R WHERE R.idEmpresa = ?1") // order by R.tipoFalla
    public List<RegistroFallas> getListaDeFallas(int idEmpresa);
	
	
	
}
