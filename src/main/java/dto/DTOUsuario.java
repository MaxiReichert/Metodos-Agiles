package dto;

import java.util.Date;

public class DTOUsuario {
	
	private String nombre;
	private String apellido;
	private String direccion;
	private String nroDoc;
	private String tipoDoc;
	private Date fechaNac;
	private String nroLegajo;
	
	
	public DTOUsuario() {
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the nroDoc
	 */
	public String getNroDoc() {
		return nroDoc;
	}
	/**
	 * @param nroDoc the nroDoc to set
	 */
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
	/**
	 * @return the tipoDoc
	 */
	public String getTipoDoc() {
		return tipoDoc;
	}
	/**
	 * @param tipoDoc the tipoDoc to set
	 */
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	/**
	 * @return the fechaNac
	 */
	public Date getFechaNac() {
		return fechaNac;
	}
	/**
	 * @param fechaNac the fechaNac to set
	 */
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	/**
	 * @return the nroLegajo
	 */
	public String getNroLegajo() {
		return nroLegajo;
	}
	/**
	 * @param nroLegajo the nroLegajo to set
	 */
	public void setNroLegajo(String nroLegajo) {
		this.nroLegajo = nroLegajo;
	}
	
	
}
