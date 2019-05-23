/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "participant", catalog = "sigefgti", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participant.findAll", query = "SELECT p FROM Participant p"),
    @NamedQuery(name = "Participant.findByIduser", query = "SELECT p FROM Participant p WHERE p.participantPK.iduser = :iduser"),
    @NamedQuery(name = "Participant.findByIdevents", query = "SELECT p FROM Participant p WHERE p.participantPK.idevents = :idevents"),
    @NamedQuery(name = "Participant.findByDatetaken", query = "SELECT p FROM Participant p WHERE p.datetaken = :datetaken"),
    @NamedQuery(name = "Participant.findByResearchfields", query = "SELECT p FROM Participant p WHERE p.researchfields = :researchfields")})
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipantPK participantPK;
    @Column(name = "datetaken")
    @Temporal(TemporalType.DATE)
    private Date datetaken;
    @Column(name = "researchfields", length = 255)
    private String researchfields;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "idevents", referencedColumnName = "idevent", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Event event;

    public Participant() {
    }

    public Participant(ParticipantPK participantPK) {
        this.participantPK = participantPK;
    }

    public Participant(int iduser, int idevents) {
        this.participantPK = new ParticipantPK(iduser, idevents);
    }

    public ParticipantPK getParticipantPK() {
        return participantPK;
    }

    public void setParticipantPK(ParticipantPK participantPK) {
        this.participantPK = participantPK;
    }

    public Date getDatetaken() {
        return datetaken;
    }

    public void setDatetaken(Date datetaken) {
        this.datetaken = datetaken;
    }

    public String getResearchfields() {
        return researchfields;
    }

    public void setResearchfields(String researchfields) {
        this.researchfields = researchfields;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participantPK != null ? participantPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participant)) {
            return false;
        }
        Participant other = (Participant) object;
        if ((this.participantPK == null && other.participantPK != null) || (this.participantPK != null && !this.participantPK.equals(other.participantPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Participant[ participantPK=" + participantPK + " ]";
    }
    
}
