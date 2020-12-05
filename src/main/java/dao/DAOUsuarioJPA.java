package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entidades.Usuario;
import persistencia.MyEntityManager;

public class DAOUsuarioJPA implements DAOUsuario {

	//busca un usurio por su DNI
	@Override
	public Usuario buscarPorDNI(String nroDoc) throws Exception {
		EntityManager em= MyEntityManager.get();
		Usuario result= (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.numeroDoc= ?1")
				.setParameter(1, nroDoc)
				.getSingleResult();
		em.close();
		return result;
	}
	
	//guarda un nuevo usuario en la base de datos
	@Override
	public void persistirUsuario(Usuario u) throws Exception {
		EntityManager em= MyEntityManager.get();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		em.persist(u);
		tx.commit();
		em.close();
	}

	//verifica la existencia de un usuario
	@Override
	public boolean existeUsuario(String nroDoc) throws Exception {
		EntityManager em= MyEntityManager.get();
		long result= (long) em.createQuery("SELECT count(u) FROM Usuario u WHERE u.numeroDoc= ?1")
				.setParameter(1, nroDoc)
				.getSingleResult();
		em.close();
		return result != 0;
	}
	
	//actualiza un usuario
	@Override
	public void actualizarUsuario(Usuario u) throws Exception {
		EntityManager em= MyEntityManager.get();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		em.merge(u);
		tx.commit();
		em.close();
	}

}
