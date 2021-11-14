package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.DetalleRequerimientos;

public interface CuadroRequerimientosDAO {
	public int registrar(CuadroRequerimientos cuaReq, ArrayList<DetalleRequerimientos> lista);
	public ArrayList<CuadroRequerimientos> listarTodo();
}
