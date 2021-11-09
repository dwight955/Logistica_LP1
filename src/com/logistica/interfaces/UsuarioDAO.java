package com.logistica.interfaces;

import com.logistica.entidad.Logistica;
import com.logistica.entidad.UnidadOrganica;

public interface UsuarioDAO {
	public Logistica iniciarSesionLog(String login,String clave);
	public UnidadOrganica iniciarSesionUnOrg(String login,String clave);
}
