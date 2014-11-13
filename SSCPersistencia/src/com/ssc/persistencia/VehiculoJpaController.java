//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)VehiculoJpaController.java Created on 12/10/2014, 09:12:59 AM
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
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.ssc.objetosnegocio.Tiene;
import com.ssc.objetosnegocio.Vehiculo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class VehiculoJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class VehiculoJpaController implements Serializable {

    private EntityManagerFactory emf = null;
    
    public VehiculoJpaController() {
        emf = Persistence.createEntityManagerFactory("SSCAutoFrioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vehiculo vehiculo) {
        if (vehiculo.getTieneCollection() == null) {
            vehiculo.setTieneCollection(new ArrayList<Tiene>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tiene> attachedTieneCollection = new ArrayList<Tiene>();
            for (Tiene tieneCollectionTieneToAttach : vehiculo.getTieneCollection()) {
                tieneCollectionTieneToAttach = em.getReference(tieneCollectionTieneToAttach.getClass(), tieneCollectionTieneToAttach.getIdTiene());
                attachedTieneCollection.add(tieneCollectionTieneToAttach);
            }
            vehiculo.setTieneCollection(attachedTieneCollection);
            em.persist(vehiculo);
            for (Tiene tieneCollectionTiene : vehiculo.getTieneCollection()) {
                Vehiculo oldIdVehiculoOfTieneCollectionTiene = tieneCollectionTiene.getIdVehiculo();
                tieneCollectionTiene.setIdVehiculo(vehiculo);
                tieneCollectionTiene = em.merge(tieneCollectionTiene);
                if (oldIdVehiculoOfTieneCollectionTiene != null) {
                    oldIdVehiculoOfTieneCollectionTiene.getTieneCollection().remove(tieneCollectionTiene);
                    oldIdVehiculoOfTieneCollectionTiene = em.merge(oldIdVehiculoOfTieneCollectionTiene);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vehiculo vehiculo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vehiculo = em.merge(vehiculo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vehiculo.getIdVehiculo();
                if (findVehiculo(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo vehiculo;
            try {
                vehiculo = em.getReference(Vehiculo.class, id);
                vehiculo.getIdVehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tiene> tieneCollectionOrphanCheck = vehiculo.getTieneCollection();
            for (Tiene tieneCollectionOrphanCheckTiene : tieneCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehiculo (" + vehiculo + ") cannot be destroyed since the Tiene " + tieneCollectionOrphanCheckTiene + " in its tieneCollection field has a non-nullable idVehiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vehiculo> findVehiculoEntities() {
        return findVehiculoEntities(true, -1, -1);
    }

    public List<Vehiculo> findVehiculoEntities(int maxResults, int firstResult) {
        return findVehiculoEntities(false, maxResults, firstResult);
    }

    private List<Vehiculo> findVehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Vehiculo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vehiculo findVehiculo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Vehiculo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Vehiculo> getVehiculoLike(String modelo) {
        String queryString = "SELECT v FROM Vehiculo v WHERE LOWER(v.modelo) LIKE :modelo";
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("modelo", modelo.toLowerCase() + '%');
        return query.getResultList();
    }    
    
}
