package com.logistica.interfaces;

import com.logistica.entidad.Administradores;

public interface UsuarioDAO {
	public Administradores iniciarSesion(String login,String clave);
	
}
