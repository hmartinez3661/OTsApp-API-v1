package com.mantprev.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mantprev.entidades.Documentos_OTs;
import com.mantprev.entidades.OrdenesTrabajo;
import com.mantprev.entidadesDTO.OrdTrabDtsNewOtDTO;
import com.mantprev.entidadesDTO.OrdTrabInformHomeDTO;
import com.mantprev.entidadesDTO.OrdTrabInformOtDTO;
import com.mantprev.entidadesDTO.OrdTrabRevisionDTO;
import com.mantprev.entidadesDTO.OrdenesTrabajoDTO_2;
import com.mantprev.repositorios.ConfigSpinner_Repository;
import com.mantprev.repositorios.DocumentosOTs_Repository;
import com.mantprev.repositorios.OrdsTrab_Repository;
import com.mantprev.repositorios.ReptesEjecOTs_Repository;
import com.mantprev.utilities.MetodosEstaticos;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrdsTrabServices_Impl implements OrdsTrabServices{
/*************************************************************/
	
	@Autowired
	private OrdsTrab_Repository ordsTrab_Reposit;

	@Autowired
	private ReptesEjecOTs_Repository reptesEjecOTs_Reposit;
	
	@Autowired
	private ConfigSpinner_Repository configSpinn_Reposit;
	
	@Autowired
	private DocumentosOTs_Repository documentsOT_repost;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SimpleDateFormat dateFormat;
	
	@Value("${rutaUploads}")
	private String rutaUploads;
	
	
	
	@Transactional 
	@Override
	public String actualizarStatusDeOT(int idOrdTrab, String newStatusOT) {
	/*********************************************************************/	
		Integer idRepteEjecOT = reptesEjecOTs_Reposit.getUltimoIdTblReptesEjecOTs();  //230;  //
		
		Optional <OrdenesTrabajo> ordTrabOpt = ordsTrab_Reposit.findById(idOrdTrab);
		
		OrdenesTrabajo ordenTrab;
		
		if (ordTrabOpt.isPresent()) {
			
			ordenTrab = ordTrabOpt.get();
			ordenTrab.setEstatusOT(newStatusOT);
			ordenTrab.setIdRpteEjecOT(idRepteEjecOT);
			ordenTrab.setIdReporteEjecuc(idRepteEjecOT);
			
			ordsTrab_Reposit.save(ordenTrab);
			return "OK";
			
		} else {
			
			ordenTrab = null;
			new IllegalArgumentException("No se encontro Ord. Trab.");
			return "Error al ingresar Datos";
		}
	}

	
	@Transactional(readOnly = true) 
	@Override
	public List<OrdTrabInformHomeDTO> getInformHomeDeOTs(String fechaInic, String fechaFinal, int idEmpresa) {
	/****************************************************************************************/
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFinal); 
			
		} catch (ParseException ex) {
			ex.printStackTrace(); 
		}
		
		List<OrdenesTrabajo> listaOTs = ordsTrab_Reposit.getOTsByFechas(idEmpresa, fecha1, fecha2);   //idEmpresa
		List<OrdTrabInformHomeDTO> infomHomeOTsDTO = new ArrayList<OrdTrabInformHomeDTO>();
		
		int listaOTsSize = listaOTs.size();
		for(int i=0; i < listaOTsSize; i++) {
			
			OrdenesTrabajo ordTrab = listaOTs.get(i);
			OrdTrabInformHomeDTO OTinforHomeDTO = modelMapper.map(ordTrab, OrdTrabInformHomeDTO.class); 
			
			infomHomeOTsDTO.add(OTinforHomeDTO);
		}
		
		return infomHomeOTsDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<OrdTrabInformOtDTO> getListaDeOTsNuevas(int idEmpresa) {
	/***************************************************************/  
		List<String> listaStatusOTs = new ArrayList<String>();
		listaStatusOTs = configSpinn_Reposit.getItemsDeStatusDeOTs(idEmpresa);
		
		String statusNewsOTs = listaStatusOTs.get(1);
		List<OrdenesTrabajo> listaNewOTs = ordsTrab_Reposit.getListaOrdTrabByStatus(statusNewsOTs, idEmpresa);
		
		List<OrdTrabInformOtDTO> listaOTsNuevasDTO = new ArrayList<OrdTrabInformOtDTO>();
		OrdenesTrabajo ordenTrab = null;
		
		int listaNewOTsSize = listaNewOTs.size();
		for(int i=0; i < listaNewOTsSize; i++) {
			
			ordenTrab = listaNewOTs.get(i);
			int idRepteEjec = ordenTrab.getIdReporteEjecuc();
			Date fechaIngreso = ordenTrab.getFechaIngresoOT();
			
			OrdTrabInformOtDTO nvaOrdTrabDTO = modelMapper.map(ordenTrab, OrdTrabInformOtDTO.class); 
			
			String nombrEqu = ordenTrab.getEquipo().getNombEquipo();
			String correlat = ordenTrab.getEquipo().getCorrelativo();
			nvaOrdTrabDTO.setNombEquipo(nombrEqu);
			nvaOrdTrabDTO.setCorrelativo(correlat);
			nvaOrdTrabDTO.setIdReporteEjecuc(idRepteEjec);
			nvaOrdTrabDTO.setFechaIngrStr(MetodosEstaticos.getDateFormated(fechaIngreso)); //Retorna un String-Fecha "19 ago 2024"
			
			listaOTsNuevasDTO.add(nvaOrdTrabDTO);
		}
		
		return listaOTsNuevasDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<OrdTrabInformOtDTO> getListaNewOTsByEjecutor(String ejecutor, int idEmpresa) {
	/**************************************************************************/
		List<String> listaStatusOTs = new ArrayList<String>();
		listaStatusOTs = configSpinn_Reposit.getItemsDeStatusDeOTs(idEmpresa);
		
		String statusNewsOTs = listaStatusOTs.get(1);
		List<OrdenesTrabajo> listaNewOTs = ordsTrab_Reposit.getListaOrdTrabByStatus(statusNewsOTs, idEmpresa);
		
		List<OrdTrabInformOtDTO> listaNewOTsDTO = new ArrayList<OrdTrabInformOtDTO>();
		OrdenesTrabajo ordenTrab = null;
		
		int listaNewOTsSize = listaNewOTs.size();
		for(int i=0; i < listaNewOTsSize; i++) {
			
			ordenTrab = listaNewOTs.get(i);
			int idRepteEjec = ordenTrab.getIdReporteEjecuc();
			String persEjecutor = ordenTrab.getPersEjecutor();
				
			if (persEjecutor.equals(ejecutor)) {
				OrdTrabInformOtDTO nvaOrdTrabDTO = modelMapper.map(ordenTrab, OrdTrabInformOtDTO.class); 
				
				String nombrEqu = ordenTrab.getEquipo().getNombEquipo();
				String correlat = ordenTrab.getEquipo().getCorrelativo();
				nvaOrdTrabDTO.setNombEquipo(nombrEqu);
				nvaOrdTrabDTO.setCorrelativo(correlat);
				nvaOrdTrabDTO.setIdReporteEjecuc(idRepteEjec);
				
				listaNewOTsDTO.add(nvaOrdTrabDTO);
			}
		}
		
		return listaNewOTsDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<OrdTrabInformOtDTO> getListOTsEntre2Fechas(String fechaInic, String fechaFinal, int idEmpresa) {
	/******************************************************************************************/ 
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFinal); 
			
		} catch (ParseException ex) {
			ex.printStackTrace(); 
		}
		
		List<OrdenesTrabajo> listaPrincOTs = ordsTrab_Reposit.getOTsByFechas(idEmpresa, fecha1, fecha2);   //idEmpresa
		ArrayList<OrdTrabInformOtDTO> listOTsByFechasDTO = new ArrayList<OrdTrabInformOtDTO>();
		OrdenesTrabajo ordenTrab = null;
		
		int listaOTsSize = listaPrincOTs.size();
		for(int i=0; i < listaOTsSize; i++) {
			
			ordenTrab = listaPrincOTs.get(i);
			int idRepteEjec = ordenTrab.getIdReporteEjecuc();
			Date fechaIngreso = ordenTrab.getFechaIngresoOT();
			
			OrdTrabInformOtDTO nvaOrdTrabDTO = modelMapper.map(ordenTrab, OrdTrabInformOtDTO.class); 
			
			String nombrEqu = ordenTrab.getEquipo().getNombEquipo();
			String correlat = ordenTrab.getEquipo().getCorrelativo();
			nvaOrdTrabDTO.setNombEquipo(nombrEqu);
			nvaOrdTrabDTO.setCorrelativo(correlat); 
			nvaOrdTrabDTO.setIdReporteEjecuc(idRepteEjec);
			nvaOrdTrabDTO.setFechaIngrStr(MetodosEstaticos.getDateFormated(fechaIngreso)); //Retorna un String-Fecha "19 ago 2024"
			
			listOTsByFechasDTO.add(nvaOrdTrabDTO);
		}
		
		return listOTsByFechasDTO;
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public List<OrdTrabInformOtDTO> getListOTsByFechasUser(String fechaInic, String fechaFinal, String nombrUser, int idEmpresa) {
	/*********************************************************************************************************/
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFinal); 
			
		} catch (ParseException ex) {
			ex.printStackTrace(); 
		}
		
		List<OrdenesTrabajo> listaPrincOTs = ordsTrab_Reposit.getOTsByFechas(idEmpresa,fecha1, fecha2);  //idEmpresa
		ArrayList<OrdTrabInformOtDTO> listOTsByFechasDTO = new ArrayList<OrdTrabInformOtDTO>();
		OrdenesTrabajo ordenTrab = null;
		
		int listaOTsSize = listaPrincOTs.size();
		for(int i=0; i < listaOTsSize; i++) {
			
			ordenTrab = listaPrincOTs.get(i);
			int idRepteEjec = ordenTrab.getIdReporteEjecuc();
			String nombreSolicit = ordenTrab.getNombSolicitante();
			Date fechaIngreso = ordenTrab.getFechaIngresoOT();
			
			if (nombreSolicit.equals(nombrUser)) {
				
				OrdTrabInformOtDTO nvaOrdTrabDTO = modelMapper.map(ordenTrab, OrdTrabInformOtDTO.class); 
				
				String nombrEqu = ordenTrab.getEquipo().getNombEquipo();
				String correlat = ordenTrab.getEquipo().getCorrelativo();
				nvaOrdTrabDTO.setNombEquipo(nombrEqu);
				nvaOrdTrabDTO.setCorrelativo(correlat); 
				nvaOrdTrabDTO.setIdReporteEjecuc(idRepteEjec);
				nvaOrdTrabDTO.setFechaIngrStr(MetodosEstaticos.getDateFormated(fechaIngreso)); //Retorna un String-Fecha "19 ago 2024"
				
				listOTsByFechasDTO.add(nvaOrdTrabDTO);
			}
		}
		
		return listOTsByFechasDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<OrdTrabInformOtDTO> getListOTsParaCerrar(String status1, String status2, int idEmpresa) {
	/***********************************************************************************/ 
		List<OrdenesTrabajo> listaPrincOTs = ordsTrab_Reposit.getListaOTsParaCerrar(status1, status2, idEmpresa);
		ArrayList<OrdTrabInformOtDTO> listOTsParaCerrarDTO = new ArrayList<OrdTrabInformOtDTO>();
		OrdenesTrabajo ordenTrab = null;
		
		int listaOTsSize = listaPrincOTs.size();
		for(int i=0; i < listaOTsSize; i++) {
			
			ordenTrab = listaPrincOTs.get(i);
			int idRepteEjec = ordenTrab.getIdReporteEjecuc(); 
			Date fechaIngreso = ordenTrab.getFechaIngresoOT();
			
			OrdTrabInformOtDTO ordTrabModifDTO = modelMapper.map(ordenTrab, OrdTrabInformOtDTO.class); 
			
			String nombrEqu = ordenTrab.getEquipo().getNombEquipo();
			String correlat = ordenTrab.getEquipo().getCorrelativo();
			ordTrabModifDTO.setNombEquipo(nombrEqu);
			ordTrabModifDTO.setCorrelativo(correlat); 
			ordTrabModifDTO.setIdReporteEjecuc(idRepteEjec);
			ordTrabModifDTO.setFechaIngrStr(MetodosEstaticos.getDateFormated(fechaIngreso)); //Retorna un String-Fecha "19 ago 2024"
			
			listOTsParaCerrarDTO.add(ordTrabModifDTO);
		}
		
		return listOTsParaCerrarDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<OrdTrabInformOtDTO> getListOTsParaCerrarEjecut(String status1, String status2, String ejecutor, int idEmpresa) {
	/***********************************************************************************************************/
		List<OrdenesTrabajo> listaPrincOTs = ordsTrab_Reposit.getListaOTsParaCerrar(status1, status2, idEmpresa);
		ArrayList<OrdTrabInformOtDTO> listOTsParaCerrarDTO = new ArrayList<OrdTrabInformOtDTO>();
		OrdenesTrabajo ordenTrab = null;
		
		int listaOTsSize = listaPrincOTs.size();
		for(int i=0; i < listaOTsSize; i++) {
			
			ordenTrab = listaPrincOTs.get(i);
			int idRepteEjec = ordenTrab.getIdReporteEjecuc();
			String persEject = ordenTrab.getPersEjecutor();
			
			if(persEject.equals(ejecutor)) {
				
				OrdTrabInformOtDTO ordTrabModifDTO = modelMapper.map(ordenTrab, OrdTrabInformOtDTO.class); 
				
				String nombrEqu = ordenTrab.getEquipo().getNombEquipo();
				String correlat = ordenTrab.getEquipo().getCorrelativo();
				ordTrabModifDTO.setNombEquipo(nombrEqu);
				ordTrabModifDTO.setCorrelativo(correlat); 
				ordTrabModifDTO.setIdReporteEjecuc(idRepteEjec);
				
				listOTsParaCerrarDTO.add(ordTrabModifDTO);
			}
		}
		
		return listOTsParaCerrarDTO;
	}
	
	
	@Transactional
	@Override
	public String guardarRevisAutomatOT(OrdTrabRevisionDTO dtsOrdTrab) {
	/************************************************************/
		Integer idOrdTrab    = dtsOrdTrab.getIdOrdTrab();
		String nombreReviso  = dtsOrdTrab.getNombPersReviso();
		String newStatusDeOT = dtsOrdTrab.getStatusDeOT();
		String newClasificOT = dtsOrdTrab.getClasificJOB();
		String fechaRevision = dtsOrdTrab.getFechaRevison();
		String horaRevision  = dtsOrdTrab.getHoraRevison();
		
		Date fechaRevisDate = null;
		try {
			fechaRevisDate = dateFormat.parse(fechaRevision);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		Optional <OrdenesTrabajo> ordTrabOpt = ordsTrab_Reposit.findById(idOrdTrab);
		OrdenesTrabajo ordenTrab;
		
		if (ordTrabOpt.isPresent()) {
			
			ordenTrab = ordTrabOpt.get();
			ordenTrab.setNombreReviso(nombreReviso);
			ordenTrab.setEstatusOT(newStatusDeOT);
			ordenTrab.setClasificTrabajo(newClasificOT);
			ordenTrab.setFechaRevision(fechaRevisDate);
			ordenTrab.setHoraRevision(horaRevision);
			
			ordsTrab_Reposit.save(ordenTrab);
			return "OK";
			
		} else {
			ordenTrab = null;
			new IllegalArgumentException("No se encontro Ord. Trab.");
			return "Error al ingresar Datos";
		}
	}
	
	
	@Override
	public String gurdarRevisionOT(OrdTrabRevisionDTO dtsOrdTrab) {
	/*************************************************/
		Integer idOrdTrab = dtsOrdTrab.getIdOrdTrab();
		String nombRevis = dtsOrdTrab.getNombPersReviso();
		String fechRevis = dtsOrdTrab.getFechaRevison();
		String horaRevis = dtsOrdTrab.getHoraRevison();
		String trabSolic = dtsOrdTrab.getTrabajoSolicit();
		String persEject = dtsOrdTrab.getPersEjecutor();
		String prioridOT = dtsOrdTrab.getPrioridadOT();
		String clasifJOB = dtsOrdTrab.getClasificJOB();
		String estatusOT = dtsOrdTrab.getStatusDeOT();
		String nEstimTec = dtsOrdTrab.getNumEstimTecn();
		String nEstimHrs = dtsOrdTrab.getNumEstimHrs();
		String indPrevTrab = dtsOrdTrab.getIndicPrevTrab();
		String explRechazo = dtsOrdTrab.getExplicRechazo();
		
		Date fechaRevisDate = null;
		try {
			fechaRevisDate = dateFormat.parse(fechRevis);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		Optional <OrdenesTrabajo> ordTrabOpt = ordsTrab_Reposit.findById(idOrdTrab);
		OrdenesTrabajo ordenTrab;
		
		if (ordTrabOpt.isPresent()) {
			
			ordenTrab = ordTrabOpt.get();
			
			ordenTrab.setNombreReviso(nombRevis);
			ordenTrab.setFechaRevision(fechaRevisDate);
			ordenTrab.setHoraRevision(horaRevis);
			ordenTrab.setTrabajoSolicit(trabSolic);
			ordenTrab.setPersEjecutor(persEject);
			ordenTrab.setPrioridadOT(prioridOT);
			ordenTrab.setClasificTrabajo(clasifJOB);
			ordenTrab.setEstatusOT(estatusOT);
			ordenTrab.setPersonalEstim(Integer.parseInt(nEstimTec));
			ordenTrab.setTiempoEstim(Double.parseDouble(nEstimHrs));
			ordenTrab.setIndicacPreviasTrab(indPrevTrab);  
			ordenTrab.setExplicRechazo(explRechazo);
			
			ordsTrab_Reposit.save(ordenTrab);
			return "OK";
			
		} else {
			ordenTrab = null;
			new IllegalArgumentException("No se encontro Ord. Trab.");
			return "Error al ingresar Datos";
		}
	}
	
	
	@Transactional
	@Override
	public String guardarNuevaOrdTrab(OrdTrabDtsNewOtDTO newOrdTrab) {
	/*********************************************************/
		String idEquipo    = newOrdTrab.getIdEquipo();
		String trabSolic   = newOrdTrab.getTrabajoSolicit();
		String persEjecut  = newOrdTrab.getPersEjecutor();
		String prioridadOT = newOrdTrab.getPrioridadOT();
		String nombSolict  = newOrdTrab.getNombreSolict();
		String statusDeOT  = newOrdTrab.getStatusDeOT();
		String horaAct     = newOrdTrab.getHoraAct();
		String cantFotos   = newOrdTrab.getCantFotosAnex();
		int idEmpresa = newOrdTrab.getIdEmpresa();
		
		OrdenesTrabajo nvaOrdTrab = new OrdenesTrabajo();
		
		int numOT; //Cuando una empresa no ha generado OTs el ultimo numero va a ser null
		if(ordsTrab_Reposit.getUltimNumOrdenesTrab(idEmpresa) != null) {
			numOT = ordsTrab_Reposit.getUltimNumOrdenesTrab(idEmpresa) + 1;
		} else {
			numOT = 1;
		}
		
		nvaOrdTrab.setIdEquipo(Integer.parseInt(idEquipo));
		nvaOrdTrab.setTrabajoSolicit(trabSolic);
		nvaOrdTrab.setPersEjecutor(persEjecut);
		nvaOrdTrab.setPrioridadOT(prioridadOT);
		nvaOrdTrab.setNombSolicitante(nombSolict);
		nvaOrdTrab.setEstatusOT(statusDeOT);
		nvaOrdTrab.setFechaIngresoOT(new Date());
		nvaOrdTrab.setHoraIngresoOT(horaAct);
		nvaOrdTrab.setCantFotosAnex(Integer.parseInt(cantFotos));
		nvaOrdTrab.setIdReporteEjecuc(0);
		nvaOrdTrab.setTiempoEstim(0.25);
		nvaOrdTrab.setPersonalEstim(1);  
		nvaOrdTrab.setIdEmpresa(idEmpresa);
		nvaOrdTrab.setNumOT(numOT); 
		
		String idOrdTrab = "0";
		try {
			ordsTrab_Reposit.save(nvaOrdTrab);
			idOrdTrab = Integer.toString(nvaOrdTrab.getIdOT());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return idOrdTrab;
	}


	@Transactional(readOnly = true)
	@Override
	public OrdenesTrabajoDTO_2 getOrdenDeTrabById(int idOT) {
	/*******************************************************/
		Optional <OrdenesTrabajo> ordTrabOpt = ordsTrab_Reposit.findById(idOT);
		
		OrdenesTrabajo ordenTrab;
		OrdenesTrabajoDTO_2 ordTrabfDTO = null;
		
		if (ordTrabOpt.isPresent()) {
			
			ordenTrab = ordTrabOpt.get();
			ordTrabfDTO = modelMapper.map(ordenTrab, OrdenesTrabajoDTO_2.class); 
			
			String nombrEqu = ordenTrab.getEquipo().getNombEquipo();
			String correlat = ordenTrab.getEquipo().getCorrelativo();
			//int idEmpresa = ordenTrab.getEquipo().getIdEmpresa();
			
			ordTrabfDTO.setNombEquipo(nombrEqu);
			ordTrabfDTO.setCorrelativo(correlat);
		}
		
		return ordTrabfDTO;
	}


	
	@Transactional(readOnly = true)
	@Override
	public List<Documentos_OTs> getListaDocumentosOT(int idOT) {
	/**********************************************************/
		List<Documentos_OTs> listDocsOT = documentsOT_repost.getListaDocumentosOT(idOT);
		
		int listaDocsSize = listDocsOT.size();
		
		for (int i=0; i < listaDocsSize; i++) {
			Documentos_OTs docOT = listDocsOT.get(i);
			docOT.setComodin(Integer.toString(i+1));  //Para numerar el documento
		}
		
		return listDocsOT;
	}
	
	
	@Transactional
	@Override
	public String saveDocumentoOT(MultipartFile fileDoc, int idOT, int idEmpresa){;
	/*******************************************************/
		MultipartFile docMultipart = fileDoc;
		String nombreDoc = fileDoc.getOriginalFilename();
		String nombSinEs = nombreDoc.replaceAll(" ", "_");
		nombreDoc = nombreDoc.replaceAll(" ", "_");

		String rutaDocumOTs = rutaUploads + "/docsOTs/";
		
		try {
			byte[] docBytes = docMultipart.getBytes();
			Path rutaFinalDoc = Paths.get(rutaDocumOTs + nombreDoc);
			Files.write(rutaFinalDoc, docBytes);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		
		//Guarda los nombres de los documentos en su tabla en la BD
		Documentos_OTs documentOT = new Documentos_OTs();
		documentOT.setNombreDoc(nombreDoc);
		documentOT.setNombreSinEsp(nombSinEs); 
		documentOT.setIdOrdTrab(idOT);
		documentOT.setIdEmpresa(idEmpresa);
		
		documentsOT_repost.save(documentOT);
		return "OK";
	}


	@Transactional
	@Override
	public String saveListDatosDocsOT(List<Documentos_OTs> listaDocsOT) {
	/*******************************************************************/
		documentsOT_repost.saveAll(listaDocsOT);
		return "OK";
	}


	


	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
