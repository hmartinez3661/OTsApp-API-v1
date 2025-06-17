package com.mantprev.entidadesDTO;

import java.util.Date;


import lombok.Builder;
import lombok.Data;


@Data
@Builder 
public class OrdenesTrabajoDTO_1 {
	
	
	private int idOT;
	private int numOT;
	private int idEquipo;
	private String persEjecutor;
	private String clasificTrabajo;
	private String prioridadOT;
	private String trabajoSolicit;
	private String nombSolicitante;
	private Date   fechaIngresoOT;
	private String horaIngresoOT;
	private int    idReporteEjecuc;
	private String nombreReviso;
	private Date   fechaRevision;
	private String horaRevision;
	private Double tiempoEstim;
	private int    personalEstim;
	private String tecnAsignado;
	private String indicacPreviasTrab;
	private String explicRechazo;
	private String estatusOT;
	private int    cantFotosAnex;
	private String comodinCorrelat;
	private int    idRpteEjecOT;
	
	
	
	
	
	
	
	
}
