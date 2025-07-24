package com.mantprev.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "androi_version")
public class Androi_Version {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    private int id;
	
	private int    versionCode;
    private String versionName;
    private String dato01;
    private String dato02;
    private String dato03;
    
    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersionCode() {
		return versionCode;
	}
	
	public void setVersionCode(int versionCode) {
		
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	public String getDato01() {
		return dato01;
	}
	
	public void setDato01(String dato01) {
		this.dato01 = dato01;
	}
	
	public String getDato02() {
		return dato02;
	}
	
	public void setDato02(String dato02) {
		this.dato02 = dato02;
	}
	
	public String getDato03() {
		return dato03;
	}
	
	public void setDato03(String dato03) {
		this.dato03 = dato03;
	}
	
	
	
    
    
    
	
}
