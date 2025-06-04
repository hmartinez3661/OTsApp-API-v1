package com.mantprev.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mantprev.entidades.ConfigSpinners;
import com.mantprev.entidadesDTO.ConfigSpinners_DTO;
import com.mantprev.services.ConfigSpnService;


@RestController
@RequestMapping("/api/v1/configSpn")
public class ConfigSpinersController {

	
	@Autowired
	private ConfigSpnService configSpnService;
	
	
	
	@GetMapping(path = "/getStatusOTs/{idEmpresa}") 
	@ResponseStatus(HttpStatus.OK) //Cod. 200 
	public List<String> getItemsStatusOTs(@PathVariable int idEmpresa){ 	
	//**************************************
		return this.configSpnService.getItemsStatusDeOTs(idEmpresa); 
	} 
	
	
	@GetMapping(path = "/getConf/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ConfigSpinners_DTO> getConfiguracSpinner(@PathVariable int idEmpresa){	
	//*******************************************************************************
		return this.configSpnService.getConfiguracSpinners(idEmpresa); 
	} 
	
	
	@PutMapping(path = "/updateEjec/{idItemConf}/{nombreEjecut}/{idioma}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarEjecutorOTs(@PathVariable int idItemConf, @PathVariable String nombreEjecut, @PathVariable String idioma){
	/*************************************************************************************************/	
		return this.configSpnService.actualizarEjecutorOTs(idItemConf, nombreEjecut, idioma); 
	}
	
	
	@PutMapping(path = "/updateClasif/{idItemConf}/{nombreClasif}/{idioma}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarClasificOTs(@PathVariable int idItemConf, @PathVariable String nombreClasif, @PathVariable String idioma){
	/*************************************************************************************************/	
		return this.configSpnService.actualizarClasificOTs(idItemConf, nombreClasif, idioma); 
	}
	
	
	@PutMapping(path = "/updatePriorid/{idItemConf}/{nombrePriorid}/{idioma}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarPrioridadOTs(@PathVariable int idItemConf, @PathVariable String nombrePriorid, @PathVariable String idioma){
	/*************************************************************************************************/	
		return this.configSpnService.actualizarPrioridadOTs(idItemConf, nombrePriorid, idioma); 
	}
	
	
	@PutMapping(path = "/updateClasificFallas/{idItemConf}/{nombrClasificFalla}/{idioma}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarClasifcFallasOTs(@PathVariable int idItemConf, @PathVariable String nombrClasificFalla, @PathVariable String idioma){
	/*************************************************************************************************/	
		return this.configSpnService.actualizarClasificFallasOTs(idItemConf, nombrClasificFalla, idioma); 
	}
	
	
	@PutMapping(path = "/updateConfigEmails/{configEmails}/{idEmpresa}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarConfigEmails(@PathVariable String configEmails, @PathVariable int idEmpresa){
	/*************************************************************************************************/	
		return this.configSpnService.actualizarConfigEmails(configEmails, idEmpresa); 
	}
	
	
	@PutMapping(path = "/actualizEjectOTs/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizarEjectOTs(@RequestBody List<String> listEjecOTs, @PathVariable int idEmpresa){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListaEjectOTs(listEjecOTs, idEmpresa);
		return response;  
	}
	
	
	@PutMapping(path = "/actualizClasificOTs/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizClasificOTs(@RequestBody List<String> listClasificOTs, @PathVariable int idEmpresa){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListaClasificOTs(listClasificOTs, idEmpresa);
		return response;  
	}
	
	
	@PutMapping(path = "/actualizPrioridsOTs/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizarPrioridadsOTs(@RequestBody List<String> listPrioridsOTs, @PathVariable int idEmpresa){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListPrioridadsOTs(listPrioridsOTs, idEmpresa);
		return response;  
	}
	
	
	@PutMapping(path = "/actualizEstadosEqu/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizarListEstadosEquips(@RequestBody List<String> listEstadosEqu, @PathVariable int idEmpresa){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListEstadosEquips(listEstadosEqu, idEmpresa);
		return response;  
	}
	
	
	@PutMapping(path = "/actualizClasificFallas/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizarClasificFallas(@RequestBody List<String> listClasificFall, @PathVariable int idEmpresa){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListClasificFallas(listClasificFall, idEmpresa);
		return response;  
	}
	
	
	@PostMapping(path = "/setConfigSpins")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String setConfigInicSpinners(@RequestBody List<ConfigSpinners> listConfigSpinn){
	/***************************************************************************/
		String response = configSpnService.setConfigInicSpinners(listConfigSpinn);
		return response;  
	}
	
	
	
	
	
	
	
	
	
	
	
}
