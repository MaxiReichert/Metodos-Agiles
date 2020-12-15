/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import entidades.*;

/**
 *
 * @author Maxi
 */
@Entity
@Table(name = "licencia")
@NamedQueries({
    @NamedQuery(name = "Licencia.findAll", query = "SELECT l FROM Licencia l")})
public class Licencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "fecha_otor")
    @Temporal(TemporalType.DATE)
    private Date fechaOtor;
    @Basic(optional = false)
    @Column(name = "fecha_venc")
    @Temporal(TemporalType.DATE)
    private Date fechaVenc;
    @Basic(optional = false)
    @Column(name = "costo")
    private int costo;
    @Column(name = "observaciones")
    private String observaciones;
    @ManyToOne(optional= false)
    @JoinColumn(name="titular")
    private Titular titular;
    @OneToOne(optional = false)
    @JoinColumn(name = "tramite")
    private Tramite tramite;
    @Basic(optional = false)
    @Column(name = "activa")
    private boolean activa;
    @Basic(optional = false)
    @Column(name = "copia")
    private boolean copia;
    @OneToMany(mappedBy= "titular")
	private List<Licencia> licenciaList;

    public List<Licencia> getLicenciaList() {
		return licenciaList;
	}

	public void setLicenciaList(List<Licencia> licenciaList) {
		this.licenciaList = licenciaList;
	}

    public Licencia() {
    }

    public Licencia(Integer id) {
        this.id = id;
    }

    public Licencia(Integer id, String tipo, Date fechaOtor, Date fechaVenc, int costo) {
        this.id = id;
        this.tipo = tipo;
        this.fechaOtor = fechaOtor;
        this.fechaVenc = fechaVenc;
        this.costo = costo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
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

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licencia)) {
            return false;
        }
        Licencia other = (Licencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    public Integer calcularCosto() {
        Integer costo = 8;
        Integer periodo = this.fechaVenc.getYear() - this.fechaOtor.getYear();
        System.out.format("");
        switch (this.tipo) {
            case "A":
                if (periodo >= 5) costo += 40;
                else if (periodo >= 4) costo += 30;
                else if (periodo >= 3) costo += 25;
                else costo += 20;
                return costo;
            case "B":
                if (periodo >= 5) costo += 40;
                else if (periodo >= 4) costo += 30;
                else if (periodo >= 3) costo += 25;
                else costo += 20;
                return costo;
            case "C":
                if (periodo >= 5) costo += 47;
                else if (periodo >= 4) costo += 35;
                else if (periodo >= 3) costo += 30;
                else costo += 23;
                return costo;
            case "D":
                if (periodo >= 5) costo += 59;
                else if (periodo >= 4) costo += 44;
                else if (periodo >= 3) costo += 39;
                else costo += 29;
                return costo;
            case "E":
                if (periodo >= 5) costo += 59;
                else if (periodo >= 4) costo += 44;
                else if (periodo >= 3) costo += 39;
                else costo += 29;
                return costo;
            case "F":
                if (periodo >= 5) costo += 59;
                else if (periodo >= 4) costo += 44;
                else if (periodo >= 3) costo += 39;
                else costo += 29;
                return costo;
            case "G":
                if (periodo >= 5) costo += 40;
                else if (periodo >= 4) costo += 30;
                else if (periodo >= 3) costo += 25;
                else costo += 20;
                return costo;
            default:
                return 0;
        }
    }
    
  //Criterios:
 // Durante la emisión de la licencia, se establece la vigencia de la misma, de acuerdo a la siguiente tabla:
 // - Menores de 21 años: 1 año la primera vez y 3 años las siguientes
 // - Hasta 46 años: 5 años
 // - Hasta 60 años: 4 años
 // - Hasta 70 años: 3 años
 // - Mayores de 70 años: 1 año
 // El día y mes de la fecha de vencimiento deben coincidir con el día y mes de la fecha de nacimiento
 // del titular, respectivamente. La fecha de inicio de vigencia debe ser la fecha del sistema, y no puede cambiarse.

 public Date calcularVigencia()
 {
	LocalDate fechaVigencia;
	LocalDate fechaNac = Instant.ofEpochMilli(this.titular.getFechaNac().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 	LocalDate fechaActual = LocalDate.now();
 	int edad=Period.between(fechaNac , fechaActual).getYears();
 	int aniosVigencia=0;

 	//Se toma como minima edad 14 años correspondiente a la licencia de tipo LCM

 	if ((edad>=14) && (edad<=21)) {aniosVigencia=1;}
 	else if ((edad>21) && (edad<=46)) {aniosVigencia=5;}
 	else if ((edad>46) && (edad<=60)) {aniosVigencia=4;}
 	else if ((edad>60) && (edad<=70)) {aniosVigencia=3;}
    else if (edad>70) {aniosVigencia=1;}
 	fechaVigencia = LocalDate.of(fechaActual.getYear()+aniosVigencia, fechaNac.getMonth(), fechaNac.getDayOfMonth());
 	
 	return Date.from(fechaVigencia.atStartOfDay(ZoneId.systemDefault()).toInstant());
 }
    
    

    @Override
    public String toString() {
        return "Entidades.Licencia[ id=" + id + " ]";
    }
    
   
 
}


