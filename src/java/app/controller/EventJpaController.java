/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.IllegalOrphanException;
import app.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.Activity;
import app.model.Event;
import app.model.Responsible;
import java.util.ArrayList;
import java.util.List;
import app.model.Participant;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class EventJpaController implements Serializable {

    public EventJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Event event) {
        if (event.getResponsibleList() == null) {
            event.setResponsibleList(new ArrayList<Responsible>());
        }
        if (event.getParticipantList() == null) {
            event.setParticipantList(new ArrayList<Participant>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Activity idactivity = event.getIdactivity();
            if (idactivity != null) {
                idactivity = em.getReference(idactivity.getClass(), idactivity.getIdactivity());
                event.setIdactivity(idactivity);
            }
            List<Responsible> attachedResponsibleList = new ArrayList<Responsible>();
            for (Responsible responsibleListResponsibleToAttach : event.getResponsibleList()) {
                responsibleListResponsibleToAttach = em.getReference(responsibleListResponsibleToAttach.getClass(), responsibleListResponsibleToAttach.getResponsiblePK());
                attachedResponsibleList.add(responsibleListResponsibleToAttach);
            }
            event.setResponsibleList(attachedResponsibleList);
            List<Participant> attachedParticipantList = new ArrayList<Participant>();
            for (Participant participantListParticipantToAttach : event.getParticipantList()) {
                participantListParticipantToAttach = em.getReference(participantListParticipantToAttach.getClass(), participantListParticipantToAttach.getParticipantPK());
                attachedParticipantList.add(participantListParticipantToAttach);
            }
            event.setParticipantList(attachedParticipantList);
            em.persist(event);
            if (idactivity != null) {
                idactivity.getEventList().add(event);
                idactivity = em.merge(idactivity);
            }
            for (Responsible responsibleListResponsible : event.getResponsibleList()) {
                Event oldEventOfResponsibleListResponsible = responsibleListResponsible.getEvent();
                responsibleListResponsible.setEvent(event);
                responsibleListResponsible = em.merge(responsibleListResponsible);
                if (oldEventOfResponsibleListResponsible != null) {
                    oldEventOfResponsibleListResponsible.getResponsibleList().remove(responsibleListResponsible);
                    oldEventOfResponsibleListResponsible = em.merge(oldEventOfResponsibleListResponsible);
                }
            }
            for (Participant participantListParticipant : event.getParticipantList()) {
                Event oldEventOfParticipantListParticipant = participantListParticipant.getEvent();
                participantListParticipant.setEvent(event);
                participantListParticipant = em.merge(participantListParticipant);
                if (oldEventOfParticipantListParticipant != null) {
                    oldEventOfParticipantListParticipant.getParticipantList().remove(participantListParticipant);
                    oldEventOfParticipantListParticipant = em.merge(oldEventOfParticipantListParticipant);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Event event) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Event persistentEvent = em.find(Event.class, event.getIdevent());
            Activity idactivityOld = persistentEvent.getIdactivity();
            Activity idactivityNew = event.getIdactivity();
            List<Responsible> responsibleListOld = persistentEvent.getResponsibleList();
            List<Responsible> responsibleListNew = event.getResponsibleList();
            List<Participant> participantListOld = persistentEvent.getParticipantList();
            List<Participant> participantListNew = event.getParticipantList();
            List<String> illegalOrphanMessages = null;
            for (Responsible responsibleListOldResponsible : responsibleListOld) {
                if (!responsibleListNew.contains(responsibleListOldResponsible)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Responsible " + responsibleListOldResponsible + " since its event field is not nullable.");
                }
            }
            for (Participant participantListOldParticipant : participantListOld) {
                if (!participantListNew.contains(participantListOldParticipant)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Participant " + participantListOldParticipant + " since its event field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idactivityNew != null) {
                idactivityNew = em.getReference(idactivityNew.getClass(), idactivityNew.getIdactivity());
                event.setIdactivity(idactivityNew);
            }
            List<Responsible> attachedResponsibleListNew = new ArrayList<Responsible>();
            for (Responsible responsibleListNewResponsibleToAttach : responsibleListNew) {
                responsibleListNewResponsibleToAttach = em.getReference(responsibleListNewResponsibleToAttach.getClass(), responsibleListNewResponsibleToAttach.getResponsiblePK());
                attachedResponsibleListNew.add(responsibleListNewResponsibleToAttach);
            }
            responsibleListNew = attachedResponsibleListNew;
            event.setResponsibleList(responsibleListNew);
            List<Participant> attachedParticipantListNew = new ArrayList<Participant>();
            for (Participant participantListNewParticipantToAttach : participantListNew) {
                participantListNewParticipantToAttach = em.getReference(participantListNewParticipantToAttach.getClass(), participantListNewParticipantToAttach.getParticipantPK());
                attachedParticipantListNew.add(participantListNewParticipantToAttach);
            }
            participantListNew = attachedParticipantListNew;
            event.setParticipantList(participantListNew);
            event = em.merge(event);
            if (idactivityOld != null && !idactivityOld.equals(idactivityNew)) {
                idactivityOld.getEventList().remove(event);
                idactivityOld = em.merge(idactivityOld);
            }
            if (idactivityNew != null && !idactivityNew.equals(idactivityOld)) {
                idactivityNew.getEventList().add(event);
                idactivityNew = em.merge(idactivityNew);
            }
            for (Responsible responsibleListNewResponsible : responsibleListNew) {
                if (!responsibleListOld.contains(responsibleListNewResponsible)) {
                    Event oldEventOfResponsibleListNewResponsible = responsibleListNewResponsible.getEvent();
                    responsibleListNewResponsible.setEvent(event);
                    responsibleListNewResponsible = em.merge(responsibleListNewResponsible);
                    if (oldEventOfResponsibleListNewResponsible != null && !oldEventOfResponsibleListNewResponsible.equals(event)) {
                        oldEventOfResponsibleListNewResponsible.getResponsibleList().remove(responsibleListNewResponsible);
                        oldEventOfResponsibleListNewResponsible = em.merge(oldEventOfResponsibleListNewResponsible);
                    }
                }
            }
            for (Participant participantListNewParticipant : participantListNew) {
                if (!participantListOld.contains(participantListNewParticipant)) {
                    Event oldEventOfParticipantListNewParticipant = participantListNewParticipant.getEvent();
                    participantListNewParticipant.setEvent(event);
                    participantListNewParticipant = em.merge(participantListNewParticipant);
                    if (oldEventOfParticipantListNewParticipant != null && !oldEventOfParticipantListNewParticipant.equals(event)) {
                        oldEventOfParticipantListNewParticipant.getParticipantList().remove(participantListNewParticipant);
                        oldEventOfParticipantListNewParticipant = em.merge(oldEventOfParticipantListNewParticipant);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = event.getIdevent();
                if (findEvent(id) == null) {
                    throw new NonexistentEntityException("The event with id " + id + " no longer exists.");
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
            Event event;
            try {
                event = em.getReference(Event.class, id);
                event.getIdevent();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The event with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Responsible> responsibleListOrphanCheck = event.getResponsibleList();
            for (Responsible responsibleListOrphanCheckResponsible : responsibleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Event (" + event + ") cannot be destroyed since the Responsible " + responsibleListOrphanCheckResponsible + " in its responsibleList field has a non-nullable event field.");
            }
            List<Participant> participantListOrphanCheck = event.getParticipantList();
            for (Participant participantListOrphanCheckParticipant : participantListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Event (" + event + ") cannot be destroyed since the Participant " + participantListOrphanCheckParticipant + " in its participantList field has a non-nullable event field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Activity idactivity = event.getIdactivity();
            if (idactivity != null) {
                idactivity.getEventList().remove(event);
                idactivity = em.merge(idactivity);
            }
            em.remove(event);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Event> findEventEntities() {
        return findEventEntities(true, -1, -1);
    }

    public List<Event> findEventEntities(int maxResults, int firstResult) {
        return findEventEntities(false, maxResults, firstResult);
    }

    private List<Event> findEventEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Event.class));
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

    public Event findEvent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Event.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Event> rt = cq.from(Event.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
