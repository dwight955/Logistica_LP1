package com.logistica.entidad;

public class Bienes {
	private int codBien, stockDisponible;
	private double precUni;
	private String descBien, uniMed, fecIngreso;
	
	public int getCodBien() {
		return codBien;
	}
	public void setCodBien(int codBien) {
		this.codBien = codBien;
	}
	public int getStockDisponible() {
		return stockDisponible;
	}
	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
	public double getPrecUni() {
		return precUni;
	}
	public void setPrecUni(double precUni) {
		this.precUni = precUni;
	}
	public String getDescBien() {
		return descBien;
	}
	public void setDescBien(String descBien) {
		this.descBien = descBien;
	}
	public String getUniMed() {
		return uniMed;
	}
	public void setUniMed(String uniMed) {
		this.uniMed = uniMed;
	}
	public String getFecIngreso() {
		return fecIngreso;
	}
	public void setFecIngreso(String fecIngreso) {
		this.fecIngreso = fecIngreso;
	}
	
	
}
