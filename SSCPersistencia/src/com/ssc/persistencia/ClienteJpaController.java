//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)ClienteJpaController.java Created on 10/10/2014, 07:54:46 PM
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
import com.ssc.objetosnegocio.Orden;
import com.ssc.objetosnegocio.Vehiculo;
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
 * Class ClienteJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getOrdenCollection() == null) {
            cliente.setOrdenCollection(new ArrayList<Orden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo idVehiculo = cliente.getIdVehiculo();
            if (idVehiculo != null) {
                idVehiculo = em.getReference(idVehiculo.getClass(), idVehiculo.getIdVehiculo());
                cliente.setIdVehiculo(idVehiculo);
            }
            Collection<Orden> attachedOrdenCollection = new ArrayList<Orden>();
            for (Orden ordenCollectionOrdenToAttach : cliente.getOrdenCollection()) {
                ordenCollectionOrdenToAttach = em.getReference(ordenCollectionOrdenToAttach.getClass(), ordenCollectionOrdenToAttach.getIdOrden());
                attachedOrdenCollection.add(ordenCollectionOrdenToAttach);
            }
            cliente.setOrdenCollection(attachedOrdenCollection);
            em.persist(cliente);
            if (idVehiculo != null) {
                idVehiculo.getClienteCollection().add(cliente);
                idVehiculo = em.merge(idVehiculo);
            }
            for (Orden ordenCollectionOrden : cliente.getOrdenCollection()) {
                Cliente oldIdClienteOfOrdenCollectionOrden = ordenCollectionOrden.getIdCliente();
                ordenCollectionOrden.setIdCliente(cliente);
                ordenCollectionOrden = em.merge(ordenCollectionOrden);
                if (oldIdClienteOfOrdenCollectionOrden != null) {
                    oldIdClienteOfOrdenCollectionOrden.getOrdenCollection().remove(ordenCollectionOrden);
                    oldIdClienteOfOrdenCollectionOrden = em.merge(oldIdClienteOfOrdenCollectionOrden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getIdCliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Vehiculo idVehiculoOld = persistentCliente.getIdVehiculo();
            Vehiculo idVehiculoNew = cliente.getIdVehiculo();
            Collection<Orden> ordenCollectionOld = persistentCliente.getOrdenCollection();
            Collection<Orden> ordenCollectionNew = cliente.getOrdenCollection();
            List<String> illegalOrphanMessages = null;
            for (Orden ordenCollectionOldOrden : ordenCollectionOld) {
                if (!ordenCollectionNew.contains(ordenCollectionOldOrden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Orden " + ordenCollectionOldOrden + " since its idCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idVehiculoNew != null) {
                idVehiculoNew = em.getReference(idVehiculoNew.getClass(), idVehiculoNew.getIdVehiculo());
                cliente.setIdVehiculo(idVehiculoNew);
            }
            Collection<Orden> attachedOrdenCollectionNew = new ArrayList<Orden>();
            for (Orden ordenCollectionNewOrdenToAttach : ordenCollectionNew) {
                ordenCollectionNewOrdenToAttach = em.getReference(ordenCollectionNewOrdenToAttach.getClass(), ordenCollectionNewOrdenToAttach.getIdOrden());
                attachedOrdenCollectionNew.add(ordenCollectionNewOrdenToAttach);
            }
            ordenCollectionNew = attachedOrdenCollectionNew;
            cliente.setOrdenCollection(ordenCollectionNew);
            cliente = em.merge(cliente);
            if (idVehiculoOld != null && !idVehiculoOld.equals(idVehiculoNew)) {
                idVehiculoOld.getClienteCollection().remove(cliente);
                idVehiculoOld = em.merge(idVehiculoOld);
            }
            if (idVehiculoNew != null && !idVehiculoNew.equals(idVehiculoOld)) {
                idVehiculoNew.getClienteCollection().add(cliente);
                idVehiculoNew = em.merge(idVehiculoNew);
            }
            for (Orden ordenCollectionNewOrden : ordenCollectionNew) {
                if (!ordenCollectionOld.contains(ordenCollectionNewOrden)) {
                    Cliente oldIdClienteOfOrdenCollectionNewOrden = ordenCollectionNewOrden.getIdCliente();
                    ordenCollectionNewOrden.setIdCliente(cliente);
                    ordenCollectionNewOrden = em.merge(ordenCollectionNewOrden);
                    if (oldIdClienteOfOrdenCollectionNewOrden != null && !oldIdClienteOfOrdenCollectionNewOrden.equals(cliente)) {
                        oldIdClienteOfOrdenCollectionNewOrden.getOrdenCollection().remove(ordenCollectionNewOrden);
                        oldIdClienteOfOrdenCollectionNewOrden = em.merge(oldIdClienteOfOrdenCollectionNewOrden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getIdCliente();
                if (findCliente(id) == null) {
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Orden> ordenCollectionOrphanCheck = cliente.getOrdenCollection();
            for (Orden ordenCollectionOrphanCheckOrden : ordenCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Orden " + ordenCollectionOrphanCheckOrden + " in its ordenCollection field has a non-nullable idCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Vehiculo idVehiculo = cliente.getIdVehiculo();
            if (idVehiculo != null) {
                idVehiculo.getClienteCollection().remove(cliente);
                idVehiculo = em.merge(idVehiculo);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
