//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)FPersistencia.java Created on 10/10/2014, 07:55:35 PM
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
import com.ssc.interfazpersistencia.IPersistencia;
import com.ssc.objetosnegocio.*;
import java.util.List;


/**
 * Class FPersistencia
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class FPersistencia implements IPersistencia {

    CPersistencia cp;

    public FPersistencia() {
    }
    
    //<editor-fold defaultstate="collapsed" desc=" Orden ">
    @Override
    public Orden registrarOrden(Orden orden) throws PreexistingEntityException, Exception {
        cp = new CPersistencia();
        return cp.registrarOrden(orden);
    }

    @Override
    public Orden actulizarOrden(Orden orden) throws NonexistentEntityException, Exception {
        cp = new CPersistencia();        
        return cp.actulizarOrden(orden);
    }

    @Override
    public Orden eliminarOrden(Orden orden) throws NonexistentEntityException, Exception {
        cp = new CPersistencia();
        return cp.registrarOrden(orden);
    }
    
    @Override
    public Orden buscarOrden(Orden orden) throws NonexistentEntityException {
        cp = new CPersistencia();
        return cp.buscarOrden(orden);
    }
    
    @Override
    public List<Orden> listaOrdenes() throws NonexistentEntityException {
        cp = new CPersistencia();
        return cp.listaOrdenes();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Cliente ">
    @Override
    public Cliente agregarCliente(Cliente cliente) throws PreexistingEntityException, Exception {
        cp = new CPersistencia();
        return cp.agregarCliente(cliente);
    }
    
    @Override
    public Cliente actualizarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        cp = new CPersistencia();
        return cp.actualizarCliente(cliente);
    }
    
    @Override
    public Cliente eliminarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        cp = new CPersistencia();
        return cp.eliminarCliente(cliente);
    }
    
    @Override
    public Cliente buscarCliente(Cliente cliente) throws NonexistentEntityException {
        cp = new CPersistencia();
        return cp.buscarCliente(cliente);
    }

    @Override
    public List<Cliente> listaClientes() throws NonexistentEntityException {
        cp = new CPersistencia();
        return cp.listaClientes();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Vehiculo ">
    @Override
    public Vehiculo agregarVehiculo(Vehiculo vehiculo) throws PreexistingEntityException, Exception {
        cp = new CPersistencia();
        return cp.agregarVehiculo(vehiculo);
    }
    
    @Override
    public Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        cp = new CPersistencia();
        return cp.actualizarVehiculo(vehiculo);
    }
    
    @Override
    public Vehiculo eliminarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        cp = new CPersistencia();
        return cp.eliminarVehiculo(vehiculo);
    }
    
    @Override
    public List<Vehiculo> listaVehiculos() throws NonexistentEntityException {
        cp = new CPersistencia();
        return cp.listaVehiculos();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Servicio ">
    @Override
    public Servicio agregarServicio(Servicio servicio) throws PreexistingEntityException, Exception {
        cp = new CPersistencia();
        return cp.agregarServicio(servicio);
    }
    
    @Override
    public Servicio actualizarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        cp = new CPersistencia();
        return cp.actualizarServicio(servicio);
    }
    
    @Override
    public Servicio eliminarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        cp = new CPersistencia();
        return cp.eliminarServicio(servicio);
    }
    
    @Override
    public List<Servicio> listaServicios() throws NonexistentEntityException {
        cp = new CPersistencia();
        return cp.listaServicios();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Tiene ">    
    @Override
    public List<Tiene> getVehiculosPorCliente(Cliente cliente) throws NonexistentEntityException {
        cp = new CPersistencia();
        return cp.getVehiculosPorCliente(cliente);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Detalleorden ">    
    @Override
    public List<Detalleorden> getDetalleOrden(Orden orden) throws NonexistentEntityException {
        cp = new CPersistencia();
        return cp.getDetalleOrden(orden);
    }
    //</editor-fold>
    
}
