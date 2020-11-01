/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Maxi
 */
@Entity
@Table(name = "titular")
public class Titular implements Serializable {

    @Basic(optional = false)
    @Column(name = "tipo_doc")
    private String tipoDoc;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_doc")
    private Integer numeroDoc;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "tipo_lic_solicitada")
    private String tipoLicSolicitada;
    @Basic(optional = false)
    @Column(name = "grupo_sanguineo")
    private String grupoSanguineo;
    @Basic(optional = false)
    @Column(name = "factor")
    private String factor;
    @Basic(optional = false)
    @Column(name = "donante")
    private boolean donante;

    public Titular() {
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Integer getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(Integer numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoLicSolicitada() {
        return tipoLicSolicitada;
    }

    public void setTipoLicSolicitada(String tipoLicSolicitada) {
        this.tipoLicSolicitada = tipoLicSolicitada;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public boolean getDonante() {
        return donante;
    }

    public void setDonante(boolean donante) {
        this.donante = donante;
    }

    
}
