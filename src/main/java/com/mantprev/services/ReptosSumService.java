package com.mantprev.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mantprev.entidades.Repuestos_Sum;
import com.mantprev.entidadesDTO.RepuestosSum_DTO;


public interface ReptosSumService {
/***********************************/
    
	
	public Page<Repuestos_Sum> getRepuestosByNombreRepto(Pageable pageRequest, String textBuacar);
	
	public Page<Repuestos_Sum> getRepuestosByCodigoRepto(Pageable pageRequest, String codeBuacar);
	
	public String guardarNvoRepuesto(RepuestosSum_DTO repuesto);
	
	public String guardarEdicionRepuesto(RepuestosSum_DTO repuesto);
	
	public String verificarCodigoRepuesto(String codigoRepto);
	
	public Page<Repuestos_Sum> getListaAllReptosPag(Pageable pageRequest);
	
	public Page<Repuestos_Sum> getRepuestosByCodRepto(Pageable pageRequest, String codRepto);
	
	public Page<Repuestos_Sum> getRepuestosByClasifRepto(Pageable pageRequest, String clasifRepto);
	
	public Repuestos_Sum getRepuestoById(int idRepto);
	
	public String deleteRepuesto(int idRepto);
	
	public String saveListaReptos(List<RepuestosSum_DTO> listReptos);
	
	
	
	
	
    

}
