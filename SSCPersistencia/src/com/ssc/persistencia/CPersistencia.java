//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)CPersistencia.java Created on 10/10/2014, 07:55:42 PM
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
package com.ssc.persistencia;

import com.ssc.excepciones.*;
import com.ssc.objetosnegocio.*;
import java.util.List;

/**
 * Class CPersistencia
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class CPersistencia {

    //<editor-fold defaultstate="collapsed" desc=" Variables ">    
    ClienteJpaController cjc;
    DetalleordenJpaController djc;
    OrdenJpaController ojc;
    ServicioJpaController sjc;
    VehiculoJpaController vjc;
    TieneJpaController tjc;
    //</editor-fold>

    /**
     * Constructor por ausencia
     */
    protected CPersistencia() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Orden ">    
    protected Orden registrarOrden(Orden orden) throws PreexistingEntityException, Exception {
        ojc = new OrdenJpaController();
        ojc.create(orden);
        return orden;
    }

    protected Orden actulizarOrden(Orden orden) throws NonexistentEntityException, Exception {
        ojc = new OrdenJpaController();
        ojc.edit(orden);
        return orden;
    }

    protected Orden eliminarOrden(Orden orden) throws NonexistentEntityException, Exception {
        ojc = new OrdenJpaController();
        ojc.destroy(orden.getIdOrden());
        return orden;
    }

    protected Orden buscarOrden(Orden orden) throws NonexistentEntityException {
        ojc = new OrdenJpaController();
        return ojc.findOrden(orden.getIdOrden());
    }

    protected List<Orden> listaOrdenes() throws NonexistentEntityException {
        ojc = new OrdenJpaController();
        return ojc.findOrdenEntities();
    }
    
    protected List<Orden> listaEstado(String estado) {
        ojc = new OrdenJpaController();
        return ojc.getListaPorEstado(estado);
    }
    
    protected int lastInsert() {
        ojc = new OrdenJpaController();
        return ojc.lastInsert();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Cliente ">
    protected Cliente agregarCliente(Cliente cliente) throws PreexistingEntityException, Exception {
        cjc = new ClienteJpaController();
        cjc.create(cliente);
        return cliente;
    }

    protected Cliente actualizarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        cjc = new ClienteJpaController();
        cjc.edit(cliente);
        return cliente;
    }

    protected Cliente eliminarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        cjc = new ClienteJpaController();
        cjc.destroy(cliente.getIdCliente());
        return cliente;
    }

    protected Cliente buscarCliente(String cliente) throws NonexistentEntityException {
        cjc = new ClienteJpaController();
        return cjc.findCliente(cliente);
    }

    protected List<Cliente> listaClientes() throws NonexistentEntityException {
        cjc = new ClienteJpaController();
        return cjc.findClienteEntities();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Vehiculo ">
    protected Vehiculo agregarVehiculo(Vehiculo vehiculo) throws PreexistingEntityException, Exception {
        vjc = new VehiculoJpaController();
        vjc.create(vehiculo);
        return vehiculo;
    }

    protected Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        vjc = new VehiculoJpaController();
        vjc.edit(vehiculo);
        return vehiculo;
    }

    protected Vehiculo eliminarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        vjc = new VehiculoJpaController();
        vjc.destroy(vehiculo.getIdVehiculo());
        return vehiculo;
    }

    protected List<Vehiculo> listaVehiculos() throws NonexistentEntityException {
        vjc = new VehiculoJpaController();
        return vjc.findVehiculoEntities();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Servicio ">
    protected Servicio agregarServicio(Servicio servicio) throws PreexistingEntityException, Exception {
        sjc = new ServicioJpaController();
        sjc.create(servicio);
        return servicio;
    }

    protected Servicio actualizarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        sjc = new ServicioJpaController();
        sjc.edit(servicio);
        return servicio;
    }

    protected Servicio eliminarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        sjc = new ServicioJpaController();
        sjc.destroy(servicio.getIdServicio());
        return servicio;
    }    
    protected List<Servicio> listaServicios() throws NonexistentEntityException {
        sjc = new ServicioJpaController();
        return sjc.findServicioEntities();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Tiene ">    
    protected List<Tiene> getVehiculosPorCliente(Cliente cliente) throws NonexistentEntityException {
        tjc = new TieneJpaController();
        return tjc.getVehiculosPorCliente(cliente);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Detalleorden ">
    protected List<Detalleorden> getDetalleOrden(Orden orden) throws NonexistentEntityException {
        djc = new DetalleordenJpaController();
        return djc.getDetalleOrden(orden);
    }
    //</editor-fold>
}
