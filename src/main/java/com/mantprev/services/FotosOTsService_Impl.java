package com.mantprev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.mantprev.entidades.Fotos_CierreOTs;
import com.mantprev.entidades.Fotos_IngresoOTs;
import com.mantprev.repositorios.FotosCierreOTs_Repository;
import com.mantprev.repositorios.FotosIngresoOTs_Repository;
import com.mantprev.utilities.MetodosEstaticos;


@Service
public class FotosOTsService_Impl implements FotosOTsService {
	
	
	@Autowired
	private FotosIngresoOTs_Repository fotosIngresoOTs_Reposit;
	
	@Autowired 
	private FotosCierreOTs_Repository fotosCierreOTs_Reposit;
	
	@Autowired
	private FotosIngresoOTs_Repository fotosIngrOTs_Reposit;
	
	@Autowired
	private FotosCierreOTs_Repository fotosCierre_Reposit;
	
	@Value("${rutaUploads}")
	private String rutaUploads; 
	
	
	
	@Transactional(readOnly = true)
	@Override  
	public List<Fotos_IngresoOTs> getListaFotosIngresoOT(int idOT) {
	/***************************************************************/	
	    return fotosIngresoOTs_Reposit.getListaFotosIngresoOT(idOT);  
		
	}


	@Transactional(readOnly = true)
	@Override
	public List<Fotos_CierreOTs> getListaFotosCierreOT(int idOT) {
	/************************************************************/	
		return fotosCierreOTs_Reposit.getListaFotosCierreOT(idOT);
		
	}
	
	
	@Transactional(readOnly = true)
	@Override  
	public String getNombresFotosIngrOT(int idOT) {
	/**********************************************/
		List<Fotos_IngresoOTs> listaFtsIngr = fotosIngresoOTs_Reposit.getListaFotosIngresoOT(idOT);  
		
		String nombresFtsIngr = "";
		int listaFotosSize = listaFtsIngr.size();
		
		for(int i=0; i<listaFotosSize; i++) {
			
			Fotos_IngresoOTs fotoIngr = listaFtsIngr.get(i);
			String nombrFoto = fotoIngr.getNombreFoto();
			nombresFtsIngr = nombresFtsIngr +","+ nombrFoto;
		}
		
		nombresFtsIngr = nombresFtsIngr.replaceFirst(",", ""); 
		return nombresFtsIngr;
	}


	@Transactional(readOnly = true)
	@Override
	public String getNamesFotosCierreOT(int idOT) {
	/**********************************************/	
		List<Fotos_CierreOTs> listaFtsCierre = fotosCierreOTs_Reposit.getListaFotosCierreOT(idOT);
		
		String nombresFtsCierre = "";
		int listaFotosSiza = listaFtsCierre.size();
		
		for(int i=0; i<listaFotosSiza; i++) {
			
			Fotos_CierreOTs fotoCierre = listaFtsCierre.get(i);
			String nombrFoto = fotoCierre.getNombreFoto();
			nombresFtsCierre = nombresFtsCierre +","+ nombrFoto;
		}
		
		nombresFtsCierre = nombresFtsCierre.replaceFirst(",", ""); 
		return nombresFtsCierre;
	} 
	
	
	@Transactional
	@Override
	public String uploadImgsIngrOTsToServer(MultipartFile file, int idOT, int idEmpresa) {  //Utiliza la version de escritorio
	/******************************************************************/
		MultipartFile fotoMultipart = file;
		String nombreFoto = MetodosEstaticos.crearNombreDeFoto();
		String rutaFotosOTs = rutaUploads + "/fotosOTs/";
		
		try {
			byte[] fotoBytes = fotoMultipart.getBytes();
			Path rutaFinalImg = Paths.get(rutaFotosOTs + nombreFoto);
			Files.write(rutaFinalImg, fotoBytes);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		
		//Guarda los datos en la BD
		Fotos_IngresoOTs fotoIngr = new Fotos_IngresoOTs();
		fotoIngr.setNombreFoto(nombreFoto);
		fotoIngr.setIdOrdTrab(idOT);
		fotoIngr.setIdEmpresa(idEmpresa);
		
		fotosIngrOTs_Reposit.save(fotoIngr);
		return "OK"; 
	}
	
	
	@Transactional
	@Override
	public String uploadImgsCierreOTsToServer(MultipartFile file, int idOT, int idEmpresa) {  //Utiliza la version de escritorio
	/******************************************************************/
		MultipartFile fotoMultipart = file;
		String nombreFoto = MetodosEstaticos.crearNombreDeFoto(); 
		String rutaFotosOTs = rutaUploads + "/fotosOTs/";
		
		try {
			byte[] fotoBytes = fotoMultipart.getBytes();
			Path rutaFinalImg = Paths.get(rutaFotosOTs + nombreFoto);
			Files.write(rutaFinalImg, fotoBytes);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		
		//Guarda los datos en la BD
		Fotos_CierreOTs fotoCierre = new Fotos_CierreOTs();
		fotoCierre.setNombreFoto(nombreFoto);
		fotoCierre.setIdOrdTrab(idOT);
		fotoCierre.setIdEmpresa(idEmpresa);
		
		fotosCierre_Reposit.save(fotoCierre);
		return "OK";
	}
	
	
	@Transactional
	@Override
	public String uploadListImgsToServer(List<MultipartFile> listMultipart, @PathVariable int idOT) {
	/**********************************************************************************************/
		String rutaFotosOTs = rutaUploads + "/fotosOTs/";
		int listaImagesSiza = listMultipart.size();
		
		for(int i=0; i< listaImagesSiza; i++) {
			
			MultipartFile fotoMultipart = listMultipart.get(i);
			String nombreFoto = MetodosEstaticos.crearNombreDeFoto();
			
			try {
				byte[] fotoBytes = fotoMultipart.getBytes();
				Path rutaFinalImg = Paths.get(rutaFotosOTs + nombreFoto);
				Files.write(rutaFinalImg, fotoBytes);
				
			} catch (IOException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
		return "OK";
	}


	@Transactional
	@Override
	public String saveNamesFtosIngrOT(ArrayList<String> listNamesFtos, int idOT) {
	/****************************************************************************/
		int listaNamesFtsSiza = listNamesFtos.size();
		
		for(int i=0; i< listaNamesFtsSiza; i++) {
			String nombrFto = listNamesFtos.get(i);		
			
			Fotos_IngresoOTs fotoIngr = new Fotos_IngresoOTs();
			fotoIngr.setNombreFoto(nombrFto);
			fotoIngr.setIdOrdTrab(idOT);
			
			fotosIngrOTs_Reposit.save(fotoIngr);
		}
		
		return "OK";
	}
	
	
	@Transactional
	@Override
	public String uploadListImgsCierreOTsToServer(List<MultipartFile> listaParts, int idOT) {
	/******************************************************************************/	
		String rutaFotosOTs = rutaUploads + "/fotosOTs/";
		ArrayList<String> listFtosNames = new ArrayList<>();
		int cantFotos = listaParts.size() + 1;
		
		int listaPartsSize = listaParts.size();
		
		for(int i=0; i<listaPartsSize; i++) {
			int currentFto = i + 1;
			
			MultipartFile multipart = listaParts.get(1);
			String nombreFoto = MetodosEstaticos.crearNombreDeFoto();
			listFtosNames.add(nombreFoto);
			
			try {
				byte[] bytesImage = multipart.getBytes();
				Path rutaFinalImg = Paths.get(rutaFotosOTs + nombreFoto);
				Files.write(rutaFinalImg, bytesImage);
				
			} catch (IOException ex) {
				ex.printStackTrace();
				return null;
			}
			
			if(currentFto == cantFotos) {
				saveNamesFtosCierreOT(listFtosNames, idOT);
			}
		}	
		
		return "OK";
	}


	@Transactional
	@Override
	public String saveNamesFtosCierreOT(ArrayList<String> listNamesFtos, int idOT) {
	/****************************************************************************/
		int listaNamesFtsSize = listNamesFtos.size();
		
		for(int i=0; i < listaNamesFtsSize; i++) {
			String nombrFto = listNamesFtos.get(i);
			
			Fotos_CierreOTs fotoCierre = new Fotos_CierreOTs();
			fotoCierre.setNombreFoto(nombrFto);
			fotoCierre.setIdOrdTrab(idOT);
			
			fotosCierre_Reposit.save(fotoCierre);
		}
		
		return "OK";
	}


	
	@Override
	public byte[] downloadImageFromServer(String nombreFoto){
	/*****************************************************/
		String rutaFotosOTs = rutaUploads + "/fotosOTs/";
		String filePath = rutaFotosOTs;
        byte[] imageInByte = null;
        
		try {
			imageInByte = Files.readAllBytes(new File(filePath + nombreFoto).toPath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return imageInByte;
    }


	@Override
	public String uploadImgEquipoToServer(MultipartFile fotoFile, String nombrFto) {
	/******************************************************************************/
		String rutaFotosOTs = rutaUploads + "/fotosOTs/";
		String urlSave = rutaFotosOTs;
		
		try {
			byte[] bytesImage = fotoFile.getBytes();
			Path rutaFinalImg = Paths.get(urlSave + nombrFto);
			Files.write(rutaFinalImg, bytesImage);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return "OK";
	}
	
	
	
	

	
	
	
	
	
}
