package com.mantprev.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.RegistroFallas_Espa;



@Repository
public interface RegistroFallasEspa_Repository extends CrudRepository <RegistroFallas_Espa, Integer>{ 

	
	@Query("select R from RegistroFallas_Espa R where R.nombreFalla = ?1")
    public RegistroFallas_Espa getRegistroFallasEspa(String nombreFalla);
	
	@Query("select R from RegistroFallas_Espa R order by R.tipoFalla")
    public List<RegistroFallas_Espa> getListaDeFallasEspa();
	
	
	
}
