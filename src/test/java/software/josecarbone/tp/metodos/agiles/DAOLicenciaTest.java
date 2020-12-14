package software.josecarbone.tp.metodos.agiles;

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
