//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)OrdenJpaController.java Created on 12/10/2014, 09:12:58 AM
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
import com.ssc.objetosnegocio.Cliente;
import com.ssc.objetosnegocio.Detalleorden;
import com.ssc.objetosnegocio.Orden;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class OrdenJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class OrdenJpaController implements Serializable {

    private EntityManagerFactory emf = null;
    
    public OrdenJpaController() {
        emf = Persistence.createEntityManagerFactory("SSCAutoFrioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orden orden) {
        if (orden.getDetalleordenCollection() == null) {
            orden.setDetalleordenCollection(new ArrayList<Detalleorden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = orden.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                orden.setIdCliente(idCliente);
            }
            Collection<Detalleorden> attachedDetalleordenCollection = new ArrayList<Detalleorden>();
            for (Detalleorden detalleordenCollectionDetalleordenToAttach : orden.getDetalleordenCollection()) {
                detalleordenCollectionDetalleordenToAttach = em.getReference(detalleordenCollectionDetalleordenToAttach.getClass(), detalleordenCollectionDetalleordenToAttach.getIdDetalle());
                attachedDetalleordenCollection.add(detalleordenCollectionDetalleordenToAttach);
            }
            orden.setDetalleordenCollection(attachedDetalleordenCollection);
            em.persist(orden);
            if (idCliente != null) {
                idCliente.getOrdenCollection().add(orden);
                idCliente = em.merge(idCliente);
            }
            for (Detalleorden detalleordenCollectionDetalleorden : orden.getDetalleordenCollection()) {
                Orden oldIdOrdenOfDetalleordenCollectionDetalleorden = detalleordenCollectionDetalleorden.getIdOrden();
                detalleordenCollectionDetalleorden.setIdOrden(orden);
                detalleordenCollectionDetalleorden = em.merge(detalleordenCollectionDetalleorden);
                if (oldIdOrdenOfDetalleordenCollectionDetalleorden != null) {
                    oldIdOrdenOfDetalleordenCollectionDetalleorden.getDetalleordenCollection().remove(detalleordenCollectionDetalleorden);
                    oldIdOrdenOfDetalleordenCollectionDetalleorden = em.merge(oldIdOrdenOfDetalleordenCollectionDetalleorden);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orden orden) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orden persistentOrden = em.find(Orden.class, orden.getIdOrden());
            Cliente idClienteOld = persistentOrden.getIdCliente();
            Cliente idClienteNew = orden.getIdCliente();
            Collection<Detalleorden> detalleordenCollectionOld = persistentOrden.getDetalleordenCollection();
            Collection<Detalleorden> detalleordenCollectionNew = orden.getDetalleordenCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalleorden detalleordenCollectionOldDetalleorden : detalleordenCollectionOld) {
                if (!detalleordenCollectionNew.contains(detalleordenCollectionOldDetalleorden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleorden " + detalleordenCollectionOldDetalleorden + " since its idOrden field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                orden.setIdCliente(idClienteNew);
            }
            Collection<Detalleorden> attachedDetalleordenCollectionNew = new ArrayList<Detalleorden>();
            for (Detalleorden detalleordenCollectionNewDetalleordenToAttach : detalleordenCollectionNew) {
                detalleordenCollectionNewDetalleordenToAttach = em.getReference(detalleordenCollectionNewDetalleordenToAttach.getClass(), detalleordenCollectionNewDetalleordenToAttach.getIdDetalle());
                attachedDetalleordenCollectionNew.add(detalleordenCollectionNewDetalleordenToAttach);
            }
            detalleordenCollectionNew = attachedDetalleordenCollectionNew;
            orden.setDetalleordenCollection(detalleordenCollectionNew);
            orden = em.merge(orden);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getOrdenCollection().remove(orden);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getOrdenCollection().add(orden);
                idClienteNew = em.merge(idClienteNew);
            }
            for (Detalleorden detalleordenCollectionNewDetalleorden : detalleordenCollectionNew) {
                if (!detalleordenCollectionOld.contains(detalleordenCollectionNewDetalleorden)) {
                    Orden oldIdOrdenOfDetalleordenCollectionNewDetalleorden = detalleordenCollectionNewDetalleorden.getIdOrden();
                    detalleordenCollectionNewDetalleorden.setIdOrden(orden);
                    detalleordenCollectionNewDetalleorden = em.merge(detalleordenCollectionNewDetalleorden);
                    if (oldIdOrdenOfDetalleordenCollectionNewDetalleorden != null && !oldIdOrdenOfDetalleordenCollectionNewDetalleorden.equals(orden)) {
                        oldIdOrdenOfDetalleordenCollectionNewDetalleorden.getDetalleordenCollection().remove(detalleordenCollectionNewDetalleorden);
                        oldIdOrdenOfDetalleordenCollectionNewDetalleorden = em.merge(oldIdOrdenOfDetalleordenCollectionNewDetalleorden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orden.getIdOrden();
                if (findOrden(id) == null) {
                    throw new NonexistentEntityException("The orden with id " + id + " no longer exists.");
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
            Orden orden;
            try {
                orden = em.getReference(Orden.class, id);
                orden.getIdOrden();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orden with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalleorden> detalleordenCollectionOrphanCheck = orden.getDetalleordenCollection();
            for (Detalleorden detalleordenCollectionOrphanCheckDetalleorden : detalleordenCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Orden (" + orden + ") cannot be destroyed since the Detalleorden " + detalleordenCollectionOrphanCheckDetalleorden + " in its detalleordenCollection field has a non-nullable idOrden field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idCliente = orden.getIdCliente();
            if (idCliente != null) {
                idCliente.getOrdenCollection().remove(orden);
                idCliente = em.merge(idCliente);
            }
            em.remove(orden);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orden> findOrdenEntities() {
        return findOrdenEntities(true, -1, -1);
    }

    public List<Orden> findOrdenEntities(int maxResults, int firstResult) {
        return findOrdenEntities(false, maxResults, firstResult);
    }

    private List<Orden> findOrdenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Orden as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Orden findOrden(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orden.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Orden as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public int lastInsert() {
        String queryString = "select MAX(idOrden) from orden";
        Query query = getEntityManager().createNativeQuery(queryString);
        return ((Integer) query.getSingleResult()).intValue();
    } 
}
