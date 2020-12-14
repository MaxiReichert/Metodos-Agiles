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
import entidades.Licencia;
import entidades.Tramite;
import entidades.Usuario;
import exceptions.EmitirLicenciaException;
import gestores.GestorUsuario;

/**
 * @author josesei
 *
 */
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
		lic.setCosto(15);
		Date fechaOtor = Date.from(Instant.now());
		Date fechaVenc = Date.from(Instant.now().plus(1, ChronoUnit.YEARS));
		lic.setFechaOtor(fechaOtor);
		lic.setFechaVenc(fechaVenc);
		lic.setObservaciones("Necesita utilizar anteojos.");
		Tramite tramite = new Tramite();
		tramite.setFechaReali(Calendar.getInstance().getTime());
		Usuario usuario = GestorUsuario.obtenerUsuarioActual();
		lic.setTramite(tramite);
		tramite.setUsuario(usuario);
		
		lic.setId(-1);
		
		try {
			DAOLicenciaJPA.getInstance().darDeAltaLicencia(lic, "-1");
		} catch (EmitirLicenciaException e) {
			fail("No se pudo dar de alta la licencia");
		}
		
	}

}
