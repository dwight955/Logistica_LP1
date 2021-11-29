package com.logistica.entidad;

public class DetalleOrdenCompra {
	private String codBien;
	private int nroOC, cantidad;
	private double precio, total;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCodBien() {
		return codBien;
	}
	public void setCodBien(String codBien) {
		this.codBien = codBien;
	}
	public int getNroOC() {
		return nroOC;
	}
	public void setNroOC(int nroOC) {
		this.nroOC = nroOC;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
