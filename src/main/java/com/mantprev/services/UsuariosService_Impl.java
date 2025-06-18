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

import com.mantprev.entidades.Empresas_Inscrit;
import com.mantprev.entidades.Usuarios;
import com.mantprev.repositorios.Empresas_Repository;
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
		String nombOrigin = userDTO.getNombreUsuario();
		String nombreUser = userDTO.getPassword();    //El nuevo nombre de usuario viene el el password desde la APP
		String emailUser  = userDTO.getEmailUsuario();
		String rolDeUser  = userDTO.getUserRol();
		
		List<Usuarios> listaUsuarios = usuariosReposit.getUsuarioByNameAndIdEmpresa(nombOrigin, idEmpresa);
		Usuarios usuario = listaUsuarios.get(0);
		
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
			return passwProv;
			
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




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
