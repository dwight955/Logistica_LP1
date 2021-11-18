package com.logistica.entidad;

public class CuadroRequerimientos {
	private String numreq,deUnidadOrg, paraUnidadOrg, estado, fechaEmi;   
	private int dniSoli,dniEntr;
	
	//Variables solo para el listado / No entran en la base de datos
	private String nomUniSoli,nomUniEntr,apenomSoli,apenomEntre;
	
	public String getNomUniSoli() {
		return nomUniSoli;
	}
	public void setNomUniSoli(String nomUniSoli) {
		this.nomUniSoli = nomUniSoli;
	}
	public String getNomUniEntr() {
		return nomUniEntr;
	}
	public void setNomUniEntr(String nomUniEntr) {
		this.nomUniEntr = nomUniEntr;
	}
	public String getApenomSoli() {
		return apenomSoli;
	}
	public void setApenomSoli(String apenomSoli) {
		this.apenomSoli = apenomSoli;
	}
	public String getApenomEntre() {
		return apenomEntre;
	}
	public void setApenomEntre(String apenomEntre) {
		this.apenomEntre = apenomEntre;
	}
	public String getNumreq() {
		return numreq;
	}
	public void setNumreq(String numreq) {
		this.numreq = numreq;
	}
	public String getDeUnidadOrg() {
		return deUnidadOrg;
	}
	public void setDeUnidadOrg(String deUnidadOrg) {
		this.deUnidadOrg = deUnidadOrg;
	}
	public String getParaUnidadOrg() {
		return paraUnidadOrg;
	}
	public void setParaUnidadOrg(String paraUnidadOrg) {
		this.paraUnidadOrg = paraUnidadOrg;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaEmi() {
		return fechaEmi;
	}
	public void setFechaEmi(String fechaEmi) {
		this.fechaEmi = fechaEmi;
	}
	public int getDniSoli() {
		return dniSoli;
	}
	public void setDniSoli(int dniSoli) {
		this.dniSoli = dniSoli;
	}
	public int getDniEntr() {
		return dniEntr;
	}
	public void setDniEntr(int dniEntr) {
		this.dniEntr = dniEntr;
	}
	
	
}
