package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import persistencia.MyEntityManager;

public class DAOLicenciaJPA implements DAOLicencia {

	@Override
	public long ObtenerNumeroFactura() {
		EntityManager em= MyEntityManager.get();
		long result= (Long) em.createQuery("SELECT count(*) FROM Licencia").getSingleResult();
		em.close();
		return result;
	}

}
