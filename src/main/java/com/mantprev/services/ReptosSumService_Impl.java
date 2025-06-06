
package com.mantprev.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.Repuestos_Sum;
import com.mantprev.entidadesDTO.RepuestosSum_DTO;
import com.mantprev.repositorios.RepuestosSum_Repository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ReptosSumService_Impl implements ReptosSumService {

	
	@Autowired
	private RepuestosSum_Repository reptosSumReposit;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Transactional(readOnly = true) 
	@Override
	public Page<Repuestos_Sum> getRepuestosByNombreRepto(Pageable pageRequest, String textBuscar) {
	/***********************************************************************************************/	
		return reptosSumReposit.findByNombreRepContaining(textBuscar, pageRequest);
	}

	
	@Transactional(readOnly = true) 
	@Override
	public Page<Repuestos_Sum> getRepuestosByCodigoRepto(Pageable pageRequest, String codeBuscar) {
	/**********************************************************************************************/	
		return reptosSumReposit.findByCodigoRepContaining(codeBuscar, pageRequest);
	}


	@Transactional
	@Override
	public String guardarNvoRepuesto(RepuestosSum_DTO repuesto) {
	/***********************************************************/
		Repuestos_Sum nvoRepuesto = new Repuestos_Sum();
		nvoRepuesto.setNombreRep(repuesto.getNombreRep());
		nvoRepuesto.setCodigoRep(repuesto.getCodigoRep());
		nvoRepuesto.setUnidades(repuesto.getUnidades());
		nvoRepuesto.setClasificac(repuesto.getClasificac());
		nvoRepuesto.setCostoProm(repuesto.getCostoProm());
		nvoRepuesto.setEspecifTecn(repuesto.getEspecifTecn());
		nvoRepuesto.setIdEmpresa(repuesto.getIdEmpresa()); 
		
		reptosSumReposit.save(nvoRepuesto);
		return "Exito";
	}
	
	
	@Transactional
	@Override
	public String guardarEdicionRepuesto(RepuestosSum_DTO repuesto) {
	/***********************************************************/
		Repuestos_Sum nvoRepuesto = new Repuestos_Sum();
		nvoRepuesto.setIdRepSum(repuesto.getIdRepSum());
		nvoRepuesto.setNombreRep(repuesto.getNombreRep());
		nvoRepuesto.setCodigoRep(repuesto.getCodigoRep());
		nvoRepuesto.setUnidades(repuesto.getUnidades());
		nvoRepuesto.setClasificac(repuesto.getClasificac());
		nvoRepuesto.setCostoProm(repuesto.getCostoProm());
		nvoRepuesto.setEspecifTecn(repuesto.getEspecifTecn());
		nvoRepuesto.setIdEmpresa(repuesto.getIdEmpresa());
		
		reptosSumReposit.save(nvoRepuesto);
		return "Exito";
	}


	@Transactional(readOnly = true) 
	@Override
	public String verificarCodigoRepuesto(String codigoRepto, int idEmpresa) {
	/**********************************************************/
		Repuestos_Sum repuesto = reptosSumReposit.getRepuestoByCodigoRep(codigoRepto, idEmpresa);
		
		String codResponse = "***";
		if (repuesto != null) {
			codResponse = repuesto.getCodigoRep();
		}
		return codResponse;
	}


	@Transactional(readOnly = true) 
	@Override
	public Page<Repuestos_Sum> getListaAllReptosPag(Pageable pageRequest, int idEmpresa) {
	/***********************************************************************************/
		Page<Repuestos_Sum> pageReptos = reptosSumReposit.findByIdEmpresa(idEmpresa, pageRequest);
		return pageReptos;
	}


	@Transactional(readOnly = true) 
	@Override
	public Page<Repuestos_Sum> getRepuestosByCodRepto(Pageable pageRequest, String codRepto) {
	/**********************************************************************************/
		Page<Repuestos_Sum> pageReptos = reptosSumReposit.findByCodigoRepContaining(codRepto, pageRequest);
		return pageReptos;
	}


	@Transactional(readOnly = true) 
	@Override
	public Page<Repuestos_Sum> getRepuestosByClasifRepto(Pageable pageRequest, String clasifRepto) {
	/*********************************************************************************************/
		Page<Repuestos_Sum> pageReptos = reptosSumReposit.findByClasificacContaining(clasifRepto, pageRequest);
		return pageReptos;
	}


	@Transactional(readOnly = true) 
	@Override
	public Repuestos_Sum getRepuestoById(int idRepto) {
	/*************************************************/
		Repuestos_Sum repuesto = reptosSumReposit.getRepuestoByIdRepto(idRepto);
		return repuesto;
	}


	@Transactional
	@Override
	public String deleteRepuesto(int idRepto) {
	/*****************************************/
		String respuesta = "Exito";
		try {
			reptosSumReposit.deleteById(idRepto);
			
		} catch (Exception e) {
			respuesta = "***";
			e.printStackTrace();
		} 
		return respuesta;
	}


	private List<Repuestos_Sum> listReptosEnBD = null;
	
	@Transactional
	@Override
	public String saveListaReptos(List<RepuestosSum_DTO> listReptosDto, int idEmpresa) {
	/*********************************************************************************/
		//boolean existeCodigo = listReptosEnBD.stream().anyMatch(Obj -> "codigoRep".equals(Obj.getCodigoRep())); 
		
		listReptosEnBD = reptosSumReposit.getListaTodosRepSum(idEmpresa);
		
		for (int i=0; i<listReptosDto.size(); i++) {
			
			RepuestosSum_DTO reptoDto = listReptosDto.get(i);
			String codReptoDto = reptoDto.getCodigoRep();
			boolean existeCode = verificExisteCodigoEnBD(codReptoDto); 
			
			if(existeCode == false) {  //Si no existe guarda el repuesto nuevo
				Repuestos_Sum repuesto = modelMapper.map(reptoDto, Repuestos_Sum.class); 
				reptosSumReposit.save(repuesto);
				
			} else {  //Si existe el repuesto --> lo actualiza
				Repuestos_Sum reptoEnBD = reptosSumReposit.getRepuestoByCodigoRep(codReptoDto, idEmpresa);
				int idReptoEnBD = reptoEnBD.getIdRepSum();
				
				reptoDto.setIdRepSum(idReptoEnBD);  //Le pone el id para que al guardar solo lo actualice
				Repuestos_Sum repuesto = modelMapper.map(reptoDto, Repuestos_Sum.class); 
				reptosSumReposit.save(repuesto);
			}
		}
		
		return "Exito"; 
	}
	
	
	public boolean verificExisteCodigoEnBD(final String codRepto){ 
	/**********************************************************/	
		boolean existeCodigo = listReptosEnBD.stream().filter(Obj -> Obj.getCodigoRep().equals(codRepto)).findFirst().isPresent();
	    return existeCodigo; 
	}
	
	
	

}
