package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.Proveedor;
import com.logistica.entidad.Trabajador;


public interface ProveedorDAO {
	public int Actualizar(Proveedor bean);
	public String[] buscarProveedor(String ruc);
	public ArrayList<Proveedor> ListarBusqueda(String nomape,String comodin, String unidadorg);
}
