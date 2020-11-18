package software.josecarbone.tp.metodos.agiles;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.itextpdf.text.DocumentException;

import dto.DTOLicencia;
import dto.DTOTitular;
import gestores.GestorLicencia;

public class GestorLicenciaTest {

	@Test
	public void imprimirTicketTest() {
		DTOTitular dtoTitular= new DTOTitular();
		DTOLicencia dtoLicencia= new DTOLicencia();
		GestorLicencia gestorLicencia= new GestorLicencia();
		
		dtoTitular.setApellido("Reichert");
		dtoTitular.setNombre("Maximiliano");
		dtoTitular.setDireccion("Calle Siempre Viva 234");
		dtoLicencia.setCosto(50);
		dtoLicencia.setTipo("B");
		
		try {
			gestorLicencia.ImprimirTicket(dtoTitular, dtoLicencia);
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
		GestorLicencia gestor= new GestorLicencia();
		
		dtoTitular.setApellido("Reichert");
		dtoTitular.setDireccion("Calle Siempre Viva 234");
		dtoTitular.setDonador("si");
		dtoTitular.setFactorS("+");
		dtoTitular.setGrupoS("0");
		dtoTitular.setNombre("Maximiliano");
		dtoTitular.setNroDoc("38260807");
		dtoTitular.setFechaNac("06/06/1994");
		
		dtoLicencia.setFechaOtor("03/06/2017");
		dtoLicencia.setFechaVenc("06/06/2021");
		dtoLicencia.setObservaciones("Utiliza anteojos para conducir");
		dtoLicencia.setTipo("G");
		try {
			gestor.ImprimirLicencia(dtoTitular, dtoLicencia);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			fail("La prueba ha fallado");
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
			fail("La prueba ha fallado");
		}
	}

}
