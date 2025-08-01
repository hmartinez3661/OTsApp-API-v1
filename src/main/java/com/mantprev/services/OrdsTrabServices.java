package com.mantprev.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mantprev.entidades.Documentos_OTs;
import com.mantprev.entidadesDTO.OrdTrabDtsNewOtDTO;
import com.mantprev.entidadesDTO.OrdTrabInformHomeDTO;
import com.mantprev.entidadesDTO.OrdTrabInformOtDTO;
import com.mantprev.entidadesDTO.OrdTrabRevisionDTO;
import com.mantprev.entidadesDTO.OrdenesTrabajoDTO_2;


public interface OrdsTrabServices {
	
	
	public String actualizarStatusDeOT(int idOrdTrab, String newStatusOT); 
	
	public List<OrdTrabInformHomeDTO> getInformHomeDeOTs(String fechaInic, String fechaFinal, int idEmpresa);
	
	public List<OrdTrabInformOtDTO> getListaDeOTsNuevas(int idEmpresa);
	
	public List<OrdTrabInformOtDTO> getListaNewOTsByEjecutor(String ejecutor, int idEmpresa);
	
	public List<OrdTrabInformOtDTO> getListOTsEntre2Fechas(String fechaInic, String fechaFinal, int idEmpresa);
	
	public List<OrdTrabInformOtDTO> getListOTsByFechasUser(String fechaInic, String fechaFinal, String nombrUser, int idEmpresa); 
	
	public List<OrdTrabInformOtDTO> getListOTsParaCerrar(String status1, String status2, int idEmpresa);
	
	public List<OrdTrabInformOtDTO> getListOTsParaCerrarEjecut(String status1, String status2, String ejecutor, int idEmpresa);
	
	public String guardarRevisAutomatOT(OrdTrabRevisionDTO dtsOrdTrab);  
	
	public String gurdarRevisionOT(OrdTrabRevisionDTO dtsOrdTrab); 
	
	public String guardarNuevaOrdTrab(OrdTrabDtsNewOtDTO newOrdTrab);  
	
	public OrdenesTrabajoDTO_2 getOrdenDeTrabById(int idOT);

	public List<Documentos_OTs> getListaDocumentosOT(int idOT);
	
	public String saveDocumentoOT(MultipartFile fileDoc, int idOT, int idEmpresa);
	
	public String saveListDatosDocsOT(List<Documentos_OTs> listaDocsOT);
	
	
	
	
	
	
	
	
}
