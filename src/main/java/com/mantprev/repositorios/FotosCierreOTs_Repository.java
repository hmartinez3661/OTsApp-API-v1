package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Fotos_CierreOTs;


@Repository
public interface FotosCierreOTs_Repository extends CrudRepository <Fotos_CierreOTs, Integer>{

	
	
	@Query("select F from Fotos_CierreOTs F where F.idOrdTrab = ?1")
    List<Fotos_CierreOTs> getListaFotosCierreOT(int idOT);
    
    
    
    
	
	
	
}
