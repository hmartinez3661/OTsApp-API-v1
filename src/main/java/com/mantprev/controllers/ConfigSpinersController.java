package com.mantprev.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mantprev.entidadesDTO.ConfigSpinners_DTO;
import com.mantprev.services.ConfigSpnService;


@RestController
@RequestMapping("/api/v1/configSpn")
public class ConfigSpinersController {

	
	@Autowired
	private ConfigSpnService configSpnService;
	
	
	
	@GetMapping(path = "/getStatusOTs/{idioma}") 
	@ResponseStatus(HttpStatus.OK) //Cod. 200 
	public List<String> getItemsStatusOTs(@PathVariable String idioma){ 	
	//**************************************
		return this.configSpnService.getItemsStatusDeOTs(idioma); 
	} 
	
	
	@GetMapping(path = "/getConf/{idioma}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ConfigSpinners_DTO> getConfiguracSpinner(@PathVariable String idioma){	
	//*******************************************************************************
		return this.configSpnService.getConfiguracSpinners(idioma); 
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
	
	
	@PutMapping(path = "/updateConfigEmails/{idConfig}/{configEmails}/{idioma}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarConfigEmails(@PathVariable int idConfig, @PathVariable String configEmails, @PathVariable String idioma){
	/*************************************************************************************************/	
		return this.configSpnService.actualizarConfigEmails(idConfig, configEmails, idioma); 
	}
	
	
	@PutMapping(path = "/actualizEjectOTs/{idioma}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizarEjectOTs(@RequestBody List<String> listEjecOTs, @PathVariable String idioma){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListaEjectOTs(listEjecOTs, idioma);
		return response;  
	}
	
	
	@PutMapping(path = "/actualizClasificOTs/{idioma}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizClasificOTs(@RequestBody List<String> listClasificOTs, @PathVariable String idioma){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListaClasificOTs(listClasificOTs, idioma);
		return response;  
	}
	
	
	@PutMapping(path = "/actualizPrioridsOTs/{idioma}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizarPrioridadsOTs(@RequestBody List<String> listPrioridsOTs, @PathVariable String idioma){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListPrioridadsOTs(listPrioridsOTs, idioma);
		return response;  
	}
	
	
	@PutMapping(path = "/actualizEstadosEqu/{idioma}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizarListEstadosEquips(@RequestBody List<String> listEstadosEqu, @PathVariable String idioma){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListEstadosEquips(listEstadosEqu, idioma);
		return response;  
	}
	
	
	@PutMapping(path = "/actualizClasificFallas/{idioma}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String actualizarClasificFallas(@RequestBody List<String> listClasificFall, @PathVariable String idioma){
	/**************************************************************************************************/
		String response = configSpnService.actualizarListClasificFallas(listClasificFall, idioma);
		return response;  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
