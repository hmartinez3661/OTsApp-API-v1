package com.mantprev.entidadesDTO;


import java.sql.Date;

import lombok.Data;


@Data
public class OrdTrabInformOtDTO {
	
	
	private int    idOT;
	private String trabajoSolicit;
	private String nombEquipo;
	private String persEjecutor;
	private String prioridadOT;
	private String estatusOT;
	private Date   fechaIngresoOT;
	private String nombSolicitante;
	private String correlativo; 
	private int    idReporteEjecuc;
	private String fechaIngrStr;
	
	
}
