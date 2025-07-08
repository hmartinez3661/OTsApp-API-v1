
package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Empresas_Inscrit;


@Repository
public interface Empresas_Repository extends CrudRepository <Empresas_Inscrit, Integer>{

	
	@Query("select E from Empresas_Inscrit E where E.idEmpresa > 0")
	List<Empresas_Inscrit> getAllEmpresasInscr();
	
	
	
}
