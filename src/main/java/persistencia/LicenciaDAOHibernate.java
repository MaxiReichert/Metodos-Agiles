/**
 * 
 */
package persistencia;

import javax.persistence.EntityManager;

import entidades.Licencia;
import exceptions.EmitirLicenciaException;

/**
 * @author josesei
 *
 */
public class LicenciaDAOHibernate implements LicenciaDAO {
	
	private static LicenciaDAOHibernate instance = null;
	
	public static LicenciaDAO getInstance() {
		if(instance==null) {
			instance = new LicenciaDAOHibernate();
		}
		return instance;
	}

	@Override
	public void darDeAltaLicencia(Licencia licencia) throws EmitirLicenciaException {
		EntityManager em = MyEntityManager.get();
		em.persist(licencia);
		return;
	}


	

}
