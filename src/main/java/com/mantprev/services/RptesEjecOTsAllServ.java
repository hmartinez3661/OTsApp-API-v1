package com.mantprev.services;

import java.util.Date;
import java.util.List;

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


public interface RptesEjecOTsAllServ {
/***********************************/
    
	
	
	public List<Repte2Datos_DTO> getListReptesPersEjecOTByIdOT(int idOT);
	
	public List<ReptesPersTecn_DTO> getListReptesPersEjecOTByIdOT2(int idOT);
	
	public List<Repte2Datos_DTO> getListReptesRepuestosEjecOT(int idOT); 
	
	public List<ReptesReptos_DTO> getListReptesRepuestosEjecOT2(int idOT);
	
	public List<Repte2Datos_DTO> getListaReptesServExterEjecOT(int idOT); 
	
	public List<RepteServExtOTs_DTO> getListaReptesServExterEjecOT2(int idOT); 
	
	public List<ReptesPersTecn_DTO> getListReptesPersEjecOTsByDates(String fechaInic, String fechaFnl, int idEmpresa);
	
	public List<ReptesPersTecn_DTO> getListReptesPersEjecOTsByDates2(Date fechaInic, Date fechaFnl, int idEmpresa);
	
	public List<ReptesReptos_DTO> getListReptesReptosEjecOTsByDates(String fechaInic, String fechaFnl, String numFmt, int idEmpresa);
	
	public List<RepteHrsParoEquips_DTO> getListReptesHrsParoEquipos(String fechaInic, String fechaFnl, int idEmpresa);
	
	public List<RepteRecurrFallas_DTO> getListReptesRecurrFallas(String nombrFalla, String fechaInic, String fechaFnl, int idEmpresa);
	
	public List<RepteRecurrFallas_DTO> getListaFallasByFechas(String fechaInic, String fechaFnl, int idEmpresa);
	
	public List<RepteTendAveriasDTO> getDtsAveriasTodosEquipos(String fechaInic, String fechaFnl, int idEmpesa);
	
	public ReptesEjecOTs_DTO getRepteEjecucOT(int idReporte);
	
	public List<ReptesEjecOTs_DTO> getlistaReptesEjecucOT(String fechaInic, String fechaFnl, int idEmpresa);
	
	public String guardarReporteEjecOT2(ReptesEjecOTs_DTO repteEjecOT);
	
	public int guardarReporteEjecOT3(ReptesEjecOTs_DTO repteEjecOT);
	
	public String guardarReptePersEjecOT(ReptesPersTecn_DTO2 reptePers);
	
	public String guardarRepteReptosEjecOT(ReptesReptos_DTO repteRepto);
	
	public String guardarRepteReptosEjecOT2(ReptesReptos_DTO2 rpteRepto);
	
	public String guardarRepteServExtEjecOT(RepteServExtOTs_DTO repteServEx);
	
	public List<RepteHistorMantto_DTO> getHistorialManttoEquips(String fechaInic, String fechaFnl, int idEmpresa);
	
	public List<RepteHistorMantto_DTO> getHistorialEquipsOTsClosed(String fechaInic, String fechaFnl, String statusClosed, int idEmpresa);
	
	
	
	
	
	
	
	
    
    
    

}
