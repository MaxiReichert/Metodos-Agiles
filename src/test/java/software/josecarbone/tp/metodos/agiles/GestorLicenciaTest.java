package software.josecarbone.tp.metodos.agiles;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.itextpdf.text.DocumentException;

import dto.DTOLicencia;
import dto.DTOTitular;
import exceptions.EmitirLicenciaException;
import gestores.GestorLicencia;
import gestores.GestorTitular;

public class GestorLicenciaTest {

	@Test
	public void imprimirTicketTest() {
		DTOTitular dtoTitular= new DTOTitular();
		DTOLicencia dtoLicencia= new DTOLicencia();
		GestorLicencia gestorLicencia= GestorLicencia.getInstance();
		
		dtoTitular.setApellido("Reichert");
		dtoTitular.setNombre("Maximiliano");
		dtoTitular.setDireccion("Calle Siempre Viva 234");
		dtoLicencia.setCosto(50);
		dtoLicencia.setTipo("B");
		
		try {
			gestorLicencia.imprimirTicket(dtoTitular, dtoLicencia);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			fail("La prueba ha fallado");
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
			fail("La prueba ha fallado");
		}
		
	}
	
	@Test
	public void imprimirLicenciaTest() {
		DTOTitular dtoTitular= new DTOTitular();
		DTOLicencia dtoLicencia= new DTOLicencia();
		GestorLicencia gestor= GestorLicencia.getInstance();
		
		dtoTitular.setApellido("Reichert");
		dtoTitular.setDireccion("Calle Siempre Viva 234");
		dtoTitular.setDonador(true);
		dtoTitular.setFactorS("+");
		dtoTitular.setGrupoS("0");
		dtoTitular.setNombre("Maximiliano");
		dtoTitular.setNroDoc("38260807");
		dtoTitular.setFechaNac(new Date("06/06/1994"));
		
		dtoLicencia.setFechaOtor(new Date("03/06/2017"));
		dtoLicencia.setFechaVenc(new Date("06/06/2021"));
		dtoLicencia.setObservaciones("Utiliza anteojos para conducir");
		dtoLicencia.setTipo("G");
		try {
			gestor.imprimirLicencia(dtoTitular, dtoLicencia);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			fail("La prueba ha fallado");
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
			fail("La prueba ha fallado");
		}
	}
	
	@Test
	public void emitirLicenciaTest() {
		DTOLicencia dtoLic = new DTOLicencia();
		DTOTitular dtoTitu = null;
		dtoTitu = GestorTitular.getInstance().obtenerTitular("42204789");
		dtoLic.setTipo("G");
		dtoLic.setObservaciones("Necesita lentes");
		dtoLic.setTitular(dtoTitu);
		
		try {
			GestorLicencia.emitirLicencia(dtoLic);
		} catch (EmitirLicenciaException e) {
			fail();
		}
		
	}
	
	@Test(expected = EmitirLicenciaException.class)
	public void emitirLicenciaNoCumpleRequisitosTest() throws EmitirLicenciaException {
		DTOLicencia dtoLic = new DTOLicencia();
		DTOTitular dtoTitu = null;
		dtoTitu = GestorTitular.getInstance().obtenerTitular("42204789");
		dtoLic.setTipo("C");
		dtoLic.setObservaciones("Necesita lentes");
		dtoLic.setTitular(dtoTitu);
		
		GestorLicencia.emitirLicencia(dtoLic);
	
		
	}
	
	@Test(expected = EmitirLicenciaException.class)
	public void emitirLicenciaDelMismoTipoDosVecesFalla() throws EmitirLicenciaException {
		DTOLicencia dtoLic = new DTOLicencia();
		DTOTitular dtoTitu = null;
		dtoTitu = GestorTitular.getInstance().obtenerTitular("42204789");
		dtoLic.setTipo("F");
		dtoLic.setObservaciones("Necesita lentes");
		dtoLic.setTitular(dtoTitu);
		
		GestorLicencia.emitirLicencia(dtoLic);
		GestorLicencia.emitirLicencia(dtoLic);
}
		
	

}
