package com.logistica.entidad;

public class OrdenCompra {
	private int nroOC;
	private String nroRUC,codFinan, docRefe, fecha, nomFina, nomRazSoc, estado;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNomFina() {
		return nomFina;
	}
	public void setNomFina(String nomFina) {
		this.nomFina = nomFina;
	}
	public String getNomRazSoc() {
		return nomRazSoc;
	}
	public void setNomRazSoc(String nomRazSoc) {
		this.nomRazSoc = nomRazSoc;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getNroOC() {
		return nroOC;
	}
	public void setNroOC(int nroOC) {
		this.nroOC = nroOC;
	}
	public String getNroRUC() {
		return nroRUC;
	}
	public void setNroRUC(String nroRUC) {
		this.nroRUC = nroRUC;
	}
	public String getCodFinan() {
		return codFinan;
	}
	public void setCodFinan(String codFinan) {
		this.codFinan = codFinan;
	}
	public String getDocRefe() {
		return docRefe;
	}
	public void setDocRefe(String docRefe) {
		this.docRefe = docRefe;
	}
	
	
}
