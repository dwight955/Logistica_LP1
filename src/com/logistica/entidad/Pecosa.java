package com.logistica.entidad;

public class Pecosa {
	private String referencia,uniOrgPec,fecPec,estadoPec,meta;
	private int numPec,dniSoliPec,dniEntrPec;
	public String getReferencia() {
		return referencia;
	}
	public String getEstadoPec() {
		return estadoPec;
	}
	public void setEstadoPec(String estadoPec) {
		this.estadoPec = estadoPec;
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getUniOrgPec() {
		return uniOrgPec;
	}
	public void setUniOrgPec(String uniOrgPec) {
		this.uniOrgPec = uniOrgPec;
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
}
