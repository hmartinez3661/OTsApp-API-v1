package com.mantprev.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.mantprev.entidades.Fotos_CierreOTs;
import com.mantprev.entidades.Fotos_IngresoOTs;


public interface FotosOTsService {
	
	
	public List<Fotos_IngresoOTs> getListaFotosIngresoOT(int idOT);
	
	public List<Fotos_CierreOTs> getListaFotosCierreOT(int idOT);
	
	public String getNombresFotosIngrOT(int idOT);
	
	public String getNamesFotosCierreOT(int idOT);
	
	public String saveNamesFtosIngrOT(ArrayList<String> listNamesFtos, int idOT);
	
	public String saveNamesFtosCierreOT(ArrayList<String> listNamesFtos, int idOT);
	
	public String uploadImgsIngrOTsToServer(MultipartFile file, int idOT, int idEmpresa); 
	
	public String uploadImgsCierreOTsToServer(MultipartFile file, int idOT, int idEmpresa);
	
	public String uploadListImgsToServer(List<MultipartFile> listMultipart, @PathVariable int idOT);
	
	public String uploadListImgsCierreOTsToServer(List<MultipartFile> listaParts, @PathVariable int idOT);
	
	public byte[] downloadImageFromServer(String nombreFoto);
	
	public String uploadImgEquipoToServer(MultipartFile fotoFile, String nombrFto);
	
	public String saveListaDtsFtosIngreso(List<Fotos_IngresoOTs> listDtsFotosIngr);  
	
	public String saveListaDtsFtosCierre(List<Fotos_CierreOTs> listDtsFotosCierre);
	
	
	
	
}
