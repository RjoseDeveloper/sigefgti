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
import app.model.Department;
import app.model.Role;
import app.model.Responsible;
import java.util.ArrayList;
import java.util.List;
import app.model.Participant;
import app.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getResponsibleList() == null) {
            user.setResponsibleList(new ArrayList<Responsible>());
        }
        if (user.getParticipantList() == null) {
            user.setParticipantList(new ArrayList<Participant>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Department iddepartment = user.getIddepartment();
            if (iddepartment != null) {
                iddepartment = em.getReference(iddepartment.getClass(), iddepartment.getIddepartment());
                user.setIddepartment(iddepartment);
            }
            Role idrole = user.getIdrole();
            if (idrole != null) {
                idrole = em.getReference(idrole.getClass(), idrole.getIdrole());
                user.setIdrole(idrole);
            }
            List<Responsible> attachedResponsibleList = new ArrayList<Responsible>();
            for (Responsible responsibleListResponsibleToAttach : user.getResponsibleList()) {
                responsibleListResponsibleToAttach = em.getReference(responsibleListResponsibleToAttach.getClass(), responsibleListResponsibleToAttach.getResponsiblePK());
                attachedResponsibleList.add(responsibleListResponsibleToAttach);
            }
            user.setResponsibleList(attachedResponsibleList);
            List<Participant> attachedParticipantList = new ArrayList<Participant>();
            for (Participant participantListParticipantToAttach : user.getParticipantList()) {
                participantListParticipantToAttach = em.getReference(participantListParticipantToAttach.getClass(), participantListParticipantToAttach.getParticipantPK());
                attachedParticipantList.add(participantListParticipantToAttach);
            }
            user.setParticipantList(attachedParticipantList);
            em.persist(user);
            if (iddepartment != null) {
                iddepartment.getUserList().add(user);
                iddepartment = em.merge(iddepartment);
            }
            if (idrole != null) {
                idrole.getUserList().add(user);
                idrole = em.merge(idrole);
            }
            for (Responsible responsibleListResponsible : user.getResponsibleList()) {
                User oldUserOfResponsibleListResponsible = responsibleListResponsible.getUser();
                responsibleListResponsible.setUser(user);
                responsibleListResponsible = em.merge(responsibleListResponsible);
                if (oldUserOfResponsibleListResponsible != null) {
                    oldUserOfResponsibleListResponsible.getResponsibleList().remove(responsibleListResponsible);
                    oldUserOfResponsibleListResponsible = em.merge(oldUserOfResponsibleListResponsible);
                }
            }
            for (Participant participantListParticipant : user.getParticipantList()) {
                User oldUserOfParticipantListParticipant = participantListParticipant.getUser();
                participantListParticipant.setUser(user);
                participantListParticipant = em.merge(participantListParticipant);
                if (oldUserOfParticipantListParticipant != null) {
                    oldUserOfParticipantListParticipant.getParticipantList().remove(participantListParticipant);
                    oldUserOfParticipantListParticipant = em.merge(oldUserOfParticipantListParticipant);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getIduser());
            Department iddepartmentOld = persistentUser.getIddepartment();
            Department iddepartmentNew = user.getIddepartment();
            Role idroleOld = persistentUser.getIdrole();
            Role idroleNew = user.getIdrole();
            List<Responsible> responsibleListOld = persistentUser.getResponsibleList();
            List<Responsible> responsibleListNew = user.getResponsibleList();
            List<Participant> participantListOld = persistentUser.getParticipantList();
            List<Participant> participantListNew = user.getParticipantList();
            List<String> illegalOrphanMessages = null;
            for (Responsible responsibleListOldResponsible : responsibleListOld) {
                if (!responsibleListNew.contains(responsibleListOldResponsible)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Responsible " + responsibleListOldResponsible + " since its user field is not nullable.");
                }
            }
            for (Participant participantListOldParticipant : participantListOld) {
                if (!participantListNew.contains(participantListOldParticipant)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Participant " + participantListOldParticipant + " since its user field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (iddepartmentNew != null) {
                iddepartmentNew = em.getReference(iddepartmentNew.getClass(), iddepartmentNew.getIddepartment());
                user.setIddepartment(iddepartmentNew);
            }
            if (idroleNew != null) {
                idroleNew = em.getReference(idroleNew.getClass(), idroleNew.getIdrole());
                user.setIdrole(idroleNew);
            }
            List<Responsible> attachedResponsibleListNew = new ArrayList<Responsible>();
            for (Responsible responsibleListNewResponsibleToAttach : responsibleListNew) {
                responsibleListNewResponsibleToAttach = em.getReference(responsibleListNewResponsibleToAttach.getClass(), responsibleListNewResponsibleToAttach.getResponsiblePK());
                attachedResponsibleListNew.add(responsibleListNewResponsibleToAttach);
            }
            responsibleListNew = attachedResponsibleListNew;
            user.setResponsibleList(responsibleListNew);
            List<Participant> attachedParticipantListNew = new ArrayList<Participant>();
            for (Participant participantListNewParticipantToAttach : participantListNew) {
                participantListNewParticipantToAttach = em.getReference(participantListNewParticipantToAttach.getClass(), participantListNewParticipantToAttach.getParticipantPK());
                attachedParticipantListNew.add(participantListNewParticipantToAttach);
            }
            participantListNew = attachedParticipantListNew;
            user.setParticipantList(participantListNew);
            user = em.merge(user);
            if (iddepartmentOld != null && !iddepartmentOld.equals(iddepartmentNew)) {
                iddepartmentOld.getUserList().remove(user);
                iddepartmentOld = em.merge(iddepartmentOld);
            }
            if (iddepartmentNew != null && !iddepartmentNew.equals(iddepartmentOld)) {
                iddepartmentNew.getUserList().add(user);
                iddepartmentNew = em.merge(iddepartmentNew);
            }
            if (idroleOld != null && !idroleOld.equals(idroleNew)) {
                idroleOld.getUserList().remove(user);
                idroleOld = em.merge(idroleOld);
            }
            if (idroleNew != null && !idroleNew.equals(idroleOld)) {
                idroleNew.getUserList().add(user);
                idroleNew = em.merge(idroleNew);
            }
            for (Responsible responsibleListNewResponsible : responsibleListNew) {
                if (!responsibleListOld.contains(responsibleListNewResponsible)) {
                    User oldUserOfResponsibleListNewResponsible = responsibleListNewResponsible.getUser();
                    responsibleListNewResponsible.setUser(user);
                    responsibleListNewResponsible = em.merge(responsibleListNewResponsible);
                    if (oldUserOfResponsibleListNewResponsible != null && !oldUserOfResponsibleListNewResponsible.equals(user)) {
                        oldUserOfResponsibleListNewResponsible.getResponsibleList().remove(responsibleListNewResponsible);
                        oldUserOfResponsibleListNewResponsible = em.merge(oldUserOfResponsibleListNewResponsible);
                    }
                }
            }
            for (Participant participantListNewParticipant : participantListNew) {
                if (!participantListOld.contains(participantListNewParticipant)) {
                    User oldUserOfParticipantListNewParticipant = participantListNewParticipant.getUser();
                    participantListNewParticipant.setUser(user);
                    participantListNewParticipant = em.merge(participantListNewParticipant);
                    if (oldUserOfParticipantListNewParticipant != null && !oldUserOfParticipantListNewParticipant.equals(user)) {
                        oldUserOfParticipantListNewParticipant.getParticipantList().remove(participantListNewParticipant);
                        oldUserOfParticipantListNewParticipant = em.merge(oldUserOfParticipantListNewParticipant);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getIduser();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getIduser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Responsible> responsibleListOrphanCheck = user.getResponsibleList();
            for (Responsible responsibleListOrphanCheckResponsible : responsibleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Responsible " + responsibleListOrphanCheckResponsible + " in its responsibleList field has a non-nullable user field.");
            }
            List<Participant> participantListOrphanCheck = user.getParticipantList();
            for (Participant participantListOrphanCheckParticipant : participantListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Participant " + participantListOrphanCheckParticipant + " in its participantList field has a non-nullable user field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Department iddepartment = user.getIddepartment();
            if (iddepartment != null) {
                iddepartment.getUserList().remove(user);
                iddepartment = em.merge(iddepartment);
            }
            Role idrole = user.getIdrole();
            if (idrole != null) {
                idrole.getUserList().remove(user);
                idrole = em.merge(idrole);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
