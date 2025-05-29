package com.mantprev.controllers;

import java.util.Date;
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

import com.mantprev.entidades.Usuarios;
import com.mantprev.entidadesDTO.Usuarios01_DTO;
import com.mantprev.services.UsuariosService;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

	
	@Autowired
	UsuariosService usuariosService;
	
	
	
	@GetMapping(path = "/getSuperv")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Usuarios01_DTO> getListaDeSupervisores(){	
	//**************************************************
		return this.usuariosService.getLstaDeSupervisores(); 
		
	} 
	
	
	@GetMapping(path = "/getAll")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Usuarios01_DTO> getLstaDeUsuarios(){	
	//**********************************************
		return this.usuariosService.getLstaDeUsuarios(); 
		
	} 
	
	
	@PostMapping(path = "/saveNew") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String registrarNuevoTecnico(@RequestBody Usuarios nvoUsuario){
	/********************************************************************/	
		return this.usuariosService.guardarNvoUsuario(nvoUsuario);  
	}
	
	
	@PutMapping(path = "/updateUser") //Cuando user admin edita los datos de un usuario
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosUser(@RequestBody Usuarios01_DTO userDTO){ 
	/**********************************************************************/	
		return this.usuariosService.actualizarDatosUsuario(userDTO); 
	}
	
	
	@PutMapping(path = "/update") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosUsuario(@RequestBody Usuarios01_DTO userDTO){ 
	/**********************************************************************/	
		return this.usuariosService.actualizarDatosUsuario1(userDTO); 
		
	}
	
	
	@PutMapping(path = "/update2") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosUsuario2(@RequestBody Usuarios01_DTO userDTO){ 
	/**********************************************************************/	
		return this.usuariosService.actualizarDatosUsuario2(userDTO); 
		
	}
	
	
	@DeleteMapping(path = "/delete/{emailUsuario}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 201
	public String eliminarUsuarioDeBD(@PathVariable String emailUsuario) {
	/*******************************************************************/
		return this.usuariosService.eliminarUsuarioDeBD(emailUsuario); 
	}
	
	
	@DeleteMapping(path = "/delete2/{idUsuario}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 201
	public String eliminarUsuarioDeBD2(@PathVariable int idUsuario) {
	/*******************************************************************/
		return this.usuariosService.eliminarUsuarioDeBD2(idUsuario); 
	}
	
	
	@GetMapping(path = "/getUserByEmail/{emailUsuario}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public Usuarios getUsuarioByID(@PathVariable String emailUsuario){	
	//***************************************************************
		return this.usuariosService.getUserByEmail(emailUsuario); 
		
	} 
	
	
	@PutMapping(path = "/updateDtsEmpr/{nombreEmpresa}/{nvaCantMaxUsers}/{nvaFechaExpirac}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosEmpresa(@PathVariable String nombreEmpresa, @PathVariable int nvaCantMaxUsers, @PathVariable Date nvaFechaExpirac){ 
	/**************************************************************************************************************************************/	
		return this.usuariosService.actualizarDatosEmpresa(nombreEmpresa, nvaCantMaxUsers, nvaFechaExpirac); 
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
