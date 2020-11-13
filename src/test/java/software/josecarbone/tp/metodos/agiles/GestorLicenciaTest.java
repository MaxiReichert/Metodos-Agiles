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

}
