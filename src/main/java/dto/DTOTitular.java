package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DTOTitular {
	
	private String nombre;
	private String apellido;
	private String direccion;
	private String nroDoc;
	private String tipoDoc;
	private boolean donador;
	private String grupoS;
	private String factorS;
	private Date fechaNac;
	private List<DTOLicencia> licenciaList;
	
	public void crearListaDTOLicencia() {
		this.licenciaList = new ArrayList<DTOLicencia>();
	}
	
	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public boolean getDonador() {
		return donador;
	}

	public void setDonador(boolean donador) {
		this.donador = donador;
	}

	public String getGrupoS() {
		return grupoS;
	}

	public void setGrupoS(String grupoS) {
		this.grupoS = grupoS;
	}

	public String getFactorS() {
		return factorS;
	}

	public void setFactorS(String factorS) {
		this.factorS = factorS;
	}

	public List<DTOLicencia> getLicenciaList() {
		return licenciaList;
	}

	public void setLicenciaList(List<DTOLicencia> licenciaDTOList) {
		this.licenciaList = licenciaDTOList;
	}
	
	public void addToLicenciaList(DTOLicencia licenciaDTO) {
		System.out.println("Va bien 2");
		this.licenciaList.add(licenciaDTO);
		System.out.println("Va bien 3");
	}
	/*
	public DTOLicencia getElementoLicenciaList(int i) {
		return this.licenciaList.get(i);		
	}*/
	
}
