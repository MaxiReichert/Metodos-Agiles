package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;

import entidades.Licencia;
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
		EntityManager em= MyEntityManager.get();
		Session session = em.unwrap(Session.class);
		EntityTransaction etx = null;
		Titular result = null;
		try {
			etx = em.getTransaction();
			etx.begin();
			Titular titularAux = (Titular) em.find(Titular.class, nro);
			result = new Titular();
			result.setApellido(titularAux.getApellido());
			result.setDireccion(titularAux.getDireccion());
			result.setDonante(titularAux.getDonante());
			result.setFactor(titularAux.getFactor());
			result.setFechaNac(titularAux.getFechaNac());
			result.setGrupoSanguineo(titularAux.getGrupoSanguineo());
			List<Licencia> licenciaList = new ArrayList<Licencia>();
			licenciaList.addAll(titularAux.getLicenciaList());
			result.setLicenciaList(titularAux.getLicenciaList());
			result.setNombre(titularAux.getNombre());
			result.setNumeroDoc(titularAux.getNumeroDoc());
			result.setTipoDoc(titularAux.getTipoDoc());
			em.flush();
		}catch(Exception e){
			e.printStackTrace();
			if(etx!=null) {
				etx.rollback();
			}
		}
		finally {
			em.close();
		}
		return result;
	}	
	@Override
	public void persistirTitular (Titular unTitular) throws Exception {
		EntityManager em= MyEntityManager.get();
		Session session = em.unwrap(Session.class);
		EntityTransaction etx = null;
		try {
			etx = em.getTransaction();
			etx.begin();
			em.persist(unTitular);
			em.flush();
			etx.commit();
		}catch(Exception e){
			if(etx!=null) {
				etx.rollback();
			}
			throw new Exception("Error al guardar titular");
		}
		finally {
			em.close();
		}
	}
}
