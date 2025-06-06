
package com.mantprev.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.mantprev.entidades.Repuestos_Sum;
import com.mantprev.entidadesDTO.RepuestosSum_DTO;
import com.mantprev.services.ReptosSumService;


@RestController
@RequestMapping("/api/v1/reptosSum")
public class ReptosSumController {

	
	@Autowired
	private ReptosSumService reptosSumService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	 
	
	@GetMapping(path = "/listSugNames/{textBuscar}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<String> getListaSugerNombresReptos(@PathVariable String textBuscar, @PathVariable int idEmpresa){
	/*********************************************************************************/	
		Pageable pageRequest = PageRequest.of(0, 12);
		Page<Repuestos_Sum> pageRepSum = reptosSumService.getRepuestosByNombreRepto(pageRequest, textBuscar);
		
		List<Repuestos_Sum> listaReposSum = pageRepSum.getContent();   //rep_sumService.getSugerenciasNombresRepSum(palBuscar);
		List<String> listaNombres = new ArrayList<String>();
		
		for (int i=0; i<listaReposSum.size(); i++) {
			
			int idEmpresaRep = listaReposSum.get(i).getIdEmpresa();
			
			if (idEmpresaRep == idEmpresa) {
				String nombRepSum = listaReposSum.get(i).getNombreRep();
				int idRepSum = listaReposSum.get(i).getIdRepSum();
				Double costo = listaReposSum.get(i).getCostoProm();
				
				listaNombres.add(nombRepSum +"  ("+ costo +"-"+ idRepSum +")");
			}
		}
		
		return listaNombres; 
	}
	
	
	@GetMapping(path = "/listSugNamesCod/{codigoBuscar}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<String> getListaSugerNombresReptosByCode(@PathVariable String codigoBuscar, @PathVariable int idEmpresa){
	/*********************************************************************************/	
		Pageable pageRequest = PageRequest.of(0, 12);
		Page<Repuestos_Sum> pageRepSum = reptosSumService.getRepuestosByCodigoRepto(pageRequest, codigoBuscar);
		
		List<Repuestos_Sum> listaReposSum = pageRepSum.getContent();   //rep_sumService.getSugerenciasNombresRepSum(palBuscar);
		List<String> listaNombres = new ArrayList<String>();
		
		for (int i=0; i<listaReposSum.size(); i++) {

			int idEmpresaRep = listaReposSum.get(i).getIdEmpresa();
			
			if (idEmpresaRep == idEmpresa) {
				String codigoRpto = listaReposSum.get(i).getCodigoRep();
				String nombRepSum = listaReposSum.get(i).getNombreRep();
				int idRepSum = listaReposSum.get(i).getIdRepSum();
				Double costo = listaReposSum.get(i).getCostoProm();
				
				listaNombres.add(codigoRpto +" * "+ nombRepSum +"  ("+ costo +"-"+ idRepSum +")");
			}
		}
		
		return listaNombres; 
	}
	
	
	@PostMapping(path = "/saveNvoRepto")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String guardarNvoRepuesto(@RequestBody RepuestosSum_DTO repuesto){
	/**********************************************************************/  //unidades clidific costo prom especi
		String respone = reptosSumService.guardarNvoRepuesto(repuesto);
		return respone; 
	}
	
	
	@PostMapping(path = "/saveEdicRepto")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String guardarEdicionRepuesto(@RequestBody RepuestosSum_DTO repuesto){
	/**********************************************************************/  //unidades clidific costo prom especi
		String respone = reptosSumService.guardarEdicionRepuesto(repuesto);
		return respone; 
	}
	
	
	@GetMapping(path = "/getReptoByCode/{codigoRepto}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String buscarRepuestoByCodigo(@PathVariable String codigoRepto, @PathVariable int idEmpresa){
	/**********************************************************************************************/	
		String codResponse = reptosSumService.verificarCodigoRepuesto(codigoRepto, idEmpresa);
		return codResponse; 
	}
	
	
	@GetMapping(path = "/getCantPagsReptos/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public int getCantPagsRepuestos(@PathVariable int idEmpresa){
	/************************************************************/	
		int numPag = 0;
		int cantItemsXpag = 25;
		
		Pageable pageRequest = PageRequest.of(numPag, cantItemsXpag);
		Page<Repuestos_Sum> pageReptos = reptosSumService.getListaAllReptosPag(pageRequest, idEmpresa);  
		int totPages = pageReptos.getTotalPages();
		
		return totPages; 
	}
	
	
	@GetMapping(path = "/getlistaReptosPag/{numPag}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepuestosSum_DTO> getListaAllReptosPag(@PathVariable int numPag, @PathVariable int idEmpresa){
	/********************************************************************************************************/	
		int cantItemsXpag = 25; 
		
		Pageable pageRequest = PageRequest.of(numPag, cantItemsXpag);
		Page<Repuestos_Sum> pageReptos = reptosSumService.getListaAllReptosPag(pageRequest, idEmpresa);
		int cantPags = pageReptos.getTotalPages();
		
		List<Repuestos_Sum> listaReptos = pageReptos.getContent();
		List<RepuestosSum_DTO> listReptosDTO = new ArrayList<RepuestosSum_DTO>();
		
		for(int i=0; i<listaReptos.size(); i++) {
			
			Repuestos_Sum repuesto = listaReptos.get(i);
			RepuestosSum_DTO repuestoDTO = modelMapper.map(repuesto, RepuestosSum_DTO.class);
			repuestoDTO.setEspecifTecn(Integer.toString(cantPags));
			
			listReptosDTO.add(repuestoDTO);
		}
		
		return listReptosDTO; 
	}
	
	
	@GetMapping(path = "/getlistReptosByNamePag/{numPag}/{nameRepto}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepuestosSum_DTO> getlistReptosByNamePag(@PathVariable int numPag, @PathVariable String nameRepto, @PathVariable int idEmpresa){
	/*********************************************************************************************************************************/	
		int cantItemsXpag = 25; 
		
		Pageable pageRequest = PageRequest.of(numPag, cantItemsXpag);
		Page<Repuestos_Sum> pageReptos = reptosSumService.getRepuestosByNombreRepto(pageRequest, nameRepto);
		int cantPags = pageReptos.getTotalPages();
		
		List<Repuestos_Sum> listaReptos = pageReptos.getContent();
		List<RepuestosSum_DTO> listReptosDTO = new ArrayList<RepuestosSum_DTO>();
		
		for(int i=0; i<listaReptos.size(); i++) {
			Repuestos_Sum repuesto = listaReptos.get(i);
			int idEmpresaRto = repuesto.getIdEmpresa();
			
			if(idEmpresaRto == idEmpresa) {
				RepuestosSum_DTO repuestoDTO = modelMapper.map(repuesto, RepuestosSum_DTO.class); 
				repuestoDTO.setEspecifTecn(Integer.toString(cantPags));
				
				listReptosDTO.add(repuestoDTO);
			}
		}
		
		return listReptosDTO;  
	}
	
	
	@GetMapping(path = "/getlistReptosByCodPag/{numPag}/{codRepto}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepuestosSum_DTO> getListReptosByCodPag(@PathVariable int numPag, @PathVariable String codRepto, @PathVariable int idEmpresa){
	/*****************************************************************************************************/	
		int cantItemsXpag = 25; 
		
		Pageable pageRequest = PageRequest.of(numPag, cantItemsXpag);
		Page<Repuestos_Sum> pageReptos = reptosSumService.getRepuestosByCodRepto(pageRequest, codRepto);
		int cantPags = pageReptos.getTotalPages();
		
		List<Repuestos_Sum> listaReptos = pageReptos.getContent();
		List<RepuestosSum_DTO> listReptosDTO = new ArrayList<RepuestosSum_DTO>();
		
		for(int i=0; i<listaReptos.size(); i++) {
			Repuestos_Sum repuesto = listaReptos.get(i);
			int idEmpresaRto = repuesto.getIdEmpresa();
			
			if(idEmpresaRto == idEmpresa) {
				RepuestosSum_DTO repuestoDTO = modelMapper.map(repuesto, RepuestosSum_DTO.class); 
				repuestoDTO.setEspecifTecn(Integer.toString(cantPags));
				
				listReptosDTO.add(repuestoDTO);
			}
		}
		
		return listReptosDTO;  
	}
	
	
	@GetMapping(path = "/getlistReptosByClasifPag/{numPag}/{clasifRepto}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepuestosSum_DTO> getListReptosByClasificPag(@PathVariable int numPag, @PathVariable String clasifRepto, @PathVariable int idEmpresa){
	/************************************************************************************************/	
		int cantItemsXpag = 25; 
		
		Pageable pageRequest = PageRequest.of(numPag, cantItemsXpag);
		Page<Repuestos_Sum> pageReptos = reptosSumService.getRepuestosByClasifRepto(pageRequest, clasifRepto);
		int cantPags = pageReptos.getTotalPages();
		
		List<Repuestos_Sum> listaReptos = pageReptos.getContent();
		List<RepuestosSum_DTO> listReptosDTO = new ArrayList<RepuestosSum_DTO>();
		
		for(int i=0; i<listaReptos.size(); i++) {
			Repuestos_Sum repuesto = listaReptos.get(i);
			int idEmpresaRto = repuesto.getIdEmpresa();
			
			if(idEmpresaRto == idEmpresa) {
				RepuestosSum_DTO repuestoDTO = modelMapper.map(repuesto, RepuestosSum_DTO.class); 
				repuestoDTO.setEspecifTecn(Integer.toString(cantPags));
				
				listReptosDTO.add(repuestoDTO);
			}
		}
		
		return listReptosDTO;  
	}
	
	
	@GetMapping(path = "/getRepuestoById/{idRepto}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public RepuestosSum_DTO getRepuestoById(@PathVariable int idRepto){
	/****************************************************************/
		Repuestos_Sum repuesto = reptosSumService.getRepuestoById(idRepto);
		RepuestosSum_DTO repuestoDTO = modelMapper.map(repuesto, RepuestosSum_DTO.class);
		return repuestoDTO;  
	}
	
	
	@PutMapping(path = "/deleteRepuesto/{idRepto}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String deleteRepuesto(@PathVariable int idRepto){
	/******************************************************/
		String response = reptosSumService.deleteRepuesto(idRepto);
		return response;  
	}
	
	
	@PutMapping(path = "/saveListaReptos/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String saveListaReptos(@PathVariable int idEmpresa, @RequestBody List<RepuestosSum_DTO> listReptos){
	/****************************************************************************/
		String response = reptosSumService.saveListaReptos(listReptos, idEmpresa);
		return response;  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
