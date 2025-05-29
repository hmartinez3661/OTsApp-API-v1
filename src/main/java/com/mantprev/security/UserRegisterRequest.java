
package com.mantprev.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor   //Constructor vacio
@AllArgsConstructor  //Constructor con todos loa atributos 
public class UserRegisterRequest{
//******************************* 
	
    
    private String nombreUsuario;
    private String emailUsuario;
    private String password;
    private String userRol;
    private String nombreEmpresa;
    private String paisEmpresa;
    private String idiomaGrupo;
    private String simbMoneda;
    private String codigoPais;
    private int    cantMaxUsers;
    private String fechaSuscrip;
    private String fechaFnlSuscrip;
    
   
    
    
    
}
