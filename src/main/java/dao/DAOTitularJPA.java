package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import persistencia.MyEntityManager;

public class DAOTitularJPA implements DAOTitular{
	@Override
	public boolean titularExiste(String nro) {
		boolean ret= false;
		EntityManager em= MyEntityManager.get();
		long result= (Long) em.createQuery("SELECT nroDoc FROM titular where nroDoc = ?")
				.setParameter(1, nro)
				.getSingleResult();
		em.close();
		if(result != 0) {
			ret = true;
		}
		return ret;
	}
}
