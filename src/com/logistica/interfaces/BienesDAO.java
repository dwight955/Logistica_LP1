package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.Bienes;

public interface BienesDAO {
	public int Ingresar(Bienes bean);
	public int Actualizar(Bienes bean);
	int Eliminar(Bienes bean); 
	public ArrayList<Bienes> ListarTodo();
	public ArrayList<Bienes> buscarBien(String descr,String comodin,String categoria);
	public String[] buscarBienPorCodigo(String codBien);
	
}

