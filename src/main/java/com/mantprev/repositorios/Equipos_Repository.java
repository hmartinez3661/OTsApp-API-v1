package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Equipos;



@Repository
public interface Equipos_Repository extends CrudRepository <Equipos, Integer>{

	
	
	@Query("select E from Equipos E WHERE E.idEmpresa = ?1 order by E.correlativo")   //, E.idEquipo desc
	public List<Equipos> getListaDeTodosLosEquipos(int idEmpresa); 
	
	
	@Query("select E from Equipos E where E.idEquipo = ?1")
    public Equipos getEquipoById(int idEquipo);
	
	
	@Query("select E from Equipos E where E.idEquipoPadre = ?1 order by E.correlativo") // 
    public List<Equipos> getListaEquiposHijos(int idEquipo);


	
	
		
	/*
	
	
	@Query("select E from Equipos E where E.marcaEquip <> null order by E.correlativo")
    public List<Equipos> getEquipsConFichaTec();
	
    @Query("select E from Equipos E order by E.correlativo asc")  //desc asc
    public List<Equipos> listaEquiposOrdenados();
    
    @Query("select E from Equipos E order by E.correlativo, E.idEquipo desc")  //desc asc
    public List<Equipos> listaDeTodosLosEquipos();
    
    @Query("select E from Equipos E where E.correlativo like ?1% order by E.correlativo, E.idEquipo desc")
    public List<Equipos> getTodaLaRamaDeEquipos(String correlatPdr);
    
    @Query("select E from Equipos E where E.idEquipoPadre = ?1 order by E.numHijo") // 
    public List<Equipos> getListaEquiposHijos(int idEqPadre);
    
    @Query("select E.nombEquipo from Equipos E where E.idEquipo = ?1")
    public String getNombreDelEquipo(int idEquipo);
    
    @Query("select E.idEquipoPadre from Equipos E where E.idEquipo = ?1")
    public int getIdDelEquipoPadre(int idEquipo);
    
    @Query("select E from Equipos E where E.correlativo like ?1% and E.nivelArbol = ?2 order by E.correlativo desc")
    public List<Equipos> getListaEquiposCorrelat(String correlativo, int nivelArbol);
    
    @Query("select E.correlativo from Equipos E where E.idEquipo = ?1")
    public String getCorrelativoDeEquipo(int idEquipo);
    
    @Query("select E from Equipos E where E.correlativo like ?1%")
    public List<Equipos> getPadreHijos(String correlativo);
    
    @Query("select E from Equipos E where E.nivelArbol = '1' order by E.numHijo desc")
    public List<Equipos> getEquiposEnPrimerNivelArbol();
    
    @Query("select E.idEquipo from Equipos E where E.nombEquipo = ?1")
    public int getIdDelEquipo(String nombreEquipo);
    
    @Query("select E.idEquipo from Equipos E where E.nombEquipo = ?1 and E.correlativo = ?2")
    public int getIdDelEquipo(String nombEquipo, String correlatSt);
    
    @Query("select E from Equipos E where E.nombEquipo = ?1")
    public Equipos getDtsDelEquipo(String nombreEquipo);
	
	*/
	
}
