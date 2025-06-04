package com.mantprev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mantprev.entidades.RegistroFallas;
import com.mantprev.entidadesDTO.RegistroFallasDTO;
import com.mantprev.services.ListaFallasService;


@RestController
@RequestMapping("/api/v1/fallas")
public class RegistroFallasController {

	
	@Autowired
	ListaFallasService listaFallasService;
	
	
	
	@GetMapping(path = "/getAll/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RegistroFallasDTO> getListaDeFallas(@PathVariable int idEmpresa){	
	//***********************************************************************
		return this.listaFallasService.getListaDeFallas(idEmpresa); 
	} 
	
	
	@PostMapping(path = "/save/{nombrFalla}/{tipoFalla}/{idioma}")
	@ResponseStatus(HttpStatus.CREATED) //Cod. 201
	public String registrarNuevoEquipo(@PathVariable String nombrFalla, @PathVariable String tipoFalla, @PathVariable String idioma) {
	//**************************************************************************************************************************
		return listaFallasService.registrarNuevaFalla(nombrFalla, tipoFalla, idioma); 
	}
	
	
	@DeleteMapping(path = "/delete/{nombreFalla}/{idioma}") //Metodo que utiliza androi
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 201
	public String eliminarRegistroFalla(@PathVariable String nombreFalla, @PathVariable String idioma) {
	//*********************************************************************************************
		return listaFallasService.eliminarRegistroFalla(nombreFalla, idioma);
	}
	
	
	@PutMapping(path = "/saveFallaForm/{idEmpresa}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 201
	public int guardarDescripcFalla(@RequestBody RegistroFallasDTO descripFalla, @PathVariable int idEmpresa) {
	//*******************************************************************************************************
		return listaFallasService.guardarDescripcFalla(descripFalla, idEmpresa);
	}
	
	
	@DeleteMapping(path = "/delete2/{idFalla}/{idEmpresa}") //Metodo que utiliza opcion Web
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 201
	public String eliminarRegistroFalla2(@PathVariable int idFalla, @PathVariable int idEmpresa) {
	//*********************************************************************************************
		return listaFallasService.eliminarRegistroFalla2(idFalla, idEmpresa);
	}
	
	
	@PutMapping(path = "/saveFallasInic")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String setConfigInicSpinners(@RequestBody List<RegistroFallas> listInicFallas){ 
	/***************************************************************************/
		String response = listaFallasService.saveListaInicFallas(listInicFallas); 
		return response;  
	}
	
	
	
	
	
	
	
	
	
	/*
	
	@PutMapping(path = "/update/{idEquipo}/{nombreEquipo}/{correlatEquip}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosEquipo(@PathVariable int idEquipo, 
										@PathVariable String nombreEquipo, 
										@PathVariable String correlatEquip) {
	//*************************************************************************
		try {
			equiposService_Impl.actualizarDatosEquipo(idEquipo, nombreEquipo, correlatEquip); 
			return "EXITO";
			
		} catch (DataAccessException dtaExcp){
			return "FALLO EN ACTUALIZAR EQUIPO"; 
		}
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
