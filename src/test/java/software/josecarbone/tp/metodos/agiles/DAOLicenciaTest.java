/**
 * 
 */
package software.josecarbone.tp.metodos.agiles;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dao.DAOLicenciaJPA;
import dao.DAOTitularJPA;
import dto.DTOTitular;
import entidades.Licencia;
import entidades.Titular;
import entidades.Tramite;
import entidades.Usuario;
import exceptions.EmitirLicenciaException;
import gestores.GestorTitular;
import gestores.GestorUsuario;

/**
 * @author josesei
 *
 */


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.DAOLicencia;
import dao.DAOLicenciaJPA;
import entidades.Licencia;
import entidades.Tramite;
import gestores.GestorLicencia;

public class DAOLicenciaTest {
	
	/**
	 * @throws EmitirLicenciaException
	 */
	@Test(expected = EmitirLicenciaException.class)
	public void exceptionEmitirLicencia() throws EmitirLicenciaException {
		//pruebo que la excepción se lance de forma correcta
		DAOLicenciaJPA.getInstance().darDeAltaLicencia(null, "1213123");
	}
	
	@Test
	public void emitirLicenciaPrueba() {
		Licencia lic = new Licencia();
		Date fechaOtor = Date.from(Instant.now());
		lic.setFechaOtor(fechaOtor);
		String observaciones = "Necesita utilizar anteojos.";
		lic.setObservaciones(observaciones);
		Tramite tramite = new Tramite();
		tramite.setFechaReali(fechaOtor);
		Usuario usuario = GestorUsuario.obtenerUsuarioActual();
		tramite.setUsuario(usuario);
		lic.setTramite(tramite);
		String tipoLicencia = "A";
		lic.setTipo(tipoLicencia);
		String nroDoc = "42204789";
		
		Titular titular = DAOTitularJPA.getInstance().obtenerTitular(nroDoc);
		lic.setTitular(titular);
		
		Licencia licDadaDeAlta = null;
		
		try {
			licDadaDeAlta = DAOLicenciaJPA.getInstance().darDeAltaLicencia(lic, nroDoc);
		} catch (EmitirLicenciaException e) {
			e.printStackTrace();
			fail("No se pudo dar de alta la licencia");
		}
		assertEquals(licDadaDeAlta.getCosto(), 28);
		assertEquals(licDadaDeAlta.getFechaOtor(), fechaOtor);
		assertEquals(licDadaDeAlta.getObservaciones(), observaciones);
		assertTrue(licDadaDeAlta.getTipo().equals(tipoLicencia));
		assertTrue(licDadaDeAlta.getTitular().getNumeroDoc().equals(nroDoc));
		assertEquals(licDadaDeAlta.getTramite().getUsuario().getNumeroLegajo(), usuario.getNumeroLegajo());
		assertEquals(licDadaDeAlta.getTramite().getFechaReali(), fechaOtor);
	
		
	}
	
	@Test
	public void obtenerLicenciaTest() {
		DAOLicencia dao= new DAOLicenciaJPA();
		try {
			List<Licencia> listLicencias=dao.obtenerLicencia("98765432");
			assertEquals("El numero de licencias es equivocado", listLicencias.size(), 2);
		}
		catch(Exception e) {
			System.err.print(e.getMessage());
			fail("No se pudo obtener las licencias");
		}
	}
	
	@Test
	public void existeLicenciaRenovarTest() {
		DAOLicencia dao= new DAOLicenciaJPA();
		try {
			assertEquals("El resultado es equivcado", dao.existenLicenciasRenovar("98765432"), true);
		}
		catch(Exception e) {
			System.err.print(e.getMessage());
			fail("No se pudo saber si existian licencias");
		}
	}
	
	@Test
	public void buscarTramiteTest() {
		DAOLicencia dao= new DAOLicenciaJPA();
		Tramite tramite= new Tramite();
		tramite.setFechaReali(new Date("15/12/2015"));
		tramite.setId(2);
		try {
			Tramite aux= dao.buscarTramite(tramite.getId());
			assertEquals("Busco mal el id", aux.getId(), tramite.getId());
			assertEquals("Fecha de realizacion incorrecta", aux.getFechaReali(), tramite.getFechaReali());
			assertEquals("Usuario equivocado", aux.getUsuario().getNumeroLegajo(), 1234);
		}
		catch(Exception e) {
			System.err.print(e.getMessage());
			fail("No se pudo encontrar el tramite");
		}
	}
}

