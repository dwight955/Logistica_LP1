package com.logistica.entidad;

public class Pecosa {
	private String referencia,fecPec,estadoPec;
	private int numPec,dniSoliPec,dniEntrPec,numReq;
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
	public int getDniSoliPec() {
		return dniSoliPec;
	}
	public void setDniSoliPec(int dniSoliPec) {
		this.dniSoliPec = dniSoliPec;
	}
	public int getDniEntrPec() {
		return dniEntrPec;
	}
	public void setDniEntrPec(int dniEntrPec) {
		this.dniEntrPec = dniEntrPec;
	}
	public int getNumReq() {
		return numReq;
	}
	public void setNumReq(int numReq) {
		this.numReq = numReq;
	}	
}
