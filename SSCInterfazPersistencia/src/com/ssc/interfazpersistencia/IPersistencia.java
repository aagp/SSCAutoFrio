//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)IPersistencia.java Created on 11/10/2014, 12:25:58 PM
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
package com.ssc.interfazpersistencia;

import com.ssc.excepciones.*;
import com.ssc.objetosnegocio.*;
import java.util.List;

/**
 *
 * @author Alan García
 */
public interface IPersistencia {
    
    Orden registrarOrden(Orden orden) throws PreexistingEntityException, Exception;
    Orden actulizarOrden(Orden orden) throws NonexistentEntityException, Exception;
    Orden eliminarOrden(Orden orden) throws NonexistentEntityException, Exception;
    Orden buscarOrden(Orden orden) throws NonexistentEntityException;
    List<Orden> listaOrdenes() throws NonexistentEntityException;
    List<Orden> listaEstado(String estado);
    int lastInsert();
    
    Cliente agregarCliente(Cliente cliente) throws PreexistingEntityException, Exception;
    Cliente actualizarCliente(Cliente cliente) throws NonexistentEntityException, Exception;
    Cliente eliminarCliente(Cliente cliente) throws NonexistentEntityException, Exception;
    Cliente buscarCliente(String cliente) throws NonexistentEntityException;
    List<Cliente> listaClientes() throws NonexistentEntityException;
    
    Vehiculo agregarVehiculo(Vehiculo vehiculo) throws PreexistingEntityException, Exception;
    Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception;
    Vehiculo eliminarVehiculo(Vehiculo vehiculo) throws NonexistentEntityException, Exception;
    List<Vehiculo> listaVehiculos() throws NonexistentEntityException;
    
    Servicio agregarServicio(Servicio servicio) throws PreexistingEntityException, Exception;
    Servicio actualizarServicio(Servicio servicio) throws NonexistentEntityException, Exception;
    Servicio eliminarServicio(Servicio servicio) throws NonexistentEntityException, Exception;
    List<Servicio> listaServicios() throws NonexistentEntityException;
    List<Servicio> getServicioLike(String nombre);
    
    List<Tiene> getVehiculosPorCliente(Cliente cliente) throws NonexistentEntityException;
    List<Detalleorden> getDetalleOrden(Orden orden) throws NonexistentEntityException;
    
}
