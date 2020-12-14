package dto;

import java.util.Date;

public class DTOLicencia {
	
	private int id;
	private String tipo;
	private int costo;
	private Date fechaOtor;
	private Date fechaVenc;
	private String observaciones; 
	private DTOTitular titular;
	private boolean activa;
	private boolean copia;
	private int idTramite;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCosto() {
		return costo;
	}
	public DTOTitular getTitular() {
		return titular;
	}
	public void setTitular (DTOTitular titular) {
		this.titular = titular;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public Date getFechaOtor() {
		return fechaOtor;
	}
	public void setFechaOtor(Date fechaOtor) {
		this.fechaOtor = fechaOtor;
	}
	public Date getFechaVenc() {
		return fechaVenc;
	}
	public void setFechaVenc(Date fechaVenc) {
		this.fechaVenc = fechaVenc;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	public boolean isCopia() {
		return copia;
	}
	public void setCopia(boolean copia) {
		this.copia = copia;
	}
	public int getIdTramite() {
		return idTramite;
	}
	public void setIdTramite(int idTramite) {
		this.idTramite = idTramite;
	}
	
	
	
	
}
