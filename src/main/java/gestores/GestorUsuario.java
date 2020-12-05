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
	public static Usuario obtenerUsuarioActual(){
    	Usuario usuarioActual = new Usuario();
    	usuarioActual.setTipoDoc("DNI");
    	usuarioActual.setNumeroDoc("11111111");
    	return usuarioActual;
    }
	
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
		daoUsuario.persistirUsuario(usuario);
	}
	
	public boolean existeUsuario(String nroDoc) throws Exception {
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		return daoUsuario.existeUsuario(nroDoc);
	}
	
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
		daoUsuario.actualizarUsuario(usuario);
		
	}
}
