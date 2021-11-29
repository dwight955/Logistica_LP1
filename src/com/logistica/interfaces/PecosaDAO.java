package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.DetallePecosa;
import com.logistica.entidad.DetalleRequerimientos;
import com.logistica.entidad.Pecosa;

public interface PecosaDAO {
	public int Ingresar(Pecosa bean, ArrayList<DetallePecosa> data);
	public  ArrayList<Pecosa> ListarTodo(int op,String estado);
	public String[] buscarTrabajador(String str);
	public ArrayList<DetalleRequerimientos> listarDetalleReqPorNum(String numreq);
	public int ActualizarFechaYDni(Pecosa bean);
}
