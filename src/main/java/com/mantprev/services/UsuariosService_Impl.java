package com.mantprev.services;

import java.util.Date;  //java.util.Date
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantprev.entidades.Androi_Version;
import com.mantprev.entidades.ConfigSpinners;
import com.mantprev.entidades.Empresas_Inscrit;
import com.mantprev.entidades.Equipos;
import com.mantprev.entidades.Personal_Tecnico;
import com.mantprev.entidades.RegistroFallas;
import com.mantprev.entidades.Usuarios;
import com.mantprev.repositorios.AndroiVersion_Repository;
import com.mantprev.repositorios.ConfigSpinner_Repository;
import com.mantprev.repositorios.Empresas_Repository;
import com.mantprev.repositorios.Equipos_Repository;
import com.mantprev.repositorios.PersonaTecnico_Repository;
import com.mantprev.repositorios.RegistroFallas_Repository;
import com.mantprev.repositorios.Usuarios_Repository;
import com.mantprev.entidadesDTO.Empresas_DTO;
import com.mantprev.entidadesDTO.Usuarios01_DTO;
import com.mantprev.exceptions.UserNotFoundException;
import com.mantprev.security.AuthResponse;
import com.mantprev.security.JWTService;
import com.mantprev.security.UserCredentials;
import com.mantprev.security.UserRegisterRequest;
import com.mantprev.utilities.MetodosEstaticos; 

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuariosService_Impl implements UsuariosService {
	
	
	@Autowired
	private Usuarios_Repository usuariosReposit;
	
	@Autowired
	private Empresas_Repository empresasReposit;
	
	@Autowired
	private ConfigSpinner_Repository configSpinn_Reposit;
	
	@Autowired
	private RegistroFallas_Repository registFallas_Reposit;
	
	@Autowired
	private Equipos_Repository equipos_Reposit;
	
	@Autowired
	private PersonaTecnico_Repository persTecn_Reposit;
	
	@Autowired
	private AndroiVersion_Repository androiVers_Reposit;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JWTService jwtService;
	
	
	
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuarios01_DTO> getLstaDeSupervisores(int idEmpresa) {
	/**************************************************/
		List<Usuarios> listaUsuarios = usuariosReposit.getUsuariosByIdEmpresa(idEmpresa);
		List<Usuarios01_DTO> listaUsuariosDTO = new ArrayList<Usuarios01_DTO>();
		int listaUsuariosSize = listaUsuarios.size();
		
		for(int i=0; i< listaUsuariosSize; i++) {
			
			Usuarios usuario = listaUsuarios.get(i);
			String rolDelUsuario = usuario.getUserRol();
			String nombreUsuario = usuario.getNombreUsuario();
			String emailUsuario  = usuario.getEmailUsuario();

             if (rolDelUsuario.substring(0, 3).equals("SDM") || rolDelUsuario.substring(0, 3).equals("ADM")){
            	 
            	 Usuarios01_DTO usuarioDTO = new Usuarios01_DTO();
            	 usuarioDTO.setNombreUsuario(nombreUsuario);
            	 usuarioDTO.setUserRol(rolDelUsuario);
            	 usuarioDTO.setEmailUsuario(emailUsuario);
            	 
            	 listaUsuariosDTO.add(usuarioDTO); 
             }
		}
		
		return listaUsuariosDTO;
	}


	@Transactional(readOnly = true)
	@Override
	public List<Usuarios01_DTO> getLstaDeUsuarios(int idEmpresa) {
	/************************************************/
		List<Usuarios> listaUsuarios = usuariosReposit.getUsuariosByIdEmpresa(idEmpresa);
		List<Usuarios01_DTO> listaUduariosDTO = new ArrayList<Usuarios01_DTO>();
		int listaUsuariosSize = listaUsuarios.size();
		
		for(int i=0; i< listaUsuariosSize; i++) {
			
			Usuarios usuario = listaUsuarios.get(i);
			int idUsuario = usuario.getIdUsuario();
			String nombUser = usuario.getNombreUsuario();
			String emailUser = usuario.getEmailUsuario();
			String rolUsuer = usuario.getUserRol();
			
			Usuarios01_DTO userDTO = new Usuarios01_DTO();
			userDTO.setIdUsuario(idUsuario);
			userDTO.setNombreUsuario(nombUser);
			userDTO.setEmailUsuario(emailUser);
			userDTO.setUserRol(rolUsuer);
			
       	 	listaUduariosDTO.add(userDTO);
		}
		
		return listaUduariosDTO;
	}


	@Transactional
	@Override
	public String guardarNvoUsuario (Usuarios nvoUsuario){
	/****************************************************/
		try {
			usuariosReposit.save(nvoUsuario);
			return "EXITO";
			
		} catch (Exception ex) {
			return "No se pudo crea Nuevo Usuario";
		}
	}
	
	
	@Transactional
	@Override      //Cuando user admin edita los datos de un usuario
	public String actualizarDatosUsuario(Usuarios01_DTO userDTO) {
	/******************************************************/
		int idUser = userDTO.getIdUsuario();
		String nombreUser = userDTO.getNombreUsuario();
		String emailUser  = userDTO.getEmailUsuario();
		String rolDeUser  = userDTO.getUserRol();
		
		Usuarios usuario = usuariosReposit.getUsuarioByIdUser(idUser);
		usuario.setNombreUsuario(nombreUser);
		usuario.setEmailUsuario(emailUser);
		usuario.setUserRol(rolDeUser);
		
		try {
			usuariosReposit.save(usuario);
			return "EXITO";
			
		} catch (Exception ex) {
			return "FALLO EN ACTUALIZAR USUARIO"; 
		}
	}


	@Transactional
	@Override
	public String actualizarDatosUsuario1(Usuarios01_DTO userDTO, int idEmpresa) {
	/***********************************************************************/
		String nombreUser = userDTO.getNombreUsuario();    //El nuevo nombre de usuario viene el el password desde la APP
		String emailUser  = userDTO.getEmailUsuario();
		String rolDeUser  = userDTO.getUserRol();
		int idDelUsuario  = userDTO.getIdUsuario();		
		
		Usuarios usuario = usuariosReposit.getUsuarioByIdUser(idDelUsuario);
		
		usuario.setNombreUsuario(nombreUser);
		usuario.setEmailUsuario(emailUser);
		usuario.setUserRol(rolDeUser);
		
		try {
			usuariosReposit.save(usuario);
			return "EXITO";
			
		} catch (Exception ex) {
			return "FALLO EN ACTUALIZAR USUARIO"; 
		}
	}  
	
	
	@Transactional
	@Override        //cuando el usuario entra por 1a vez y cambia su password y otros datos
	public String actualizarDatosUsuario2(Usuarios01_DTO userDTO) {
	/******************************************************/
		int idUsuario = userDTO.getIdUsuario(); 
		String nombrUser = userDTO.getNombreUsuario();
		String emailUser = userDTO.getEmailUsuario();
		String password  = userDTO.getPassword();
		
		Optional<Usuarios> usuarioOpt = usuariosReposit.findById(idUsuario);
		Usuarios usuario = null;
		
		if(usuarioOpt != null) {
			usuario = usuarioOpt.get();
		}
		
		usuario.setNombreUsuario(nombrUser);
		usuario.setEmailUsuario(emailUser);
		usuario.setPasswordEncrip(passwordEncoder.encode(password));
		usuario.setPasswordNormal(password);
		
		try {
			usuariosReposit.save(usuario);
			return "Exito";
			
		} catch (Exception ex) {
			return null; 
		}
	}


	@Transactional
	@Override
	public String eliminarUsuarioDeBD(String emailUsuario) {
	/******************************************************/ 
		Usuarios usuario = usuariosReposit.getUsuarioByEmail(emailUsuario).get(0);
		
		try {
			usuariosReposit.delete(usuario);
			return "Eliminado";
			
		} catch (Exception ex) {
			return "FALLO EN ELIMINAR USUARIO"; 
		}
	}
	
	
	@Transactional
	@Override
	public String eliminarUsuarioDeBD2(int idUsuario) {
	/*************************************************/ 
		try {
			usuariosReposit.deleteById(idUsuario);
			return "Eliminado";
			
		} catch (Exception ex) {
			return "FALLO EN ELIMINAR USUARIO"; 
		}
	}


	@Transactional(readOnly = true)
	@Override
	public Usuarios getUserByEmail(String emailUsuario) {
	/**********************************************/
		Usuarios usuario = null;
		
		try {
			usuario = usuariosReposit.getUsuarioByEmail2(emailUsuario);
			usuario.setPasswordEncrip(null);
			usuario.setPasswordNormal(null);
			return usuario;
			
		} catch (Exception ex) {
			return usuario; 
		}
	}


	@Autowired
	private AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	@Override
	public AuthResponse loginRequest(UserCredentials request) {
	/*********************************************************/
		String emailUser = request.getEmailUser();
		String password  = request.getPasswordUser();
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailUser, password));
		
        UserDetails usuario = usuariosReposit.findOneByEmailUsuario(emailUser)
        						.orElseThrow(() -> new UserNotFoundException("Usuario no existe. Por favor verifique sus credenciales"));
        
        String token = jwtService.createToken(usuario); 
        
        AuthResponse authResponse = new AuthResponse();
		authResponse.setToken(token);
		return authResponse;
	}
	
	
	@Transactional
	@Override
	public Integer registrarNvaEmpresa(Empresas_DTO nvaEmpresaDTO) {
	/***************************************************************/
		Date dateSuscrip = MetodosEstaticos.getDateFromString(nvaEmpresaDTO.getFechaSuscrip());
		Date dateFnlSuscrip = MetodosEstaticos.getDateFromString(nvaEmpresaDTO.getFechaFnlSuscrip()); 
		
		Empresas_Inscrit nuevaEmpresa = new Empresas_Inscrit(); 
		nuevaEmpresa.setNombEmpresa(nvaEmpresaDTO.getNombEmpresa());
        nuevaEmpresa.setPaisEmpresa(nvaEmpresaDTO.getPaisEmpresa());
        nuevaEmpresa.setIdiomaGrupo(nvaEmpresaDTO.getIdiomaGrupo());
        nuevaEmpresa.setSimbMoneda(nvaEmpresaDTO.getSimbMoneda());
        nuevaEmpresa.setCodigoPais(nvaEmpresaDTO.getCodigoPais());
        nuevaEmpresa.setCantMaxUsers(nvaEmpresaDTO.getCantMaxUsers());
        nuevaEmpresa.setFechaSuscrip(dateSuscrip);
        nuevaEmpresa.setFechaFnlSuscrip(dateFnlSuscrip);
		
        empresasReposit.save(nuevaEmpresa); 
		return nuevaEmpresa.getIdEmpresa();
	}
	
	
	@Transactional
	@Override
	public AuthResponse registrarUserAdmin(UserRegisterRequest solicRegistroUser) {
	/****************************************************************************/
		//verifica si ya existe usuario con el mismo correo
		String emailUserAdmin = solicRegistroUser.getEmailUsuario();
		Optional<Usuarios> usuario = usuariosReposit.findOneByEmailUsuario(emailUserAdmin);
		
		Date dateSuscrip = MetodosEstaticos.getDateFromString(solicRegistroUser.getFechaSuscrip());
		Date dateFnlSuscrip = MetodosEstaticos.getDateFromString(solicRegistroUser.getFechaFnlSuscrip());
		
		if (!usuario.isPresent()){ //Se registra 1er usuario Admin
			int idEmpresa = solicRegistroUser.getIdEmpresa();
			String langGrup = solicRegistroUser.getIdiomaGrupo();
			Usuarios nvoUsuarioAdmin = new Usuarios();
			
			nvoUsuarioAdmin.setNombreUsuario(solicRegistroUser.getNombreUsuario());
			nvoUsuarioAdmin.setEmailUsuario(solicRegistroUser.getEmailUsuario());
			nvoUsuarioAdmin.setPasswordEncrip(passwordEncoder.encode(solicRegistroUser.getPassword())); //Encripta el password
			nvoUsuarioAdmin.setPasswordNormal(solicRegistroUser.getPassword());
			nvoUsuarioAdmin.setUserRol(solicRegistroUser.getUserRol());
			nvoUsuarioAdmin.setIdiomaGrupo(solicRegistroUser.getIdiomaGrupo());
			nvoUsuarioAdmin.setNombreEmpresa(solicRegistroUser.getNombreEmpresa());
			nvoUsuarioAdmin.setIdEmpresa(solicRegistroUser.getIdEmpresa()); 
			nvoUsuarioAdmin.setPaisEmpresa(solicRegistroUser.getPaisEmpresa());
			nvoUsuarioAdmin.setSimbMoneda(solicRegistroUser.getSimbMoneda());
			nvoUsuarioAdmin.setCodigoPais(solicRegistroUser.getCodigoPais());
			nvoUsuarioAdmin.setCantMaxUsers(solicRegistroUser.getCantMaxUsers());
			nvoUsuarioAdmin.setFechaSuscrip(dateSuscrip);
			nvoUsuarioAdmin.setFechaFnlSuscrip(dateFnlSuscrip);
			usuariosReposit.save(nvoUsuarioAdmin);
			String token = jwtService.createToken(nvoUsuarioAdmin);
			
			AuthResponse authResponse = new AuthResponse();
			authResponse.setToken(token);
			
			//CREA LAS CONFIGURACIONES INICIALES DE LA NUEVA EMPRESA ********
			makeConfigInicialesSpinners(idEmpresa, langGrup);
			makeConfigInicialesRegistroFallas(idEmpresa, langGrup);
			crearArbolEquiposInicial(idEmpresa, langGrup);
			ingresarUnTecnEjecutor(idEmpresa, langGrup);
			//***************************************************************
			
			return authResponse;
			
		} else {
			AuthResponse authResponse = new AuthResponse();
			authResponse.setToken("***");  //Ya existe un usuario con ese email
			return authResponse;
		}
	}
	
	
	@Transactional
	@Override
	public String registrarNvoUser(UserRegisterRequest solicRegistroUser) {
	/*********************************************************************/
		Date dateFnlSuscrip = (Date) MetodosEstaticos.getDateFromString(solicRegistroUser.getFechaFnlSuscrip());
		
		//verifica si ya existe usuario con el mismo correo
		String emailUser = solicRegistroUser.getEmailUsuario();
		Optional<Usuarios> usuario = usuariosReposit.findOneByEmailUsuario(emailUser);
		
		if (!usuario.isPresent()){ //Se registra nuevo usuario
			Usuarios nvoUsuario = new Usuarios();
			String passwProv = MetodosEstaticos.crearPasswProvis(); 
			
			nvoUsuario.setNombreUsuario(solicRegistroUser.getNombreUsuario());
			nvoUsuario.setEmailUsuario(solicRegistroUser.getEmailUsuario());
			nvoUsuario.setPasswordEncrip(passwordEncoder.encode(passwProv));  //Encripta el password
			nvoUsuario.setPasswordNormal(passwProv);
			nvoUsuario.setUserRol(solicRegistroUser.getUserRol());
			nvoUsuario.setIdiomaGrupo(solicRegistroUser.getIdiomaGrupo());
			nvoUsuario.setNombreEmpresa(solicRegistroUser.getNombreEmpresa());
			nvoUsuario.setIdEmpresa(solicRegistroUser.getIdEmpresa());
			nvoUsuario.setPaisEmpresa(solicRegistroUser.getPaisEmpresa());
			nvoUsuario.setCodigoPais(solicRegistroUser.getCodigoPais());
			nvoUsuario.setSimbMoneda(solicRegistroUser.getSimbMoneda());
			nvoUsuario.setCantMaxUsers(solicRegistroUser.getCantMaxUsers());
			nvoUsuario.setFechaFnlSuscrip(dateFnlSuscrip);
			
			usuariosReposit.save(nvoUsuario);
			int idNvoUsuario = nvoUsuario.getIdUsuario();
			return passwProv +"-"+ idNvoUsuario;  //Retorna el password prov + el Id nuevo User
			
		} else {
			return "***";   //Ya existe un usuario con ese email
		}
	}


	@Transactional(readOnly = true)
	@Override
	public String getPasswProvisional(String emailUsuario) {
	/******************************************************/
		Optional<Usuarios> usuarioOpt = usuariosReposit.findOneByEmailUsuario(emailUsuario);
		Usuarios usuario = null;
		String passwProv = null;
		
		if (usuarioOpt.isPresent()) {
			usuario = usuarioOpt.get();	
			passwProv = MetodosEstaticos.crearPasswProvis();
			usuario.setPasswordEncrip(passwordEncoder.encode(passwProv));
			usuario.setPasswordNormal(passwProv);
			usuariosReposit.save(usuario);
		}
		
		return passwProv;
	}


	@Transactional
	@Override
	public String actualizarDatosEmpresa(int idEmpresa, int nvaCantMaxUsers, Date nvaFechaExpirac) {
	/**********************************************************************************************/ 
		List<Usuarios> listaUsuarios = usuariosReposit.getUsuariosByIdEmpresa(idEmpresa);
		ArrayList<Usuarios> nvaListaUsuarios = new ArrayList<Usuarios>();
		
		for (int i=0; i<listaUsuarios.size(); i++) {
			
			Usuarios usuario = listaUsuarios.get(i);
			usuario.setCantMaxUsers(nvaCantMaxUsers);
			usuario.setFechaFnlSuscrip(nvaFechaExpirac);
			
			nvaListaUsuarios.add(usuario);
		}
		
		usuariosReposit.saveAll(nvaListaUsuarios);
		return "EXITO";
	}


	@Transactional(readOnly = true)   //Utiliza aplicacion web p/ mostrar dts user al pedir password final
	@Override
	public Usuarios01_DTO getUsuarioByEmail(String emailUser) {
	/*********************************************************/
		Usuarios usuario = usuariosReposit.getUsuarioByEmail2(emailUser);
		
		Usuarios01_DTO usuarioDTO = null;
		usuarioDTO = modelMapper.map(usuario, Usuarios01_DTO.class);
		
		return usuarioDTO;
	}


	@Transactional
	private void makeConfigInicialesSpinners(int idEmpresa, String idiomaGrupo) {
    /***********************************************************************/
        List<ConfigSpinners> listConfigSpinners = new ArrayList<>();

        switch(idiomaGrupo) {
            case "es":
                ConfigSpinners configSpinner1 = new ConfigSpinners();
                configSpinner1.setEstatusOTs("---");
                configSpinner1.setEjecutoresOTs("---");
                configSpinner1.setClasificTrabOTs("---");
                configSpinner1.setPrioridTrabOTs("---");
                configSpinner1.setEstadoEquipo("---");
                configSpinner1.setClasificFallas("---");
                configSpinner1.setRolesUsers("---");
                configSpinner1.setConfigCorreos("Yes");
                configSpinner1.setMsgsWhatsApp("Not");
                configSpinner1.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner1);

                ConfigSpinners configSpinner2 = new ConfigSpinners();
                configSpinner2.setEstatusOTs("Nueva OT");
                configSpinner2.setEjecutoresOTs("Pers. Mecánico");
                configSpinner2.setClasificTrabOTs("Avería");
                configSpinner2.setPrioridTrabOTs("Hacer el trabajo ahora");
                configSpinner2.setEstadoEquipo("Equipo en Paro");
                configSpinner2.setClasificFallas("Mecánica");
                configSpinner2.setRolesUsers("ADM-ADMIN");
                configSpinner2.setConfigCorreos("");
                configSpinner2.setMsgsWhatsApp("");
                configSpinner2.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner2);

                ConfigSpinners configSpinner3 = new ConfigSpinners();
                configSpinner3.setEstatusOTs("Revisada");
                configSpinner3.setEjecutoresOTs("Pers. Electricista");
                configSpinner3.setClasificTrabOTs("Preventivo de Avería");
                configSpinner3.setPrioridTrabOTs("Hacer el trabajo hoy");
                configSpinner3.setEstadoEquipo("Equipo en Marcha");
                configSpinner3.setClasificFallas("Eléctrica");
                configSpinner3.setRolesUsers("SDM-Supervisor de Mantto.");
                configSpinner3.setConfigCorreos("");
                configSpinner3.setMsgsWhatsApp("");
                configSpinner3.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner3);

                ConfigSpinners configSpinner4 = new ConfigSpinners();
                configSpinner4.setEstatusOTs("En Proceso");
                configSpinner4.setEjecutoresOTs("Instrumentación");
                configSpinner4.setClasificTrabOTs("Mantto. Normal");
                configSpinner4.setPrioridTrabOTs("Trabajo Máximo 1 Sem.");
                configSpinner4.setEstadoEquipo("Paro Programado");
                configSpinner4.setClasificFallas("Electrónica");
                configSpinner4.setRolesUsers("EWO-Emisor de Ord. Trabajo");
                configSpinner4.setConfigCorreos("");
                configSpinner4.setMsgsWhatsApp("");
                configSpinner4.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner4);

                ConfigSpinners configSpinner5 = new ConfigSpinners();
                configSpinner5.setEstatusOTs("Finalizada");
                configSpinner5.setEjecutoresOTs("Maq. Herramientas");
                configSpinner5.setClasificTrabOTs("Modificación");
                configSpinner5.setPrioridTrabOTs("Trabajo Máximo 1 Mes");
                configSpinner5.setEstadoEquipo("");
                configSpinner5.setClasificFallas("Lubricación");
                configSpinner5.setRolesUsers("");
                configSpinner5.setConfigCorreos("");
                configSpinner5.setMsgsWhatsApp("");
                configSpinner5.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner5);

                ConfigSpinners configSpinner6 = new ConfigSpinners();
                configSpinner6.setEstatusOTs("Rechazada");
                configSpinner6.setEjecutoresOTs("Aire Acondicionado");
                configSpinner6.setClasificTrabOTs("Tiempo indefinido");
                configSpinner6.setPrioridTrabOTs("");
                configSpinner6.setEstadoEquipo("");
                configSpinner6.setClasificFallas("Producción");
                configSpinner6.setRolesUsers("");
                configSpinner6.setConfigCorreos("");
                configSpinner6.setMsgsWhatsApp("");
                configSpinner6.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner6);

                ConfigSpinners configSpinner7 = new ConfigSpinners();
                configSpinner7.setEstatusOTs("");
                configSpinner7.setEjecutoresOTs("Pers. Producción");
                configSpinner7.setClasificTrabOTs("");
                configSpinner7.setPrioridTrabOTs("");
                configSpinner7.setEstadoEquipo("");
                configSpinner7.setClasificFallas("");
                configSpinner7.setRolesUsers("");
                configSpinner7.setConfigCorreos("");
                configSpinner7.setMsgsWhatsApp("");
                configSpinner7.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner7);

                ConfigSpinners configSpinEs = null;
                for (int i=0; i<10; i++) {
                    configSpinEs = new ConfigSpinners();
                    configSpinEs.setIdEmpresa(idEmpresa);
                    listConfigSpinners.add(configSpinEs);
                }
                break;

            case "en":
                ConfigSpinners configSpinner1en = new ConfigSpinners();
                configSpinner1en.setEstatusOTs("---");
                configSpinner1en.setEjecutoresOTs("---");
                configSpinner1en.setClasificTrabOTs("---");
                configSpinner1en.setPrioridTrabOTs("---");
                configSpinner1en.setEstadoEquipo("---");
                configSpinner1en.setClasificFallas("---");
                configSpinner1en.setRolesUsers("---");
                configSpinner1en.setConfigCorreos("Yes");
                configSpinner1en.setMsgsWhatsApp("Not");
                configSpinner1en.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner1en);

                ConfigSpinners configSpinner2en = new ConfigSpinners();
                configSpinner2en.setEstatusOTs("New WO");
                configSpinner2en.setEjecutoresOTs("Mechanical staff");
                configSpinner2en.setClasificTrabOTs("Breakdown");
                configSpinner2en.setPrioridTrabOTs("Make the job right now");
                configSpinner2en.setEstadoEquipo("Stopped machine");
                configSpinner2en.setClasificFallas("Mechanical");
                configSpinner2en.setRolesUsers("ADM-ADMIN");
                configSpinner2en.setConfigCorreos("");
                configSpinner2en.setMsgsWhatsApp("");
                configSpinner2en.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner2en);

                ConfigSpinners configSpinner3en = new ConfigSpinners();
                configSpinner3en.setEstatusOTs("Reviewed");
                configSpinner3en.setEjecutoresOTs("Electrician staff");
                configSpinner3en.setClasificTrabOTs("Breakdown prevent");
                configSpinner3en.setPrioridTrabOTs("Make the job today");
                configSpinner3en.setEstadoEquipo("Machine working");
                configSpinner3en.setClasificFallas("Electrical");
                configSpinner3en.setRolesUsers("SDM-Mainten. supervisor");
                configSpinner3en.setConfigCorreos("");
                configSpinner3en.setMsgsWhatsApp("");
                configSpinner3en.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner3en);

                ConfigSpinners configSpinner4en = new ConfigSpinners();
                configSpinner4en.setEstatusOTs("In Progress");
                configSpinner4en.setEjecutoresOTs("Instrumentation staff");
                configSpinner4en.setClasificTrabOTs("Normal maintenan.");
                configSpinner4en.setPrioridTrabOTs("Make the job max 1 week");
                configSpinner4en.setEstadoEquipo("Scheduled stoppage");
                configSpinner4en.setClasificFallas("Electronic");
                configSpinner4en.setRolesUsers("EWO-Work Order Issuer");
                configSpinner4en.setConfigCorreos("");
                configSpinner4en.setMsgsWhatsApp("");
                configSpinner4en.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner4en);

                ConfigSpinners configSpinner5en = new ConfigSpinners();
                configSpinner5en.setEstatusOTs("Finished");
                configSpinner5en.setEjecutoresOTs("Machine tools staff");
                configSpinner5en.setClasificTrabOTs("Modification");
                configSpinner5en.setPrioridTrabOTs("Make the job max 1 Month");
                configSpinner5en.setEstadoEquipo("");
                configSpinner5en.setClasificFallas("Lubrication");
                configSpinner5en.setRolesUsers("");
                configSpinner5en.setConfigCorreos("");
                configSpinner5en.setMsgsWhatsApp("");
                configSpinner5en.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner5en);

                ConfigSpinners configSpinner6en = new ConfigSpinners();
                configSpinner6en.setEstatusOTs("Rejected");
                configSpinner6en.setEjecutoresOTs("Air Conditioning staff");
                configSpinner6en.setClasificTrabOTs("New project");
                configSpinner6en.setPrioridTrabOTs("Undefined time");
                configSpinner6en.setEstadoEquipo("");
                configSpinner6en.setClasificFallas("Production");
                configSpinner6en.setRolesUsers("");
                configSpinner6en.setConfigCorreos("");
                configSpinner6en.setMsgsWhatsApp("");
                configSpinner6en.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner6en);

                ConfigSpinners configSpinner7en = new ConfigSpinners();
                configSpinner7en.setEstatusOTs("");
                configSpinner7en.setEjecutoresOTs("Production staff");
                configSpinner7en.setClasificTrabOTs("");
                configSpinner7en.setPrioridTrabOTs("");
                configSpinner7en.setEstadoEquipo("");
                configSpinner7en.setClasificFallas("");
                configSpinner7en.setRolesUsers("");
                configSpinner7en.setConfigCorreos("");
                configSpinner7en.setMsgsWhatsApp("");
                configSpinner7en.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner7en);

                ConfigSpinners configSpinEn = null;
                for (int i=0; i<10; i++) {
                    configSpinEn = new ConfigSpinners();
                    configSpinEn.setIdEmpresa(idEmpresa);
                    listConfigSpinners.add(configSpinEn);
                }
                break;

            case "pt":
                ConfigSpinners configSpinner1pt = new ConfigSpinners();
                configSpinner1pt.setEstatusOTs("---");
                configSpinner1pt.setEjecutoresOTs("---");
                configSpinner1pt.setClasificTrabOTs("---");
                configSpinner1pt.setPrioridTrabOTs("---");
                configSpinner1pt.setEstadoEquipo("---");
                configSpinner1pt.setClasificFallas("---");
                configSpinner1pt.setRolesUsers("---");
                configSpinner1pt.setConfigCorreos("Yes");
                configSpinner1pt.setMsgsWhatsApp("Not");
                configSpinner1pt.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner1pt);

                ConfigSpinners configSpinner2pt = new ConfigSpinners();
                configSpinner2pt.setEstatusOTs("Nova OT");
                configSpinner2pt.setEjecutoresOTs("Pessoal mecânico");
                configSpinner2pt.setClasificTrabOTs("Avaria");
                configSpinner2pt.setPrioridTrabOTs("Faça o trabalho agora");
                configSpinner2pt.setEstadoEquipo("Máquina parada");
                configSpinner2pt.setClasificFallas("Mecânicas");
                configSpinner2pt.setRolesUsers("ADM-ADMIN");
                configSpinner2pt.setConfigCorreos("");
                configSpinner2pt.setMsgsWhatsApp("");
                configSpinner2pt.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner2pt);

                ConfigSpinners configSpinner3pt = new ConfigSpinners();
                configSpinner3pt.setEstatusOTs("Revisada");
                configSpinner3pt.setEjecutoresOTs("Pessoal elétrica");
                configSpinner3pt.setClasificTrabOTs("Preventivo do avaria");
                configSpinner3pt.setPrioridTrabOTs("Faça o trabalho hoje");
                configSpinner3pt.setEstadoEquipo("Máquina funcionan.");
                configSpinner3pt.setClasificFallas("Elétricas");
                configSpinner3pt.setRolesUsers("SDM-Superv. Manutenção");
                configSpinner3pt.setConfigCorreos("");
                configSpinner3pt.setMsgsWhatsApp("");
                configSpinner3pt.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner3pt);

                ConfigSpinners configSpinner4pt = new ConfigSpinners();
                configSpinner4pt.setEstatusOTs("Em Processo");
                configSpinner4pt.setEjecutoresOTs("Instrumentação");
                configSpinner4pt.setClasificTrabOTs("Manutenção normal");
                configSpinner4pt.setPrioridTrabOTs("Trabalho máximo 1 sem.");
                configSpinner4pt.setEstadoEquipo("Parada programada");
                configSpinner4pt.setClasificFallas("Eletrônicas");
                configSpinner4pt.setRolesUsers("EWO-Emissor de Ord. Trabalho");
                configSpinner4pt.setConfigCorreos("");
                configSpinner4pt.setMsgsWhatsApp("");
                configSpinner4pt.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner4pt);

                ConfigSpinners configSpinner5pt = new ConfigSpinners();
                configSpinner5pt.setEstatusOTs("Finalizada");
                configSpinner5pt.setEjecutoresOTs("Máq. ferramentas");
                configSpinner5pt.setClasificTrabOTs("Modificação");
                configSpinner5pt.setPrioridTrabOTs("Trabalho máximo 1 mês");
                configSpinner5pt.setEstadoEquipo("");
                configSpinner5pt.setClasificFallas("Lubrificação");
                configSpinner5pt.setRolesUsers("");
                configSpinner5pt.setConfigCorreos("");
                configSpinner5pt.setMsgsWhatsApp("");
                configSpinner5pt.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner5pt);

                ConfigSpinners configSpinner6pt = new ConfigSpinners();
                configSpinner6pt.setEstatusOTs("Rejeitada");
                configSpinner6pt.setEjecutoresOTs("Ar-condicionado");
                configSpinner6pt.setClasificTrabOTs("Novo projeto");
                configSpinner6pt.setPrioridTrabOTs("Tempo indefinido");
                configSpinner6pt.setEstadoEquipo("");
                configSpinner6pt.setClasificFallas("Produção");
                configSpinner6pt.setRolesUsers("");
                configSpinner6pt.setConfigCorreos("");
                configSpinner6pt.setMsgsWhatsApp("");
                configSpinner6pt.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner6pt);

                ConfigSpinners configSpinner7pt = new ConfigSpinners();
                configSpinner7pt.setEstatusOTs("");
                configSpinner7pt.setEjecutoresOTs("Equipe de produção");
                configSpinner7pt.setClasificTrabOTs("");
                configSpinner7pt.setPrioridTrabOTs("");
                configSpinner7pt.setEstadoEquipo("");
                configSpinner7pt.setClasificFallas("");
                configSpinner7pt.setRolesUsers("");
                configSpinner7pt.setConfigCorreos("");
                configSpinner7pt.setMsgsWhatsApp("");
                configSpinner7pt.setIdEmpresa(idEmpresa);
                listConfigSpinners.add(configSpinner7pt);

                ConfigSpinners configSpinPt = null;
                for (int i=0; i<10; i++) {
                    configSpinPt = new ConfigSpinners();
                    configSpinPt.setIdEmpresa(idEmpresa);
                    listConfigSpinners.add(configSpinPt);
                }
        }

        //Guarda en la base de datos la configuracion de espinners
        configSpinn_Reposit.saveAll(listConfigSpinners);
        
    }

	
	@Transactional
	private void  makeConfigInicialesRegistroFallas(int idEmpresa, String idiomaGrupo){
    /************************************************************************/
        //PROCEDE A CREAR LA LISTA DE RegistroFallas EN EL IDIOMA DE GRUPO
        String nombreFalla01 = ""; String tipoFall01 = "";
        String nombreFalla02 = ""; String tipoFall02 = "";
        String nombreFalla03 = ""; String tipoFall03 = "";
        String nombreFalla04 = ""; String tipoFall04 = "";
        String nombreFalla05 = ""; String tipoFall05 = "";
        String nombreFalla06 = ""; String tipoFall06 = "";
        String nombreFalla07 = ""; String tipoFall07 = "";
        String nombreFalla08 = ""; String tipoFall08 = "";
        String nombreFalla09 = ""; String tipoFall09 = "";
        String nombreFalla10 = ""; String tipoFall10 = "";
        String nombreFalla11 = ""; String tipoFall11 = "";
        String nombreFalla12 = ""; String tipoFall12 = "";
        String nombreFalla13 = ""; String tipoFall13 = "";
        String nombreFalla14 = ""; String tipoFall14 = "";
        String nombreFalla15 = ""; String tipoFall15 = "";
        String nombreFalla16 = ""; String tipoFall16 = "";

        switch(idiomaGrupo) {
            case "es":
                nombreFalla01 = "Falla de Rodamiento"; tipoFall01 = "Mecánica";
                nombreFalla02 = "Falla hidráulica"; tipoFall02 = "Mecánica";
                nombreFalla03 = "Falla neumática"; tipoFall03 = "Mecánica";
                nombreFalla04 = "Sello de Bomba"; tipoFall04 = "Mecánica";
                nombreFalla05 = "Fatiga de material"; tipoFall05 = "Mecánica";
                nombreFalla06 = "Falla mecánica Varias"; tipoFall06 = "Mecánica";
                nombreFalla07 = "Falla componente eléctrico"; tipoFall07 = "Eléctrica";
                nombreFalla08 = "Falla de aislamiento"; tipoFall08 = "Eléctrica";
                nombreFalla09 = "Falla eléctica varias"; tipoFall09 = "Eléctrica";
                nombreFalla10 = "Falla de componente electrónico"; tipoFall10 = "Electrónica";
                nombreFalla11 = "Falla electrónica varias"; tipoFall11 = "Electrónica";
                nombreFalla12 = "Falta de lubricación"; tipoFall12 = "Lubricación";
                nombreFalla13 = "Mala lubricación"; tipoFall13 = "Lubricación";
                nombreFalla14 = "Falla de Material"; tipoFall14 = "Producción";
                nombreFalla15 = "Sobrecarga de equipo"; tipoFall15 = "Producción";
                nombreFalla16 = "Calidad de materia prima"; tipoFall16 = "Producción";
                break;

            case "en":
                nombreFalla01 = "Bearing Failure"; tipoFall01 = "Mechanical";
                nombreFalla02 = "Hydraulic Failure"; tipoFall02 = "Mechanical";
                nombreFalla03 = "Pneumatic Failure"; tipoFall03 = "Mechanical";
                nombreFalla04 = "Pump Seal"; tipoFall04 = "Mechanical";
                nombreFalla05 = "Material Fatigue"; tipoFall05 = "Mechanical";
                nombreFalla06 = "Various Mechanical Failure"; tipoFall06 = "Mechanical";
                nombreFalla07 = "Electrical Component Failure"; tipoFall07 = "Electrical";
                nombreFalla08 = "Insulation Failure"; tipoFall08 = "Electrical";
                nombreFalla09 = "Various Electrical Failure"; tipoFall09 = "Electrical";
                nombreFalla10 = "Electronic Component Failure"; tipoFall10 = "Electronics";
                nombreFalla11 = "Miscellaneous Electronic Failure"; tipoFall11 = "Electronics";
                nombreFalla12 = "Lack of Lubrication"; tipoFall12 = "Lubrication";
                nombreFalla13 = "Poor Lubrication"; tipoFall13 = "Lubrication";
                nombreFalla14 = "Material Failure"; tipoFall14 = "Production";
                nombreFalla15 = "Equipment Overload"; tipoFall15 = "Production";
                nombreFalla16 = "Raw Material Quality"; tipoFall16 = "Production";
                break;

            case "pt":
                nombreFalla01 = "Falha rolamento"; tipoFall01 = "Mecânico";
                nombreFalla02 = "Falha hidráulica"; tipoFall02 = "Mecânico";
                nombreFalla03 = "Falha pneumática"; tipoFall03 = "Mecânico";
                nombreFalla04 = "Carimbo de bomba"; tipoFall04 = "Mecânico";
                nombreFalla05 = "Fadiga do material"; tipoFall05 = "Mecânico";
                nombreFalla06 = "Várias falhas mecânicas"; tipoFall06 = "Mecânico";
                nombreFalla07 = "Falha de componente elétrico"; tipoFall07 = "Elétrico";
                nombreFalla08 = "Falha de isolamento"; tipoFall08 = "Elétrico";
                nombreFalla09 = "Várias falhas elétricas"; tipoFall09 = "Elétrico";
                nombreFalla10 = "Falha de componente eletrônico"; tipoFall10 = "Eletrônicos";
                nombreFalla11 = "Várias falhas eletrônicas"; tipoFall11 = "Eletrônicos";
                nombreFalla12 = "Falta de lubrificação"; tipoFall12 = "Lubrificação";
                nombreFalla13 = "Lubrificação ruim"; tipoFall13 = "Lubrificação";
                nombreFalla14 = "Falha de material"; tipoFall14 = "Produção";
                nombreFalla15 = "Sobrecarga do equipamento"; tipoFall15 = "Produção";
                nombreFalla16 = "Qualidade da matéria-prima"; tipoFall16 = "Produção";
        }

        List<RegistroFallas> listRegistroRegistroFallas = new ArrayList<>();

        RegistroFallas registroFall01 = new RegistroFallas();
        registroFall01.setNombreFalla(nombreFalla01);
        registroFall01.setTipoFalla(tipoFall01);
        registroFall01.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall01);

        RegistroFallas registroFall02 = new RegistroFallas();
        registroFall02.setNombreFalla(nombreFalla02);
        registroFall02.setTipoFalla(tipoFall02);
        registroFall02.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall02);

        RegistroFallas registroFall03 = new RegistroFallas();
        registroFall03.setNombreFalla(nombreFalla03);
        registroFall03.setTipoFalla(tipoFall03);
        registroFall03.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall03);

        RegistroFallas registroFall04 = new RegistroFallas();
        registroFall04.setNombreFalla(nombreFalla04);
        registroFall04.setTipoFalla(tipoFall04);
        registroFall04.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall04);

        RegistroFallas registroFall05 = new RegistroFallas();
        registroFall05.setNombreFalla(nombreFalla05);
        registroFall05.setTipoFalla(tipoFall05);
        registroFall05.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall05);

        RegistroFallas registroFall06 = new RegistroFallas();
        registroFall06.setNombreFalla(nombreFalla06);
        registroFall06.setTipoFalla(tipoFall06);
        registroFall06.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall06);

        RegistroFallas registroFall07 = new RegistroFallas();
        registroFall07.setNombreFalla(nombreFalla07);
        registroFall07.setTipoFalla(tipoFall07);
        registroFall07.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall07);

        RegistroFallas registroFall08 = new RegistroFallas();
        registroFall08.setNombreFalla(nombreFalla08);
        registroFall08.setTipoFalla(tipoFall08);
        registroFall08.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall08);

        RegistroFallas registroFall09 = new RegistroFallas();
        registroFall09.setNombreFalla(nombreFalla09);
        registroFall09.setTipoFalla(tipoFall09);
        registroFall09.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall09);

        RegistroFallas registroFall10 = new RegistroFallas();
        registroFall10.setNombreFalla(nombreFalla10);
        registroFall10.setTipoFalla(tipoFall10);
        registroFall10.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall10);

        RegistroFallas registroFall11 = new RegistroFallas();
        registroFall11.setNombreFalla(nombreFalla11);
        registroFall11.setTipoFalla(tipoFall11);
        registroFall11.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall11);

        RegistroFallas registroFall12 = new RegistroFallas();
        registroFall12.setNombreFalla(nombreFalla12);
        registroFall12.setTipoFalla(tipoFall12);
        registroFall12.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall12);

        RegistroFallas registroFall13 = new RegistroFallas();
        registroFall13.setNombreFalla(nombreFalla13);
        registroFall13.setTipoFalla(tipoFall13);
        registroFall13.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall13);

        RegistroFallas registroFall14 = new RegistroFallas();
        registroFall14.setNombreFalla(nombreFalla14);
        registroFall14.setTipoFalla(tipoFall14);
        registroFall14.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall14);

        RegistroFallas registroFall15 = new RegistroFallas();
        registroFall15.setNombreFalla(nombreFalla15);
        registroFall15.setTipoFalla(tipoFall15);
        registroFall15.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall15);

        RegistroFallas registroFall16 = new RegistroFallas();
        registroFall16.setNombreFalla(nombreFalla16);
        registroFall16.setTipoFalla(tipoFall16);
        registroFall16.setIdEmpresa(idEmpresa);
        listRegistroRegistroFallas.add(registroFall16);

        //Guarda en la BD la 1a lista de RegistroFallas
        registFallas_Reposit.saveAll(listRegistroRegistroFallas);
        
    }
	
	
	@Transactional
	private void crearArbolEquiposInicial(int idNvaEmpresa, String idiomaGrupo) {
	/************************************************************************/	
		String nombrNodoRaiz = "";
    	String nombrDeptoProd1 = "";
    	String nombrDeptoProd2 = "";
    	String nombrDeptoProd3 = "";
    	String nombDeptServGen = "";
    	String equipPro1dDepto1 = "";
    	String equipPro2dDepto1 = "";
    	String equipPro1dDepto2 = "";
    	String equipPro2dDepto2 = "";
    	
    	
    	switch(idiomaGrupo) {
    	  	case "es":
    		  	nombrNodoRaiz = "PLANTA GENERAL";
    	    	nombrDeptoProd1 = "DEPTO. PRODUCCION 1";
    	    	nombrDeptoProd2 = "DEPTO. PRODUCCION 2";
    	    	nombrDeptoProd3 = "DEPTO. PRODUCCION 3";
    	    	nombDeptServGen = "DEPTO. SERV. GENERALES";
    	    	equipPro1dDepto1 = "Máquina Producc. 1-1";
    	    	equipPro2dDepto1 = "Máquina Producc. 2-1";
    	    	equipPro1dDepto2 = "Máquina Producc. 1-2";
    	    	equipPro2dDepto2 = "Máquina Producc. 2-2";
    	    	break;
    	    
    	  	case "en":
    		  	nombrNodoRaiz = "GENERAL PLANT";
  	    		nombrDeptoProd1 = "PRODUCT. DEPARTMENT 1";
  	    		nombrDeptoProd2 = "PRODUCT. DEPARTMENT 2";
  	    		nombrDeptoProd3 = "PRODUCT. DEPARTMENT 3";
    	    	nombDeptServGen = "GENERAL SERVICES DEPT.";
  	    		equipPro1dDepto1 = "Production Machine 1-1";
  	    		equipPro2dDepto1 = "Production Machine 2-1";
  	    		equipPro1dDepto2 = "Production Machine 1-2";
  	    		equipPro2dDepto2 = "Production Machine 2-2";
  	    		break;
    	    
    	  	case "pt":
  		  		nombrNodoRaiz = "PLANTA GERAL";
	    		nombrDeptoProd1 = "DEPTO. PRODUÇÃO 1";
	    		nombrDeptoProd2 = "DEPTO. PRODUÇÃO 2";
	    		nombrDeptoProd3 = "DEPTO. PRODUÇÃO 3";
    	    	nombDeptServGen = "DEPTO. SERVIÇO. EM GERAL";
	    		equipPro1dDepto1 = "Máquina produção 1-1";
	    		equipPro2dDepto1 = "Máquina produção 2-1";
	    		equipPro1dDepto2 = "Máquina produção 1-2";
	    		equipPro2dDepto2 = "Máquina produção 2-2";
    	}
    	
    	//Equipos que formaran el arbol de equipos
    	List<Equipos> listaEquipsArbol = new ArrayList<>(); 
    	
    	Equipos equipoRaiz = new Equipos(); 
    	equipoRaiz.setIdEquipoPadre(0);
    	equipoRaiz.setNombEquipo(nombrNodoRaiz);
    	equipoRaiz.setNumHijo("0");
    	equipoRaiz.setNivelArbol("0");
    	equipoRaiz.setCorrelativo("0");
    	equipoRaiz.setCostoHoraParo(0.0);
    	equipoRaiz.setIdEmpresa(idNvaEmpresa); 
    	listaEquipsArbol.add(0, equipoRaiz);
    	
    	//PRODUCCION 1 *****************************************
    	Equipos equipoDepto1 = new Equipos();
    	equipoDepto1.setIdEquipoPadre(0); //Se actualizar en la API
    	equipoDepto1.setNombEquipo(nombrDeptoProd1);
    	equipoDepto1.setNumHijo("1");
    	equipoDepto1.setNivelArbol("1");
    	equipoDepto1.setCorrelativo("0.01");
    	equipoDepto1.setCostoHoraParo(0.0);
    	equipoDepto1.setIdEmpresa(idNvaEmpresa);
    	listaEquipsArbol.add(1, equipoDepto1);
    	
    	Equipos maq1DeptoProd1 = new Equipos();
    	maq1DeptoProd1.setIdEquipoPadre(0);  //Se actualizar en la API
    	maq1DeptoProd1.setNombEquipo(equipPro1dDepto1);
    	maq1DeptoProd1.setNumHijo("1");
    	maq1DeptoProd1.setNivelArbol("2");
    	maq1DeptoProd1.setCorrelativo("0.01.01");
    	maq1DeptoProd1.setCostoHoraParo(0.0);
    	maq1DeptoProd1.setIdEmpresa(idNvaEmpresa);
    	listaEquipsArbol.add(2, maq1DeptoProd1);
    	
    	Equipos maq2DeptoProd1 = new Equipos();
    	maq2DeptoProd1.setIdEquipoPadre(0);  //Se actualizar en la API
    	maq2DeptoProd1.setNombEquipo(equipPro2dDepto1);
    	maq2DeptoProd1.setNumHijo("2");
    	maq2DeptoProd1.setNivelArbol("2");
    	maq2DeptoProd1.setCorrelativo("0.01.02");
    	maq2DeptoProd1.setCostoHoraParo(0.0);
    	maq2DeptoProd1.setIdEmpresa(idNvaEmpresa);
    	listaEquipsArbol.add(3, maq2DeptoProd1); 
    	
    	//DEPTO PRODUCC 2 ****************
    	Equipos equipoDepto2 = new Equipos();
    	equipoDepto2.setIdEquipoPadre(0);  //Se actualizar en la API
    	equipoDepto2.setNombEquipo(nombrDeptoProd2);
    	equipoDepto2.setNumHijo("2");
    	equipoDepto2.setNivelArbol("1");
    	equipoDepto2.setCorrelativo("0.02");
    	equipoDepto2.setCostoHoraParo(0.0);
    	equipoDepto2.setIdEmpresa(idNvaEmpresa);
    	listaEquipsArbol.add(4, equipoDepto2);
    	
    	Equipos maq1DeptoProd2 = new Equipos();
    	maq1DeptoProd2.setIdEquipoPadre(0);  //Se actualizar en la API
    	maq1DeptoProd2.setNombEquipo(equipPro1dDepto2);
    	maq1DeptoProd2.setNumHijo("1");
    	maq1DeptoProd2.setNivelArbol("2");
    	maq1DeptoProd2.setCorrelativo("0.02.01");
    	maq1DeptoProd2.setCostoHoraParo(0.0);
    	maq1DeptoProd2.setIdEmpresa(idNvaEmpresa);
    	listaEquipsArbol.add(5, maq1DeptoProd2);
    	
    	Equipos maq2DeptoProd2 = new Equipos();
    	maq2DeptoProd2.setIdEquipoPadre(0);  //Se actualizar en la API
    	maq2DeptoProd2.setNombEquipo(equipPro2dDepto2);
    	maq2DeptoProd2.setNumHijo("2");
    	maq2DeptoProd2.setNivelArbol("2");
    	maq2DeptoProd2.setCorrelativo("0.02.02");
    	maq2DeptoProd2.setCostoHoraParo(0.0);
    	maq2DeptoProd2.setIdEmpresa(idNvaEmpresa); 
    	listaEquipsArbol.add(6, maq2DeptoProd2);
    	
    	//DEPTO PRODUCC 3 ****************
    	Equipos equipoDepto3 = new Equipos();
    	equipoDepto3.setIdEquipoPadre(0);  //Se actualizar en la API
    	equipoDepto3.setNombEquipo(nombrDeptoProd3);
    	equipoDepto3.setNumHijo("3");
    	equipoDepto3.setNivelArbol("1");
    	equipoDepto3.setCorrelativo("0.03");
    	equipoDepto3.setCostoHoraParo(0.0);
    	equipoDepto3.setIdEmpresa(idNvaEmpresa); 
    	listaEquipsArbol.add(7, equipoDepto3);
    	
    	//DEPTO SERV. GENERALES ****************
    	Equipos deptoServGenerals = new Equipos();
    	deptoServGenerals.setIdEquipoPadre(0);  //Se actualizar en la API
    	deptoServGenerals.setNombEquipo(nombDeptServGen);
    	deptoServGenerals.setNumHijo("4");
    	deptoServGenerals.setNivelArbol("1");
    	deptoServGenerals.setCorrelativo("0.04");
    	deptoServGenerals.setCostoHoraParo(0.0);
    	deptoServGenerals.setIdEmpresa(idNvaEmpresa); 
    	listaEquipsArbol.add(8, deptoServGenerals);
		
    	
    	//GUARDA EL ARBOL DE EQUIPOS DE LA NUEVA EMPRESA
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
		maq1DeptoProd1.setIdEquipoPadre(idDeptoProduc1); 
		maq2DeptoProd1.setIdEquipoPadre(idDeptoProduc1);  
		
		List<Equipos> maquinasDeptoProd1 = new ArrayList<>();
		maquinasDeptoProd1.add(maq1DeptoProd1);
		maquinasDeptoProd1.add(maq2DeptoProd1);
		equipos_Reposit.saveAll(maquinasDeptoProd1);
		
		//Guarda los equipos de producc Depto1
		int idDeptoProduc2 = deptProduc2.getIdEquipo();
		maq1DeptoProd2.setIdEquipoPadre(idDeptoProduc2); 
		maq2DeptoProd2.setIdEquipoPadre(idDeptoProduc2); 
		
		List<Equipos> maquinasDeptoProd2 = new ArrayList<>();
		maquinasDeptoProd2.add(maq1DeptoProd2);
		maquinasDeptoProd2.add(maq2DeptoProd2);
		equipos_Reposit.saveAll(maquinasDeptoProd2);
	}
	
	
	@Transactional
	private void ingresarUnTecnEjecutor(int idEmpresa, String langGrup) {
	/*******************************************************************/	
		String tipoEjector = "";
    	String nombreTecn  = "";
    	
    	switch(langGrup) {
		  	case "es":
		  		tipoEjector = "Pers. Mecánico";
		  		nombreTecn  = "Juan - edite nombre";
		  		
		  	case "en":	
		  		tipoEjector = "Mechanical staff";
		  		nombreTecn  = "Jonh - edit name";
		  		
		  	case "pt":
		  		tipoEjector = "Pessoal mecânico";
		  		nombreTecn  = "Juan - editar nome";
    	}
    	
    	Personal_Tecnico newPersTec = new Personal_Tecnico();
    	newPersTec.setTipoEjecutor(tipoEjector);
    	newPersTec.setNombreEmpl(nombreTecn);
    	newPersTec.setIdEmpresa(idEmpresa);
    	newPersTec.setIdEmpleado(0); 
    	newPersTec.setStatusPers("Activo");
    	
    	//Procede a registrar el primer tecnico de la empresa
    	persTecn_Reposit.save(newPersTec);
	}


	@Transactional(readOnly = true) 
	@Override
	public Integer getNewestAppVersion() {
	/**********************************/
		Androi_Version androiVers = androiVers_Reposit.getAndroiVersion();
		int appVers = androiVers.getVersionCode();
		
		return appVers;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
