/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Raimundo Jose
 */
@Embeddable
public class ParticipantPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iduser", nullable = false)
    private int iduser;
    @Basic(optional = false)
    @Column(name = "idevents", nullable = false)
    private int idevents;

    public ParticipantPK() {
    }

    public ParticipantPK(int iduser, int idevents) {
        this.iduser = iduser;
        this.idevents = idevents;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdevents() {
        return idevents;
    }

    public void setIdevents(int idevents) {
        this.idevents = idevents;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iduser;
        hash += (int) idevents;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipantPK)) {
            return false;
        }
        ParticipantPK other = (ParticipantPK) object;
        if (this.iduser != other.iduser) {
            return false;
        }
        if (this.idevents != other.idevents) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.ParticipantPK[ iduser=" + iduser + ", idevents=" + idevents + " ]";
    }
    
}
