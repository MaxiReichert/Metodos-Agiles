/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.DAOTitular;
import dao.DAOTitularJPA;
import dto.DTOTitular;
import dto.DTOLicencia;
import entidades.Licencia;
import entidades.Titular;

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
    
	public boolean comprobarExistencia (String doc) { //Busca en BD si existe una entrada en la tabla titular con el nroDoc = doc, si existe, retorna true

		DAOTitularJPA daoTitular = new DAOTitularJPA();
		boolean ret = false;
		Titular unTitular = daoTitular.obtenerTitular(doc);
		if (unTitular != null) {
			ret = true; 
		}
		return ret;
	}
	
	public DTOTitular obtenerTitular (String doc) {
		DAOTitularJPA daoTitular = new DAOTitularJPA();
		Titular titular = daoTitular.obtenerTitular(doc);
		DTOTitular titularDTO = new DTOTitular();
		titularDTO.setApellido(titular.getApellido());
		titularDTO.setDireccion(titular.getDireccion());
		titularDTO.setDonador(titular.getDonante());
		titularDTO.setFactorS(titular.getFactor());
		titularDTO.setFechaNac(titular.getFechaNac());
		titularDTO.setGrupoS(titular.getGrupoSanguineo());
		titularDTO.setLicenciaList(
					titular.getLicenciaList()
					.stream()
					.map((Licencia lic) -> {
						DTOLicencia dtoLicAux = new DTOLicencia();
						dtoLicAux.setCosto(lic.getCosto());
						dtoLicAux.setFechaOtor(lic.getFechaOtor());
						dtoLicAux.setFechaVenc(lic.getFechaVenc());
						dtoLicAux.setObservaciones(lic.getObservaciones());
						dtoLicAux.setTipo(lic.getTipo());
						dtoLicAux.setTitular(titularDTO);
						return dtoLicAux;
					}).collect(Collectors.toList()));	
				
		titularDTO.setNombre(titular.getNombre());
		titularDTO.setTipoDoc(titular.getTipoDoc());
		titularDTO.setNroDoc(titular.getNumeroDoc());
		
		
		
		return titularDTO;
		
	}	
	
	public void darDeAltaTitular(DTOTitular titularDto) throws Exception {
		DAOTitular daoTitular = DAOTitularJPA.getInstance();
		Titular titular = new Titular();
		titular.setApellido(titularDto.getApellido());
		titular.setDireccion(titularDto.getDireccion());
		titular.setDonante(titularDto.getDonador());
		titular.setFactor(titularDto.getFactorS());
		titular.setFechaNac(titularDto.getFechaNac());
		titular.setGrupoSanguineo(titularDto.getGrupoS());
		List<Licencia> listaLicenciasVacia = new ArrayList<Licencia>();
		titular.setLicenciaList(listaLicenciasVacia);
		titular.setNombre(titularDto.getNombre());
		titular.setNumeroDoc(titularDto.getNroDoc());
		titular.setTipoDoc(titularDto.getTipoDoc());
		try {
			daoTitular.persistirTitular(titular);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al guardar el titular");
		}
			
		}
		
	public DTOTitular obtenerTitularCompleto (String doc){
		DAOTitularJPA daoTitular = new DAOTitularJPA();
		Titular titular = daoTitular.obtenerTitular (doc);
		DTOTitular titularDTO = new DTOTitular();
		titularDTO.setApellido(titular.getApellido());
		titularDTO.setNombre(titular.getNombre());
		titularDTO.setTipoDoc(titular.getTipoDoc());
		titularDTO.setNroDoc(titular.getNumeroDoc());
		titularDTO.setDireccion(titular.getDireccion());
		titularDTO.setFechaNac(titular.getFechaNac());
		titularDTO.setGrupoS(titular.getGrupoSanguineo());
		titularDTO.setFactorS(titular.getFactor());
		titularDTO.setDonador(titular.getDonante());
		
		return titularDTO;
		
	}
	
	public boolean existeTitular(String doc)throws Exception {
		DAOTitular daoTitular= new DAOTitularJPA();
		return daoTitular.existeTitular(doc);
		}
}
	
	
