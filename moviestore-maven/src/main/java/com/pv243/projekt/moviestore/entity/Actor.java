/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "Actor")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
  @NamedQuery(name = "Actor.findByIdActor", query = "SELECT a FROM Actor a WHERE a.idActor = :idActor"),
  @NamedQuery(name = "Actor.findByIdPerson", query = "SELECT a FROM Actor a WHERE a.idPerson = :idPerson")})
public class Actor implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idActor")
  private Integer idActor;
  @Basic(optional = false)
  @Column(name = "idPerson")
  private int idPerson;

  public Actor() {
  }

  public Actor(Integer idActor) {
    this.idActor = idActor;
  }

  public Actor(Integer idActor, int idPerson) {
    this.idActor = idActor;
    this.idPerson = idPerson;
  }

  public Integer getIdActor() {
    return idActor;
  }

  public void setIdActor(Integer idActor) {
    this.idActor = idActor;
  }

  public int getIdPerson() {
    return idPerson;
  }

  public void setIdPerson(int idPerson) {
    this.idPerson = idPerson;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idActor != null ? idActor.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Actor)) {
      return false;
    }
    Actor other = (Actor) object;
    if ((this.idActor == null && other.idActor != null) || (this.idActor != null && !this.idActor.equals(other.idActor))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Actor[ idActor=" + idActor + " ]";
  }
  
}
