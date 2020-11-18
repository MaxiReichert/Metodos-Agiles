/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import entidades.Licencia;
import entidades.Titular;
import exceptions.EmitirLicenciaException;
import persistencia.LicenciaDAOHibernate;



/**
 *
 * @author Maxi
 */
public class GestorLicencia {
	
	private final static long SECONDS_IN_YEAR = 31536000;
	
	public static void emitirLicencia(Licencia licencia) throws EmitirLicenciaException{
		
		String tipo = licencia.getTipo();
		
		Titular titular = licencia.getTitular();
		
		long timeToday = Calendar.getInstance().getTime().getTime();
		long timeBorn = titular.getFechaNac().getTime();
		
		if(tipo == "A" || tipo == "B" || tipo == "F" || tipo == "G") {
			
			if( timeToday - timeBorn  < SECONDS_IN_YEAR * 17) {
				throw new EmitirLicenciaException("El titular debe tener 17 años o más para obtener una licencia de clase "+tipo);
			}
		}else if(tipo == "C" || tipo == "D" || tipo == "E") {
			if( timeToday - timeBorn  < SECONDS_IN_YEAR * 21 ) {
				throw new EmitirLicenciaException("El titular debe tener 21 años o más para obtener una licencia de clase "+tipo);
			}
			Predicate<Licencia> licenciaBUnAnioAntesPredicate = lic -> lic.getTipo() == "B" && timeToday - lic.getFechaOtor().getTime() >= SECONDS_IN_YEAR;
			Predicate<Licencia> licenciaProfesionalPredicate = lic -> lic.getTipo() == "C" || lic.getTipo() == "D" || lic.getTipo() == "E";
			
			boolean alMenosLicenciaBUnAnioAntes = titular.getLicenciaList().stream().anyMatch(licenciaBUnAnioAntesPredicate);
			
			if(!alMenosLicenciaBUnAnioAntes) {
				throw new EmitirLicenciaException("El titular debe haber obtenido una licencia de clase B, como mínimo, un año antes");
			}
				
			if(timeToday - timeBorn >= 65*SECONDS_IN_YEAR) {
				boolean existeLicenciaProfesionalPrevia = titular.getLicenciaList().stream().anyMatch(licenciaProfesionalPredicate);				
				if(!existeLicenciaProfesionalPrevia) {
					throw new EmitirLicenciaException("El titular debe ser menor a 65 años para obtener su primera licencia profesional");
				}
			}
		}
		Predicate<Licencia> licenciaDelMismoTipoPredicate = lic -> lic.getTipo() == licencia.getTipo();
		boolean tieneOTuvoLicenciaDelMismoTipo = titular.getLicenciaList().stream().anyMatch(licenciaDelMismoTipoPredicate);
		
		if(tieneOTuvoLicenciaDelMismoTipo) {
			throw new EmitirLicenciaException("El titular ya posee la licencia. Si expiró, debe renovarla");
		}
		
		try {
			LicenciaDAOHibernate.getInstance().darDeAltaLicencia(licencia);
		}
		catch(Exception e){
			throw new EmitirLicenciaException("Error al guardar la licencia en la base de datos. Si el problema persiste, contacte al administrador del sistema");
		}
			

	}
    
}