package com.mantprev.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.RegistroFallas_Ingl;



@Repository
public interface RegistroFallasIngl_Repository extends CrudRepository <RegistroFallas_Ingl, Integer>{ 

	
	@Query("select R from RegistroFallas_Ingl R where R.nombreFalla = ?1")
    public RegistroFallas_Ingl getRegistroFallasIngl(String nombreFalla);
	
	@Query("select R from RegistroFallas_Ingl R order by R.tipoFalla")
    public List<RegistroFallas_Ingl> getListaDeFallasIngl();
	
	
	
}
