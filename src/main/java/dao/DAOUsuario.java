package dao;

import entidades.Usuario;

public interface DAOUsuario {
	
	public Usuario buscarPorDNI(String dni) throws Exception ;
	
	public void persistirUsuario(Usuario u) throws Exception;
}
