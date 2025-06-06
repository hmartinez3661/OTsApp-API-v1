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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mantprev.entidades.Documentos_OTs;
import com.mantprev.entidadesDTO.OrdTrabDtsNewOtDTO;
import com.mantprev.entidadesDTO.OrdTrabInformHomeDTO;
import com.mantprev.entidadesDTO.OrdTrabInformOtDTO;
import com.mantprev.entidadesDTO.OrdTrabRevisionDTO;
import com.mantprev.entidadesDTO.OrdenesTrabajoDTO_2;
import com.mantprev.services.OrdsTrabServices;


@RestController
@RequestMapping("/api/v1/ordsTrab")
public class OrdenesTrabajoController {

	
	@Autowired
	OrdsTrabServices ordsTrabServices;
	
	
	
	@PutMapping(path = "/update/{idOrdTrab}/{newStatusOT}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarStatusDeOT(@PathVariable int idOrdTrab, @PathVariable String newStatusOT){
	/*************************************************************************************************/	
		return this.ordsTrabServices.actualizarStatusDeOT(idOrdTrab, newStatusOT); 
	}
	
	
	@GetMapping(path = "/getInfHome/{fechaInic}/{fechaFinal}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<OrdTrabInformHomeDTO> getInfomHomeDeOTs(@PathVariable String fechaInic, @PathVariable String fechaFinal, @PathVariable int idEmpresa){	
	//************************************************************************************************************
		return this.ordsTrabServices.getInformHomeDeOTs(fechaInic, fechaFinal, idEmpresa); 
	} 
	
	
	@GetMapping(path = "/listNewOTs/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200
	public List<OrdTrabInformOtDTO> getListaNewOTs(@PathVariable int idEmpresa){
	/***********************************************/	
		return this.ordsTrabServices.getListaDeOTsNuevas(idEmpresa);  
	} 
	
	
	@GetMapping(path = "/listNewOTsEjec/{ejecutor}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200
	public List<OrdTrabInformOtDTO> getListaNewOTsByEjecut(@PathVariable String ejecutor, @PathVariable int idEmpresa){
	/************************************************************************************/	
		return this.ordsTrabServices.getListaNewOTsByEjecutor(ejecutor, idEmpresa); 
	} 
	
	
	@GetMapping(path = "/getOTsByFechas/{fechaInic}/{fechaFinal}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<OrdTrabInformOtDTO> getListaOTsByFechas(@PathVariable String fechaInic, @PathVariable String fechaFinal, @PathVariable int idEmpresa){	
	//***********************************************************************************************************
		return this.ordsTrabServices.getListOTsEntre2Fechas(fechaInic, fechaFinal, idEmpresa);
	} 
	
	
	@GetMapping(path = "/OTsByFechasUser/{fechaInic}/{fechaFinal}/{nombrUser}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<OrdTrabInformOtDTO> getListaOTsByFechasUser(@PathVariable String fechaInic, 
														    @PathVariable String fechaFinal, 
														    @PathVariable String nombrUser, @PathVariable int idEmpresa){	
	//*****************************************************************************************************************
		return this.ordsTrabServices.getListOTsByFechasUser(fechaInic, fechaFinal, nombrUser, idEmpresa);
	} 
	
	
	@GetMapping(path = "/OTsLast30days/{fechaInic}/{fechaFinal}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<OrdTrabInformOtDTO> getListaOTsLast30days(@PathVariable String fechaInic, @PathVariable String fechaFinal, @PathVariable int idEmpresa){	
	//***********************************************************************************************************
		return this.ordsTrabServices.getListOTsEntre2Fechas(fechaInic, fechaFinal, idEmpresa);
	} 
	
	
	@GetMapping(path = "/OTsLast30daysUser/{fechaInic}/{fechaFinal}/{nombrUser}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<OrdTrabInformOtDTO> getListaOTsLast30daysUser(@PathVariable String fechaInic, 
														     @PathVariable String fechaFinal, 
														     @PathVariable String nombrUser, @PathVariable int idEmpresa){
	//***************************************************************************************
		return this.ordsTrabServices.getListOTsByFechasUser(fechaInic, fechaFinal, nombrUser, idEmpresa);
	} 
	
	
	@GetMapping(path = "/OTsParaCerrar/{status1}/{status2}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<OrdTrabInformOtDTO> getListaOTsParaCerrar(@PathVariable String status1, @PathVariable String status2, @PathVariable int idEmpresa){
	//***********************************************************************************************************
		return this.ordsTrabServices.getListOTsParaCerrar(status1, status2, idEmpresa);
	} 
	
	
	@GetMapping(path = "/OTsParaCerrarEjec/{status1}/{status2}/{ejecutor}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<OrdTrabInformOtDTO> getListaOTsParaCerrarEjecut(@PathVariable String status1, 
															   @PathVariable String status2,
															   @PathVariable String ejecutor, @PathVariable int idEmpresa){
	//****************************************************************************************
		return this.ordsTrabServices.getListOTsParaCerrarEjecut(status1, status2, ejecutor, idEmpresa); 
	} 
	

	@PutMapping(path = "/saveRevAutmOT") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String guardarRevisAutomatOT(@RequestBody OrdTrabRevisionDTO dtsOrdTrab){
	/********************************************************************/	
		return this.ordsTrabServices.guardarRevisAutomatOT(dtsOrdTrab); 
	}
	
	
	@PutMapping(path = "/saveRevOT") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String gurdarRevisionOT(@RequestBody OrdTrabRevisionDTO dtsOrdTrab){
	/**************************************************************/	
		return this.ordsTrabServices.gurdarRevisionOT(dtsOrdTrab); 
	}
	
	
	@PostMapping(path = "/saveNewOrdTrab") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String registrarNuevaOT(@RequestBody OrdTrabDtsNewOtDTO newOrdTrab){
	/********************************************************************/	
		return this.ordsTrabServices.guardarNuevaOrdTrab(newOrdTrab);
	}
	
	
	@GetMapping(path = "/getById/{idOT}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public OrdenesTrabajoDTO_2 getOrdenDeTrabById(@PathVariable int idOT){	
	//********************************************************************
		return this.ordsTrabServices.getOrdenDeTrabById(idOT);
	} 
	
	
	@GetMapping(path = "/getListDocsOT/{idOT}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Documentos_OTs> getListaDocumentosOT(@PathVariable int idOT){	
	//***********************************************************************
		return this.ordsTrabServices.getListaDocumentosOT(idOT);
	} 
	
	
	@PostMapping(path = "/saveDocumentOT/{idOT}") 
	@ResponseStatus(HttpStatus.OK) //Cod. 200 
	public String saveDatosDocOT(@RequestParam MultipartFile file, @PathVariable int idOT){
	/********************************************************************************/	
		ordsTrabServices.saveDocumentoOT(file, idOT);
		return "OK";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
