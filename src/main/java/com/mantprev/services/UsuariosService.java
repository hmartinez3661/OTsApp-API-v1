package com.mantprev.services;

import java.util.Date;
import java.util.List;

import com.mantprev.entidades.Usuarios;
import com.mantprev.entidadesDTO.Empresas_DTO;
import com.mantprev.entidadesDTO.Usuarios01_DTO;
import com.mantprev.security.AuthResponse;
import com.mantprev.security.UserCredentials;
import com.mantprev.security.UserRegisterRequest;


public interface UsuariosService {
	
	
	public List<Usuarios01_DTO> getLstaDeSupervisores(int idEmpresa); 
	
	public List<Usuarios01_DTO> getLstaDeUsuarios(int idEmpresa);  
	
	public String guardarNvoUsuario(Usuarios nvoUsuario);
	
	public String actualizarDatosUsuario(Usuarios01_DTO userDTO);
	
	public String actualizarDatosUsuario1(Usuarios01_DTO userDTO);
	
	public String actualizarDatosUsuario2(Usuarios01_DTO userDTO);
	
	public String eliminarUsuarioDeBD(String emailUsuario); 
	
	public String eliminarUsuarioDeBD2(int idUsuario);
	
	public Usuarios getUserByEmail(String emailUsuario); 
	
	public AuthResponse loginRequest (UserCredentials solicRegistroUser); 
	
	public Integer registrarNvaEmpresa(Empresas_DTO nvaEmpresa);
	
	public AuthResponse registrarUserAdmin(UserRegisterRequest solicRegistroUser); 
	
	public String registrarNvoUser(UserRegisterRequest solicRegistroUser);
	
	public String getPasswProvisional(String emailUsuario); 
	
	public String actualizarDatosEmpresa(int idEmpresa, int nvaCantMaxUsers, Date nvaFechaExpirac);

	public Usuarios01_DTO getUsuarioByEmail(String emailUser);
	
	
	
	
}
