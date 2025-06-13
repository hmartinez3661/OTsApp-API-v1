package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Personal_Tecnico;


@Repository
public interface PersonaTecnico_Repository extends CrudRepository <Personal_Tecnico, Integer>{
	
	
	@Query("select P from Personal_Tecnico P where P.statusPers ='Activo' AND P.idEmpresa = ?1 order by P.tipoEjecutor")
    public List<Personal_Tecnico> getListaPersonalTecnico(int idEmpresa); 
    
    
    @Query("select P from Personal_Tecnico P where P.nombreEmpl = ?1 AND P.idEmpresa = ?2")
    public List<Personal_Tecnico> getPersonalTecnicoByName(String nombrOrigTecnico, int idEmpresa);
    
    
    @Query("select P from Personal_Tecnico P where P.idEmpleado = ?1")
    public Personal_Tecnico getPersonalTecnicoById(int idEmpleado);
    
    
	
		
	
}
