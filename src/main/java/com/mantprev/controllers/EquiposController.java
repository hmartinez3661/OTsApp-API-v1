package com.mantprev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.mantprev.entidadesDTO.Equipos01_DTO;
import com.mantprev.services.EquiposService;


@RestController
@RequestMapping("/api/v1/equipos")
public class EquiposController {

	
	@Autowired
	EquiposService equiposService;
	
	
	
	@GetMapping(path = "/getAll/{idEmpresa}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Equipos01_DTO> getListaEquipos(@PathVariable int idEmpresa){	
	//*********************************************************************
		return this.equiposService.getLstaTodosLosEquipos(idEmpresa);
	} 
	
	
	@GetMapping(path = "/getEquipAndChildren/{idEquipo}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Equipos01_DTO> getEquipAndChildren(@PathVariable int idEquipo){	
	//******************************************
		return this.equiposService.getEquipAndChildren(idEquipo);
	} 
	
	
	@GetMapping(path = "/getListEquipsChildren/{idPadre}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Equipos01_DTO> getListEquipsChildren(@PathVariable int idPadre){	
	//**************************************************************************
		return this.equiposService.getListEquipsChildren(idPadre);
	} 
	
	
	@DeleteMapping(path = "/delete/{idEquipo}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 201
	public String eliminarEquipoDeBD(@PathVariable int idEquipo) {
	/************************************************************/
		try {
			equiposService.eliminarEquipoDeBD(idEquipo); 
			return"Eliminado";
			
		} catch (DataAccessException dtaExcp){
			return "NoEliminado"; 
		}
	}
	
	
	@PostMapping(path = "/save/{nombreNvoEquip}/{correlatNvoEqu}")
	@ResponseStatus(HttpStatus.CREATED) //Cod. 201
	public String registrarNuevoEquipo(@PathVariable String nombreNvoEquip, @PathVariable String correlatNvoEqu) {
	/************************************************************************************************************/
		nombreNvoEquip = nombreNvoEquip.replaceAll("*", "-").replace('"', '-');
		
		try {
			equiposService.registrarNuevoEquipo(nombreNvoEquip, correlatNvoEqu); 
			return "EXITO";
			
		} catch (DataAccessException dtaExcp){
			return "Error al ingresar Datos"; 
		}
	}
	
	
	@PutMapping(path = "/update/{idEquipo}/{nombreEquipo}/{correlatEquip}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String actualizarDatosEquipo(@PathVariable int idEquipo, 
										@PathVariable String nombreEquipo, 
										@PathVariable String correlatEquip) {
	/************************************************************************/
		nombreEquipo = nombreEquipo.replaceAll("*", "-").replace('"', '-');
		
		try {
			equiposService.actualizarDatosEquipo(idEquipo, nombreEquipo, correlatEquip); 
			return "EXITO";
			
		} catch (DataAccessException dtaExcp){
			return "FALLO EN ACTUALIZAR EQUIPO"; 
		}
	}
	
	
	@GetMapping(path = "/getFichTec/{idEquipo}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public Equipos01_DTO getFichaTecnEquipo(@PathVariable int idEquipo){	
	//***********************************************************
		return this.equiposService.getEquipoById(idEquipo); 
	} 
	
	
	@PutMapping(path = "/saveFichaTec/{idEquipo}/{datosTecn}/{nombrFotogr}")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String guardarFichaTecnica(@PathVariable int idEquipo, 
									  @PathVariable String datosTecn, 
									  @PathVariable String nombrFotogr) {
	/********************************************************************/
		return this.equiposService.guardarFichaTecnica(idEquipo, datosTecn, nombrFotogr); 
	}
	
	
	@PutMapping(path = "/insertNvoEquip")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public int insertarNuevoEquipo(@RequestBody Equipos01_DTO equipoDTO) {
	/********************************************************************/
		return this.equiposService.insertarNuevoEquipo(equipoDTO); 
	}
	
	
	@PutMapping(path = "/changeNameEquip")
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String renombrarEquipo(@RequestBody Equipos01_DTO equipoDTO) {
	/****************************************************************/
		return this.equiposService.renombrarEquipo(equipoDTO); 
	}
	
	
	@GetMapping(path = "/getCantOTs/{idEquipo}") // @GetMapping
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public int getCantidadOTsDelEquipo(@PathVariable int idEquipo){	
	//***********************************************************
		return this.equiposService.getCantidadOTsDelEquipo(idEquipo); 
	} 
	
	
	
	
	
	
	
	
	
	
	
}
