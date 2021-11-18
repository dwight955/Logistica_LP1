package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.DetallePecosa;
import com.logistica.entidad.DetalleRequerimientos;
import com.logistica.entidad.Pecosa;

public interface PecosaDAO {
	public int Ingresar(Pecosa bean, ArrayList<DetallePecosa> data);
	public  ArrayList<Pecosa> ListarTodo();
	public String[] buscarTrabajador(String str);
	public ArrayList<DetalleRequerimientos> listarDetalleReqPorNum(String numreq);
}
