package com.mantprev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.Equipos;
import com.mantprev.entidades.OrdenesTrabajo;
import com.mantprev.entidadesDTO.Equipos01_DTO;
import com.mantprev.repositorios.Equipos_Repository;
import com.mantprev.repositorios.OrdsTrab_Repository;


@Service
public class EquiposService_Impl implements EquiposService {
	
	
	@Autowired
	Equipos_Repository equipos_Reposit;
	
	@Autowired
	OrdsTrab_Repository ordsTrabReposit;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Transactional(readOnly = true)
	@Override
	public List<Equipos01_DTO> getLstaTodosLosEquipos(int idEmpresa) {
	/****************************************************************/
		List<Equipos> listaEquipos = equipos_Reposit.getListaDeTodosLosEquipos(idEmpresa);
		List<Equipos01_DTO> listaEquiposDTO = new ArrayList<Equipos01_DTO>();
		
		int listaEquiposSize = listaEquipos.size();
		for(int i=0; i<listaEquiposSize; i++) {
			
			Equipos equipo = listaEquipos.get(i);
			Equipos01_DTO equipoDTO = modelMapper.map(equipo, Equipos01_DTO.class); 
			
			listaEquiposDTO.add(equipoDTO);
		}
		
		return listaEquiposDTO;
	}


	@Transactional
	@Override
	public void eliminarEquipoDeBD(int idEquipo) {
	/********************************************/
		equipos_Reposit.deleteById(idEquipo); 
		
	}


	@Transactional
	@Override
	public void registrarNuevoEquipo(String nombreNvoEquip, String correlatNvoEqu) {
	/******************************************************************************/
		Equipos nvoEquipo = new Equipos();
		nvoEquipo.setNombEquipo(nombreNvoEquip);
		nvoEquipo.setCorrelativo(correlatNvoEqu);
		nvoEquipo.setNumHijo("");
		nvoEquipo.setNivelArbol("");
		nvoEquipo.setCostoHoraParo(0.0); 
		
		equipos_Reposit.save(nvoEquipo); 
	}


	@Transactional
	@Override
	public void actualizarDatosEquipo(int idEquipo, String nombreEquipo, String correlatEquip) {
	/******************************************************************************************/
		Equipos equipo = equipos_Reposit.getEquipoById(idEquipo);
		equipo.setNombEquipo(nombreEquipo);
		equipo.setCorrelativo(correlatEquip);
		
		equipos_Reposit.save(equipo); 
	}


	@Transactional(readOnly = true)
	@Override
	public Equipos01_DTO getEquipoById(int idEquipo) {
	/*******************************************/
		Optional<Equipos> equipoOpt = equipos_Reposit.findById(idEquipo);
		Equipos equipo = null;
		Equipos01_DTO equipoDTO = null;
		
		if (equipoOpt != null) {
			equipo = equipoOpt.get();
			equipoDTO = modelMapper.map(equipo, Equipos01_DTO.class);
		}
		
		return equipoDTO;  
	}


	@Transactional
	@Override
	public String guardarFichaTecnica(int idEquipo, String datosTecn, String nombrFotogr) {
	/***********************************************************************************/	
		Optional<Equipos> equipoOpt = equipos_Reposit.findById(idEquipo);
		Equipos equipo = null;
		
		if (nombrFotogr.equals("-")){
			nombrFotogr= "";
        }
		
		if (equipoOpt != null) {
			equipo = equipoOpt.get();
			equipo.setCaractTecnicas(datosTecn);
			equipo.setNombFotoEquip(nombrFotogr);
			
			equipos_Reposit.save(equipo);
			return "OK";
			
		}else {
			return "No se puedo guardar Ficha Técnica";
		}
	}


	@Transactional(readOnly = true)
	@Override
	public List<Equipos01_DTO> getEquipAndChildren(int idEquipo) {
	/**********************************************************/
		Equipos equipPdre = equipos_Reposit.getEquipoById(idEquipo);
		List<Equipos> listaEquipos = equipos_Reposit.getListaEquiposHijos(idEquipo);
		listaEquipos.add(0, equipPdre);
		
		List<Equipos01_DTO> listaEquiposDTO = new ArrayList<Equipos01_DTO>();
		
		int listaEquiposSize = listaEquipos.size();
		for(int i=0; i<listaEquiposSize; i++) {
			Equipos equipo = listaEquipos.get(i);
			Equipos01_DTO equipoDTO = modelMapper.map(equipo, Equipos01_DTO.class); 
			listaEquiposDTO.add(equipoDTO);
		}
		
		return listaEquiposDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<Equipos01_DTO> getListEquipsChildren(int idPadre) {
	/**********************************************************/
		List<Equipos> listaEquipos = equipos_Reposit.getListaEquiposHijos(idPadre);
		
		List<Equipos01_DTO> listaEquiposDTO = new ArrayList<Equipos01_DTO>();
		
		int listaEquiposSize = listaEquipos.size();
		
		for(int i=0; i<listaEquiposSize; i++) {
			Equipos equipo = listaEquipos.get(i);
			Equipos01_DTO equipoDTO = modelMapper.map(equipo, Equipos01_DTO.class); 
			listaEquiposDTO.add(equipoDTO);
		}
		
		return listaEquiposDTO;
	}


	@Transactional
	@Override
	public int insertarNuevoEquipo(Equipos01_DTO equipoDTO) {
	/*********************************************************/
		Equipos nvoEquipo = new Equipos();
		nvoEquipo.setNombEquipo(equipoDTO.getNombEquipo());
		nvoEquipo.setIdEquipoPadre(equipoDTO.getIdEquipoPadre());
		nvoEquipo.setNumHijo(equipoDTO.getNumHijo());
		nvoEquipo.setNivelArbol(equipoDTO.getNivelArbol());
		nvoEquipo.setCorrelativo(equipoDTO.getCorrelativo());
		nvoEquipo.setCostoHoraParo(0.0); 
		nvoEquipo.setIdEquipo(equipoDTO.getIdEquipo());
		nvoEquipo.setIdEmpresa(equipoDTO.getIdEmpresa());
		
		int idNvoEquipo = 0;
		try {
			equipos_Reposit.save(nvoEquipo); 
			idNvoEquipo = nvoEquipo.getIdEquipo();
			
		} catch (Exception e){
			//hacer nada
		}
		return idNvoEquipo;
	}


	@Transactional
	@Override
	public String renombrarEquipo(Equipos01_DTO equipoDTO) {
	/**************************************************/
		int idEquipo = equipoDTO.getIdEquipo();
		String nvoNombEquip = equipoDTO.getNombEquipo();
		
		Equipos equipo = equipos_Reposit.getEquipoById(idEquipo);
		equipo.setNombEquipo(nvoNombEquip); 
		equipos_Reposit.save(equipo);
		
		return "";
	}


	@Transactional(readOnly = true)
	@Override
	public int getCantidadOTsDelEquipo(int idEquipo) {
	/************************************************/
		List<OrdenesTrabajo> listaOTs = ordsTrabReposit.getOrdenesTrabajoByIdEquipo(idEquipo);
		int cantOTs = listaOTs.size();
		return cantOTs;
	}


	@Transactional
	@Override
	public String crearGuardarArbolEquipos(List<Equipos> listaEquipsArbol) {
	/*********************************************************************/
		Equipos equipoRaiz = listaEquipsArbol.get(0);
		
		//Guerda el equipoRaiz
		equipos_Reposit.save(equipoRaiz);   //Inserta en al arbol
		int idEquipoRaiz = equipoRaiz.getIdEquipo();
		
		//Guarda los Departamentos
		Equipos deptProduc1 = listaEquipsArbol.get(1);  deptProduc1.setIdEquipoPadre(idEquipoRaiz); 
		Equipos deptProduc2 = listaEquipsArbol.get(4);  deptProduc2.setIdEquipoPadre(idEquipoRaiz); 
		Equipos deptProduc3 = listaEquipsArbol.get(7);  deptProduc3.setIdEquipoPadre(idEquipoRaiz); 
		Equipos deptServGen = listaEquipsArbol.get(8);  deptServGen.setIdEquipoPadre(idEquipoRaiz); 
		
		List<Equipos> listDeptsProducc = new ArrayList<>();
		listDeptsProducc.add(deptProduc1);
		listDeptsProducc.add(deptProduc2);
		listDeptsProducc.add(deptProduc3);
		listDeptsProducc.add(deptServGen);
		equipos_Reposit.saveAll(listDeptsProducc); //Inserta en al arbol
		
		//Guarda los equipos de producc Depto1
		int idDeptoProduc1 = deptProduc1.getIdEquipo();
		Equipos maq1DeptoProd1 = listaEquipsArbol.get(2);  maq1DeptoProd1.setIdEquipoPadre(idDeptoProduc1); 
		Equipos maq2DeptoProd1 = listaEquipsArbol.get(3);  maq2DeptoProd1.setIdEquipoPadre(idDeptoProduc1);  
		
		List<Equipos> maquinasDeptoProd1 = new ArrayList<>();
		maquinasDeptoProd1.add(maq1DeptoProd1);
		maquinasDeptoProd1.add(maq2DeptoProd1);
		equipos_Reposit.saveAll(maquinasDeptoProd1);
		
		//Guarda los equipos de producc Depto1
		int idDeptoProduc2 = deptProduc2.getIdEquipo();
		Equipos maq1DeptoProd2 = listaEquipsArbol.get(5);  maq1DeptoProd2.setIdEquipoPadre(idDeptoProduc2); 
		Equipos maq2DeptoProd2 = listaEquipsArbol.get(6);  maq2DeptoProd2.setIdEquipoPadre(idDeptoProduc2); 
		
		List<Equipos> maquinasDeptoProd2 = new ArrayList<>();
		maquinasDeptoProd2.add(maq1DeptoProd2);
		maquinasDeptoProd2.add(maq2DeptoProd2);
		equipos_Reposit.saveAll(maquinasDeptoProd2);
		
		return "";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
