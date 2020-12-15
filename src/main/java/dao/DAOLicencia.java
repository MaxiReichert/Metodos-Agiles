package dao;

import java.util.List;


import entidades.Licencia;
import entidades.Tramite;
import exceptions.EmitirLicenciaException;

public interface DAOLicencia {
	
	public Licencia darDeAltaLicencia(Licencia licencia, String nroDocTitular) throws EmitirLicenciaException;

	
	public long obtenerNumeroFactura();
	
	public List<Licencia> obtenerLicencia(String nro);
	
	public boolean existenLicenciasRenovar(String nroDocTitular) throws Exception;
	
	public void actualizarLicencia(Licencia l) throws Exception;
	
	public Tramite buscarTramite(int idTramite) throws Exception;
}