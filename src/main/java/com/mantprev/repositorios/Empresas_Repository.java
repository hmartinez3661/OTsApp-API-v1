
package com.mantprev.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Empresas_Inscrit;


@Repository
public interface Empresas_Repository extends CrudRepository <Empresas_Inscrit, Integer>{

	
}
