package gestores;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dao.DAOLicenciaJPA;
import dto.DTOLicencia;
import dto.DTOTitular;
import entidades.Licencia;
import entidades.Titular;
import entidades.Tramite;
import entidades.Usuario;
import exceptions.EmitirLicenciaException;



/**
 *
 * @author Maxi
 */
public class GestorLicencia {
	
	private final static long SECONDS_IN_YEAR = 31536000;
	
	public static void emitirLicencia(DTOLicencia DTOLicencia) throws EmitirLicenciaException{
		
		String tipo = DTOLicencia.getTipo();
		
		DTOTitular titularDTO = DTOLicencia.getTitular();
		
		long timeToday = Calendar.getInstance().getTime().getTime();
		long timeBorn = titularDTO.getFechaNac().getTime();
		
		if(tipo == "A" || tipo == "B" || tipo == "F" || tipo == "G") {
			
			if( timeToday - timeBorn  < SECONDS_IN_YEAR * 17) {
				throw new EmitirLicenciaException("El titular debe tener 17 años o más para obtener una licencia de clase "+tipo);
			}
		}else if(tipo == "C" || tipo == "D" || tipo == "E") {
			if( timeToday - timeBorn  < SECONDS_IN_YEAR * 21 ) {
				throw new EmitirLicenciaException("El titular debe tener 21 años o más para obtener una licencia de clase "+tipo);
			}
			Predicate<DTOLicencia> licenciaBUnAnioAntesPredicate = lic -> lic.getTipo() == "B" && timeToday - lic.getFechaOtor().getTime() >= SECONDS_IN_YEAR;
			Predicate<DTOLicencia> licenciaProfesionalPredicate = lic -> lic.getTipo() == "C" || lic.getTipo() == "D" || lic.getTipo() == "E";
			
			boolean alMenosLicenciaBUnAnioAntes = titularDTO.getLicenciaList().stream().anyMatch(licenciaBUnAnioAntesPredicate);
			
			if(!alMenosLicenciaBUnAnioAntes) {
				throw new EmitirLicenciaException("El titular debe haber obtenido una licencia de clase B, como mínimo, un año antes");
			}
				
			if(timeToday - timeBorn >= 65*SECONDS_IN_YEAR) {
				boolean existeLicenciaProfesionalPrevia = titularDTO.getLicenciaList().stream().anyMatch(licenciaProfesionalPredicate);				
				if(!existeLicenciaProfesionalPrevia) {
					throw new EmitirLicenciaException("El titular debe ser menor a 65 años para obtener su primera licencia profesional");
				}
			}
		}
		Predicate<DTOLicencia> licenciaDelMismoTipoPredicate = lic -> lic.getTipo() == DTOLicencia.getTipo();
		boolean tieneOTuvoLicenciaDelMismoTipo = titularDTO.getLicenciaList().stream().anyMatch(licenciaDelMismoTipoPredicate);
		
		if(tieneOTuvoLicenciaDelMismoTipo) {
			throw new EmitirLicenciaException("El titular ya posee la licencia. Si expiró, debe renovarla");
		}
		

		
		Licencia nuevaLicencia = new Licencia();
		
		nuevaLicencia.setTipo(tipo);
		nuevaLicencia.setFechaOtor(DTOLicencia.getFechaOtor());
		nuevaLicencia.setFechaVenc(DTOLicencia.getFechaVenc());
		nuevaLicencia.setObservaciones(DTOLicencia.getObservaciones());
		Titular titular = new Titular();
		titular.setTipoDoc(DTOLicencia.getTitular().getTipoDoc());
		titular.setNumeroDoc(DTOLicencia.getTitular().getNroDoc());
		nuevaLicencia.setTitular(titular);
		Tramite tramite = new Tramite();
		tramite.setFechaReali(Calendar.getInstance().getTime());
		Usuario usuario = GestorUsuario.obtenerUsuarioActual();
		tramite.setUsuario(usuario);
		nuevaLicencia.setTramite(tramite);
		nuevaLicencia.getCosto();
		
		try {
			DAOLicenciaJPA.getInstance().darDeAltaLicencia(nuevaLicencia);
		}
		catch(Exception e){
			throw new EmitirLicenciaException("Error al guardar la licencia en la base de datos. Si el problema persiste, contacte al administrador del sistema");
		}
			

	};
}