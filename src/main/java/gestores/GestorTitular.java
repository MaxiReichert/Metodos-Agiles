/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import dao.DAOTitularJPA;
import dto.DTOTitular;
import entidades.Titular;

/**
 *
 * @author Maxi
 */
public class GestorTitular {
	public DTOTitular obtenerTitular (String doc) {
		DAOTitularJPA daoTitular = new DAOTitularJPA();
		Titular titular = daoTitular.obtenerTitular (doc);
		DTOTitular titularDTO = new DTOTitular();
		titularDTO.setApellido(titular.getApellido());
		titularDTO.setNombre(titular.getNombre());
		titularDTO.setTipoDoc(titular.getTipoDoc());
		titularDTO.setNroDoc(titular.getNumeroDoc());
		
		
		return titularDTO;
		
	}
}
