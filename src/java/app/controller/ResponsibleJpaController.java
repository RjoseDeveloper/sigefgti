/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.NonexistentEntityException;
import app.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.User;
import app.model.Event;
import app.model.Responsible;
import app.model.ResponsiblePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class ResponsibleJpaController implements Serializable {

    public ResponsibleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Responsible responsible) throws PreexistingEntityException, Exception {
        if (responsible.getResponsiblePK() == null) {
            responsible.setResponsiblePK(new ResponsiblePK());
        }
        responsible.getResponsiblePK().setIduser(responsible.getUser().getIduser());
        responsible.getResponsiblePK().setIdevent(responsible.getEvent().getIdevent());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user = responsible.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getIduser());
                responsible.setUser(user);
            }
            Event event = responsible.getEvent();
            if (event != null) {
                event = em.getReference(event.getClass(), event.getIdevent());
                responsible.setEvent(event);
            }
            em.persist(responsible);
            if (user != null) {
                user.getResponsibleList().add(responsible);
                user = em.merge(user);
            }
            if (event != null) {
                event.getResponsibleList().add(responsible);
                event = em.merge(event);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResponsible(responsible.getResponsiblePK()) != null) {
                throw new PreexistingEntityException("Responsible " + responsible + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Responsible responsible) throws NonexistentEntityException, Exception {
        responsible.getResponsiblePK().setIduser(responsible.getUser().getIduser());
        responsible.getResponsiblePK().setIdevent(responsible.getEvent().getIdevent());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsible persistentResponsible = em.find(Responsible.class, responsible.getResponsiblePK());
            User userOld = persistentResponsible.getUser();
            User userNew = responsible.getUser();
            Event eventOld = persistentResponsible.getEvent();
            Event eventNew = responsible.getEvent();
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getIduser());
                responsible.setUser(userNew);
            }
            if (eventNew != null) {
                eventNew = em.getReference(eventNew.getClass(), eventNew.getIdevent());
                responsible.setEvent(eventNew);
            }
            responsible = em.merge(responsible);
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getResponsibleList().remove(responsible);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getResponsibleList().add(responsible);
                userNew = em.merge(userNew);
            }
            if (eventOld != null && !eventOld.equals(eventNew)) {
                eventOld.getResponsibleList().remove(responsible);
                eventOld = em.merge(eventOld);
            }
            if (eventNew != null && !eventNew.equals(eventOld)) {
                eventNew.getResponsibleList().add(responsible);
                eventNew = em.merge(eventNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ResponsiblePK id = responsible.getResponsiblePK();
                if (findResponsible(id) == null) {
                    throw new NonexistentEntityException("The responsible with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ResponsiblePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsible responsible;
            try {
                responsible = em.getReference(Responsible.class, id);
                responsible.getResponsiblePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The responsible with id " + id + " no longer exists.", enfe);
            }
            User user = responsible.getUser();
            if (user != null) {
                user.getResponsibleList().remove(responsible);
                user = em.merge(user);
            }
            Event event = responsible.getEvent();
            if (event != null) {
                event.getResponsibleList().remove(responsible);
                event = em.merge(event);
            }
            em.remove(responsible);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Responsible> findResponsibleEntities() {
        return findResponsibleEntities(true, -1, -1);
    }

    public List<Responsible> findResponsibleEntities(int maxResults, int firstResult) {
        return findResponsibleEntities(false, maxResults, firstResult);
    }

    private List<Responsible> findResponsibleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Responsible.class));
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

    public Responsible findResponsible(ResponsiblePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Responsible.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsibleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Responsible> rt = cq.from(Responsible.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
