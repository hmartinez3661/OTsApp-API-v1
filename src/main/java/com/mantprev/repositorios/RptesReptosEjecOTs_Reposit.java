
package com.mantprev.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.RptesReptosEjecOTs;


@Repository
public interface RptesReptosEjecOTs_Reposit extends JpaRepository<RptesReptosEjecOTs, Integer>{
/*****************************************************************************************/
    
    
	@Query("select R from RptesReptosEjecOTs R where R.fechaConsumo between :fecha1 and :fecha2 order by R.fechaConsumo")
    public List<RptesReptosEjecOTs> getReptesReptosEjecOTsByFechas(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
	
	@Query("select R from RptesReptosEjecOTs R where R.idOT = ?1")
    public List<RptesReptosEjecOTs> getListaReptesRepuestosEjecOT(int idOT); 
	
	
	
	/*
	
    @Query("select R.idReporte from ReptesPersEjecOTs R where R.idEmpleado = ?1 and R.idOT = ?2")
    String getIdReportePersonal (int idEmpleado, int idOT);  //Nota: Devuelve un String para que no genere un error por su no hay ningun reporte de personal para esa OT
    
    
    @Query("select R from ReptesPersEjecOTs R where R.idOT = ?1 and R.calidTrabaj > 0")
    List<ReptesPersEjecOTs> getReportesPersonalTrabajoOT(int idOT);
    
    
    @Query("select R from ReptesPersEjecOTs R where R.idReporte = ?1")
    ReptesPersEjecOTs getReportePersEjecOT (int idReporte);
    
    */
    
    
    
    
    
}
