//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)ClienteJpaControllerTest.java Created on 15/11/2014, 01:57:01 PM
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
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TestClass ClienteJpaControllerTest
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class ClienteJpaControllerTest {

    public ClienteJpaControllerTest() {
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
     * Test of getEntityManager method, of class ClienteJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        ClienteJpaController instance = new ClienteJpaController();
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ClienteJpaController.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Cliente cliente = null;
        ClienteJpaController instance = new ClienteJpaController();
        instance.create(cliente);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class ClienteJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Cliente cliente = null;
        ClienteJpaController instance = new ClienteJpaController();
        instance.edit(cliente);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class ClienteJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        String id = "";
        ClienteJpaController instance = new ClienteJpaController();
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findClienteEntities method, of class ClienteJpaController.
     */
    @Test
    public void testFindClienteEntities_0args() {
        System.out.println("findClienteEntities");
        ClienteJpaController instance = new ClienteJpaController();
        List expResult = null;
        List result = instance.findClienteEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findClienteEntities method, of class ClienteJpaController.
     */
    @Test
    public void testFindClienteEntities_int_int() {
        System.out.println("findClienteEntities");
        int maxResults = 0;
        int firstResult = 0;
        ClienteJpaController instance = new ClienteJpaController();
        List expResult = null;
        List result = instance.findClienteEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findCliente method, of class ClienteJpaController.
     */
    @Test
    public void testFindCliente() {
        System.out.println("findCliente");
        String id = "";
        ClienteJpaController instance = new ClienteJpaController();
        Cliente expResult = null;
        Cliente result = instance.findCliente(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getClienteCount method, of class ClienteJpaController.
     */
    @Test
    public void testGetClienteCount() {
        System.out.println("getClienteCount");
        ClienteJpaController instance = new ClienteJpaController();
        int expResult = 0;
        int result = instance.getClienteCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getClienteLike method, of class ClienteJpaController.
     */
    @Test
    public void testGetClienteLike() {
        System.out.println("getClienteLike");
        String nombre = "";
        ClienteJpaController instance = new ClienteJpaController();
        List expResult = null;
        List result = instance.getClienteLike(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}