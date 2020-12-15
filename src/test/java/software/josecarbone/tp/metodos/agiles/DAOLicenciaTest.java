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
public class DAOLicenciaTest {

	/**
	 * @throws EmitirLicenciaException
	 */
/**	@Test(expected = EmitirLicenciaException.class)
	public void exceptionEmitirLicencia() throws EmitirLicenciaException {
		//pruebo que la excepción se lance de forma correcta
		DAOLicenciaJPA.getInstance().darDeAltaLicencia(null, "1213123");
	}
*/
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

}
