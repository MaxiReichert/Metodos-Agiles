/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import dao.DAOUsuario;
import dao.DAOUsuarioJPA;
import dto.DTOUsuario;
import entidades.Usuario;
import exceptions.ExisteUsuarioException;

/**
 *
 * @author Maxi
 */
public class GestorUsuario {
	/**
	 * Metodo que obtiene el usuario correspondiente a este equipo
	 * */
	public static Usuario obtenerUsuarioActual(){
    	Usuario usuarioActual = new Usuario();
    	/*usuarioActual.setTipoDoc("DNI");
    	usuarioActual.setNumeroDoc("11111111");*/
    	DAOUsuario daoUsuario= new DAOUsuarioJPA();
    	try {
			usuarioActual=daoUsuario.buscarPorDNI("13245678");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return usuarioActual;
    }
	
	/**
	 * Metodo que convierte un objeto de tipo DTOUsuario a Usuario y
	 * llama a la dao para guardarlo en la BD
	 * */
	public void crearUsuario(DTOUsuario dtoUsuario) throws Exception {
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		Usuario usuario= new Usuario();
		usuario.setApellido(dtoUsuario.getApellido());
		usuario.setDireccion(dtoUsuario.getDireccion());
		usuario.setFechaNac(dtoUsuario.getFechaNac());
		usuario.setNombre(dtoUsuario.getNombre());
		usuario.setNumeroDoc(dtoUsuario.getNroDoc());
		usuario.setNumeroLegajo(Integer.parseInt(dtoUsuario.getNroLegajo()));
		usuario.setTipoDoc(dtoUsuario.getTipoDoc());
		usuario.setAdministrador(false);
		daoUsuario.persistirUsuario(usuario);
	}
	
	/**
	 * Metodo que corrobora la existencia de un usuario
	 * */
	public boolean existeUsuario(String nroDoc) throws Exception {
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		return daoUsuario.existeUsuario(nroDoc);
	}
	
	/**
	 * Metodo que busca un usuario por su numeor de documento
	 * */
	public DTOUsuario buscarUsuario(String nroDoc) throws Exception{
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		DTOUsuario dtoUsuario= new DTOUsuario();
		Usuario usuario= new Usuario();
		usuario=daoUsuario.buscarPorDNI(nroDoc);
		dtoUsuario.setApellido(usuario.getApellido());
		dtoUsuario.setDireccion(usuario.getDireccion());
		dtoUsuario.setFechaNac(usuario.getFechaNac());
		dtoUsuario.setNombre(usuario.getNombre());
		dtoUsuario.setNroDoc(usuario.getNumeroDoc());
		dtoUsuario.setNroLegajo(Integer.toString(usuario.getNumeroLegajo()));
		dtoUsuario.setTipoDoc(usuario.getTipoDoc());
		return dtoUsuario;
	}
	
	/**
	 *Metodo que covierte un DTOUsuario en un Usuario y llama a la dao
	 *para actualizar el registro de ese usuario en la BD
	 * */
	public void actualizarUsuario(DTOUsuario dtoUsuario) throws Exception{
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		Usuario usuario= new Usuario();
		usuario.setApellido(dtoUsuario.getApellido());
		usuario.setDireccion(dtoUsuario.getDireccion());
		usuario.setFechaNac(dtoUsuario.getFechaNac());
		usuario.setNombre(dtoUsuario.getNombre());
		usuario.setNumeroDoc(dtoUsuario.getNroDoc());
		usuario.setNumeroLegajo(Integer.parseInt(dtoUsuario.getNroLegajo()));
		usuario.setTipoDoc(dtoUsuario.getTipoDoc());
		usuario.setAdministrador(false);
		daoUsuario.actualizarUsuario(usuario);
		
	}
}
