//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)FPersistencia.java Created on 11/10/2014, 12:43:31 PM
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
package com.ssc.negocio;

import com.ssc.excepciones.NonexistentEntityException;
import com.ssc.excepciones.PreexistingEntityException;
import com.ssc.interfazpersistencia.IPersistencia;
import com.ssc.objetosnegocio.*;
import com.ssc.persistencia.FPersistencia;
import java.util.List;

/**
 * Class FPersistencia
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class CNegocio {

    IPersistencia pe;

    protected CNegocio() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Orden ">
    protected Orden registrarOrden(Orden orden) throws PreexistingEntityException, Exception {
        pe = new FPersistencia();
        return pe.registrarOrden(orden);
    }

    protected Orden actulizarOrden(Orden orden) throws NonexistentEntityException, Exception {
        pe = new FPersistencia();
        return pe.actulizarOrden(orden);
    }

    protected Orden eliminarOrden(Orden orden) throws NonexistentEntityException, Exception {
        pe = new FPersistencia();
        return pe.registrarOrden(orden);
    }

    protected Orden buscarOrden(Orden orden) throws NonexistentEntityException {
        pe = new FPersistencia();
        return pe.buscarOrden(orden);
    }

    protected List<Orden> listaOrdenes() throws NonexistentEntityException {
        pe = new FPersistencia();
        return pe.listaOrdenes();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Cliente ">
    protected Cliente agregarCliente(Cliente cliente) throws PreexistingEntityException, Exception {
        pe = new FPersistencia();
        return pe.agregarCliente(cliente);
    }

    protected Cliente actualizarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        pe = new FPersistencia();
        return pe.actualizarCliente(cliente);
    }

    protected Cliente eliminarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        pe = new FPersistencia();
        return pe.eliminarCliente(cliente);
    }

    protected Cliente buscarCliente(Cliente cliente) throws NonexistentEntityException {
        pe = new FPersistencia();
        return pe.buscarCliente(cliente);
    }

    protected List<Cliente> listaClientes() throws NonexistentEntityException {
        pe = new FPersistencia();
        return pe.listaClientes();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Vehiculo ">
    protected Vehiculo agregarVehiculo(Vehiculo vehiculo) throws PreexistingEntityException, Exception {
        pe = new FPersistencia();
        return pe.agregarVehiculo(vehiculo);
    }

    protected Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        pe = new FPersistencia();
        return pe.actualizarVehiculo(vehiculo);
    }

    protected Vehiculo eliminarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        pe = new FPersistencia();
        return pe.eliminarVehiculo(vehiculo);
    }

    protected List<Vehiculo> listaVehiculos() throws NonexistentEntityException {
        pe = new FPersistencia();
        return pe.listaVehiculos();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Servicio ">
    protected Servicio agregarServicio(Servicio servicio) throws PreexistingEntityException, Exception {
        pe = new FPersistencia();
        return pe.agregarServicio(servicio);
    }

    protected Servicio actualizarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        pe = new FPersistencia();
        return pe.actualizarServicio(servicio);
    }

    protected Servicio eliminarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        pe = new FPersistencia();
        return pe.eliminarServicio(servicio);
    }

    protected List<Servicio> listaServicios() throws NonexistentEntityException {
        pe = new FPersistencia();
        return pe.listaServicios();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Tiene ">    
    protected List<Tiene> getVehiculosPorCliente(Cliente cliente) throws NonexistentEntityException {
        pe = new FPersistencia();
        return pe.getVehiculosPorCliente(cliente);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Detalleorden ">    
    protected List<Detalleorden> getDetalleOrden(Orden orden) throws NonexistentEntityException {
        pe = new FPersistencia();
        return pe.getDetalleOrden(orden);
    }
    //</editor-fold>    
}
