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
}
