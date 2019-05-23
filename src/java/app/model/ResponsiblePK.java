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
public class ResponsiblePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iduser", nullable = false)
    private int iduser;
    @Basic(optional = false)
    @Column(name = "idevent", nullable = false)
    private int idevent;

    public ResponsiblePK() {
    }

    public ResponsiblePK(int iduser, int idevent) {
        this.iduser = iduser;
        this.idevent = idevent;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iduser;
        hash += (int) idevent;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsiblePK)) {
            return false;
        }
        ResponsiblePK other = (ResponsiblePK) object;
        if (this.iduser != other.iduser) {
            return false;
        }
        if (this.idevent != other.idevent) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.ResponsiblePK[ iduser=" + iduser + ", idevent=" + idevent + " ]";
    }
    
}
