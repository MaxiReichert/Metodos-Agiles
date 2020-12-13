package dao;

import entidades.Licencia;
import exceptions.EmitirLicenciaException;

public interface DAOLicencia {
	
	public Licencia darDeAltaLicencia(Licencia licencia, String nroDocTitular) throws EmitirLicenciaException;
	
	public long ObtenerNumeroFactura();
}
