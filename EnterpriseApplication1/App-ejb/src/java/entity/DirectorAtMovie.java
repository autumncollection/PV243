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
@Table(name = "DirectorAtMovie")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "DirectorAtMovie.findAll", query = "SELECT d FROM DirectorAtMovie d"),
  @NamedQuery(name = "DirectorAtMovie.findByIdActorAtMovie", query = "SELECT d FROM DirectorAtMovie d WHERE d.idActorAtMovie = :idActorAtMovie"),
  @NamedQuery(name = "DirectorAtMovie.findByIdMovie", query = "SELECT d FROM DirectorAtMovie d WHERE d.idMovie = :idMovie"),
  @NamedQuery(name = "DirectorAtMovie.findByIdDirector", query = "SELECT d FROM DirectorAtMovie d WHERE d.idDirector = :idDirector")})
public class DirectorAtMovie implements Serializable {
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
  @Column(name = "idDirector")
  private int idDirector;

  public DirectorAtMovie() {
  }

  public DirectorAtMovie(Integer idActorAtMovie) {
    this.idActorAtMovie = idActorAtMovie;
  }

  public DirectorAtMovie(Integer idActorAtMovie, int idMovie, int idDirector) {
    this.idActorAtMovie = idActorAtMovie;
    this.idMovie = idMovie;
    this.idDirector = idDirector;
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

  public int getIdDirector() {
    return idDirector;
  }

  public void setIdDirector(int idDirector) {
    this.idDirector = idDirector;
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
    if (!(object instanceof DirectorAtMovie)) {
      return false;
    }
    DirectorAtMovie other = (DirectorAtMovie) object;
    if ((this.idActorAtMovie == null && other.idActorAtMovie != null) || (this.idActorAtMovie != null && !this.idActorAtMovie.equals(other.idActorAtMovie))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.DirectorAtMovie[ idActorAtMovie=" + idActorAtMovie + " ]";
  }
  
}
