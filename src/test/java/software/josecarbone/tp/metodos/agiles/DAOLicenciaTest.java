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
	@Test
	public void exceptionEmitirLicencia() throws EmitirLicenciaException {
		//pruebo que la excepción se lance de forma correcta
		DAOLicenciaJPA.getInstance().darDeAltaLicencia(null, "1213123");
	}

	@Test
	public void emitirLicenciaPrueba() {
		Licencia lic = new Licencia();
		lic.setCosto(1);
		lic.setFechaOtor(Date.from(Instant.now()));
		lic.setFechaVenc(Date.from(Instant.now().plus(1, ChronoUnit.YEARS)));
		lic.setObservaciones("adsd");
		Tramite tramite = new Tramite();
		tramite.setFechaReali(Calendar.getInstance().getTime());
		Usuario usuario = GestorUsuario.obtenerUsuarioActual();
		lic.setTramite(tramite);
		tramite.setUsuario(usuario);
		
		fail("Not yet implemented");
	}

}
