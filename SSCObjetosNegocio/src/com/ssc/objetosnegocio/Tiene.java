//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Tiene.java Created on 12/10/2014, 09:10:27 AM
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Tiene
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
@Entity
@Table(name = "tiene")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiene.findAll", query = "SELECT t FROM Tiene t"),
    @NamedQuery(name = "Tiene.findByCliente", query = "SELECT t FROM Tiene t WHERE t.idCliente = :idCliente"),
    @NamedQuery(name = "Tiene.findByIdTiene", query = "SELECT t FROM Tiene t WHERE t.idTiene = :idTiene")})
public class Tiene implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTiene")
    private Integer idTiene;
    @JoinColumn(name = "idVehiculo", referencedColumnName = "idVehiculo")
    @ManyToOne(optional = false)
    private Vehiculo idVehiculo;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public Tiene() {
    }

    public Tiene(Integer idTiene) {
        this.idTiene = idTiene;
    }

    public Tiene(Cliente idCliente, Vehiculo idVehiculo) {
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
    }
    
    public Integer getIdTiene() {
        return idTiene;
    }

    public void setIdTiene(Integer idTiene) {
        this.idTiene = idTiene;
    }

    public Vehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Vehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiene != null ? idTiene.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiene)) {
            return false;
        }
        Tiene other = (Tiene) object;
        if ((this.idTiene == null && other.idTiene != null) || (this.idTiene != null && !this.idTiene.equals(other.idTiene))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idVehiculo.getAño() + " " + idVehiculo.getMarca() + " " + idVehiculo.getModelo() + " " + idVehiculo.getCilindros() + " " + idVehiculo.getMotor();
    }

}
