package com.mantprev.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.OrdenesTrabajo;


@Repository
public interface OrdsTrab_Repository extends CrudRepository <OrdenesTrabajo, Integer>{

	
	
	@Query("select T from OrdenesTrabajo T where T.fechaIngresoOT between :fecha1 and :fecha2 AND T.idEmpresa =:idEmpresa") // AND T.idEmpresa = ?3
	public List<OrdenesTrabajo> getOTsByFechas(@Param("idEmpresa") int idEmpresa, @Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
	
	
	@Query("select T from OrdenesTrabajo T where T.fechaIngresoOT between :fecha1 and :fecha2 and T.estatusOT = :statusOTs AND T.idEmpresa =:idEmpresa order by T.idOT")
    public List<OrdenesTrabajo> findOTsCerradasByFechas(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2, @Param("idEmpresa") int idEmpresa, @Param("statusOTs") String statusOTs);
	
	
	@Query("select T from OrdenesTrabajo T where T.estatusOT = ?1 order by T.idOT desc")
    public List<OrdenesTrabajo> getListaOrdTrabByStatus(String statusOTs);   
	
	
	@Query("select T from OrdenesTrabajo T where T.estatusOT = ?1 or T.estatusOT = ?2")
    public List<OrdenesTrabajo> getListaOTsParaCerrar(String status1, String status2);
	
	
	@Query("SELECT MAX(idOT) AS idOT FROM OrdenesTrabajo")
    public int getUltimoIdDeTblOrdenesTrab();
	
	
	@Query("select T from OrdenesTrabajo T where T.idEquipo = ?1")
    public List<OrdenesTrabajo> getOrdenesTrabajoByIdEquipo(int idEquipo);


	
    
  
    
    
    
	
	/*
	 
    @Query("select T from OrdenesTrabajo T where T.fechaIngresoOT between :fecha1 and :fecha2")
    public List<OrdenesTrabajo> findOTsByFechas(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
   
    
    @Query("select T from OrdenesTrabajo T where T.fechaIngresoOT between :fecha1 and :fecha2 and T.estatusOT = :statusOTs order by T.numeroOT desc")
    public List<OrdenesTrabajo> getListaOrdenesTrabStatusOTs(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2, @Param("statusOTs") String statusOTs);
    
    
    @Query("select T from OrdenesTrabajo T where T.fechaIngresoOT between :fecha1 and :fecha2 and T.persEjecutor = :ejecutOTs order by T.numeroOT desc")
    public List<OrdenesTrabajo> getListOrdenesTrabEjecutOTs(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2, @Param("ejecutOTs") String ejecutOTs);
    
     
    @Query("select T from OrdenesTrabajo T where T.fechaIngresoOT between :fecha1 and :fecha2 and T.clasificTrabajo = :clasificTrab order by T.numeroOT desc")
    public List<OrdenesTrabajo> getListaOrdenesTrabClasificTrab(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2, @Param("clasificTrab") String clasificTrab);
    
    
    @Query("select T from OrdenesTrabajo T where T.fechaIngresoOT between :fecha1 and :fecha2 and T.prioridadOT = :priorid order by T.numeroOT desc")
    public List<OrdenesTrabajo> getListaOrdenesTrabPriorid(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2, @Param("priorid") String priorid);
    
    
    @Query("select T from OrdenesTrabajo T where T.fechaIngresoOT between :fecha1 and :fecha2 order by T.numeroOT desc")
    public List<OrdenesTrabajo> getListaOrdenesTrab(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
    
    
    @Query("select T from OrdenesTrabajo T where T.numeroOT like %?1% order by T.numeroOT desc")
    public List<OrdenesTrabajo> getSubOrdenesTrabajo(String numeroOT);
    
    
    @Query("SELECT MAX(idOT) AS idOT FROM OrdenesTrabajo")
    public int getUltimoIdDeTblOrdenesTrab();
    
    
    @Query("SELECT MAX(numeroOT) AS numeroOT FROM OrdenesTrabajo")
    public String getMaximoNumeroOT();
    
    
    @Query("select T from OrdenesTrabajo T where T.idEquipo = ?1")
    public List<OrdenesTrabajo> getOrdenesTrabajoByIdEquipo(int idEquipo);
    
    
    @Query("select T.idEquipo from OrdenesTrabajo T where T.idOT = ?1")
    public int getIdDeEquipo (int idOT);
    
    
    @Query("select T from OrdenesTrabajo T where T.idOT = ?1")
    public OrdenesTrabajo getOrdenTrabajoById(int idOT); 
    
    
    @Query("select T from OrdenesTrabajo T where T.fechaIngresoOT >= ?1")
    public List<OrdenesTrabajo> getListOrdTrabDesdeFecha(Date fechaInic); 
    
    	   
    @Query("select T from OrdenesTrabajo T where T.numeroOT = ?1")
    public OrdenesTrabajo getOrdenTrabajoByNumOT (String numeroOT); 
    
    
    @Query("select T from OrdenesTrabajo T where T.estatusOT = ?1 ")
    public List<OrdenesTrabajo> getListaOrdTrabByStatus(String statusOTs);
    
    
    @Query("select T from OrdenesTrabajo T where T.estatusOT <> 'Cerrada' and T.estatusOT <> 'Rechazada' ")
    public List<OrdenesTrabajo> getListaOrdTrabPendEjec();
    
	
	*/
	
	
}
