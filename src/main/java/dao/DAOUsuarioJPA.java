package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entidades.Usuario;
import persistencia.MyEntityManager;

public class DAOUsuarioJPA implements DAOUsuario {

	//busca un usurio por su DNI
	@Override
	public Usuario buscarPorDNI(String dni) throws Exception {
		EntityManager em= MyEntityManager.get();
		Usuario result= (Usuario) em.createQuery("SELECT * FROM Usuario WHERE nroDoc= ?1")
				.setParameter(1, dni)
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

}
