package dto;

public class DTOTitular {
	
	private String nombre;
	private String apellido;
	private String direccion;
	private String nroDoc;
	private String tipoDoc;
	private String donador;
	private String grupoS;
	private String factorS;
	private String fechaNac;
	
	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
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

	public String getDonador() {
		return donador;
	}

	public void setDonador(String donador) {
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
	
	
}
