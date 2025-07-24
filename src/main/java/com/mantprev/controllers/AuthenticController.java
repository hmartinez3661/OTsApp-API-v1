package com.mantprev.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mantprev.entidadesDTO.ConfigSpinners_DTO;
import com.mantprev.entidadesDTO.Empresas_DTO;
import com.mantprev.entidadesDTO.Usuarios01_DTO;
import com.mantprev.security.AuthResponse;
import com.mantprev.security.UserCredentials;
import com.mantprev.security.UserRegisterRequest;
import com.mantprev.services.ConfigSpnService;
import com.mantprev.services.UsuariosService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticController {

	
	@Autowired
	private UsuariosService usuariosService;
	
	@Autowired
	private ConfigSpnService configSpnService;
	
	
	
	@PostMapping(path = "/login")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public ResponseEntity<AuthResponse> loginUser (@RequestBody UserCredentials userCredent){	
	//***************************************************************************************
		return ResponseEntity.ok(usuariosService.loginRequest(userCredent));
	} 
	
	
	@PostMapping(path = "/registNvaEmpresa")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public Integer registrarNvaEmpresa (@RequestBody Empresas_DTO nvaEmpresa){	
	//************************************************************************
		return usuariosService.registrarNvaEmpresa(nvaEmpresa);  
	} 
	
	
	@PostMapping(path = "/registAdmin")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public ResponseEntity<AuthResponse> registrarUserAdmin (@RequestBody UserRegisterRequest userRegistRequest){	
	//***************************************************************************************************
		return ResponseEntity.ok(usuariosService.registrarUserAdmin(userRegistRequest)); 
	} 
	
	
	@PostMapping(path = "/register")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String registrarNvoUser (@RequestBody UserRegisterRequest userRegisterRequest){	
	//***********************************************************************************
		return usuariosService.registrarNvoUser(userRegisterRequest); //Devuelve un password provisional
	} 
	
	
	@GetMapping(path = "/userPasswProv/{emailUsuario}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String createPassProvis(@PathVariable String emailUsuario){	
	//***************************************************************
		return this.usuariosService.getPasswProvisional(emailUsuario); 
	} 
	
	
	@PutMapping(path = "/update")  //Actualizza el password final desde App androi
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosNvoUser(@RequestBody Usuarios01_DTO userDTO){ 
	/**********************************************************************/	
		return this.usuariosService.actualizarDatosUsuario2(userDTO); 
	}
	
	
	@PostMapping(path = "/update2") //Actualizza el password final desde OTs web
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosNvoUser2(@RequestBody Usuarios01_DTO userDTO){ 
	/**********************************************************************/	
		return this.usuariosService.actualizarDatosUsuario2(userDTO); 
	}
	
	
	@GetMapping(path = "/getConf/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ConfigSpinners_DTO> getConfiguracSpinner(@PathVariable int idEmpresa){	
	//*******************************************************************************
		return this.configSpnService.getConfiguracSpinners(idEmpresa); 
	} 
	
	
	@GetMapping(path = "/getUserByEmail/{emailUser}")  //Utiliza aplicacion web p/ mostrar dts user al pedir password final
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public Usuarios01_DTO getUserByEmail(@PathVariable String emailUser){	
	//*******************************************************************
		return this.usuariosService.getUsuarioByEmail(emailUser); 
	} 
	
	
	@GetMapping(path = "/getNewestAppVers")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public Integer getNewestAppVersion(){	
	//***********************************
		return this.usuariosService.getNewestAppVersion(); 
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
