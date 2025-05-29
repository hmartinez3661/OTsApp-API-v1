package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Documentos_OTs;


@Repository
public interface DocumentosOTs_Repository extends CrudRepository <Documentos_OTs, Integer>{

	
	
	@Query("select D from Documentos_OTs D where D.idOrdTrab = ?1")
    List<Documentos_OTs> getListaDocumentosOT(int idOT);
    
    
    
    
	
	
	
}
