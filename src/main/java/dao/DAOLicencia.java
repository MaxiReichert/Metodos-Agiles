package dao;

import entidades.Licencia;
import entidades.Titular;
import exceptions.EmitirLicenciaException;

public interface DAOLicencia {
	
	public void darDeAltaLicencia(Licencia licencia) throws EmitirLicenciaException;
	
	public long ObtenerNumeroFactura();
	
	public Licencia obtenerLicencia(String nro);
}
