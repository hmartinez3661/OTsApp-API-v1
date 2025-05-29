package com.mantprev.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.ReptesEjecOTs;


@Repository
public interface ReptesEjecOTs_Repository extends CrudRepository<ReptesEjecOTs, Integer>{
	
	
	@Query("SELECT MAX(idReporte) AS idReporte FROM ReptesEjecOTs")
    public Integer getUltimoIdTblReptesEjecOTs();
	
	@Query("select R from ReptesEjecOTs R where R.fechaEjec between :fecha1 and :fecha2")
    public List<ReptesEjecOTs> getReptesEjecOTsByFechas(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
	
	@Query("select R from ReptesEjecOTs R where R.fechaEjec between :fecha1 and :fecha2")
    public List<ReptesEjecOTs> getRecurrencFallasByFechas(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
	
	
	
	
	
	
	
	
	
}
