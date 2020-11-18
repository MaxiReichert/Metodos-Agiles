package dto;

public class DTOLicencia {
	
	private String tipo;
	private int costo;
	private String fechaOtor;
	private String fechaVenc;
	private String observaciones; 
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public String getFechaOtor() {
		return fechaOtor;
	}
	public void setFechaOtor(String fechaOtor) {
		this.fechaOtor = fechaOtor;
	}
	public String getFechaVenc() {
		return fechaVenc;
	}
	public void setFechaVenc(String fechaVenc) {
		this.fechaVenc = fechaVenc;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
	
}
