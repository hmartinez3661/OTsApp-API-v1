
package com.mantprev.services;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.Empresas_Inscrit;
import com.mantprev.entidades.OrdenesTrabajo;
import com.mantprev.entidades.ReptesEjecOTs;
import com.mantprev.entidades.ReptesPersEjecOTs;
import com.mantprev.entidades.RptesReptosEjecOTs;
import com.mantprev.entidades.RptesServExtEjecOTs;
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
import com.mantprev.repositorios.Empresas_Repository;
import com.mantprev.repositorios.OrdsTrab_Repository;
import com.mantprev.repositorios.ReptesEjecOTs_Repository;
import com.mantprev.repositorios.RptesReptosEjecOTs_Reposit;
import com.mantprev.repositorios.RptesServExtEjecOTs_Reposit;
import com.mantprev.repositorios.RtesPersEjecOTs_Reposit;
import com.mantprev.repositorios.Usuarios_Repository;
import com.mantprev.utilities.MetodosEstaticos;


@Service
public class RptesEjecOTsAllServ_Impl implements RptesEjecOTsAllServ {
	
	
	@Autowired
	private ReptesEjecOTs_Repository reptesEjecOTs_Reposit;
	
	@Autowired
	private RtesPersEjecOTs_Reposit rtesPersEjecOTs_Reposit;
	
	@Autowired
	private RptesReptosEjecOTs_Reposit rptesReptosEjecOTs_Reposit;
	
	@Autowired
	private RptesServExtEjecOTs_Reposit rptesServExtEjecOTs_Reposit;
	
	@Autowired
	private OrdsTrab_Repository ordsTrab_Reposit;
	
	@Autowired
	private Empresas_Repository empresasInscrit_Reposit;
	
	@Autowired
	private Usuarios_Repository usuariosReposit;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//@Autowired
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	@Transactional(readOnly = true)
	@Override
	public List<Repte2Datos_DTO> getListReptesPersEjecOTByIdOT(int idOT) {  //Lo utiliza App Androi
	/****************************************************************/
		List<ReptesPersEjecOTs> listaReptesPers = rtesPersEjecOTs_Reposit.getReportesPersonalTrabajoOT(idOT);
		List<Repte2Datos_DTO> listaTencs = new ArrayList<Repte2Datos_DTO>();
		int listaReptosPersSize = listaReptesPers.size();
		
		for (int i=0; i <listaReptosPersSize; i++) {
			
			ReptesPersEjecOTs reptePers = listaReptesPers.get(i);
			Repte2Datos_DTO repte2Dts = new Repte2Datos_DTO();
			
			repte2Dts.setNombre(reptePers.getPersonal_tecn().getNombreEmpl());
			repte2Dts.setValor(reptePers.getCantHrs());
			
			listaTencs.add(repte2Dts);
		}
		
		return listaTencs; 
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public List<ReptesPersTecn_DTO> getListReptesPersEjecOTByIdOT2(int idOT) {  //Lo utiliza App Web
	/****************************************************************/
		List<ReptesPersEjecOTs> listaReptesPers = rtesPersEjecOTs_Reposit.getReportesPersonalTrabajoOT(idOT);
		List<ReptesPersTecn_DTO> listaTencs = new ArrayList<ReptesPersTecn_DTO>();
		int listaReptosPersSize = listaReptesPers.size();
				
		for (int i=0; i <listaReptosPersSize; i++) {
			
			ReptesPersEjecOTs reptePers = listaReptesPers.get(i);
			ReptesPersTecn_DTO reptePersTec = new ReptesPersTecn_DTO();
			
			reptePersTec.setNombreEmpl(reptePers.getPersonal_tecn().getNombreEmpl());
			reptePersTec.setCantidHras(Double.toString(reptePers.getCantHrs()));
			reptePersTec.setCalidTrabr(Double.toString(reptePers.getCalidadTrab()));
			reptePersTec.setFechaOrdTr(MetodosEstaticos.getDateFormated(reptePers.getFechaEjec()));
			
			listaTencs.add(reptePersTec);
		}
		
		return listaTencs;    //listaTencs; 
	}


	@Transactional(readOnly = true)
	@Override
	public List<Repte2Datos_DTO> getListReptesRepuestosEjecOT(int idOT) {
	/******************************************************************/
		List<RptesReptosEjecOTs> listReptesReptos = rptesReptosEjecOTs_Reposit.getListaReptesRepuestosEjecOT(idOT); 
		List<Repte2Datos_DTO> listaReptos = new ArrayList<Repte2Datos_DTO>();
		int listaReptesRtosSize = listReptesReptos.size();
		
		for (int i=0; i <listaReptesRtosSize; i++) {
			
			RptesReptosEjecOTs repteRep = listReptesReptos.get(i);
			Repte2Datos_DTO repteRepDTO = new Repte2Datos_DTO(); 
			
			repteRepDTO.setNombre(repteRep.getNombreRep());
			repteRepDTO.setValor(repteRep.getCostoTotal());
			
			listaReptos.add(repteRepDTO);
		}
		
		return listaReptos; 
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public List<ReptesReptos_DTO> getListReptesRepuestosEjecOT2(int idOT) {
	/******************************************************************/
		List<RptesReptosEjecOTs> listReptesReptos = rptesReptosEjecOTs_Reposit.getListaReptesRepuestosEjecOT(idOT); 
		List<ReptesReptos_DTO> listaReptos = new ArrayList<ReptesReptos_DTO>();
		int listaReptesRtosSize = listReptesReptos.size();
		
		for (int i=0; i <listaReptesRtosSize; i++) {
			
			RptesReptosEjecOTs repteRep = listReptesReptos.get(i);
			ReptesReptos_DTO repteRepDTO = new ReptesReptos_DTO(); 
			
			repteRepDTO.setNombreRep(repteRep.getNombreRep());
			repteRepDTO.setCostTotal(Double.toString(repteRep.getCostoTotal()));
			repteRepDTO.setDateConsu(MetodosEstaticos.getDateFormated(repteRep.getFechaConsumo()));
			repteRepDTO.setIdOrdTrab(Integer.toString(repteRep.getIdOT()));
			
			listaReptos.add(repteRepDTO);
		}
		
		return listaReptos; 
	}


	@Transactional(readOnly = true)
	@Override
	public List<Repte2Datos_DTO> getListaReptesServExterEjecOT(int idOT) {
	/******************************************************************/
		List<RptesServExtEjecOTs> listReptesServExt = rptesServExtEjecOTs_Reposit.getListaReptesServExterEjecOT(idOT); 
		List<Repte2Datos_DTO> listaServExt = new ArrayList<Repte2Datos_DTO>();
		int listaRtesServExtSize = listReptesServExt.size();
		
		for (int i=0; i<listaRtesServExtSize; i++) {
			
			RptesServExtEjecOTs RepteSerExt = listReptesServExt.get(i);
			Repte2Datos_DTO repteRepDTO = new Repte2Datos_DTO();
			
			repteRepDTO.setNombre(RepteSerExt.getNombreServic());
			repteRepDTO.setValor(RepteSerExt.getCostoServic());
			
			listaServExt.add(repteRepDTO);
		}
		
		return listaServExt; 
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public List<RepteServExtOTs_DTO> getListaReptesServExterEjecOT2(int idOT) {
	/******************************************************************/
		List<RptesServExtEjecOTs> listReptesServExt = rptesServExtEjecOTs_Reposit.getListaReptesServExterEjecOT(idOT); 
		List<RepteServExtOTs_DTO> listaServExt = new ArrayList<RepteServExtOTs_DTO>();
		int listaRtesServExtSize = listReptesServExt.size();
		
		for (int i=0; i<listaRtesServExtSize; i++) {
			
			RptesServExtEjecOTs RepteSerExt = listReptesServExt.get(i);
			RepteServExtOTs_DTO repteRepDTO = new RepteServExtOTs_DTO();
			
			repteRepDTO.setNombreServic(RepteSerExt.getNombreServic());
			repteRepDTO.setCostoServic(RepteSerExt.getCostoServic());
			repteRepDTO.setFechaServic(MetodosEstaticos.getDateFormated(RepteSerExt.getFechaServic())); 
			repteRepDTO.setIdOT(RepteSerExt.getIdOT());
			repteRepDTO.setIdReporte(RepteSerExt.getIdReporte());
			
			listaServExt.add(repteRepDTO);
		}
		
		return listaServExt; 
	}


	@Transactional(readOnly = true)
	@Override
	public List<ReptesPersTecn_DTO> getListReptesPersEjecOTsByDates(String fechaInic, String fechaFnl, int idEmpresa) {
	/**************************************************************************************************************/
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace(); 
		}
		
		List<ReptesPersEjecOTs> listRtesPersTec = rtesPersEjecOTs_Reposit.getReptsPersEjecOTsByFechas(fecha1, fecha2, idEmpresa);
		List<ReptesPersTecn_DTO> listRtesPerEjecOTsDTO = new ArrayList<ReptesPersTecn_DTO>();
		int listaPersTecnSize = listRtesPersTec.size();
		
		for (int i=0; i < listaPersTecnSize; i++) {
			
			ReptesPersEjecOTs reptePersTec = listRtesPersTec.get(i);
			ReptesPersTecn_DTO reptePersTecDTO = new ReptesPersTecn_DTO();
			
			reptePersTecDTO.setNumOT(reptePersTec.getOrdenTrab().getNumOT());
			reptePersTecDTO.setNombreEmpl(reptePersTec.getPersonal_tecn().getNombreEmpl());
			reptePersTecDTO.setTipoEjecut(reptePersTec.getPersonal_tecn().getTipoEjecutor());
			reptePersTecDTO.setCantidHras(Double.toString(reptePersTec.getCantHrs()));
			reptePersTecDTO.setCalidTrabr(Double.toString(reptePersTec.getCalidadTrab()));
			reptePersTecDTO.setIdEmpleado(Integer.toString(reptePersTec.getIdEmpleado()));
			reptePersTecDTO.setIdOrdTraba(Integer.toString(reptePersTec.getIdOT()));
			reptePersTecDTO.setFechaOrdTr(dateFormat.format(reptePersTec.getFechaEjec()));
			reptePersTecDTO.setTrabSolict(reptePersTec.getOrdenTrab().getTrabajoSolicit());
			
			listRtesPerEjecOTsDTO.add(reptePersTecDTO);
		}
		
		return listRtesPerEjecOTsDTO;
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public List<ReptesPersTecn_DTO> getListReptesPersEjecOTsByDates2(Date fechaInic, Date fechaFnl, int idEmpresa) {
	/**************************************************************************************************************/
		List<ReptesPersEjecOTs> listRtesPersTec = rtesPersEjecOTs_Reposit.getReptsPersEjecOTsByFechas(fechaInic, fechaFnl, idEmpresa);
		List<ReptesPersTecn_DTO> listRtesPerEjecOTsDTO = new ArrayList<ReptesPersTecn_DTO>();
		int listaPersTecnSize = listRtesPersTec.size();
		
		for (int i=0; i < listaPersTecnSize; i++) {
			
			ReptesPersEjecOTs reptePersTec = listRtesPersTec.get(i);
			ReptesPersTecn_DTO reptePersTecDTO = new ReptesPersTecn_DTO();
			
			reptePersTecDTO.setNombreEmpl(reptePersTec.getPersonal_tecn().getNombreEmpl());
			reptePersTecDTO.setTipoEjecut(reptePersTec.getPersonal_tecn().getTipoEjecutor());
			reptePersTecDTO.setCantidHras(Double.toString(reptePersTec.getCantHrs()));
			reptePersTecDTO.setCalidTrabr(Double.toString(reptePersTec.getCalidadTrab()));
			reptePersTecDTO.setIdEmpleado(Integer.toString(reptePersTec.getIdEmpleado()));
			reptePersTecDTO.setIdOrdTraba(Integer.toString(reptePersTec.getIdOT()));
			reptePersTecDTO.setFechaOrdTr(dateFormat.format(reptePersTec.getFechaEjec()));
			reptePersTecDTO.setTrabSolict(reptePersTec.getOrdenTrab().getTrabajoSolicit());
			
			listRtesPerEjecOTsDTO.add(reptePersTecDTO);
		}
		
		return listRtesPerEjecOTsDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<ReptesReptos_DTO> getListReptesReptosEjecOTsByDates(String fechaInic, String fechaFnl, String numFmt, int idEmpresa) {
	/*****************************************************************************************************************/
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		String[] dtsLocale = numFmt.split("-");  //Ejemplo: "en-US" --> idioma y codigo de pais
		String idioma  = dtsLocale[0];
		String codPais = dtsLocale[1];
		
		Locale locale = Locale.of(idioma, codPais); //Locale.of(language, country); 
		NumberFormat numberFmt = NumberFormat.getInstance(locale);
		numberFmt.setMinimumFractionDigits(2);
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace(); 
		}
		
		List<RptesReptosEjecOTs> listRtesReptos = rptesReptosEjecOTs_Reposit.getReptesReptosEjecOTsByFechas(fecha1, fecha2, idEmpresa);
		List<ReptesReptos_DTO> listRtesReptosDTO = new ArrayList<ReptesReptos_DTO>(); 
		int listaRtesReptosSize = listRtesReptos.size();
		
		for (int i=0; i< listaRtesReptosSize; i++) {
			
			RptesReptosEjecOTs repteRepto = listRtesReptos.get(i);
			Date dateConsumo = repteRepto.getFechaConsumo();
			Double costRepto = repteRepto.getCostoTotal();
			
			ReptesReptos_DTO repteReptoDTO = new ReptesReptos_DTO();
			
			repteReptoDTO.setNombreRep(repteRepto.getNombreRep());
			repteReptoDTO.setCostTotal(numberFmt.format(costRepto));
			repteReptoDTO.setDateConsu(MetodosEstaticos.getDateFormated(dateConsumo)); 
			repteReptoDTO.setIdOrdTrab(Integer.toString(repteRepto.getIdOT()));
			repteReptoDTO.setNumOT(repteRepto.getOrdenTrab().getNumOT());
			repteReptoDTO.setTrabSolic(repteRepto.getOrdenTrab().getTrabajoSolicit());
			repteReptoDTO.setNombEquip(repteRepto.getOrdenTrab().getEquipo().getNombEquipo());
			repteReptoDTO.setCorrelatE(repteRepto.getOrdenTrab().getEquipo().getCorrelativo());
			repteReptoDTO.setNumMesCons(MetodosEstaticos.getNumMesFromDate(repteRepto.getFechaConsumo()));
			repteReptoDTO.setEjecutor(repteRepto.getOrdenTrab().getPersEjecutor());
			repteReptoDTO.setComodin(Double.toString(costRepto));
			
			listRtesReptosDTO.add(repteReptoDTO);
		}
		
		return listRtesReptosDTO;
	}

	
	@Transactional(readOnly = true)
	@Override
	public List<RepteHrsParoEquips_DTO> getListReptesHrsParoEquipos(String fechaInic, String fechaFnl, int idEmpresa) {
	/**************************************************************************************************/	
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace(); 
		}
		
		List<ReptesEjecOTs> listReptesEjec = reptesEjecOTs_Reposit.getReptesEjecOTsByFechas(fecha1, fecha2, idEmpresa);
		List<RepteHrsParoEquips_DTO> listRtesParoEquDTO = new ArrayList<RepteHrsParoEquips_DTO>(); 
		int listaReptesEjecSize = listReptesEjec.size();
		
		for (int i=0; i<listaReptesEjecSize; i++) {
			
			ReptesEjecOTs repteEjec = listReptesEjec.get(i);
			Double hrsParo = repteEjec.getTpoParoProduc();
			
			if (hrsParo > 0.0) {
				RepteHrsParoEquips_DTO repteParoEquDTO = new RepteHrsParoEquips_DTO();
				
				repteParoEquDTO.setNumOT(repteEjec.getOrdenTrab().getNumOT());
				repteParoEquDTO.setIdOrdenTrabj(Integer.toString(repteEjec.getIdOT()));
				repteParoEquDTO.setPersEjecutor(repteEjec.getOrdenTrab().getPersEjecutor());
				repteParoEquDTO.setClasificTrab(repteEjec.getOrdenTrab().getClasificTrabajo());
				repteParoEquDTO.setTrabajoSolic(repteEjec.getOrdenTrab().getTrabajoSolicit());
				repteParoEquDTO.setFechaIngreOT(MetodosEstaticos.getDateFormated(repteEjec.getFechaEjec()));
				repteParoEquDTO.setNombreEquipo(repteEjec.getOrdenTrab().getEquipo().getNombEquipo());
				repteParoEquDTO.setCorrelatEqui(repteEjec.getOrdenTrab().getEquipo().getCorrelativo());
				repteParoEquDTO.setHrsParoProdu(Double.toString(repteEjec.getTpoParoProduc()));
				repteParoEquDTO.setNumMesParo(MetodosEstaticos.getNumMesFromDate(repteEjec.getFechaEjec()));
				
				listRtesParoEquDTO.add(repteParoEquDTO);
			}	
	    }
		
		return listRtesParoEquDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<RepteRecurrFallas_DTO> getListReptesRecurrFallas(String nombrFalla, String fechaInic, String fechaFnl, int idEmpresa) {
	/*******************************************************************************************************/	
		nombrFalla = nombrFalla.replace("-", " "); 
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace(); 
		}
		
		List<ReptesEjecOTs> listReptesEjec = reptesEjecOTs_Reposit.getReptesEjecOTsByFechas(fecha1, fecha2, idEmpresa);
		List<RepteRecurrFallas_DTO> listRtesRecurrFallasDTO = new ArrayList<RepteRecurrFallas_DTO>(); 
		int listaReptesEjecSize = listReptesEjec.size();
		
		for (int i=0; i<listaReptesEjecSize; i++) {
			
			ReptesEjecOTs repteEjec = listReptesEjec.get(i);
			String nombrFallaRpte = repteEjec.getNombreFalla();
			
			if (nombrFallaRpte.equals(nombrFalla)) {
				
				RepteRecurrFallas_DTO repteRecurrFalla = new RepteRecurrFallas_DTO();
				
				repteRecurrFalla.setFechaDFalla(dateFormat.format(repteEjec.getOrdenTrab().getFechaIngresoOT()));
				repteRecurrFalla.setTmpParoProd(Double.toString(repteEjec.getTpoParoProduc()));
				repteRecurrFalla.setTmpRealRepa(Double.toString(repteEjec.getTpoRealReparac()));
				repteRecurrFalla.setNombrEquipo(repteEjec.getOrdenTrab().getEquipo().getNombEquipo());
				repteRecurrFalla.setNombreFalla(nombrFalla);
				
				listRtesRecurrFallasDTO.add(repteRecurrFalla);
			}
	    }
		
		return listRtesRecurrFallasDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<RepteRecurrFallas_DTO> getListaFallasByFechas(String fechaInic, String fechaFnl, int idEmpresa) {
	/*********************************************************************************************/	
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace(); 
		}
		
		List<ReptesEjecOTs> listReptesEjec = reptesEjecOTs_Reposit.getReptesEjecOTsByFechas(fecha1, fecha2, idEmpresa);
		List<RepteRecurrFallas_DTO> listRtesRecurrFallasDTO = new ArrayList<RepteRecurrFallas_DTO>(); 
		int listaReptesEjecSize = listReptesEjec.size();
		
		for (int i=0; i<listaReptesEjecSize; i++) {
			
			ReptesEjecOTs repteEjec = listReptesEjec.get(i);
			RepteRecurrFallas_DTO repteRecurrFalla = new RepteRecurrFallas_DTO();
			
			repteRecurrFalla.setNombreFalla(repteEjec.getNombreFalla());
			repteRecurrFalla.setTmpParoProd(Double.toString(repteEjec.getTpoParoProduc()));	
			repteRecurrFalla.setNumOT(repteEjec.getOrdenTrab().getNumOT());
			repteRecurrFalla.setFechaDFalla(MetodosEstaticos.getMonthFromDate(repteEjec.getOrdenTrab().getFechaIngresoOT()));
			repteRecurrFalla.setTmpParoProd(Double.toString(repteEjec.getTpoParoProduc()));
			repteRecurrFalla.setTmpRealRepa(Double.toString(repteEjec.getTpoRealReparac()));
			repteRecurrFalla.setNombrEquipo(repteEjec.getOrdenTrab().getEquipo().getNombEquipo());
			repteRecurrFalla.setTrabRealiza(repteEjec.getOrdenTrab().getTrabajoSolicit());
			repteRecurrFalla.setCorrelatEqu(repteEjec.getOrdenTrab().getEquipo().getCorrelativo());
			repteRecurrFalla.setMesFalla(MetodosEstaticos.getNumMesFromDate(repteEjec.getOrdenTrab().getFechaIngresoOT()));
			
			listRtesRecurrFallasDTO.add(repteRecurrFalla); 
	    }
		
		return listRtesRecurrFallasDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<RepteTendAveriasDTO> getDtsAveriasTodosEquipos(String fechaInic, String fechaFnl, int idEmpresa) {
	/********************************************************************************************/	
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		List<OrdenesTrabajo> listaOrdTrab = ordsTrab_Reposit.getOTsByFechas(idEmpresa, fecha1, fecha2);
		List<RepteTendAveriasDTO> repteTendAverDTO = new ArrayList<RepteTendAveriasDTO>(); 
		int listaOTsSize = listaOrdTrab.size();
		
		for (int i=0; i < listaOTsSize; i++) {
			
			OrdenesTrabajo ordenTrab = listaOrdTrab.get(i);
			if (ordenTrab.getClasificTrabajo() != null) {
				
				if(!ordenTrab.getClasificTrabajo().equals("")) {
					
					RepteTendAveriasDTO repteTendAver = new RepteTendAveriasDTO();
					
					repteTendAver.setNumOT(ordenTrab.getNumOT());
					repteTendAver.setIdOrdenTrabj(Integer.toString(ordenTrab.getIdOT()));
					repteTendAver.setPersEjecutor(ordenTrab.getPersEjecutor());	
					repteTendAver.setClasificTrab(ordenTrab.getClasificTrabajo());
					repteTendAver.setTrabajoSolic(ordenTrab.getTrabajoSolicit());
					repteTendAver.setFechaIngreOT(MetodosEstaticos.getDateFormated(ordenTrab.getFechaIngresoOT()));
					repteTendAver.setNombreEquipo(ordenTrab.getEquipo().getNombEquipo());
					repteTendAver.setCorrelatEqui(ordenTrab.getEquipo().getCorrelativo());
					repteTendAver.setNumMesOT(MetodosEstaticos.getNumMesFromDate(ordenTrab.getFechaIngresoOT()));
					
					repteTendAverDTO.add(repteTendAver);
				}
			}
	    }
		return repteTendAverDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public ReptesEjecOTs_DTO getRepteEjecucOT(int idReporte) {
	/****************************************************/
		ReptesEjecOTs repteEjecOT = null;
		Optional <ReptesEjecOTs> repteEjecOTopt = reptesEjecOTs_Reposit.findById(idReporte); 
		ReptesEjecOTs_DTO repteEjecDTO = null;
		
		if (repteEjecOTopt.isPresent()){
			repteEjecOT = repteEjecOTopt.get();
			repteEjecDTO = modelMapper.map(repteEjecOT, ReptesEjecOTs_DTO.class); 
			
		}
		
		return repteEjecDTO;
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public List<ReptesEjecOTs_DTO> getlistaReptesEjecucOT(String fechaInic, String fechaFnl, int idEmpresa) {
	/**************************************************************************************/
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		List<ReptesEjecOTs> listRptesEjecOT = reptesEjecOTs_Reposit.getReptesEjecOTsByFechas(fecha1, fecha2, idEmpresa); 
		List<ReptesEjecOTs_DTO> listRtesEjecDTO = new ArrayList<>();
		int listaRptesEjecSize = listRptesEjecOT.size();
		
		for(int i=0; i< listaRptesEjecSize; i++) {
			
			ReptesEjecOTs repteEjec = listRptesEjecOT.get(i);
			ReptesEjecOTs_DTO repteEjecDTO   = modelMapper.map(repteEjec, ReptesEjecOTs_DTO.class); 
			
			repteEjecDTO.setNombreEquipo(repteEjec.getOrdenTrab().getEquipo().getNombEquipo());
			repteEjecDTO.setCorrelatEqu(repteEjec.getOrdenTrab().getEquipo().getCorrelativo());
			repteEjecDTO.setIdEquipo(repteEjec.getOrdenTrab().getEquipo().getIdEquipo()); 
			
			listRtesEjecDTO.add(repteEjecDTO);
		}
		
		return listRtesEjecDTO;
	}
	
	
	@Transactional
	@Override
	public String guardarReporteEjecOT2(ReptesEjecOTs_DTO repteEjecOT) {
	/************************************************************/
		int idOrdTrab     = repteEjecOT.getIdOT();
		String fechaEjec  = repteEjecOT.getFechaEjec();
		Double hrsParoPro = repteEjecOT.getTpoParoProduc();
		Double hrsTrabajo = repteEjecOT.getTpoRealReparac();
		Double calidTrab  = repteEjecOT.getCalidadTrab();
		String reportHist = repteEjecOT.getReporteHistor();
		String nombrFalla = repteEjecOT.getNombreFalla();
		String supervOT   = repteEjecOT.getNombreSuperv();
		String recibTrab  = repteEjecOT.getPersRecivTrab();
		int    cantFotos  = repteEjecOT.getCantFotosCierre();
		int    cantRptos  = repteEjecOT.getCantRptosUtiliz();;
		int    cantSrvEx  = repteEjecOT.getCantServExter();
		int    idEmpresa  = repteEjecOT.getIdEmpresa();
		
		Date fechaEjecDt = null;
		try {
			fechaEjecDt = dateFormat.parse(fechaEjec);
			
			ReptesEjecOTs repteEjec = new ReptesEjecOTs();
			
			repteEjec.setIdOT(idOrdTrab);
			repteEjec.setFechaEjec(fechaEjecDt); 
			repteEjec.setTpoParoProduc(hrsParoPro);
			repteEjec.setTpoRealReparac(hrsTrabajo);
			repteEjec.setCalidadTrab(calidTrab);
			repteEjec.setReporteHistor(reportHist);
			repteEjec.setNombreFalla(nombrFalla);
			repteEjec.setNombreSuperv(supervOT);
			repteEjec.setPersRecivTrab(recibTrab);
			repteEjec.setCantFotosCierre(cantFotos);
			repteEjec.setCantRptosUtiliz(cantRptos);
			repteEjec.setCantServExter(cantSrvEx); 
			repteEjec.setIdEmpresa(idEmpresa);
			
			reptesEjecOTs_Reposit.save(repteEjec);
			return "EXITO";
			
		} catch (ParseException e) {
			e.printStackTrace();
			return "No se pudo guardar reporte de Ejecución";
		}
	}
	
	
	@Transactional
	@Override
	public int guardarReporteEjecOT3(ReptesEjecOTs_DTO repteEjecOT) {
	/************************************************************/
		int idOrdTrab     = repteEjecOT.getIdOT();
		String fechaEjec  = repteEjecOT.getFechaEjec();
		Double hrsParoPro = repteEjecOT.getTpoParoProduc();
		Double hrsTrabajo = repteEjecOT.getTpoRealReparac();
		Double calidTrab  = repteEjecOT.getCalidadTrab();
		String reportHist = repteEjecOT.getReporteHistor();
		String nombrFalla = repteEjecOT.getNombreFalla();
		String supervOT   = repteEjecOT.getNombreSuperv();
		String recibTrab  = repteEjecOT.getPersRecivTrab();
		int    cantFotos  = repteEjecOT.getCantFotosCierre();
		int    cantRptos  = repteEjecOT.getCantRptosUtiliz();;
		int    cantSrvEx  = repteEjecOT.getCantServExter();
		int    idEmpresa  = repteEjecOT.getIdEmpresa();
		
		Date fechaEjecDt = null;
		try {
			fechaEjecDt = dateFormat.parse(fechaEjec);
			
			ReptesEjecOTs repteEjec = new ReptesEjecOTs();
			
			repteEjec.setIdOT(idOrdTrab);
			repteEjec.setFechaEjec(fechaEjecDt); 
			repteEjec.setTpoParoProduc(hrsParoPro);
			repteEjec.setTpoRealReparac(hrsTrabajo);
			repteEjec.setCalidadTrab(calidTrab);
			repteEjec.setReporteHistor(reportHist);
			repteEjec.setNombreFalla(nombrFalla);
			repteEjec.setNombreSuperv(supervOT);
			repteEjec.setPersRecivTrab(recibTrab);
			repteEjec.setCantFotosCierre(cantFotos);
			repteEjec.setCantRptosUtiliz(cantRptos);
			repteEjec.setCantServExter(cantSrvEx); 
			repteEjec.setIdEmpresa(idEmpresa);
			
			reptesEjecOTs_Reposit.save(repteEjec);
			return repteEjec.getIdReporte();
			
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Transactional
	@Override
	public String guardarReptePersEjecOT(ReptesPersTecn_DTO2 reptePers) {
	/**********************************************************/  
		Double cantHrs = reptePers.getCantHrs();
		Double cddTrab = reptePers.getCalidadTrab();
		String fchaEje = reptePers.getFechaEjec();
		int idOrdTr    = reptePers.getIdOT();
		int idEmple    = reptePers.getIdEmpleado();
		int idEmpresa  = reptePers.getIdEmpresa();
		
		Date fechaEjecDt = null;
		try {
			fechaEjecDt = dateFormat.parse(fchaEje);
			ReptesPersEjecOTs reptePersn = new ReptesPersEjecOTs();
			
			reptePersn.setCantHrs(cantHrs);
			reptePersn.setCalidadTrab(cddTrab);
			reptePersn.setFechaEjec(fechaEjecDt);
			reptePersn.setIdOT(idOrdTr);
			reptePersn.setIdEmpleado(idEmple);
			reptePersn.setIdEmpresa(idEmpresa);
			
			rtesPersEjecOTs_Reposit.save(reptePersn);
			return "OK";
			
		} catch (ParseException e) {
			return "Error al ingresar Reporte Personal";
		}
	}


	@Transactional
	@Override
	public String guardarRepteReptosEjecOT(ReptesReptos_DTO rpteRepto) {
	/****************************************************************/	
		String idOrdTrab = rpteRepto.getIdOrdTrab();
		String nombRepto = rpteRepto.getNombreRep();
		String costTotal = rpteRepto.getCostTotal();
		String fechConsu = rpteRepto.getDateConsu();
		int    idEmpresa = rpteRepto.getIdEmpresa();
		
		Date fechaConsDt = null;
		try {
			fechaConsDt = dateFormat.parse(fechConsu);
			RptesReptosEjecOTs repteRepto = new RptesReptosEjecOTs();
			
			repteRepto.setIdOT(Integer.parseInt(idOrdTrab));
			repteRepto.setNombreRep(nombRepto);
			repteRepto.setCostoTotal(Double.parseDouble(costTotal));
			repteRepto.setFechaConsumo(fechaConsDt); 
			repteRepto.setIdEmpresa(idEmpresa);
			
			rptesReptosEjecOTs_Reposit.save(repteRepto);
			return "OK";
			
		} catch (ParseException e) {
			return "Error al ingresar Reporte Repuesto";
		}
	}
	
	
	@Transactional
	@Override
	public String guardarRepteReptosEjecOT2(ReptesReptos_DTO2 rpteRepto) {
	/****************************************************************/	
		int    idOrdTrab = rpteRepto.getIdOT();
		String nombRepto = rpteRepto.getNombreRep();
		Double cantidad  = rpteRepto.getCantidad();
		Double costTotal = rpteRepto.getCostoTotal();
		Date fechConsu = rpteRepto.getFechaConsumo();
		int  idRepSum  = rpteRepto.getIdRepSum();
		int  idEmpresa = rpteRepto.getIdEmpresa();
		
		RptesReptosEjecOTs repteRepto = new RptesReptosEjecOTs();
		
		repteRepto.setIdOT(idOrdTrab);
		repteRepto.setNombreRep(nombRepto);
		repteRepto.setCantidad(cantidad);
		repteRepto.setCostoTotal(costTotal);
		repteRepto.setFechaConsumo(fechConsu);
		repteRepto.setIdEmpresa(idEmpresa);
		
		if (idRepSum > 0) {
			repteRepto.setIdRepSum(idRepSum);
		}
		
		rptesReptosEjecOTs_Reposit.save(repteRepto);
		return "OK";
	}


	@Transactional
	@Override
	public String guardarRepteServExtEjecOT(RepteServExtOTs_DTO  repteServEx) {
	/***********************************************************/	
		int idOrdTrab = repteServEx.getIdOT();
		String nombrServ = repteServEx.getNombreServic();
		Double costoServ = repteServEx.getCostoServic();
		String fechaServ = repteServEx.getFechaServic();
		int idEmpresa = repteServEx.getIdEmpresa();
		
		Date fechaServDt = null;
		try {
			fechaServDt = dateFormat.parse(fechaServ);
			RptesServExtEjecOTs repteServExt = new RptesServExtEjecOTs();
			
			repteServExt.setIdOT(idOrdTrab);
			repteServExt.setNombreServic(nombrServ);
			repteServExt.setCostoServic(costoServ);
			repteServExt.setFechaServic(fechaServDt);  
			repteServExt.setIdEmpresa(idEmpresa);
			
			rptesServExtEjecOTs_Reposit.save(repteServExt);
			return "OK";
			
		} catch (ParseException e) {
			return "Error al ingresar Reporte Serv. Ext.";
		}
	}


	@Transactional(readOnly = true)
	@Override
	public List<RepteHistorMantto_DTO> getHistorialManttoEquips(String fechaInic, String fechaFnl, int idEmpresa) {
	/*********************************************************************************************/	
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		List<OrdenesTrabajo> listaOrdTrab = ordsTrab_Reposit.getOTsByFechas(idEmpresa, fecha1, fecha2);
		List<RepteHistorMantto_DTO> repteHistoricDTO = new ArrayList<RepteHistorMantto_DTO>(); 
		int listaOrdTrabSize = listaOrdTrab.size();
		
		for (int i=0; i<listaOrdTrabSize; i++) {
			
			OrdenesTrabajo ordenTrab = listaOrdTrab.get(i);
			if (ordenTrab.getRepteEjecOT() != null) {
				
				RepteHistorMantto_DTO repteHist = new RepteHistorMantto_DTO();
				
				repteHist.setNumOT(ordenTrab.getNumOT()); 
				repteHist.setIdOrdTrab(Integer.toString(ordenTrab.getIdOT()));
				repteHist.setTrabSolic(ordenTrab.getTrabajoSolicit());	
				repteHist.setNombEquip(ordenTrab.getEquipo().getNombEquipo());
				repteHist.setFechaIgOT(dateFormat.format(ordenTrab.getFechaIngresoOT()));
				repteHist.setNombSolic(ordenTrab.getNombSolicitante());
				repteHist.setCorrelatE(ordenTrab.getEquipo().getCorrelativo());
				repteHist.setFechaEjec(dateFormat.format(ordenTrab.getRepteEjecOT().getFechaEjec()));
				repteHist.setRepteHist(ordenTrab.getRepteEjecOT().getReporteHistor());
				
				repteHistoricDTO.add(repteHist);
			}
	    }
		
		return repteHistoricDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<RepteHistorMantto_DTO> getHistorialEquipsOTsClosed(String fechaInic, String fechaFnl, String statusClosed, int idEmpresa) {
	/**********************************************************************************************************************************/	
		Date fecha1 = null;  //new Date();
		Date fecha2 = null;  //new Date(); 
		
		try {
			fecha1 = dateFormat.parse(fechaInic);
			fecha2 = dateFormat.parse(fechaFnl); 
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		List<OrdenesTrabajo> listaOrdTrab = ordsTrab_Reposit.findOTsCerradasByFechas(fecha1, fecha2, idEmpresa, statusClosed);
		List<RepteHistorMantto_DTO> repteHistoricDTO = new ArrayList<RepteHistorMantto_DTO>(); 
		int listaOrdTrabSize = listaOrdTrab.size();
		
		for (int i=0; i<listaOrdTrabSize; i++) {
			
			OrdenesTrabajo ordenTrab = listaOrdTrab.get(i);
			if (ordenTrab.getRepteEjecOT() != null) {
				
				RepteHistorMantto_DTO repteHist = new RepteHistorMantto_DTO();
				
				repteHist.setIdOrdTrab(Integer.toString(ordenTrab.getIdOT()));
				repteHist.setNumOT(ordenTrab.getNumOT());
				repteHist.setTrabSolic(ordenTrab.getTrabajoSolicit());	
				repteHist.setNombEquip(ordenTrab.getEquipo().getNombEquipo());
				repteHist.setFechaIgOT(dateFormat.format(ordenTrab.getFechaIngresoOT()));
				repteHist.setNombSolic(ordenTrab.getNombSolicitante());
				repteHist.setCorrelatE(ordenTrab.getEquipo().getCorrelativo());
				repteHist.setFechaEjec(dateFormat.format(ordenTrab.getRepteEjecOT().getFechaEjec()));
				repteHist.setRepteHist(ordenTrab.getRepteEjecOT().getReporteHistor());
				
				repteHistoricDTO.add(repteHist);
			}
	    }
		
		return repteHistoricDTO;
	}

	
	//*********** REPORTES PARA USUARIO SUPER-ADMIN (HUGO MARTINEZ) *************************
	

	@Transactional(readOnly = true)
	@Override
	public List<Empresas_DTO> getListaDeEmpresasInscritas() {
	/*****************************************************/	
		List<Empresas_Inscrit> listaEmpresas = empresasInscrit_Reposit.getAllEmpresasInscr();
		
		List<Empresas_DTO> listaEmpresasDTO = new ArrayList<>();
		int listaEmpresasSize = listaEmpresas.size();
		
		for(int i=0; i< listaEmpresasSize; i++) {
			
			Empresas_Inscrit empresaInscr = listaEmpresas.get(i);
			Empresas_DTO empresaDTO = modelMapper.map(empresaInscr, Empresas_DTO.class); 
			
			listaEmpresasDTO.add(empresaDTO);
		}
		
		return listaEmpresasDTO; 
	}


    
	@Transactional(readOnly = true)
	@Override
	public List<Usuarios> getLstaDeUsuarios(int idEmpresa) { 
	/************************************************/
		List<Usuarios> listaUsuarios = usuariosReposit.getUsuariosByIdEmpresa(idEmpresa);
		List<Usuarios> listaUserEnv  = new ArrayList<>();
		int listaUsuariosSize = listaUsuarios.size();
		
		for(int i=0; i< listaUsuariosSize; i++) {
			
			Usuarios usuario = listaUsuarios.get(i);
			usuario.setPasswordEncrip(null);
			usuario.setPasswordNormal(null);
			
       	 	listaUserEnv.add(usuario);
		}
		
		return listaUserEnv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    
    

}
