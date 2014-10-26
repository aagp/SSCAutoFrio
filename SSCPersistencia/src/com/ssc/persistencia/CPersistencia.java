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
        ojc = new OrdenJpaController();       
        cjc = new ClienteJpaController();
        vjc = new VehiculoJpaController();
        sjc = new ServicioJpaController();
        tjc = new TieneJpaController();
        djc = new DetalleordenJpaController();        
    }

    //<editor-fold defaultstate="collapsed" desc=" Orden ">    
    protected Orden registrarOrden(Orden orden) throws PreexistingEntityException, Exception {
        ojc.create(orden);
        return orden;
    }

    protected Orden actulizarOrden(Orden orden) throws NonexistentEntityException, Exception {
        ojc.edit(orden);
        return orden;
    }

    protected Orden eliminarOrden(Orden orden) throws NonexistentEntityException, Exception {
        ojc.destroy(orden.getIdOrden());
        return orden;
    }

    protected Orden buscarOrden(Orden orden) throws NonexistentEntityException {
        return ojc.findOrden(orden.getIdOrden());
    }

    protected List<Orden> listaOrdenes() throws NonexistentEntityException {
        return ojc.findOrdenEntities();
    }
    
    protected List<Orden> listaEstado(String estado) {
        return ojc.getListaPorEstado(estado);
    }
    
    protected int lastInsert() {
        return ojc.lastInsert();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Cliente ">
    protected Cliente agregarCliente(Cliente cliente) throws PreexistingEntityException, Exception {
        cjc.create(cliente);
        return cliente;
    }

    protected Cliente actualizarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        cjc.edit(cliente);
        return cliente;
    }

    protected Cliente eliminarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        cjc.destroy(cliente.getIdCliente());
        return cliente;
    }

    protected Cliente buscarCliente(String cliente) throws NonexistentEntityException {
        return cjc.findCliente(cliente);
    }

    protected List<Cliente> listaClientes() throws NonexistentEntityException {
        return cjc.findClienteEntities();
    }
    
    protected List<Cliente> getClienteLike(String nombre) {
        return cjc.getClienteLike(nombre);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Vehiculo ">
    protected Vehiculo agregarVehiculo(Vehiculo vehiculo) throws PreexistingEntityException, Exception {
        vjc.create(vehiculo);
        return vehiculo;
    }

    protected Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        vjc.edit(vehiculo);
        return vehiculo;
    }

    protected Vehiculo eliminarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        vjc.destroy(vehiculo.getIdVehiculo());
        return vehiculo;
    }

    protected List<Vehiculo> listaVehiculos() throws NonexistentEntityException {
        return vjc.findVehiculoEntities();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Servicio ">
    protected Servicio agregarServicio(Servicio servicio) throws PreexistingEntityException, Exception {
        sjc.create(servicio);
        return servicio;
    }

    protected Servicio actualizarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        sjc.edit(servicio);
        return servicio;
    }

    protected Servicio eliminarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        sjc.destroy(servicio.getIdServicio());
        return servicio;
    }    
    protected List<Servicio> listaServicios() throws NonexistentEntityException {
        return sjc.findServicioEntities();
    }
    
    protected List<Servicio> getServicioLike(String nombre) {
        return sjc.getServicioLike(nombre);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Tiene ">    
    protected List<Tiene> getVehiculosPorCliente(Cliente cliente) throws NonexistentEntityException {
        return tjc.getVehiculosPorCliente(cliente);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Detalleorden ">
    protected List<Detalleorden> getDetalleOrden(Orden orden) throws NonexistentEntityException {
        return djc.getDetalleOrden(orden);
    }
    
    protected Detalleorden guardarDetalle(Detalleorden detalleOrden) throws PreexistingEntityException, Exception {
        djc.create(detalleOrden);
        return detalleOrden;
    }
    //</editor-fold>
}
