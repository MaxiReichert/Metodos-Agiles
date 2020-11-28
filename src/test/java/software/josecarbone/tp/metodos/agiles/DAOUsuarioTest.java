package software.josecarbone.tp.metodos.agiles;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import dao.DAOUsuario;
import dao.DAOUsuarioJPA;
import entidades.Usuario;

public class DAOUsuarioTest {
	
	@Test
	public void existeUsuarioTest() {
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		try {
			assertEquals("El resultado no es nulo", daoUsuario.existeUsuario("98765432"), false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("Se produjo una excepcion");
		}
		try {
			assertEquals("No encontro el usuario", daoUsuario.existeUsuario("12345678"), true);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail("Se produjo una excepcion");
		}
	}
	
	@Test
	public void buscarUsuarioTest() {
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		Usuario usuario= new Usuario();
		usuario.setApellido("Reichert");
		usuario.setDireccion("Calle Siempre Viva 243");
		usuario.setFechaNac(new Date("06/06/1994"));
		usuario.setNombre("Maximiliano");
		usuario.setNumeroDoc("12345678");
		usuario.setNumeroLegajo(1234);
		usuario.setTipoDoc("DNI");
		try {
			Usuario aux=daoUsuario.buscarPorDNI("12345678");
			assertEquals(aux.getApellido(), usuario.getApellido());
			assertEquals(aux.getDireccion(), usuario.getDireccion());
			assertEquals(aux.getFechaNac(), usuario.getFechaNac());
			assertEquals(aux.getNombre(), usuario.getNombre());
			assertEquals(aux.getNumeroDoc(), usuario.getNumeroDoc());
			assertEquals(aux.getNumeroLegajo(), usuario.getNumeroLegajo());
			assertEquals(aux.getTipoDoc(), usuario.getTipoDoc());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("Se produjo una excepcion");
		}
	}
	
	@Test
	public void persistirUsuarioTest() {
		DAOUsuario daoUsuario= new DAOUsuarioJPA();
		Usuario usuario= new Usuario();
		usuario.setApellido("Reichert");
		usuario.setDireccion("Calle Siempre Viva 243");
		usuario.setFechaNac(new Date("09/09/1999"));
		usuario.setNombre("Marianela");
		usuario.setNumeroDoc("15975312");
		usuario.setNumeroLegajo(1345);
		usuario.setTipoDoc("DNI");
		try {
			daoUsuario.persistirUsuario(usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("No se pudo guardar el usuario");
		}
	}
	
}
