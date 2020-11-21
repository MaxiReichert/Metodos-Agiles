package dto;

import java.util.Date;

public class DTOLicencia {
	
	private String tipo;
	private int costo;
	private Date fechaOtor;
	private Date fechaVenc;
	private String observaciones; 
	private DTOTitular titular;
	
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
	
	
	
	
}
