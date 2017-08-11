/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.hibernate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mangel.tercero
 */
@Entity
@Table(name = "seguimiento_componente")
public class SeguimientoComponente implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected SeguimientoComponentePK seguimientoComponentePK;
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Basic(optional = false)
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;
    @Basic(optional = false)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "componente_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Componente componente;
    @JoinColumn(name = "seguimiento_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Seguimiento seguimiento;

    public SeguimientoComponente() {
    }

    public SeguimientoComponente(SeguimientoComponentePK seguimientoComponentePK) {
        this.seguimientoComponentePK = seguimientoComponentePK;
    }

    public SeguimientoComponente(SeguimientoComponentePK seguimientoComponentePK, long id, int cantidad, Date fechaEntrega, Date fechaPedido, String numeroFactura, String observaciones) {
        this.seguimientoComponentePK = seguimientoComponentePK;
        this.id = id;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
        this.fechaPedido = fechaPedido;
        this.numeroFactura = numeroFactura;
        this.observaciones = observaciones;
    }

    public SeguimientoComponente(long componenteId, long seguimientoId) {
        this.seguimientoComponentePK = new SeguimientoComponentePK(componenteId, seguimientoId);
    }

    public SeguimientoComponentePK getSeguimientoComponentePK() {
        return seguimientoComponentePK;
    }

    public void setSeguimientoComponentePK(SeguimientoComponentePK seguimientoComponentePK) {
        this.seguimientoComponentePK = seguimientoComponentePK;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seguimientoComponentePK != null ? seguimientoComponentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoComponente)) {
            return false;
        }
        SeguimientoComponente other = (SeguimientoComponente) object;
        if ((this.seguimientoComponentePK == null && other.seguimientoComponentePK != null) || (this.seguimientoComponentePK != null && !this.seguimientoComponentePK.equals(other.seguimientoComponentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.matercero.spc.hibernate.SeguimientoComponente[ seguimientoComponentePK=" + seguimientoComponentePK + " ]";
    }
    
}
