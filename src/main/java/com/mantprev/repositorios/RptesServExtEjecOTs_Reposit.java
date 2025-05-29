
package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.RptesServExtEjecOTs;


@Repository
public interface RptesServExtEjecOTs_Reposit extends JpaRepository<RptesServExtEjecOTs, Integer>{
/********************************************************************************************/
    
    
	
	@Query("select R from RptesServExtEjecOTs R where R.idOT = ?1")
    List<RptesServExtEjecOTs> getListaReptesServExterEjecOT(int idOT); 
	
	
	
	/*
	
    @Query("select R.idReporte from ReptesPersEjecOTs R where R.idEmpleado = ?1 and R.idOT = ?2")
    String getIdReportePersonal (int idEmpleado, int idOT);  //Nota: Devuelve un String para que no genere un error por su no hay ningun reporte de personal para esa OT
    
    
    @Query("select R from ReptesPersEjecOTs R where R.idOT = ?1 and R.calidTrabaj > 0")
    List<ReptesPersEjecOTs> getReportesPersonalTrabajoOT(int idOT);
    
    
    @Query("select R from ReptesPersEjecOTs R where R.idReporte = ?1")
    ReptesPersEjecOTs getReportePersEjecOT (int idReporte);
    
    */
    
    
    
    
    
}
