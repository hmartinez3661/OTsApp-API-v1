package com.mantprev.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mantprev.entidades.Usuarios;



@Repository
public interface Usuarios_Repository extends CrudRepository <Usuarios, Integer>{
	
	
	@Query("select U from Usuarios U where U.nombreUsuario = ?1")
    public List<Usuarios> getUsuarioByName(String nombrOrigUsuario);
	
	
	@Query("select U from Usuarios U where U.nombreUsuario = ?1AND U.idEmpresa = ?2")
    public Usuarios getUsuarioByNameAndIdEmpresa(String nameUser, int idEmpresa);
	
	
	@Query("select U from Usuarios U where U.emailUsuario = ?1")
    public List<Usuarios> getUsuarioByEmail(String emailUsuario);	
	
	
	@Query("select U from Usuarios U where U.emailUsuario = ?1")
    public Usuarios getUsuarioByEmail2(String emailUsuario);	
	
	
	public Optional<Usuarios> findOneByEmailUsuario(String emailUsuario);	
	
	
	@Query("select U from Usuarios U where U.nombreEmpresa = ?1")
    public List<Usuarios> getUsuariosByNombreEmpresa(String nombreEmpresa);
	
	
	@Query("select U from Usuarios U where U.idEmpresa = ?1")
    public List<Usuarios> getUsuariosByIdEmpresa(int idEmpresa);
	
	
	@Query("select U from Usuarios U where U.idUsuario = ?1")
    public Usuarios getUsuarioByIdUser(int idUser);

	
	
	
	/*
	public Usuarios findByUsername(String email);
	
	public Usuarios findByNombreUsuario(String nombreUsuario);
	*/
	
}
