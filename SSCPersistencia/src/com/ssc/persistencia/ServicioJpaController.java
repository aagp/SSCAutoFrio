//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)ServicioJpaController.java Created on 10/10/2014, 07:54:46 PM
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

import com.ssc.objetosnegocio.Detalleorden;
import com.ssc.objetosnegocio.Servicio;
import com.ssc.excepciones.IllegalOrphanException;
import com.ssc.excepciones.NonexistentEntityException;
import com.ssc.excepciones.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Class ServicioJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) throws PreexistingEntityException, Exception {
        if (servicio.getDetalleordenCollection() == null) {
            servicio.setDetalleordenCollection(new ArrayList<Detalleorden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Detalleorden> attachedDetalleordenCollection = new ArrayList<Detalleorden>();
            for (Detalleorden detalleordenCollectionDetalleordenToAttach : servicio.getDetalleordenCollection()) {
                detalleordenCollectionDetalleordenToAttach = em.getReference(detalleordenCollectionDetalleordenToAttach.getClass(), detalleordenCollectionDetalleordenToAttach.getIdDetOrden());
                attachedDetalleordenCollection.add(detalleordenCollectionDetalleordenToAttach);
            }
            servicio.setDetalleordenCollection(attachedDetalleordenCollection);
            em.persist(servicio);
            for (Detalleorden detalleordenCollectionDetalleorden : servicio.getDetalleordenCollection()) {
                Servicio oldIdServicioOfDetalleordenCollectionDetalleorden = detalleordenCollectionDetalleorden.getIdServicio();
                detalleordenCollectionDetalleorden.setIdServicio(servicio);
                detalleordenCollectionDetalleorden = em.merge(detalleordenCollectionDetalleorden);
                if (oldIdServicioOfDetalleordenCollectionDetalleorden != null) {
                    oldIdServicioOfDetalleordenCollectionDetalleorden.getDetalleordenCollection().remove(detalleordenCollectionDetalleorden);
                    oldIdServicioOfDetalleordenCollectionDetalleorden = em.merge(oldIdServicioOfDetalleordenCollectionDetalleorden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicio(servicio.getIdServicio()) != null) {
                throw new PreexistingEntityException("Servicio " + servicio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getIdServicio());
            Collection<Detalleorden> detalleordenCollectionOld = persistentServicio.getDetalleordenCollection();
            Collection<Detalleorden> detalleordenCollectionNew = servicio.getDetalleordenCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalleorden detalleordenCollectionOldDetalleorden : detalleordenCollectionOld) {
                if (!detalleordenCollectionNew.contains(detalleordenCollectionOldDetalleorden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleorden " + detalleordenCollectionOldDetalleorden + " since its idServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Detalleorden> attachedDetalleordenCollectionNew = new ArrayList<Detalleorden>();
            for (Detalleorden detalleordenCollectionNewDetalleordenToAttach : detalleordenCollectionNew) {
                detalleordenCollectionNewDetalleordenToAttach = em.getReference(detalleordenCollectionNewDetalleordenToAttach.getClass(), detalleordenCollectionNewDetalleordenToAttach.getIdDetOrden());
                attachedDetalleordenCollectionNew.add(detalleordenCollectionNewDetalleordenToAttach);
            }
            detalleordenCollectionNew = attachedDetalleordenCollectionNew;
            servicio.setDetalleordenCollection(detalleordenCollectionNew);
            servicio = em.merge(servicio);
            for (Detalleorden detalleordenCollectionNewDetalleorden : detalleordenCollectionNew) {
                if (!detalleordenCollectionOld.contains(detalleordenCollectionNewDetalleorden)) {
                    Servicio oldIdServicioOfDetalleordenCollectionNewDetalleorden = detalleordenCollectionNewDetalleorden.getIdServicio();
                    detalleordenCollectionNewDetalleorden.setIdServicio(servicio);
                    detalleordenCollectionNewDetalleorden = em.merge(detalleordenCollectionNewDetalleorden);
                    if (oldIdServicioOfDetalleordenCollectionNewDetalleorden != null && !oldIdServicioOfDetalleordenCollectionNewDetalleorden.equals(servicio)) {
                        oldIdServicioOfDetalleordenCollectionNewDetalleorden.getDetalleordenCollection().remove(detalleordenCollectionNewDetalleorden);
                        oldIdServicioOfDetalleordenCollectionNewDetalleorden = em.merge(oldIdServicioOfDetalleordenCollectionNewDetalleorden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicio.getIdServicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getIdServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalleorden> detalleordenCollectionOrphanCheck = servicio.getDetalleordenCollection();
            for (Detalleorden detalleordenCollectionOrphanCheckDetalleorden : detalleordenCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicio (" + servicio + ") cannot be destroyed since the Detalleorden " + detalleordenCollectionOrphanCheckDetalleorden + " in its detalleordenCollection field has a non-nullable idServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Servicio findServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
