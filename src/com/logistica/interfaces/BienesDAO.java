package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.Bienes;

public interface BienesDAO {
	public int Ingresar(Bienes bean);
	public int Actualizar(Bienes bean);
	public int Eliminar(int cod);
	public ArrayList<Bienes> ListarTodo(); 
}

