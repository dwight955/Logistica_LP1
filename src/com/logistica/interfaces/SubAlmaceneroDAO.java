package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.CuadroRequerimientos;
import com.logistica.entidad.Pecosa;

public interface SubAlmaceneroDAO {
	public ArrayList<CuadroRequerimientos> ListarReqSegunEstado(String estado);
}
