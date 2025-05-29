package com.mantprev.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.RegistroFallas_Port;


@Repository
public interface RegistroFallasPort_Repository extends CrudRepository <RegistroFallas_Port, Integer>{ 

	
	@Query("select R from RegistroFallas_Port R where R.nombreFalla = ?1")
    public RegistroFallas_Port getRegistroFallasPort(String nombreFalla);
	
	@Query("select R from RegistroFallas_Port R order by R.tipoFalla")
    public List<RegistroFallas_Port> getListaDeFallasPort();
	
	
	
}
