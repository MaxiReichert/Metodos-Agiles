/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

public class GestorTitular {
	private static GestorTitular GTitular ; // Patron Singleton -- Unica instancia tipo gestor creada.

	private GestorTitular(){ // Patron Singleton -- Constructor privatizado para no permitir su uso.
	}

	public static GestorTitular getInstance() { // Patron Singleton -- Devuelve la instancia, si no existe la crea
		if ( GTitular == null) {
			GTitular = new GestorTitular();
		}
		return GTitular;
	}
    
	public boolean comprobarExistencia (int doc) { //Busca en BD si existe una entrada en la tabla titular con el nroDoc = doc, si existe, retorna true
		boolean ret=true; //
		//FALTA LOGICA
		return ret;
	}
	
	
}
