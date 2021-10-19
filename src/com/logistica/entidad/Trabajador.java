package com.logistica.entidad;

public class Trabajador {
	private String nomApe, cargo, fecNac;
	private int dni;
	private double sueldo;
	
	public String getNomApe() {
		return nomApe;
	}
	public void setNomApe(String nomApe) {
		this.nomApe = nomApe;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getFecNac() {
		return fecNac;
	}
	public void setFecNac(String fecNac) {
		this.fecNac = fecNac;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
}
