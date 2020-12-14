package dao;

import entidades.Titular;

public interface DAOTitular{
	
	
	
	public Titular obtenerTitular(String nro);
	public void persistirTitular (Titular unTitular) throws Exception;
}