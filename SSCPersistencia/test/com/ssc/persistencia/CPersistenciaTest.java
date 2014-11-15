//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)CPersistenciaTest.java Created on 15/11/2014, 02:06:12 PM
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

import com.ssc.objetosnegocio.Cliente;
import com.ssc.objetosnegocio.Detalleorden;
import com.ssc.objetosnegocio.Orden;
import com.ssc.objetosnegocio.Servicio;
import com.ssc.objetosnegocio.Tiene;
import com.ssc.objetosnegocio.Vehiculo;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TestClass CPersistenciaTest
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class CPersistenciaTest {

    public CPersistenciaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of registrarOrden method, of class CPersistencia.
     */
    @Test
    public void testRegistrarOrden() throws Exception {
        System.out.println("registrarOrden");
        Orden orden = null;
        CPersistencia instance = new CPersistencia();
        Orden expResult = null;
        Orden result = instance.registrarOrden(orden);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actulizarOrden method, of class CPersistencia.
     */
    @Test
    public void testActulizarOrden() throws Exception {
        System.out.println("actulizarOrden");
        Orden orden = null;
        CPersistencia instance = new CPersistencia();
        Orden expResult = null;
        Orden result = instance.actulizarOrden(orden);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarOrden method, of class CPersistencia.
     */
    @Test
    public void testEliminarOrden() throws Exception {
        System.out.println("eliminarOrden");
        Orden orden = null;
        CPersistencia instance = new CPersistencia();
        Orden expResult = null;
        Orden result = instance.eliminarOrden(orden);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarOrden method, of class CPersistencia.
     */
    @Test
    public void testBuscarOrden() throws Exception {
        System.out.println("buscarOrden");
        Orden orden = null;
        CPersistencia instance = new CPersistencia();
        Orden expResult = null;
        Orden result = instance.buscarOrden(orden);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaOrdenes method, of class CPersistencia.
     */
    @Test
    public void testListaOrdenes() throws Exception {
        System.out.println("listaOrdenes");
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.listaOrdenes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaEstado method, of class CPersistencia.
     */
    @Test
    public void testListaEstado() {
        System.out.println("listaEstado");
        String estado = "";
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.listaEstado(estado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lastInsert method, of class CPersistencia.
     */
    @Test
    public void testLastInsert() {
        System.out.println("lastInsert");
        CPersistencia instance = new CPersistencia();
        int expResult = 0;
        int result = instance.lastInsert();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarCliente method, of class CPersistencia.
     */
    @Test
    public void testAgregarCliente() throws Exception {
        System.out.println("agregarCliente");
        Cliente cliente = null;
        CPersistencia instance = new CPersistencia();
        Cliente expResult = null;
        Cliente result = instance.agregarCliente(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarCliente method, of class CPersistencia.
     */
    @Test
    public void testActualizarCliente() throws Exception {
        System.out.println("actualizarCliente");
        Cliente cliente = null;
        CPersistencia instance = new CPersistencia();
        Cliente expResult = null;
        Cliente result = instance.actualizarCliente(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarCliente method, of class CPersistencia.
     */
    @Test
    public void testEliminarCliente() throws Exception {
        System.out.println("eliminarCliente");
        Cliente cliente = null;
        CPersistencia instance = new CPersistencia();
        Cliente expResult = null;
        Cliente result = instance.eliminarCliente(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarCliente method, of class CPersistencia.
     */
    @Test
    public void testBuscarCliente() throws Exception {
        System.out.println("buscarCliente");
        String cliente = "";
        CPersistencia instance = new CPersistencia();
        Cliente expResult = null;
        Cliente result = instance.buscarCliente(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaClientes method, of class CPersistencia.
     */
    @Test
    public void testListaClientes() throws Exception {
        System.out.println("listaClientes");
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.listaClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClienteLike method, of class CPersistencia.
     */
    @Test
    public void testGetClienteLike() {
        System.out.println("getClienteLike");
        String nombre = "";
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.getClienteLike(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarVehiculo method, of class CPersistencia.
     */
    @Test
    public void testAgregarVehiculo() throws Exception {
        System.out.println("agregarVehiculo");
        Vehiculo vehiculo = null;
        CPersistencia instance = new CPersistencia();
        Vehiculo expResult = null;
        Vehiculo result = instance.agregarVehiculo(vehiculo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarVehiculo method, of class CPersistencia.
     */
    @Test
    public void testActualizarVehiculo() throws Exception {
        System.out.println("actualizarVehiculo");
        Vehiculo vehiculo = null;
        CPersistencia instance = new CPersistencia();
        Vehiculo expResult = null;
        Vehiculo result = instance.actualizarVehiculo(vehiculo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarVehiculo method, of class CPersistencia.
     */
    @Test
    public void testEliminarVehiculo() throws Exception {
        System.out.println("eliminarVehiculo");
        Vehiculo vehiculo = null;
        CPersistencia instance = new CPersistencia();
        Vehiculo expResult = null;
        Vehiculo result = instance.eliminarVehiculo(vehiculo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarVehiculo method, of class CPersistencia.
     */
    @Test
    public void testBuscarVehiculo() throws Exception {
        System.out.println("buscarVehiculo");
        int vehiculo = 0;
        CPersistencia instance = new CPersistencia();
        Vehiculo expResult = null;
        Vehiculo result = instance.buscarVehiculo(vehiculo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaVehiculos method, of class CPersistencia.
     */
    @Test
    public void testListaVehiculos() throws Exception {
        System.out.println("listaVehiculos");
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.listaVehiculos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVehiculoLike method, of class CPersistencia.
     */
    @Test
    public void testGetVehiculoLike() {
        System.out.println("getVehiculoLike");
        String nombre = "";
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.getVehiculoLike(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarServicio method, of class CPersistencia.
     */
    @Test
    public void testAgregarServicio() throws Exception {
        System.out.println("agregarServicio");
        Servicio servicio = null;
        CPersistencia instance = new CPersistencia();
        Servicio expResult = null;
        Servicio result = instance.agregarServicio(servicio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarServicio method, of class CPersistencia.
     */
    @Test
    public void testActualizarServicio() throws Exception {
        System.out.println("actualizarServicio");
        Servicio servicio = null;
        CPersistencia instance = new CPersistencia();
        Servicio expResult = null;
        Servicio result = instance.actualizarServicio(servicio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarServicio method, of class CPersistencia.
     */
    @Test
    public void testEliminarServicio() throws Exception {
        System.out.println("eliminarServicio");
        Servicio servicio = null;
        CPersistencia instance = new CPersistencia();
        Servicio expResult = null;
        Servicio result = instance.eliminarServicio(servicio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarServicio method, of class CPersistencia.
     */
    @Test
    public void testBuscarServicio() throws Exception {
        System.out.println("buscarServicio");
        int servicio = 0;
        CPersistencia instance = new CPersistencia();
        Servicio expResult = null;
        Servicio result = instance.buscarServicio(servicio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaServicios method, of class CPersistencia.
     */
    @Test
    public void testListaServicios() throws Exception {
        System.out.println("listaServicios");
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.listaServicios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServicioLike method, of class CPersistencia.
     */
    @Test
    public void testGetServicioLike() {
        System.out.println("getServicioLike");
        String nombre = "";
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.getServicioLike(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarTiene method, of class CPersistencia.
     */
    @Test
    public void testAgregarTiene() throws Exception {
        System.out.println("agregarTiene");
        Tiene tiene = null;
        CPersistencia instance = new CPersistencia();
        Tiene expResult = null;
        Tiene result = instance.agregarTiene(tiene);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarTiene method, of class CPersistencia.
     */
    @Test
    public void testEliminarTiene() throws Exception {
        System.out.println("eliminarTiene");
        Tiene tiene = null;
        CPersistencia instance = new CPersistencia();
        Tiene expResult = null;
        Tiene result = instance.eliminarTiene(tiene);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVehiculosPorCliente method, of class CPersistencia.
     */
    @Test
    public void testGetVehiculosPorCliente() throws Exception {
        System.out.println("getVehiculosPorCliente");
        Cliente cliente = null;
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.getVehiculosPorCliente(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetalleOrden method, of class CPersistencia.
     */
    @Test
    public void testGetDetalleOrden() throws Exception {
        System.out.println("getDetalleOrden");
        Orden orden = null;
        CPersistencia instance = new CPersistencia();
        List expResult = null;
        List result = instance.getDetalleOrden(orden);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarDetalle method, of class CPersistencia.
     */
    @Test
    public void testGuardarDetalle() throws Exception {
        System.out.println("guardarDetalle");
        Detalleorden detalleOrden = null;
        CPersistencia instance = new CPersistencia();
        Detalleorden expResult = null;
        Detalleorden result = instance.guardarDetalle(detalleOrden);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}