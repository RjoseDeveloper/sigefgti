/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.NonexistentEntityException;
import app.model.Activity;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.Event;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class ActivityJpaController implements Serializable {

    public ActivityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Activity activity) {
        if (activity.getEventList() == null) {
            activity.setEventList(new ArrayList<Event>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Event> attachedEventList = new ArrayList<Event>();
            for (Event eventListEventToAttach : activity.getEventList()) {
                eventListEventToAttach = em.getReference(eventListEventToAttach.getClass(), eventListEventToAttach.getIdevent());
                attachedEventList.add(eventListEventToAttach);
            }
            activity.setEventList(attachedEventList);
            em.persist(activity);
            for (Event eventListEvent : activity.getEventList()) {
                Activity oldIdactivityOfEventListEvent = eventListEvent.getIdactivity();
                eventListEvent.setIdactivity(activity);
                eventListEvent = em.merge(eventListEvent);
                if (oldIdactivityOfEventListEvent != null) {
                    oldIdactivityOfEventListEvent.getEventList().remove(eventListEvent);
                    oldIdactivityOfEventListEvent = em.merge(oldIdactivityOfEventListEvent);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Activity activity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Activity persistentActivity = em.find(Activity.class, activity.getIdactivity());
            List<Event> eventListOld = persistentActivity.getEventList();
            List<Event> eventListNew = activity.getEventList();
            List<Event> attachedEventListNew = new ArrayList<Event>();
            for (Event eventListNewEventToAttach : eventListNew) {
                eventListNewEventToAttach = em.getReference(eventListNewEventToAttach.getClass(), eventListNewEventToAttach.getIdevent());
                attachedEventListNew.add(eventListNewEventToAttach);
            }
            eventListNew = attachedEventListNew;
            activity.setEventList(eventListNew);
            activity = em.merge(activity);
            for (Event eventListOldEvent : eventListOld) {
                if (!eventListNew.contains(eventListOldEvent)) {
                    eventListOldEvent.setIdactivity(null);
                    eventListOldEvent = em.merge(eventListOldEvent);
                }
            }
            for (Event eventListNewEvent : eventListNew) {
                if (!eventListOld.contains(eventListNewEvent)) {
                    Activity oldIdactivityOfEventListNewEvent = eventListNewEvent.getIdactivity();
                    eventListNewEvent.setIdactivity(activity);
                    eventListNewEvent = em.merge(eventListNewEvent);
                    if (oldIdactivityOfEventListNewEvent != null && !oldIdactivityOfEventListNewEvent.equals(activity)) {
                        oldIdactivityOfEventListNewEvent.getEventList().remove(eventListNewEvent);
                        oldIdactivityOfEventListNewEvent = em.merge(oldIdactivityOfEventListNewEvent);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = activity.getIdactivity();
                if (findActivity(id) == null) {
                    throw new NonexistentEntityException("The activity with id " + id + " no longer exists.");
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
            Activity activity;
            try {
                activity = em.getReference(Activity.class, id);
                activity.getIdactivity();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The activity with id " + id + " no longer exists.", enfe);
            }
            List<Event> eventList = activity.getEventList();
            for (Event eventListEvent : eventList) {
                eventListEvent.setIdactivity(null);
                eventListEvent = em.merge(eventListEvent);
            }
            em.remove(activity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Activity> findActivityEntities() {
        return findActivityEntities(true, -1, -1);
    }

    public List<Activity> findActivityEntities(int maxResults, int firstResult) {
        return findActivityEntities(false, maxResults, firstResult);
    }

    private List<Activity> findActivityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Activity.class));
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

    public Activity findActivity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Activity.class, id);
        } finally {
            em.close();
        }
    }

    public int getActivityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Activity> rt = cq.from(Activity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
