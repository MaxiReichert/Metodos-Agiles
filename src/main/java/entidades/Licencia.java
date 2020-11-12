/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
    @JoinColumn(name="numero_doc")
    private Titular titular;
    @OneToOne(optional = false)
    @JoinColumn(name = "id")
    private Tramite tramite;

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

    @Override
    public String toString() {
        return "Entidades.Licencia[ id=" + id + " ]";
    }
    
}
