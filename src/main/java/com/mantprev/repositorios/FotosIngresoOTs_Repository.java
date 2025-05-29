package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Fotos_IngresoOTs;


@Repository
public interface FotosIngresoOTs_Repository extends CrudRepository <Fotos_IngresoOTs, Integer>{

	
	
	@Query("select F from Fotos_IngresoOTs F where F.idOrdTrab = ?1")
    List<Fotos_IngresoOTs> getListaFotosIngresoOT(int idOT);
	
	
	
	
	
    
    
	
}
