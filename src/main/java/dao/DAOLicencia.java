package dao;

import entidades.Licencia;
import exceptions.EmitirLicenciaException;

public interface DAOLicencia {
	
	public void darDeAltaLicencia(Licencia licencia) throws EmitirLicenciaException;
	
	public long ObtenerNumeroFactura();
}
