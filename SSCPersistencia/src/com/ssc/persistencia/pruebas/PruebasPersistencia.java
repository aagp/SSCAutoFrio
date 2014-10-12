//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)PruebasPersistencia.java Created on 11/10/2014, 02:48:12 PM
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
package com.ssc.persistencia.pruebas;

import com.ssc.excepciones.NonexistentEntityException;
import com.ssc.excepciones.PreexistingEntityException;
import com.ssc.interfazpersistencia.IPersistencia;
import com.ssc.objetosnegocio.*;
import com.ssc.persistencia.*;
import java.util.logging.*;

/**
 * Class PruebasPersistencia
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class PruebasPersistencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NonexistentEntityException {
        IPersistencia p;
        p = new FPersistencia();
        OrdenJpaController ojc = new OrdenJpaController();
        TieneJpaController tjc = new TieneJpaController();
        Vehiculo v = new Vehiculo(3);
        Cliente c = new Cliente("6441643157");
        Tiene t = new Tiene(c,v);
        //tjc.create(t);
        for (Object o : p.getVehiculosPorCliente(c)) {
            System.out.println(o);
        }
        Orden orden = new Orden(ojc.lastInsert());
        for(Object o : p.getDetalleOrden(orden)) {
            System.out.println(o);
        }
        
    }
}
