/**
 * 
 */
package persistencia;

import entidades.Licencia;
import exceptions.EmitirLicenciaException;

/**
 * @author josesei
 *
 */
public interface LicenciaDAO {
	public void darDeAltaLicencia(Licencia licencia) throws EmitirLicenciaException;
}
