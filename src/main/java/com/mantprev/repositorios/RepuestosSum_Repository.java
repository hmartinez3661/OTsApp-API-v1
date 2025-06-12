package com.mantprev.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Repuestos_Sum;



@Repository
public interface RepuestosSum_Repository extends JpaRepository <Repuestos_Sum, Integer>{  //CrudRepository

	
	public Page<Repuestos_Sum> findByCodigoRepIsNotNull(Pageable pageRequest); //Para que los envie a todos 
	
	
	public Page<Repuestos_Sum> findByIdEmpresa(int idEmpresa, Pageable pageRequest); //Para que los envie a todos
	
	
	public Page<Repuestos_Sum> findByNombreRepContaining(String nameRep, Pageable page); 
	
	
	public Page<Repuestos_Sum> findByCodigoRepContaining(String codigoRep, Pageable page); 
	
	
	public Page<Repuestos_Sum> findByClasificacContaining(String clasifRep, Pageable page); 
	
	
	@Query("select R from Repuestos_Sum R where R.nombreRep like %?1%")
	List<Repuestos_Sum> getSugerenciasNombresRepSum(String textBuscar);
	
	
	@Query("select R from Repuestos_Sum R where R.codigoRep = ?1 AND R.idEmpresa = ?2")
	Repuestos_Sum getRepuestoByCodigoRep(String codigoRep, int idEmpresa);
	
	
	@Query("select R from Repuestos_Sum R where R.idRepSum = ?1")
	Repuestos_Sum getRepuestoByIdRepto(int idRepto);
	
	
	@Query("select R from Repuestos_Sum R where R.idEmpresa = ?1 ")
	List<Repuestos_Sum> getListaTodosRepSum(int idEmpresa);
	
	
	
}
