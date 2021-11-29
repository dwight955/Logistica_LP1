package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.DetalleOrdenCompra;
import com.logistica.entidad.OrdenCompra;
import com.logistica.entidad.Pecosa;

public interface OrdenCompraDAO {
	public int Ingresar(OrdenCompra bean, ArrayList<DetalleOrdenCompra> data);
	public String[] buscarProveedor(String str);
	public  ArrayList<OrdenCompra> ListarTodo(); 
}
