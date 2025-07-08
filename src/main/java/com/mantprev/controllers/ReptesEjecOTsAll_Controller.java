package com.mantprev.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mantprev.entidades.Usuarios;
import com.mantprev.entidadesDTO.Empresas_DTO;
import com.mantprev.entidadesDTO.Repte2Datos_DTO;
import com.mantprev.entidadesDTO.RepteHistorMantto_DTO;
import com.mantprev.entidadesDTO.RepteHrsParoEquips_DTO;
import com.mantprev.entidadesDTO.RepteRecurrFallas_DTO;
import com.mantprev.entidadesDTO.RepteServExtOTs_DTO;
import com.mantprev.entidadesDTO.RepteTendAveriasDTO;
import com.mantprev.entidadesDTO.ReptesEjecOTs_DTO;
import com.mantprev.entidadesDTO.ReptesPersTecn_DTO;
import com.mantprev.entidadesDTO.ReptesPersTecn_DTO2;
import com.mantprev.entidadesDTO.ReptesReptos_DTO;
import com.mantprev.entidadesDTO.ReptesReptos_DTO2;
import com.mantprev.services.RptesEjecOTsAllServ;


@RestController
@RequestMapping("/api/v1/reptes") 
public class ReptesEjecOTsAll_Controller {

	
	@Autowired
	RptesEjecOTsAllServ rptesEjecOTsAllServ;
	
	
	
	@GetMapping(path = "/reptsPers/{idOT}") // @GetMapping 
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Repte2Datos_DTO> getRptesPersTecEjecOT(@PathVariable int idOT){	
	//***********************************************************************
		return this.rptesEjecOTsAllServ.getListReptesPersEjecOTByIdOT(idOT);
		
	} 
	
	
	@GetMapping(path = "/reptsPers2/{idOT}") // @GetMapping 
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ReptesPersTecn_DTO> getRptesPersTecEjecOT2(@PathVariable int idOT){	
	//***********************************************************************
		return this.rptesEjecOTsAllServ.getListReptesPersEjecOTByIdOT2(idOT);
		
	} 
	
	
	@GetMapping(path = "/listaReptos/{idOT}") // @GetMapping 
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Repte2Datos_DTO> getListaReptosUtilizEjecOT(@PathVariable int idOT){	 
	//***********************************************************************
		return this.rptesEjecOTsAllServ.getListReptesRepuestosEjecOT(idOT); 
		
	} 
	
	
	@GetMapping(path = "/listaReptos2/{idOT}") // @GetMapping 
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ReptesReptos_DTO> getListaReptosUtilizEjecOT2(@PathVariable int idOT){	 
	//***********************************************************************
		return this.rptesEjecOTsAllServ.getListReptesRepuestosEjecOT2(idOT); 
		
	} 
	
	
	@GetMapping(path = "/listaSevExt/{idOT}") // @GetMapping 
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Repte2Datos_DTO> getListaServExterEjecOT(@PathVariable int idOT){
	//***********************************************************************
		return this.rptesEjecOTsAllServ.getListaReptesServExterEjecOT(idOT); 
		
	} 
	
	
	@GetMapping(path = "/listaSevExt2/{idOT}") // @GetMapping 
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepteServExtOTs_DTO> getListaServExterEjecOT2(@PathVariable int idOT){
	//***********************************************************************
		return this.rptesEjecOTsAllServ.getListaReptesServExterEjecOT2(idOT); 
		
	} 
	
	
	@GetMapping(path = "/getRtesPers/{fechaInic}/{fechaFnl}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ReptesPersTecn_DTO> getListReptsPersEjecOTsByDates(@PathVariable String fechaInic, @PathVariable String fechaFnl, @PathVariable int idEmpresa){	
	//************************************************************************************************************
		return this.rptesEjecOTsAllServ.getListReptesPersEjecOTsByDates(fechaInic, fechaFnl, idEmpresa); 
	} 
	
	
	@GetMapping(path = "/getRtesPers2/{fechaInic}/{fechaFnl}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ReptesPersTecn_DTO> getListReptsPersEjecOTsByDates2(@PathVariable Date fechaInic, @PathVariable Date fechaFnl, @PathVariable int idEmpresa){	
	//*************************************************************************************************************************************************
		return this.rptesEjecOTsAllServ.getListReptesPersEjecOTsByDates2(fechaInic, fechaFnl, idEmpresa); 
	} 
	
	
	@GetMapping(path = "/getRtesReptos/{fechaInic}/{fechaFnl}/{numFmt}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ReptesReptos_DTO> getListReptsReptosEjecOTsByDates(@PathVariable String fechaInic, 
																   @PathVariable String fechaFnl, @PathVariable String numFmt, @PathVariable int idEmpresa){
	//************************************************************************************************************
		return this.rptesEjecOTsAllServ.getListReptesReptosEjecOTsByDates(fechaInic, fechaFnl, numFmt, idEmpresa); 
	} 
	
	
	@GetMapping(path = "/getRtesHrsParo/{fechaInic}/{fechaFnl}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepteHrsParoEquips_DTO> getListReptesHrsParoEquipos(@PathVariable String fechaInic, @PathVariable String fechaFnl, @PathVariable int idEmpresa){
	//************************************************************************************************************
		return this.rptesEjecOTsAllServ.getListReptesHrsParoEquipos(fechaInic, fechaFnl, idEmpresa); 
	} 
	
	
	@GetMapping(path = "/getRteRecurrFalla/{nombrFalla}/{fechaInic}/{fechaFnl}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepteRecurrFallas_DTO> getListReptesRecurrFallas(@PathVariable String nombrFalla,
																 @PathVariable String fechaInic, 
																 @PathVariable String fechaFnl, @PathVariable int idEmpresa){
	//*******************************************************************************************
		return this.rptesEjecOTsAllServ.getListReptesRecurrFallas(nombrFalla, fechaInic, fechaFnl, idEmpresa); 
	} 
	
	
	@GetMapping(path = "/getListFallas/{fechaInic}/{fechaFnl}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepteRecurrFallas_DTO> getListReptesRecurrFallas(@PathVariable String fechaInic, @PathVariable String fechaFnl, @PathVariable int idEmpresa) {	
	//**************************************************************************************************************
		return this.rptesEjecOTsAllServ.getListaFallasByFechas(fechaInic, fechaFnl, idEmpresa); 
		
	} 
	
	
	@GetMapping(path = "/getRepteAver/{fechaInic}/{fechaFnl}/{idEmpresa}")// @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepteTendAveriasDTO> getDtsAveriasTodosEquipos(@PathVariable String fechaInic, @PathVariable String fechaFnl, @PathVariable int idEmpresa) {	
	//**************************************************************************************************************
		return this.rptesEjecOTsAllServ.getDtsAveriasTodosEquipos(fechaInic, fechaFnl, idEmpresa);
		
	} 
	

	@GetMapping(path = "/getRepteEjec/{idReporte}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public ReptesEjecOTs_DTO getRepteEjecOT(@PathVariable int idReporte){	
	//***************************************************************
		return this.rptesEjecOTsAllServ.getRepteEjecucOT(idReporte); 
	} 
	
	
	@GetMapping(path = "/getListReptesEjec/{fechaInic}/{fechaFnl}/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<ReptesEjecOTs_DTO> getListReptesEjecOT(@PathVariable String fechaInic, @PathVariable String fechaFnl, @PathVariable int idEmpresa){
	//***************************************************************
		return this.rptesEjecOTsAllServ.getlistaReptesEjecucOT(fechaInic, fechaFnl, idEmpresa); 
	} 
	
	
	@PostMapping(path = "/saveRepteEjec2")
	@ResponseStatus(HttpStatus.CREATED) //Cod. 201   
	public String guardarReporteEjecOT2(@RequestBody ReptesEjecOTs_DTO repteEjecOT) { 
	//******************************************************************	
		return this.rptesEjecOTsAllServ.guardarReporteEjecOT2(repteEjecOT);
	}
	
	
	@PostMapping(path = "/saveRepteEjec3")
	@ResponseStatus(HttpStatus.CREATED) //Cod. 201   
	public int guardarReporteEjecOT3(@RequestBody ReptesEjecOTs_DTO repteEjecOT) { 
	//******************************************************************
		int idRepteEjec = this.rptesEjecOTsAllServ.guardarReporteEjecOT3(repteEjecOT);
		return idRepteEjec;
	}
	
	@PostMapping(path = "/saveReptePers")   //guardarRptesPersTecn.php
	@ResponseStatus(HttpStatus.CREATED) //Cod. 201
	public String guardarReptePersTecnEjecOT(@RequestBody ReptesPersTecn_DTO2 reptePers) { 
	//************************************************************************	
		return this.rptesEjecOTsAllServ.guardarReptePersEjecOT(reptePers);
		
	}
	
	
	@PostMapping(path = "/saveRepteRepto")  //guardarRptesReptos.php
	@ResponseStatus(HttpStatus.CREATED) //Cod. 201
	public String guardarRepteReptosEjecOT(@RequestBody ReptesReptos_DTO repteRepto) { 
	//************************************************************************	
		return this.rptesEjecOTsAllServ.guardarRepteReptosEjecOT(repteRepto);
		
	}
	
	
	@PostMapping(path = "/saveRepteRepto2")  //guardarRptesReptos.php
	@ResponseStatus(HttpStatus.CREATED) //Cod. 201
	public String guardarRepteReptosEjecOT2(@RequestBody ReptesReptos_DTO2 repteRepto) { 
	//************************************************************************	
		return this.rptesEjecOTsAllServ.guardarRepteReptosEjecOT2(repteRepto);
		
	}
	
	
	@PostMapping(path = "/saveRepteSevExt")  //guardarRptesServExt.php
	@ResponseStatus(HttpStatus.CREATED) //Cod. 201
	public String guardarRepteServExtEjecOT(@RequestBody RepteServExtOTs_DTO repteServEx) { 
	//************************************************************************	
		return this.rptesEjecOTsAllServ.guardarRepteServExtEjecOT(repteServEx); 
		
	}
	
	
	@GetMapping(path = "/historEquips/{fechaInic}/{fechaFnl}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepteHistorMantto_DTO> getHistorialManttoEquips(@PathVariable String fechaInic, @PathVariable String fechaFnl, @PathVariable int idEmpresa) {	
	//**************************************************************************************************************
		return this.rptesEjecOTsAllServ.getHistorialManttoEquips(fechaInic, fechaFnl, idEmpresa);
	} 
	
	
	@GetMapping(path = "/historEquips2/{fechaInic}/{fechaFnl}/{statusClosed}/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<RepteHistorMantto_DTO> getHistorialEquipsOTsClosed(@PathVariable String fechaInic, @PathVariable String fechaFnl,
																   @PathVariable String statusClosed, @PathVariable int idEmpresa) {	
	//*******************************************************************************************************************************
		return this.rptesEjecOTsAllServ.getHistorialEquipsOTsClosed(fechaInic, fechaFnl, statusClosed, idEmpresa);
	} 
	
	
	
	
	
	//*********** REPORTES PARA USUARIO SUPER-ADMIN (HUGO MARTINEZ) *************************
	
	
	@GetMapping(path = "/getListaDeEmpesas") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Empresas_DTO> getListaDeEmpresasInscritas() {	
	//**************************************************************
		return this.rptesEjecOTsAllServ.getListaDeEmpresasInscritas();
	} 
	
	
	@GetMapping(path = "/getUsersEmpr/{idEmpresa}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Usuarios> getLstaDeUsuariosEmpr(@PathVariable int idEmpresa){	
	//************************************************************
		return this.rptesEjecOTsAllServ.getLstaDeUsuarios(idEmpresa); 
		
	} 
	
	
	
	
	
	
	
}
