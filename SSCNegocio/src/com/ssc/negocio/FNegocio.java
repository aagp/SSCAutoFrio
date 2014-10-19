//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)FNegocio.java Created on 11/10/2014, 10:59:11 AM
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

import com.ssc.excepciones.*;
import com.ssc.interfaznegocio.INegocio;
import com.ssc.objetosnegocio.*;
import java.util.List;

/**
 * Class FNegocio
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class FNegocio implements INegocio {
    
    CNegocio cn;
    
    public FNegocio() {
        cn = new CNegocio();
    }
    
    //<editor-fold defaultstate="collapsed" desc=" Orden ">
    @Override
    public Orden registrarOrden(Orden orden) throws PreexistingEntityException, Exception {
        return cn.registrarOrden(orden);
    }

    @Override
    public Orden actulizarOrden(Orden orden) throws NonexistentEntityException, Exception {
        return cn.actulizarOrden(orden);
    }

    @Override
    public Orden eliminarOrden(Orden orden) throws NonexistentEntityException, Exception {
        return cn.eliminarOrden(orden);
    }
    
    @Override
    public Orden buscarOrden(Orden orden) throws NonexistentEntityException {
        return cn.buscarOrden(orden);
    }
    
    @Override
    public List<Orden> listaOrdenes() throws NonexistentEntityException {
        return cn.listaOrdenes();
    }
    
    @Override
    public List<Orden> listaEstado(String estado) {
        return cn.listaEstado(estado);
    }
    
    @Override
    public int lastInsert() {
        return cn.lastInsert();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Cliente ">
    @Override
    public Cliente agregarCliente(Cliente cliente) throws PreexistingEntityException, Exception {
        return cn.agregarCliente(cliente);
    }
    
    @Override
    public Cliente actualizarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        return cn.actualizarCliente(cliente);
    }
    
    @Override
    public Cliente eliminarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        return cn.eliminarCliente(cliente);
    }
    
    @Override
    public Cliente buscarCliente(String cliente) throws NonexistentEntityException {
        return cn.buscarCliente(cliente);
    }

    @Override
    public List<Cliente> listaClientes() throws NonexistentEntityException {
        return cn.listaClientes();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Vehiculo ">
    @Override
    public Vehiculo agregarVehiculo(Vehiculo vehiculo) throws PreexistingEntityException, Exception {
        return cn.agregarVehiculo(vehiculo);
    }
    
    @Override
    public Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        return cn.actualizarVehiculo(vehiculo);
    }
    
    @Override
    public Vehiculo eliminarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        return cn.eliminarVehiculo(vehiculo);
    }
    
    @Override
    public List<Vehiculo> listaVehiculos() throws NonexistentEntityException {
        return cn.listaVehiculos();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Servicio ">
    @Override
    public Servicio agregarServicio(Servicio servicio) throws PreexistingEntityException, Exception {
        return cn.agregarServicio(servicio);
    }
    
    @Override
    public Servicio actualizarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        return cn.actualizarServicio(servicio);
    }
    
    @Override
    public Servicio eliminarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        return cn.eliminarServicio(servicio);
    }
    
    @Override
    public List<Servicio> listaServicios() throws NonexistentEntityException {
        return cn.listaServicios();
    }
    
    @Override
    public List<Servicio> getServicioLike(String nombre) {
        return cn.getServicioLike(nombre);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Tiene ">    
    @Override
    public List<Tiene> getVehiculosPorCliente(Cliente cliente) throws NonexistentEntityException {
        return cn.getVehiculosPorCliente(cliente);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Detalleorden ">    
    @Override
    public List<Detalleorden> getDetalleOrden(Orden orden) throws NonexistentEntityException {
        return cn.getDetalleOrden(orden);
    }
    //</editor-fold>

}
