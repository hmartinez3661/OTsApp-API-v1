package com.mantprev.entidadesDTO;

import java.util.Date;

import lombok.Data;

@Data
public class ReptesReptos_DTO2 {
	
	
	private int     idReporte;
	private Integer idOT;
	private String  nombreRep;
	private Double  cantidad;
    private Double  costoTotal;
    private Date    fechaConsumo;
    private Integer idRepSum;
	

}
