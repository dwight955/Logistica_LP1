package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.Proveedor;


public interface ProveedorDAO {
	public int Ingresar(Proveedor bean);
	public int Actualizar(Proveedor bean);
	int Eliminar(Proveedor bean); 
	public ArrayList<Proveedor> ListarTodo();
}
