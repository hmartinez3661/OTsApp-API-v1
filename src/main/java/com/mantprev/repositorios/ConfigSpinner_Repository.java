
package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mantprev.entidades.ConfigSpinners;


public interface ConfigSpinner_Repository extends CrudRepository <ConfigSpinners, Integer>{

	
	@Query("select C.estatusOTs from ConfigSpinners C where C.estatusOTs <> '' AND idEmpresa = ?1")
    List<String> getItemsDeStatusDeOTs(int idEmpresa);
	
	
	@Query("select C from ConfigSpinners C WHERE idEmpresa = ?1")
	List<ConfigSpinners> getListConfigSpinners(int idEmpresa);
	
	
	@Query("select C from ConfigSpinners C WHERE C.rolesUsers = '---' AND idEmpresa = ?1")
	ConfigSpinners getConfigSpinners(int idEmpresa);
	
	
	
	
}
