package dao;

import entidades.Usuario;

public interface DAOUsuario {
		
	public Usuario buscarPorDNI(String nroDoc) throws Exception ;
	
	public void persistirUsuario(Usuario u) throws Exception;
	
	public boolean existeUsuario(String nroDoc) throws Exception;
}
