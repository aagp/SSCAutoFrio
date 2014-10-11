//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)DetalleordenJpaController.java Created on 10/10/2014, 07:54:46 PM
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
import com.ssc.objetosnegocio.Orden;
import com.ssc.objetosnegocio.Servicio;
import com.ssc.excepciones.NonexistentEntityException;
import com.ssc.excepciones.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Class DetalleordenJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class DetalleordenJpaController implements Serializable {

    public DetalleordenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleorden detalleorden) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio idServicio = detalleorden.getIdServicio();
            if (idServicio != null) {
                idServicio = em.getReference(idServicio.getClass(), idServicio.getIdServicio());
                detalleorden.setIdServicio(idServicio);
            }
            Orden idOrden = detalleorden.getIdOrden();
            if (idOrden != null) {
                idOrden = em.getReference(idOrden.getClass(), idOrden.getIdOrden());
                detalleorden.setIdOrden(idOrden);
            }
            em.persist(detalleorden);
            if (idServicio != null) {
                idServicio.getDetalleordenCollection().add(detalleorden);
                idServicio = em.merge(idServicio);
            }
            if (idOrden != null) {
                idOrden.getDetalleordenCollection().add(detalleorden);
                idOrden = em.merge(idOrden);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleorden(detalleorden.getIdDetOrden()) != null) {
                throw new PreexistingEntityException("Detalleorden " + detalleorden + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleorden detalleorden) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleorden persistentDetalleorden = em.find(Detalleorden.class, detalleorden.getIdDetOrden());
            Servicio idServicioOld = persistentDetalleorden.getIdServicio();
            Servicio idServicioNew = detalleorden.getIdServicio();
            Orden idOrdenOld = persistentDetalleorden.getIdOrden();
            Orden idOrdenNew = detalleorden.getIdOrden();
            if (idServicioNew != null) {
                idServicioNew = em.getReference(idServicioNew.getClass(), idServicioNew.getIdServicio());
                detalleorden.setIdServicio(idServicioNew);
            }
            if (idOrdenNew != null) {
                idOrdenNew = em.getReference(idOrdenNew.getClass(), idOrdenNew.getIdOrden());
                detalleorden.setIdOrden(idOrdenNew);
            }
            detalleorden = em.merge(detalleorden);
            if (idServicioOld != null && !idServicioOld.equals(idServicioNew)) {
                idServicioOld.getDetalleordenCollection().remove(detalleorden);
                idServicioOld = em.merge(idServicioOld);
            }
            if (idServicioNew != null && !idServicioNew.equals(idServicioOld)) {
                idServicioNew.getDetalleordenCollection().add(detalleorden);
                idServicioNew = em.merge(idServicioNew);
            }
            if (idOrdenOld != null && !idOrdenOld.equals(idOrdenNew)) {
                idOrdenOld.getDetalleordenCollection().remove(detalleorden);
                idOrdenOld = em.merge(idOrdenOld);
            }
            if (idOrdenNew != null && !idOrdenNew.equals(idOrdenOld)) {
                idOrdenNew.getDetalleordenCollection().add(detalleorden);
                idOrdenNew = em.merge(idOrdenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleorden.getIdDetOrden();
                if (findDetalleorden(id) == null) {
                    throw new NonexistentEntityException("The detalleorden with id " + id + " no longer exists.");
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
            Detalleorden detalleorden;
            try {
                detalleorden = em.getReference(Detalleorden.class, id);
                detalleorden.getIdDetOrden();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleorden with id " + id + " no longer exists.", enfe);
            }
            Servicio idServicio = detalleorden.getIdServicio();
            if (idServicio != null) {
                idServicio.getDetalleordenCollection().remove(detalleorden);
                idServicio = em.merge(idServicio);
            }
            Orden idOrden = detalleorden.getIdOrden();
            if (idOrden != null) {
                idOrden.getDetalleordenCollection().remove(detalleorden);
                idOrden = em.merge(idOrden);
            }
            em.remove(detalleorden);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleorden> findDetalleordenEntities() {
        return findDetalleordenEntities(true, -1, -1);
    }

    public List<Detalleorden> findDetalleordenEntities(int maxResults, int firstResult) {
        return findDetalleordenEntities(false, maxResults, firstResult);
    }

    private List<Detalleorden> findDetalleordenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleorden.class));
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

    public Detalleorden findDetalleorden(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleorden.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleordenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleorden> rt = cq.from(Detalleorden.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
