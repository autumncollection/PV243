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
@Table(name = "ActorAtMovie")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "ActorAtMovie.findAll", query = "SELECT a FROM ActorAtMovie a"),
  @NamedQuery(name = "ActorAtMovie.findByIdActorAtMovie", query = "SELECT a FROM ActorAtMovie a WHERE a.idActorAtMovie = :idActorAtMovie"),
  @NamedQuery(name = "ActorAtMovie.findByIdMovie", query = "SELECT a FROM ActorAtMovie a WHERE a.idMovie = :idMovie"),
  @NamedQuery(name = "ActorAtMovie.findByIdActor", query = "SELECT a FROM ActorAtMovie a WHERE a.idActor = :idActor")})
public class ActorAtMovie implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idActorAtMovie")
  private Integer idActorAtMovie;
  @Basic(optional = false)
  @Column(name = "idMovie")
  private int idMovie;
  @Basic(optional = false)
  @Column(name = "idActor")
  private int idActor;

  public ActorAtMovie() {
  }

  public ActorAtMovie(Integer idActorAtMovie) {
    this.idActorAtMovie = idActorAtMovie;
  }

  public ActorAtMovie(Integer idActorAtMovie, int idMovie, int idActor) {
    this.idActorAtMovie = idActorAtMovie;
    this.idMovie = idMovie;
    this.idActor = idActor;
  }

  public Integer getIdActorAtMovie() {
    return idActorAtMovie;
  }

  public void setIdActorAtMovie(Integer idActorAtMovie) {
    this.idActorAtMovie = idActorAtMovie;
  }

  public int getIdMovie() {
    return idMovie;
  }

  public void setIdMovie(int idMovie) {
    this.idMovie = idMovie;
  }

  public int getIdActor() {
    return idActor;
  }

  public void setIdActor(int idActor) {
    this.idActor = idActor;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idActorAtMovie != null ? idActorAtMovie.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ActorAtMovie)) {
      return false;
    }
    ActorAtMovie other = (ActorAtMovie) object;
    if ((this.idActorAtMovie == null && other.idActorAtMovie != null) || (this.idActorAtMovie != null && !this.idActorAtMovie.equals(other.idActorAtMovie))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.ActorAtMovie[ idActorAtMovie=" + idActorAtMovie + " ]";
  }
  
}
