package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entidades.Licencia;
import exceptions.EmitirLicenciaException;
import persistencia.MyEntityManager;

public class DAOLicenciaJPA implements DAOLicencia {

private static DAOLicenciaJPA instance = null;
	
	public static DAOLicencia getInstance() {
		if(instance==null) {
			instance = new DAOLicenciaJPA();
		}
		return instance;
	}

	@Override
	public void darDeAltaLicencia(Licencia licencia) throws EmitirLicenciaException {
		EntityManager em = MyEntityManager.get();
		em.persist(licencia);
		return;
	}
	
	@Override
	public long ObtenerNumeroFactura() {
		EntityManager em= MyEntityManager.get();
		long result= (Long) em.createQuery("SELECT count(*) FROM Licencia").getSingleResult();
		em.close();
		return result;
	}

}
