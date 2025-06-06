package com.mantprev.services;

import java.util.List;

import com.mantprev.entidadesDTO.Equipos01_DTO;



public interface EquiposService {
	
	
	
	public List<Equipos01_DTO> getLstaTodosLosEquipos(int idEmpresa);
	
	public void eliminarEquipoDeBD(int idEquipo);
	
	public void registrarNuevoEquipo(String nombreNvoEquip, String correlatNvoEqu);
	
	public void actualizarDatosEquipo(int idEquipo, String nombreEquipo, String correlatEquip);
	
	public Equipos01_DTO getEquipoById(int idEquipo);
	
	public String guardarFichaTecnica(int idEquipo, String datosTecn, String nombrFotogr);

	public List<Equipos01_DTO> getEquipAndChildren(int idEquipo);
	
	public List<Equipos01_DTO> getListEquipsChildren(int idPadre);
	
	public int insertarNuevoEquipo(Equipos01_DTO equipoDTO);
	
	public String renombrarEquipo(Equipos01_DTO equipoDTO);
	
	public int getCantidadOTsDelEquipo(int idEquipo);
	
	
	
	
	
}
