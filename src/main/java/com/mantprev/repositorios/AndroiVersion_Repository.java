package com.mantprev.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mantprev.entidades.Androi_Version;



public interface AndroiVersion_Repository extends CrudRepository <Androi_Version, Integer>{

	
	@Query("select A from Androi_Version A where A.id = 1")
	Androi_Version getAndroiVersion();
	
}
