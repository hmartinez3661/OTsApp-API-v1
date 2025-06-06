package com.mantprev.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.ConfigSpn_Espa;



@Repository
public interface ConfigSpnEspa_Repository extends CrudRepository <ConfigSpn_Espa, Integer>{  

	
	
	@Query("select C.estatusOTs from ConfigSpn_Espa C where C.estatusOTs <> '' ")
    List<String> getItemsDeStatusDeOTs();
	
	
	@Query("select C from ConfigSpn_Espa C")
	List<ConfigSpn_Espa> getConfigSpinners();
	
	
	

	/*
	@Query("select c.clasificRepSum from ComboBoxes c where c.clasificRepSum <> '' ")
    List<String> getItemsClasificRepSum();
  
    @Query("select f.tiposDeFallas from ComboBoxes f where f.tiposDeFallas <> '' ")
    List<String> getItemsTiposDeFallas();
    
    @Query("select f.estatusOTs from ComboBoxes f where f.estatusOTs <> '' ")
    List<String> getItemsDeStatusDeOTs();
    
    @Query("select f.ejecutoresOTs from ComboBoxes f where f.ejecutoresOTs <> '' ")
    List<String> getItemsEjectoresOTs();
    
    @Query("select f.ClasificTrabOTs from ComboBoxes f where f.ClasificTrabOTs <> '' ")
    List<String> getItemsClasificTrabOTs();
    
    @Query("select f.prioridTrabOTs from ComboBoxes f where f.prioridTrabOTs <> '' ")
    List<String> getItemsPrioridadesOTs();
    
    @Query("select f.ejecutoresRuts from ComboBoxes f where f.ejecutoresRuts <> '' ")
    List<String> getEjecutoresRuts();
    
    @Query("select f.estadoEquipo from ComboBoxes f where f.estadoEquipo <> '' ")
    List<String> getEstadosEquipoParaEjec();
    
    @Query("select f.frecuencias from ComboBoxes f where f.frecuencias <> '' ")
    List<String> getListaFrecuencias();
    
    @Query("select f.tiposTrabAcum from ComboBoxes f where f.tiposTrabAcum <> '' ")
    List<String> getTiposTrabajosAcumul();
    
    @Query("select f.configCorreos from ComboBoxes f where f.configCorreos <> '' ")
    List<String> getConfigDeCorreos();
    
    @Query("select f.simbMoney from ComboBoxes f where f.simbMoney <> '' ")
    List<String> getSimboloDeMoneda();
    
    @Query("select f.background from ComboBoxes f where f.id = 1")
    String getMaxCantUsersAdmit();
    
    @Query("select f.simbMoney from ComboBoxes f where f.id = 1")
    String getSimboloDeMoneda2();
    
    @Query("select f.logoReptes from ComboBoxes f where f.id = 1")
    String getLogoReptes();
    
    @Query("select f.background from ComboBoxes f where f.id = 1")
    String getBackGroung();
    
    @Query("select C from ComboBoxes C where C.id = ?1")
    ComboBoxes getComboBoxesById (int id);

    @Query("select C.configCorreos from ComboBoxes C where C.id = 1") //METODO INACTIVO
    String getFechaInicialLicencia();
    
    @Query("select C.configCorreos from ComboBoxes C where C.id = 1") //Aqui se guarda la fecha maxima licencia 
    String getFechaMaxLicencia();

    @Query("select C.configCorreos from ComboBoxes C where C.id = 2") //Aqui se guarda la confg. envio de correos
	String getConfigEmails();
	
	*/
	
	
}
