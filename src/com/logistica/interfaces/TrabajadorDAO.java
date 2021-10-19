package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.Trabajador;

public interface TrabajadorDAO {
	public int Ingresar(Trabajador bean);
	public int Actualizar(Trabajador bean);
	public int Eliminar(Trabajador bean);
	public ArrayList<Trabajador> ListarTodo(); 
}
