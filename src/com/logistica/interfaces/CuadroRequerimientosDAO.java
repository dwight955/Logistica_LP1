package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.CuadroRequerimientos;

public interface CuadroRequerimientosDAO {
	public int registrar(CuadroRequerimientos bean);
	public ArrayList<CuadroRequerimientos> listarTodo();
}
