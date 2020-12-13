package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entidades.Titular;
import persistencia.MyEntityManager;

public class DAOTitularJPA implements DAOTitular{
	
private static DAOTitular instance = null;
	
	public static DAOTitular getInstance() {
		if(instance==null) {
			instance = new DAOTitularJPA();
		}
		return instance;
	}
	
	@Override
	public Titular obtenerTitular(String nro) {
		int nroDoc = Integer.parseInt(nro);
		EntityManager em= MyEntityManager.get();
		Titular result =(Titular) em.createQuery("SELECT * FROM titular where nroDoc = ?1")
				.setParameter(1, nro)
				.getSingleResult();
		em.close();
		return result;
	}	
	@Override
	public void persistirTitular (Titular unTitular) {
		EntityManager em= MyEntityManager.get();
		em.persist(unTitular);
		em.close();
	}
}
