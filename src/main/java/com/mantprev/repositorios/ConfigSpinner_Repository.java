
package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mantprev.entidades.ConfigSpinners;


public interface ConfigSpinner_Repository extends CrudRepository <ConfigSpinners, Integer>{

	
	@Query("select C.estatusOTs from ConfigSpinners C where C.estatusOTs <> '' ")
    List<String> getItemsDeStatusDeOTs();
	
	@Query("select C from ConfigSpinners C")
	List<ConfigSpinners> getConfigSpinners();
	
}
