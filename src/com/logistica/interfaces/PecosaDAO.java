package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.Pecosa;

public interface PecosaDAO {
	public int Ingresar(Pecosa bean);
	public int Actualizar(Pecosa bean);
	public int Eliminar(Pecosa bean);
	public  ArrayList<Pecosa> ListarTodo();
}
