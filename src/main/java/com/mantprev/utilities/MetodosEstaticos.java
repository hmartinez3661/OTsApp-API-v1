package com.mantprev.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MetodosEstaticos {
	
	
	private static String nombreImagen = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
	
	private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	
	public static String getDateFormated (Date fechaIngr) {
	/************************************************/	
		DateFormat formatFecha = DateFormat.getDateInstance(DateFormat.DEFAULT);
		return formatFecha.format(fechaIngr);   //Retorna un String-Fecha "19 ago 2024"
	}


	//Metodo para generar una cadena aleatoria de longitud N
	public static String crearNombreDeFoto() {
	/****************************************/
		int count = 10;

        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        
        while (count-- != 0) {
            int character = (int) (Math.random() * CARACTERES.length());
            builder.append(CARACTERES.charAt(character));
        }
        
        String imageFileName = nombreImagen + ".jpg";

        String nombreFoto = imageFileName;
        nombreFoto = builder + "_" + nombreFoto;

        return nombreFoto;
	}	
	
	
	public static String crearPasswProvis() {
	/****************************************/
		int count = 5;

        String CARACTERES = "ABCDEFGHJKLMNPQRSTUVXYZ23456789";
        StringBuilder builder = new StringBuilder();
        
        while (count-- != 0) {
            int character = (int) (Math.random() * CARACTERES.length());
            builder.append(CARACTERES.charAt(character));
        }
        
        String passwProvis = "mantprev" + builder;
        return passwProvis;
	}
	
	
	public static Date getDateFromString(String dateString) {
    /*******************************************************/
        Date fecha = null;
        try {
            fecha = formatDate.parse(dateString);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return fecha;
    }
	
	
	public static String getMonthFromDate (Date fechaIngr) {
	/*****************************************************/	
		DateFormat formatFecha = DateFormat.getDateInstance(DateFormat.DEFAULT);
		String fechaStr = formatFecha.format(fechaIngr);   
		fechaStr = fechaStr.substring(2, 6).toUpperCase();
		
		return fechaStr; //Retorna jun o equivalente
	}
	
	
	public static int getNumMesFromDate (Date fecha) {
	/************************************************/	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		int month = calendar.get(Calendar.MONTH)+1;
		
		return month;
	}
	
	
	public static String getMonthYearFromDate (Date fecha) {
	/************************************************/	
		String fechaStr  = formatDate.format(fecha);   // yyyy-MM-dd
		String monthYear = fechaStr.substring(0, 7);
		
		return monthYear;  //Envia: yyyy-MM
	}
	
	
	
	
	

}
