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
@Table(name = "responsible", catalog = "sigefgti", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsible.findAll", query = "SELECT r FROM Responsible r"),
    @NamedQuery(name = "Responsible.findByIduser", query = "SELECT r FROM Responsible r WHERE r.responsiblePK.iduser = :iduser"),
    @NamedQuery(name = "Responsible.findByIdevent", query = "SELECT r FROM Responsible r WHERE r.responsiblePK.idevent = :idevent"),
    @NamedQuery(name = "Responsible.findByDatetaken", query = "SELECT r FROM Responsible r WHERE r.datetaken = :datetaken"),
    @NamedQuery(name = "Responsible.findByBiograph", query = "SELECT r FROM Responsible r WHERE r.biograph = :biograph")})
public class Responsible implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResponsiblePK responsiblePK;
    @Column(name = "datetaken")
    @Temporal(TemporalType.DATE)
    private Date datetaken;
    @Column(name = "biograph", length = 255)
    private String biograph;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "idevent", referencedColumnName = "idevent", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Event event;

    public Responsible() {
    }

    public Responsible(ResponsiblePK responsiblePK) {
        this.responsiblePK = responsiblePK;
    }

    public Responsible(int iduser, int idevent) {
        this.responsiblePK = new ResponsiblePK(iduser, idevent);
    }

    public ResponsiblePK getResponsiblePK() {
        return responsiblePK;
    }

    public void setResponsiblePK(ResponsiblePK responsiblePK) {
        this.responsiblePK = responsiblePK;
    }

    public Date getDatetaken() {
        return datetaken;
    }

    public void setDatetaken(Date datetaken) {
        this.datetaken = datetaken;
    }

    public String getBiograph() {
        return biograph;
    }

    public void setBiograph(String biograph) {
        this.biograph = biograph;
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
        hash += (responsiblePK != null ? responsiblePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsible)) {
            return false;
        }
        Responsible other = (Responsible) object;
        if ((this.responsiblePK == null && other.responsiblePK != null) || (this.responsiblePK != null && !this.responsiblePK.equals(other.responsiblePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Responsible[ responsiblePK=" + responsiblePK + " ]";
    }
    
}
