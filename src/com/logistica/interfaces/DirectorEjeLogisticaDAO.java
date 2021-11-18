package com.logistica.interfaces;

import java.util.ArrayList;

import com.logistica.entidad.CuadroRequerimientos;

public interface DirectorEjeLogisticaDAO {
	public ArrayList<CuadroRequerimientos> listarReqPorEstado(String estado);
	public int aprobarCuadroReq(String num);
	public int desaprobarCuadroReq(String num);
}
