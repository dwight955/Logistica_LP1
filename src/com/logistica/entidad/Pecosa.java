package com.logistica.entidad;

public class Pecosa {
	private String referencia,fecPec,fecForm,FecApro,FecEntr,estadoPec,numReq,dniJefeApro;
	private int numPec,dniForm;
	private double total;
	
	//variables para el listado
	private String nomUnidadSoli,nomUnidadEntr;
	public String getReferencia() {
		return referencia;
	}
	public String getEstadoPec() {
		return estadoPec;
	}
	public void setEstadoPec(String estadoPec) {
		this.estadoPec = estadoPec;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getFecPec() {
		return fecPec;
	}
	public void setFecPec(String fecPec) {
		this.fecPec = fecPec;
	}
	public int getNumPec() {
		return numPec;
	}
	public void setNumPec(int numPec) {
		this.numPec = numPec;
	}
	public String getFecForm() {
		return fecForm;
	}
	public void setFecForm(String fecForm) {
		this.fecForm = fecForm;
	}
	public String getFecApro() {
		return FecApro;
	}
	public void setFecApro(String fecApro) {
		FecApro = fecApro;
	}
	public String getFecEntr() {
		return FecEntr;
	}
	public void setFecEntr(String fecEntr) {
		FecEntr = fecEntr;
	}
	public String getNumReq() {
		return numReq;
	}
	public void setNumReq(String numReq) {
		this.numReq = numReq;
	}
	public int getDniForm() {
		return dniForm;
	}
	public void setDniForm(int dniForm) {
		this.dniForm = dniForm;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getNomUnidadEntr() {
		return nomUnidadEntr;
	}
	public void setNomUnidadEntr(String nomUnidadEntr) {
		this.nomUnidadEntr = nomUnidadEntr;
	}
	public String getNomUnidadSoli() {
		return nomUnidadSoli;
	}
	public void setNomUnidadSoli(String nomUnidadSoli) {
		this.nomUnidadSoli = nomUnidadSoli;
	}
	public String getDniJefeApro() {
		return dniJefeApro;
	}
	public void setDniJefeApro(String dniJefeApro) {
		this.dniJefeApro = dniJefeApro;
	}
}
