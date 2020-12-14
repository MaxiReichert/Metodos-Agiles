package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entidades.Titular;
import persistencia.MyEntityManager;

public class DAOTitularJPA implements DAOTitular{
	@Override
	public Titular obtenerTitular(String nro) {
		
		EntityManager em= MyEntityManager.get();
		Titular result =(Titular) em.createQuery("SELECT t FROM Titular t where t.numeroDoc = ?1")
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
	/**
	 * Verifica la existencia de un titular
	 * Retorna true si existe y false en caso contrario
	 * */
	@Override
	public boolean existeTitular(String doc) {
		EntityManager em= MyEntityManager.get();
		long result= (long) em.createQuery("SELECT count(t) FROM Titular t WHERE t.numeroDoc= ?1")
				.setParameter(1, doc)
				.getSingleResult();
		em.close();
		return result!=0;
	}
}
