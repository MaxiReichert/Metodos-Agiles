package software.josecarbone.tp.metodos.agiles;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import dao.DAOUsuario;
import dao.DAOUsuarioJPA;
import entidades.Usuario;

public class DAOUsuarioTest {
	
	@Test
	public void buscarPorDNITest() {
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		Usuario usuario= new Usuario();
		usuario.setApellido("Reichert");
		usuario.setNombre("Maximiliano");
		usuario.setDireccion("Calle Siempre Viva 234");
		usuario.setFechaNac(new Date("06/06/1994"));
		usuario.setNumeroDoc("12345678");
		usuario.setTipoDoc("DNI");
		usuario.setNumeroLegajo("1234");
		try {
			assertEquals("El resultado no es nulo", daoUsuario.buscarPorDNI("98765432"), null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("Se produjo una excepcion");
		}
		try {
			assertEquals("No encontro el usuario", daoUsuario.buscarPorDNI("12345678"), usuario);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail("Se produjo una excepcion");
		}
	}
}
