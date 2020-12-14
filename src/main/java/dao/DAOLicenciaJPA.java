package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;

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
	public long ObtenerNumeroFactura() {
		EntityManager em= MyEntityManager.get();
		long result= (Long) em.createQuery("SELECT count(*) FROM Licencia").getSingleResult();
		em.close();
		return result;
	}

}
