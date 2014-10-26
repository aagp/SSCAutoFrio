//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)ClienteJpaController.java Created on 12/10/2014, 09:12:58 AM
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
import com.ssc.objetosnegocio.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.ssc.objetosnegocio.Orden;
import java.util.ArrayList;
import java.util.Collection;
import com.ssc.objetosnegocio.Tiene;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class ClienteJpaController
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class ClienteJpaController implements Serializable {

    private EntityManagerFactory emf = null;
    
    public ClienteJpaController() {
        emf = Persistence.createEntityManagerFactory("SSCAutoFrioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getOrdenCollection() == null) {
            cliente.setOrdenCollection(new ArrayList<Orden>());
        }
        if (cliente.getTieneCollection() == null) {
            cliente.setTieneCollection(new ArrayList<Tiene>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Orden> attachedOrdenCollection = new ArrayList<Orden>();
            for (Orden ordenCollectionOrdenToAttach : cliente.getOrdenCollection()) {
                ordenCollectionOrdenToAttach = em.getReference(ordenCollectionOrdenToAttach.getClass(), ordenCollectionOrdenToAttach.getIdOrden());
                attachedOrdenCollection.add(ordenCollectionOrdenToAttach);
            }
            cliente.setOrdenCollection(attachedOrdenCollection);
            Collection<Tiene> attachedTieneCollection = new ArrayList<Tiene>();
            for (Tiene tieneCollectionTieneToAttach : cliente.getTieneCollection()) {
                tieneCollectionTieneToAttach = em.getReference(tieneCollectionTieneToAttach.getClass(), tieneCollectionTieneToAttach.getIdTiene());
                attachedTieneCollection.add(tieneCollectionTieneToAttach);
            }
            cliente.setTieneCollection(attachedTieneCollection);
            em.persist(cliente);
            for (Orden ordenCollectionOrden : cliente.getOrdenCollection()) {
                Cliente oldIdClienteOfOrdenCollectionOrden = ordenCollectionOrden.getIdCliente();
                ordenCollectionOrden.setIdCliente(cliente);
                ordenCollectionOrden = em.merge(ordenCollectionOrden);
                if (oldIdClienteOfOrdenCollectionOrden != null) {
                    oldIdClienteOfOrdenCollectionOrden.getOrdenCollection().remove(ordenCollectionOrden);
                    oldIdClienteOfOrdenCollectionOrden = em.merge(oldIdClienteOfOrdenCollectionOrden);
                }
            }
            for (Tiene tieneCollectionTiene : cliente.getTieneCollection()) {
                Cliente oldIdClienteOfTieneCollectionTiene = tieneCollectionTiene.getIdCliente();
                tieneCollectionTiene.setIdCliente(cliente);
                tieneCollectionTiene = em.merge(tieneCollectionTiene);
                if (oldIdClienteOfTieneCollectionTiene != null) {
                    oldIdClienteOfTieneCollectionTiene.getTieneCollection().remove(tieneCollectionTiene);
                    oldIdClienteOfTieneCollectionTiene = em.merge(oldIdClienteOfTieneCollectionTiene);
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
            Collection<Orden> ordenCollectionOld = persistentCliente.getOrdenCollection();
            Collection<Orden> ordenCollectionNew = cliente.getOrdenCollection();
            Collection<Tiene> tieneCollectionOld = persistentCliente.getTieneCollection();
            Collection<Tiene> tieneCollectionNew = cliente.getTieneCollection();
            List<String> illegalOrphanMessages = null;
            for (Orden ordenCollectionOldOrden : ordenCollectionOld) {
                if (!ordenCollectionNew.contains(ordenCollectionOldOrden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Orden " + ordenCollectionOldOrden + " since its idCliente field is not nullable.");
                }
            }
            for (Tiene tieneCollectionOldTiene : tieneCollectionOld) {
                if (!tieneCollectionNew.contains(tieneCollectionOldTiene)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tiene " + tieneCollectionOldTiene + " since its idCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Orden> attachedOrdenCollectionNew = new ArrayList<Orden>();
            for (Orden ordenCollectionNewOrdenToAttach : ordenCollectionNew) {
                ordenCollectionNewOrdenToAttach = em.getReference(ordenCollectionNewOrdenToAttach.getClass(), ordenCollectionNewOrdenToAttach.getIdOrden());
                attachedOrdenCollectionNew.add(ordenCollectionNewOrdenToAttach);
            }
            ordenCollectionNew = attachedOrdenCollectionNew;
            cliente.setOrdenCollection(ordenCollectionNew);
            Collection<Tiene> attachedTieneCollectionNew = new ArrayList<Tiene>();
            for (Tiene tieneCollectionNewTieneToAttach : tieneCollectionNew) {
                tieneCollectionNewTieneToAttach = em.getReference(tieneCollectionNewTieneToAttach.getClass(), tieneCollectionNewTieneToAttach.getIdTiene());
                attachedTieneCollectionNew.add(tieneCollectionNewTieneToAttach);
            }
            tieneCollectionNew = attachedTieneCollectionNew;
            cliente.setTieneCollection(tieneCollectionNew);
            cliente = em.merge(cliente);
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
            for (Tiene tieneCollectionNewTiene : tieneCollectionNew) {
                if (!tieneCollectionOld.contains(tieneCollectionNewTiene)) {
                    Cliente oldIdClienteOfTieneCollectionNewTiene = tieneCollectionNewTiene.getIdCliente();
                    tieneCollectionNewTiene.setIdCliente(cliente);
                    tieneCollectionNewTiene = em.merge(tieneCollectionNewTiene);
                    if (oldIdClienteOfTieneCollectionNewTiene != null && !oldIdClienteOfTieneCollectionNewTiene.equals(cliente)) {
                        oldIdClienteOfTieneCollectionNewTiene.getTieneCollection().remove(tieneCollectionNewTiene);
                        oldIdClienteOfTieneCollectionNewTiene = em.merge(oldIdClienteOfTieneCollectionNewTiene);
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
            Collection<Tiene> tieneCollectionOrphanCheck = cliente.getTieneCollection();
            for (Tiene tieneCollectionOrphanCheckTiene : tieneCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Tiene " + tieneCollectionOrphanCheckTiene + " in its tieneCollection field has a non-nullable idCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
            Query q = em.createQuery("select object(o) from Cliente as o");
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
            Query q = em.createQuery("select count(o) from Cliente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Cliente> getClienteLike(String nombre) {
        String queryString = "SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE :nombre";
        Query query = getEntityManager().createQuery(queryString);

        query.setParameter("nombre", nombre.toLowerCase() + '%');
        return query.getResultList();
    }    
    
}
