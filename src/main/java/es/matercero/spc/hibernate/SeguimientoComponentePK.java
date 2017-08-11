/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.hibernate;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mangel.tercero
 */
@Embeddable
public class SeguimientoComponentePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "componente_id")
    private long componenteId;
    @Basic(optional = false)
    @Column(name = "seguimiento_id")
    private long seguimientoId;

    public SeguimientoComponentePK() {
    }

    public SeguimientoComponentePK(long componenteId, long seguimientoId) {
        this.componenteId = componenteId;
        this.seguimientoId = seguimientoId;
    }

    public long getComponenteId() {
        return componenteId;
    }

    public void setComponenteId(long componenteId) {
        this.componenteId = componenteId;
    }

    public long getSeguimientoId() {
        return seguimientoId;
    }

    public void setSeguimientoId(long seguimientoId) {
        this.seguimientoId = seguimientoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) componenteId;
        hash += (int) seguimientoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoComponentePK)) {
            return false;
        }
        SeguimientoComponentePK other = (SeguimientoComponentePK) object;
        if (this.componenteId != other.componenteId) {
            return false;
        }
        if (this.seguimientoId != other.seguimientoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.matercero.spc.hibernate.SeguimientoComponentePK[ componenteId=" + componenteId + ", seguimientoId=" + seguimientoId + " ]";
    }
    
}
