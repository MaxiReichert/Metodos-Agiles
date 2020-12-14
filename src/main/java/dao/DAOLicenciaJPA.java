package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dto.DTOTitular;
import entidades.Licencia;
import entidades.Titular;
import entidades.Tramite;
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
		List<Licencia> result =(List<Licencia>) em.createQuery("SELECT l FROM Licencia l where l.titular.numeroDoc = ?1 AND l.activa= ?2")
				.setParameter(1, nro)
				.setParameter(2, true)
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
	public long obtenerNumeroFactura() {
		EntityManager em= MyEntityManager.get();
		long result= (Long) em.createQuery("SELECT count(*) FROM Licencia").getSingleResult();
		em.close();
		return result;
	}
	
	/**
	 * Determina si existen licencias para renovar
	 * */
	@Override
	public boolean existenLicenciasRenovar(String nroDocTitular) throws Exception {
		EntityManager em= MyEntityManager.get();
		long result= (Long) em.createQuery("SELECT count(l) FROM Licencia l WHERE l.titular.numeroDoc = ?1 AND l.activa= ?2")
				.setParameter(1, nroDocTitular)
				.setParameter(2, true)
				.getSingleResult();
		return result !=0;
	}

	@Override
	public void actualizarLicencia(Licencia l) throws Exception {
		EntityManager em= MyEntityManager.get();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		em.merge(l);
		tx.commit();
		em.clear();
		
	}

	@Override
	public Tramite buscarTramite(int idTramite) throws Exception {
		EntityManager em= MyEntityManager.get();
		Tramite result= (Tramite) em.createQuery("SELECT t FROM tramite t WHERE t.id= ?1")
				.setParameter(1, idTramite)
				.getSingleResult();
		return result;
	}

}
