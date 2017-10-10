/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mangel.tercero
 */
@Entity
@Table(name = "componente")
public class Componente implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componente")
    private Set<SeguimientoComponente> seguimientoComponenteSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "coste")
    private Double coste;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Basic(optional = false)
    @Column(name = "last_updated")
    @Temporal(TemporalType.DATE)
    private Date lastUpdated;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "defecto")
    private boolean defecto;

    public Componente() {
    }

    public Componente(Long id) {
        this.id = id;
    }

    public Componente(Long id, Date dateCreated, boolean enabled, Date lastUpdated, String nombre) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.lastUpdated = lastUpdated;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCoste() {
        return coste;
    }

    public void setCoste(Double coste) {
        this.coste = coste;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof Componente)) {
            return false;
        }
        Componente other = (Componente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @XmlTransient
    public Set<SeguimientoComponente> getSeguimientoComponenteSet() {
        return seguimientoComponenteSet;
    }

    public void setSeguimientoComponenteSet(Set<SeguimientoComponente> seguimientoComponenteSet) {
        this.seguimientoComponenteSet = seguimientoComponenteSet;
    }

    /**
     * @return the defecto
     */
    public boolean isDefecto() {
        return defecto;
    }

    /**
     * @param defecto the defecto to set
     */
    public void setDefecto(boolean defecto) {
        this.defecto = defecto;
    }
    
}
