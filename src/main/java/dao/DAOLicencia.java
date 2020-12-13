package dao;

import java.util.List;


import entidades.Licencia;
import exceptions.EmitirLicenciaException;

public interface DAOLicencia {
	
	
	public void darDeAltaLicencia(Licencia licencia) throws EmitirLicenciaException;
	
	public long ObtenerNumeroFactura();
	
	public List<Licencia> obtenerLicencia(String nro);
}