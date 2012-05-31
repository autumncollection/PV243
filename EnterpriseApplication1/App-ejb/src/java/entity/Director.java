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
@Table(name = "Director")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Director.findAll", query = "SELECT d FROM Director d"),
  @NamedQuery(name = "Director.findByIdDector", query = "SELECT d FROM Director d WHERE d.idDector = :idDector"),
  @NamedQuery(name = "Director.findByIdPerson", query = "SELECT d FROM Director d WHERE d.idPerson = :idPerson")})
public class Director implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idDector")
  private Integer idDector;
  @Basic(optional = false)
  @Column(name = "idPerson")
  private int idPerson;

  public Director() {
  }

  public Director(Integer idDector) {
    this.idDector = idDector;
  }

  public Director(Integer idDector, int idPerson) {
    this.idDector = idDector;
    this.idPerson = idPerson;
  }

  public Integer getIdDector() {
    return idDector;
  }

  public void setIdDector(Integer idDector) {
    this.idDector = idDector;
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
    hash += (idDector != null ? idDector.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Director)) {
      return false;
    }
    Director other = (Director) object;
    if ((this.idDector == null && other.idDector != null) || (this.idDector != null && !this.idDector.equals(other.idDector))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Director[ idDector=" + idDector + " ]";
  }
  
}
