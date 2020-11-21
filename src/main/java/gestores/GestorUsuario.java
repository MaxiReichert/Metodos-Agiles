/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import entidades.Usuario;

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
}
