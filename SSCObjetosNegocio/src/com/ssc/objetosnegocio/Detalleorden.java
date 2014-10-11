//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Detalleorden.java Created on 10/10/2014, 07:54:10 PM
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses>
 *
 * Copyright (C) 2014 Alan García. All rights reserved.
 */
//</editor-fold>

package com.ssc.objetosnegocio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Detalleorden
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
@Entity
@Table(name = "detalleorden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleorden.findAll", query = "SELECT d FROM Detalleorden d"),
    @NamedQuery(name = "Detalleorden.findByIdDetOrden", query = "SELECT d FROM Detalleorden d WHERE d.idDetOrden = :idDetOrden"),
    @NamedQuery(name = "Detalleorden.findByCantidad", query = "SELECT d FROM Detalleorden d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleorden.findByPUnit", query = "SELECT d FROM Detalleorden d WHERE d.pUnit = :pUnit"),
    @NamedQuery(name = "Detalleorden.findByImporte", query = "SELECT d FROM Detalleorden d WHERE d.importe = :importe")})
public class Detalleorden implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDetOrden")
    private Integer idDetOrden;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Cantidad")
    private Double cantidad;
    @Column(name = "PUnit")
    private Double pUnit;
    @Column(name = "Importe")
    private Double importe;
    @JoinColumn(name = "idServicio", referencedColumnName = "idServicio")
    @ManyToOne(optional = false)
    private Servicio idServicio;
    @JoinColumn(name = "idOrden", referencedColumnName = "idOrden")
    @ManyToOne(optional = false)
    private Orden idOrden;

    public Detalleorden() {
    }

    public Detalleorden(Integer idDetOrden) {
        this.idDetOrden = idDetOrden;
    }

    public Integer getIdDetOrden() {
        return idDetOrden;
    }

    public void setIdDetOrden(Integer idDetOrden) {
        this.idDetOrden = idDetOrden;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPUnit() {
        return pUnit;
    }

    public void setPUnit(Double pUnit) {
        this.pUnit = pUnit;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Servicio getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicio idServicio) {
        this.idServicio = idServicio;
    }

    public Orden getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Orden idOrden) {
        this.idOrden = idOrden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetOrden != null ? idDetOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleorden)) {
            return false;
        }
        Detalleorden other = (Detalleorden) object;
        if ((this.idDetOrden == null && other.idDetOrden != null) || (this.idDetOrden != null && !this.idDetOrden.equals(other.idDetOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ssc.objetosnegocio.Detalleorden[ idDetOrden=" + idDetOrden + " ]";
    }

}
