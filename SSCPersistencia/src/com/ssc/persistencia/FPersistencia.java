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
        cp = new CPersistencia();
    }
    
    //<editor-fold defaultstate="collapsed" desc=" Orden ">
    @Override
    public Orden registrarOrden(Orden orden) throws PreexistingEntityException, Exception {
        return cp.registrarOrden(orden);
    }

    @Override
    public Orden actulizarOrden(Orden orden) throws NonexistentEntityException, Exception {
        return cp.actulizarOrden(orden);
    }

    @Override
    public Orden eliminarOrden(Orden orden) throws NonexistentEntityException, Exception {
        return cp.eliminarOrden(orden);
    }
    
    @Override
    public Orden buscarOrden(Orden orden) throws NonexistentEntityException {
        return cp.buscarOrden(orden);
    }
    
    @Override
    public List<Orden> listaOrdenes() throws NonexistentEntityException {
        return cp.listaOrdenes();
    }
    
    @Override
    public List<Orden> listaEstado(String estado) {
        return cp.listaEstado(estado);
    }
    
    @Override
    public int lastInsert() {
        return cp.lastInsert();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Cliente ">
    @Override
    public Cliente agregarCliente(Cliente cliente) throws PreexistingEntityException, Exception {
        return cp.agregarCliente(cliente);
    }
    
    @Override
    public Cliente actualizarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        return cp.actualizarCliente(cliente);
    }
    
    @Override
    public Cliente eliminarCliente(Cliente cliente) throws NonexistentEntityException, Exception {
        return cp.eliminarCliente(cliente);
    }
    
    @Override
    public Cliente buscarCliente(String cliente) throws NonexistentEntityException {
        return cp.buscarCliente(cliente);
    }

    @Override
    public List<Cliente> listaClientes() throws NonexistentEntityException {
        return cp.listaClientes();
    }
    
    @Override
    public List<Cliente> getClienteLike(String nombre) {
        return cp.getClienteLike(nombre);
    }    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Vehiculo ">
    @Override
    public Vehiculo agregarVehiculo(Vehiculo vehiculo) throws PreexistingEntityException, Exception {
        return cp.agregarVehiculo(vehiculo);
    }
    
    @Override
    public Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        return cp.actualizarVehiculo(vehiculo);
    }
    
    @Override
    public Vehiculo eliminarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
        return cp.eliminarVehiculo(vehiculo);
    }
    
    @Override
    public Vehiculo buscarVehiculo(int vehiculo) throws NonexistentEntityException {
        return cp.buscarVehiculo(vehiculo);
    }
    
    @Override
    public List<Vehiculo> listaVehiculos() throws NonexistentEntityException {
        return cp.listaVehiculos();
    }
    
    @Override
    public List<Vehiculo> getVehiculoLike(String nombre) {
        return cp.getVehiculoLike(nombre);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Servicio ">
    @Override
    public Servicio agregarServicio(Servicio servicio) throws PreexistingEntityException, Exception {
        return cp.agregarServicio(servicio);
    }
    
    @Override
    public Servicio actualizarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        return cp.actualizarServicio(servicio);
    }
    
    @Override
    public Servicio eliminarServicio(Servicio servicio) throws NonexistentEntityException, Exception {
        return cp.eliminarServicio(servicio);
    }
    
    @Override
    public Servicio buscarServicio(int servicio) throws NonexistentEntityException {
        return cp.buscarServicio(servicio);
    }
    
    @Override
    public List<Servicio> listaServicios() throws NonexistentEntityException {
        return cp.listaServicios();
    }
    
    @Override
    public List<Servicio> getServicioLike(String nombre) {
        return cp.getServicioLike(nombre);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Tiene "> 
    @Override
    public Tiene agregarTiene(Tiene tiene) throws PreexistingEntityException, Exception {
        return cp.agregarTiene(tiene);
    }
    
    @Override
    public Tiene eliminarTiene(Tiene tiene) throws NonexistentEntityException, Exception {
        return cp.eliminarTiene(tiene);
    }   
    
    @Override
    public List<Tiene> getVehiculosPorCliente(Cliente cliente) throws NonexistentEntityException {
        return cp.getVehiculosPorCliente(cliente);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Detalleorden ">    
    @Override
    public List<Detalleorden> getDetalleOrden(Orden orden) throws NonexistentEntityException {
        return cp.getDetalleOrden(orden);
    }
    
    @Override
    public Detalleorden guardarDetalle(Detalleorden detalleOrden) throws PreexistingEntityException, Exception {
        return cp.guardarDetalle(detalleOrden);
    }
    //</editor-fold>
    
}
