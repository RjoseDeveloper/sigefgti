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
import app.model.Participant;
import app.model.ParticipantPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class ParticipantJpaController implements Serializable {

    public ParticipantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Participant participant) throws PreexistingEntityException, Exception {
        if (participant.getParticipantPK() == null) {
            participant.setParticipantPK(new ParticipantPK());
        }
        participant.getParticipantPK().setIdevents(participant.getEvent().getIdevent());
        participant.getParticipantPK().setIduser(participant.getUser().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user = participant.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getIduser());
                participant.setUser(user);
            }
            Event event = participant.getEvent();
            if (event != null) {
                event = em.getReference(event.getClass(), event.getIdevent());
                participant.setEvent(event);
            }
            em.persist(participant);
            if (user != null) {
                user.getParticipantList().add(participant);
                user = em.merge(user);
            }
            if (event != null) {
                event.getParticipantList().add(participant);
                event = em.merge(event);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findParticipant(participant.getParticipantPK()) != null) {
                throw new PreexistingEntityException("Participant " + participant + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Participant participant) throws NonexistentEntityException, Exception {
        participant.getParticipantPK().setIdevents(participant.getEvent().getIdevent());
        participant.getParticipantPK().setIduser(participant.getUser().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Participant persistentParticipant = em.find(Participant.class, participant.getParticipantPK());
            User userOld = persistentParticipant.getUser();
            User userNew = participant.getUser();
            Event eventOld = persistentParticipant.getEvent();
            Event eventNew = participant.getEvent();
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getIduser());
                participant.setUser(userNew);
            }
            if (eventNew != null) {
                eventNew = em.getReference(eventNew.getClass(), eventNew.getIdevent());
                participant.setEvent(eventNew);
            }
            participant = em.merge(participant);
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getParticipantList().remove(participant);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getParticipantList().add(participant);
                userNew = em.merge(userNew);
            }
            if (eventOld != null && !eventOld.equals(eventNew)) {
                eventOld.getParticipantList().remove(participant);
                eventOld = em.merge(eventOld);
            }
            if (eventNew != null && !eventNew.equals(eventOld)) {
                eventNew.getParticipantList().add(participant);
                eventNew = em.merge(eventNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ParticipantPK id = participant.getParticipantPK();
                if (findParticipant(id) == null) {
                    throw new NonexistentEntityException("The participant with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ParticipantPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Participant participant;
            try {
                participant = em.getReference(Participant.class, id);
                participant.getParticipantPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The participant with id " + id + " no longer exists.", enfe);
            }
            User user = participant.getUser();
            if (user != null) {
                user.getParticipantList().remove(participant);
                user = em.merge(user);
            }
            Event event = participant.getEvent();
            if (event != null) {
                event.getParticipantList().remove(participant);
                event = em.merge(event);
            }
            em.remove(participant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Participant> findParticipantEntities() {
        return findParticipantEntities(true, -1, -1);
    }

    public List<Participant> findParticipantEntities(int maxResults, int firstResult) {
        return findParticipantEntities(false, maxResults, firstResult);
    }

    private List<Participant> findParticipantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Participant.class));
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

    public Participant findParticipant(ParticipantPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Participant.class, id);
        } finally {
            em.close();
        }
    }

    public int getParticipantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Participant> rt = cq.from(Participant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
