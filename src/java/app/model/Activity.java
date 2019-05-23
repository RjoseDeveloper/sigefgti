/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "activity", catalog = "sigefgti", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByIdactivity", query = "SELECT a FROM Activity a WHERE a.idactivity = :idactivity"),
    @NamedQuery(name = "Activity.findByDescription", query = "SELECT a FROM Activity a WHERE a.description = :description"),
    @NamedQuery(name = "Activity.findByDetails", query = "SELECT a FROM Activity a WHERE a.details = :details")})
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactivity", nullable = false)
    private Integer idactivity;
    @Column(name = "description", length = 255)
    private String description;
    @Column(name = "details", length = 255)
    private String details;
    @OneToMany(mappedBy = "idactivity", fetch = FetchType.LAZY)
    private List<Event> eventList;

    public Activity() {
    }

    public Activity(Integer idactivity) {
        this.idactivity = idactivity;
    }

    public Integer getIdactivity() {
        return idactivity;
    }

    public void setIdactivity(Integer idactivity) {
        this.idactivity = idactivity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @XmlTransient
    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactivity != null ? idactivity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.idactivity == null && other.idactivity != null) || (this.idactivity != null && !this.idactivity.equals(other.idactivity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Activity[ idactivity=" + idactivity + " ]";
    }
    
}
