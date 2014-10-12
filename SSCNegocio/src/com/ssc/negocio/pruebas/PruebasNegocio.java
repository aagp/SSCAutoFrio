//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)PruebasNegocio.java Created on 12/10/2014, 11:42:42 AM
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

package com.ssc.negocio.pruebas;

import com.ssc.excepciones.NonexistentEntityException;
import com.ssc.excepciones.PreexistingEntityException;
import com.ssc.interfaznegocio.INegocio;
import com.ssc.negocio.FNegocio;
import com.ssc.objetosnegocio.Orden;
import com.ssc.objetosnegocio.Servicio;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class PruebasNegocio
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class PruebasNegocio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NonexistentEntityException {
        INegocio ne = new FNegocio();
        
        Orden orden = new Orden(1);
        for(Object o : ne.getDetalleOrden(orden)) {
            System.out.println(o);
        }
        
    }

}
