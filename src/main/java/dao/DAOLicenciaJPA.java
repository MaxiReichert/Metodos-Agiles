package dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;

import entidades.Licencia;
import entidades.Titular;
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
	public Licencia darDeAltaLicencia(Licencia licencia, String nroDocTitular) throws EmitirLicenciaException {
		EntityManager em = MyEntityManager.get();
		
		EntityTransaction tx = null;
		
		tx = em.getTransaction();
		try {		
			tx.begin();
			em.persist(licencia.getTramite());
			em.flush();
			
			Titular titular = null;
			try {
				titular = DAOTitularJPA.getInstance().obtenerTitular(nroDocTitular);
			}
			catch(Exception e) {
				throw new EmitirLicenciaException("Error al recuperar el titular desde la base de datos. Si el problema persiste, contacte al administrador del sistema");
			}
			licencia.setTitular(titular);
			
			licencia.setFechaVenc(licencia.calcularVigencia());
			licencia.setCosto(licencia.calcularCosto().intValue());
			
			em.persist(licencia);
			em.flush();
			
			titular.getLicenciaList().add(licencia);
			em.merge(titular);
			em.flush();
			
			
			
			
			
			
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new EmitirLicenciaException("Error al guardar la licencia en la base de datos.");
		}
		finally {
			em.close();
		}
		
		return licencia;
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
		Tramite result= (Tramite) em.createQuery("SELECT t FROM Tramite t WHERE t.id= ?1")
				.setParameter(1, idTramite)
				.getSingleResult();
		return result;
	}
	
	@Override
	public List<Licencia> obtenerLicenciasExpiradas(){
		EntityManager em= MyEntityManager.get();
		List<Licencia> result =  em.createQuery("SELECT l FROM Licencia l WHERE l.fechaVenc < ?1")
				.setParameter(1, Calendar.getInstance().getTime())
				.getResultList();
		return result;
	}
	
	@Override
	public List<Licencia> obtenerLicenciasVigentes(int criterio) {
		EntityManager em= MyEntityManager.get();
		List<Licencia> result=null;
		switch(criterio) {
		case 1:
			result=(List<Licencia>) em.createQuery("SELECT l FROM Licencia l where l.activa= ?1 AND l.fechaVenc > ?2 ORDER BY l.titular.apellido ASC, l.titular.nombre")
		.setParameter(1, true)
		.setParameter(2, Calendar.getInstance().getTime())
		.getResultList();
			break;
		case 2:
			result=(List<Licencia>) em.createQuery("SELECT l FROM Licencia l where l.activa= ?1 AND l.fechaVenc > ?2 ORDER BY l.titular.grupoSanguineo ASC")
			.setParameter(1, true)
			.setParameter(2, Calendar.getInstance().getTime())
			.getResultList();
			break;
		case 3:
			result=(List<Licencia>) em.createQuery("SELECT l FROM Licencia l where l.activa= ?1 AND l.fechaVenc > ?2 ORDER BY l.titular.factor ASC")
			.setParameter(1, true)
			.setParameter(2, Calendar.getInstance().getTime())
			.getResultList();
			break;
		case 4:
			result=(List<Licencia>) em.createQuery("SELECT l FROM Licencia l where l.activa= ?1 AND l.fechaVenc > ?2 ORDER BY l.titular.donante ASC")
			.setParameter(1, true)
			.setParameter(2, Calendar.getInstance().getTime())
			.getResultList();
			break;
		}
		em.close();
		return result;
	}

}
