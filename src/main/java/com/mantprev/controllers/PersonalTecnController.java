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

import com.mantprev.entidadesDTO.PersonalTecn_DTO;
import com.mantprev.services.PersonalTecnService;


@RestController
@RequestMapping("/api/v1/persTecn")
public class PersonalTecnController {

	
	@Autowired
	PersonalTecnService personalTecnService;
	
	
	
	@GetMapping(path = "/getAll")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<PersonalTecn_DTO> getListaPersonalTecnico(){	
	//**************************************************
		return this.personalTecnService.getLstaDePersonalTecn(); 
		
	} 
	
	
	@PostMapping(path = "/saveNew/{nombrTecnico}/{tipoEjecutor}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public  String registrarNuevoTecnico(@PathVariable String nombrTecnico, @PathVariable String tipoEjecutor){
	/*******************************************************************************************************/	
		return this.personalTecnService.registrarNuevoTecnico(nombrTecnico, tipoEjecutor); 
	}
	
	
	@PostMapping(path = "/saveNew2") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public  String registrarNuevoTecnico2(@RequestBody PersonalTecn_DTO persTecn){
	/****************************************************************************/	
		return this.personalTecnService.registrarNuevoTecnico2(persTecn); 
	}
	
	
	@PutMapping(path = "/update/{nombreTecn}/{tipoEjecut}/{nombrOrign}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosTecnico(@PathVariable String nombreTecn, 
										 @PathVariable String tipoEjecut,
										 @PathVariable String nombrOrign){
	/**********************************************************************/	
		return this.personalTecnService.actualizarDatosTecnico(nombreTecn, tipoEjecut, nombrOrign); 
		
	}
	
	
	@PutMapping(path = "/delete/{nombreTecn}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 201
	public String inactivarTecnico(@PathVariable String nombreTecn) {
	/***************************************************************/
		return this.personalTecnService.eliminarPersTecnico(nombreTecn); 
	}
	
	
	@PutMapping(path = "/delete2/{idPersTec}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 201
	public String inactivarTecnico2(@PathVariable int idPersTec) {
	/***************************************************************/
		return this.personalTecnService.eliminarPersTecnico2(idPersTec); 
	}
	
	
	
	
	
	
	
	
	
	
	
}
