package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dto.DTOTitular;
import entidades.Licencia;
import entidades.Titular;
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
	public List<Licencia> obtenerLicencia(String nro) {
		
		EntityManager em= MyEntityManager.get();
		List<Licencia> result =(List<Licencia>) em.createQuery("SELECT l FROM Licencia l where l.titular.numeroDoc = ?1")
				.setParameter(1, nro)
				.getResultList();
		em.close();
		return result;
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
