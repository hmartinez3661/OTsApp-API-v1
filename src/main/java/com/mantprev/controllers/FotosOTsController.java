package com.mantprev.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mantprev.entidades.Fotos_CierreOTs;
import com.mantprev.entidades.Fotos_IngresoOTs;
import com.mantprev.services.FotosOTsService;



@RestController
@RequestMapping("/api/v1/fotosOTs")
public class FotosOTsController {
	
	
	@Autowired
	FotosOTsService fotosIngrOTsServ;
	
	

	@GetMapping(path = "/fotosIngreso/{idOT}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Fotos_IngresoOTs> getListaFotosIngresoOT(@PathVariable int idOT){
	//**************************************************************************
		return this.fotosIngrOTsServ.getListaFotosIngresoOT(idOT);
		 
	} 
	
	
	@GetMapping(path = "/fotosCierre/{idOT}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public List<Fotos_CierreOTs> getListaFotosCierreOT(@PathVariable int idOT){
	//************************************************************************
		return this.fotosIngrOTsServ.getListaFotosCierreOT(idOT);
		 
	}
	
	
	@GetMapping(path = "/namesFtsIngre/{idOT}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String getNombreFotosIngresoOT(@PathVariable int idOT){
	//************************************************************
		return this.fotosIngrOTsServ.getNombresFotosIngrOT(idOT);
		 
	} 
	
	
	@GetMapping(path = "/namesFtsCierre/{idOT}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200  
	public String getNombresFotosCierreOT(@PathVariable int idOT){
	//************************************************************
		return this.fotosIngrOTsServ.getNamesFotosCierreOT(idOT);
		 
	}
	

//*********************************  METODOS QUE USA Mantprev OTs de escritorio
	
	
	@PostMapping(path = "/uploadImgsIngr/{idOT}/{idEmpresa}")  
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String uploadFtosIngrToServer(@RequestParam MultipartFile file, @PathVariable int idOT, @PathVariable int idEmpresa){
	/**********************************************************************************************/
		return this.fotosIngrOTsServ.uploadImgsIngrOTsToServer(file, idOT, idEmpresa); 
	}
	
	
	@PostMapping(path = "/uploadImgCierre/{idOT}/{idEmpresa}") 
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String uploadFtosCierreToServer(@RequestParam MultipartFile file, @PathVariable int idOT, @PathVariable int idEmpresa){
	/*****************************************************************************************/
		return this.fotosIngrOTsServ.uploadImgsCierreOTsToServer(file, idOT, idEmpresa);
	}
	
	
	@PostMapping(path = "/uploadListImgsIngr/{idOT}/{idEmpresa}")           
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String uploadListImgsIngr(@RequestParam List<MultipartFile> listParts, @PathVariable int idOT,  @PathVariable int idEmpresa){ 
	/***************************************************************************************************/
		return this.fotosIngrOTsServ.uploadListImgsToServer(listParts, idOT);  
	}
	
	
	@PostMapping(path = "/saveListDtsFtosIngr")           
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String uploadDatosFotosIngrOT(@RequestBody List<Fotos_IngresoOTs> listDtsFotosIngr){  
	/**************************************************************************************/
		return this.fotosIngrOTsServ.saveListaDtsFtosIngreso(listDtsFotosIngr);
	}
	
	
	@PostMapping(path = "/saveListDtsFtosCierre")           
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String uploadDatosFotosCierreOT(@RequestBody List<Fotos_CierreOTs> listDtsFotsCierre){  
	/**************************************************************************************/
		return this.fotosIngrOTsServ.saveListaDtsFtosCierre(listDtsFotsCierre);
	}
	
	
//*********************************	
	
	
	@PostMapping(path = "/uploadListImgsCierre/{idOT}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)   //@RequestParam  @RequestPart             
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String uploadListFtosCierreToServer(@RequestParam List<MultipartFile> listaParts, @PathVariable int idOT){ 
	/******************************************************************************************************/
		return this.fotosIngrOTsServ.uploadListImgsCierreOTsToServer(listaParts, idOT);
	}
	
	
	@GetMapping("/downloadImg/{nombreFoto}") 
    public ResponseEntity<?> downloadImage(@PathVariable String nombreFoto){
	/*********************************************************************/	
        byte[] imageInbytes = fotosIngrOTsServ.downloadImageFromServer(nombreFoto);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpg")).body(imageInbytes);
    }
	
	
	@PostMapping(path = "/uploadImgEquip/{nombrFto}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)   //@RequestParam  @RequestPart             
	@ResponseStatus(HttpStatus.ACCEPTED) //Cod. 202
	public String uploadImgEquipoToServer(@RequestParam MultipartFile fotoFile, @PathVariable String nombrFto){ 
	/*************************************************************************************************************/
		return this.fotosIngrOTsServ.uploadImgEquipoToServer(fotoFile, nombrFto);
	}
	
	
	@PostMapping(path = "/saveNamesFtosIngr/{listNamesFotos}/{idOT}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200 
	public void saveNamesFtosIngrOT(@PathVariable ArrayList<String> listNamesFotos, @PathVariable int idOT){
	/******************************************************************************************************/	
		this.fotosIngrOTsServ.saveNamesFtosIngrOT(listNamesFotos, idOT);
	}
	
	
	@PostMapping(path = "/saveNamesFtosCierre/{listNamesFotos}/{idOT}")
	@ResponseStatus(HttpStatus.OK) //Cod. 200 
	public void saveNamesFtosCierreOT(@PathVariable ArrayList<String> listNamesFotos, @PathVariable int idOT){
	/******************************************************************************************************/	
		this.fotosIngrOTsServ.saveNamesFtosCierreOT(listNamesFotos, idOT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
