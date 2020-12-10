/**
 * 
 */
package dao;

import entidades.Licencia;
import exceptions.EmitirLicenciaException;

/**
 * @author josesei
 *
 */
public interface DAOLicencia {
	public void darDeAltaLicencia(Licencia licencia) throws EmitirLicenciaException;

	/**
	 * @return
	 */
	long ObtenerNumeroFactura();
}
