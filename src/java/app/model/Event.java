/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "event", catalog = "sigefgti", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByIdevent", query = "SELECT e FROM Event e WHERE e.idevent = :idevent"),
    @NamedQuery(name = "Event.findByDetails", query = "SELECT e FROM Event e WHERE e.details = :details"),
    @NamedQuery(name = "Event.findByStarttime", query = "SELECT e FROM Event e WHERE e.starttime = :starttime"),
    @NamedQuery(name = "Event.findByEndtime", query = "SELECT e FROM Event e WHERE e.endtime = :endtime"),
    @NamedQuery(name = "Event.findByDateadded", query = "SELECT e FROM Event e WHERE e.dateadded = :dateadded"),
    @NamedQuery(name = "Event.findByLocation", query = "SELECT e FROM Event e WHERE e.location = :location"),
    @NamedQuery(name = "Event.findByRelevance", query = "SELECT e FROM Event e WHERE e.relevance = :relevance")})
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevent", nullable = false)
    private Integer idevent;
    @Column(name = "details", length = 255)
    private String details;
    @Column(name = "starttime", length = 100)
    private String starttime;
    @Column(name = "endtime", length = 100)
    private String endtime;
    @Column(name = "dateadded")
    @Temporal(TemporalType.DATE)
    private Date dateadded;
    @Column(name = "location", length = 255)
    private String location;
    @Column(name = "relevance", length = 255)
    private String relevance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", fetch = FetchType.LAZY)
    private List<Responsible> responsibleList;
    @JoinColumn(name = "idactivity", referencedColumnName = "idactivity")
    @ManyToOne(fetch = FetchType.LAZY)
    private Activity idactivity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", fetch = FetchType.LAZY)
    private List<Participant> participantList;

    public Event() {
    }

    public Event(Integer idevent) {
        this.idevent = idevent;
    }

    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Date getDateadded() {
        return dateadded;
    }

    public void setDateadded(Date dateadded) {
        this.dateadded = dateadded;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    @XmlTransient
    public List<Responsible> getResponsibleList() {
        return responsibleList;
    }

    public void setResponsibleList(List<Responsible> responsibleList) {
        this.responsibleList = responsibleList;
    }

    public Activity getIdactivity() {
        return idactivity;
    }

    public void setIdactivity(Activity idactivity) {
        this.idactivity = idactivity;
    }

    @XmlTransient
    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevent != null ? idevent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.idevent == null && other.idevent != null) || (this.idevent != null && !this.idevent.equals(other.idevent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Event[ idevent=" + idevent + " ]";
    }
    
}
