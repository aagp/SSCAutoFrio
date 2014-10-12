//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)TieneJpaController.java Created on 12/10/2014, 09:12:59 AM
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

import com.ssc.excepciones.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.ssc.objetosnegocio.Vehiculo;
import com.ssc.objetosnegocio.Cliente;
import com.ssc.objetosnegocio.Tiene;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class TieneJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class TieneJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public TieneJpaController() {
        emf = Persistence.createEntityManagerFactory("SSCAutoFrioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiene tiene) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo idVehiculo = tiene.getIdVehiculo();
            if (idVehiculo != null) {
                idVehiculo = em.getReference(idVehiculo.getClass(), idVehiculo.getIdVehiculo());
                tiene.setIdVehiculo(idVehiculo);
            }
            Cliente idCliente = tiene.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                tiene.setIdCliente(idCliente);
            }
            em.persist(tiene);
            if (idVehiculo != null) {
                idVehiculo.getTieneCollection().add(tiene);
                idVehiculo = em.merge(idVehiculo);
            }
            if (idCliente != null) {
                idCliente.getTieneCollection().add(tiene);
                idCliente = em.merge(idCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiene tiene) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiene persistentTiene = em.find(Tiene.class, tiene.getIdTiene());
            Vehiculo idVehiculoOld = persistentTiene.getIdVehiculo();
            Vehiculo idVehiculoNew = tiene.getIdVehiculo();
            Cliente idClienteOld = persistentTiene.getIdCliente();
            Cliente idClienteNew = tiene.getIdCliente();
            if (idVehiculoNew != null) {
                idVehiculoNew = em.getReference(idVehiculoNew.getClass(), idVehiculoNew.getIdVehiculo());
                tiene.setIdVehiculo(idVehiculoNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                tiene.setIdCliente(idClienteNew);
            }
            tiene = em.merge(tiene);
            if (idVehiculoOld != null && !idVehiculoOld.equals(idVehiculoNew)) {
                idVehiculoOld.getTieneCollection().remove(tiene);
                idVehiculoOld = em.merge(idVehiculoOld);
            }
            if (idVehiculoNew != null && !idVehiculoNew.equals(idVehiculoOld)) {
                idVehiculoNew.getTieneCollection().add(tiene);
                idVehiculoNew = em.merge(idVehiculoNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getTieneCollection().remove(tiene);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getTieneCollection().add(tiene);
                idClienteNew = em.merge(idClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiene.getIdTiene();
                if (findTiene(id) == null) {
                    throw new NonexistentEntityException("The tiene with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiene tiene;
            try {
                tiene = em.getReference(Tiene.class, id);
                tiene.getIdTiene();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiene with id " + id + " no longer exists.", enfe);
            }
            Vehiculo idVehiculo = tiene.getIdVehiculo();
            if (idVehiculo != null) {
                idVehiculo.getTieneCollection().remove(tiene);
                idVehiculo = em.merge(idVehiculo);
            }
            Cliente idCliente = tiene.getIdCliente();
            if (idCliente != null) {
                idCliente.getTieneCollection().remove(tiene);
                idCliente = em.merge(idCliente);
            }
            em.remove(tiene);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiene> findTieneEntities() {
        return findTieneEntities(true, -1, -1);
    }

    public List<Tiene> findTieneEntities(int maxResults, int firstResult) {
        return findTieneEntities(false, maxResults, firstResult);
    }

    private List<Tiene> findTieneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tiene as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tiene findTiene(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiene.class, id);
        } finally {
            em.close();
        }
    }

    public int getTieneCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tiene as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Tiene> getVehiculosPorCliente(Cliente idCliente) {
        List results = getEntityManager().createNamedQuery("Tiene.findByCliente")
                .setParameter("idCliente", idCliente)
                .getResultList();
        return results;
    }
}
