/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "Movie")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
  @NamedQuery(name = "Movie.findByIdMovie", query = "SELECT m FROM Movie m WHERE m.idMovie = :idMovie"),
  @NamedQuery(name = "Movie.findByMovieName", query = "SELECT m FROM Movie m WHERE m.movieName = :movieName"),
  @NamedQuery(name = "Movie.findByMovieDate", query = "SELECT m FROM Movie m WHERE m.movieDate = :movieDate"),
  @NamedQuery(name = "Movie.findByLength", query = "SELECT m FROM Movie m WHERE m.length = :length")})
public class Movie implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idMovie")
  private Integer idMovie;
  @Basic(optional = false)
  @Column(name = "movieName")
  private String movieName;
  @Column(name = "movieDate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date movieDate;
  @Column(name = "length")
  private Integer length;
  @Lob
  @Column(name = "description")
  private String description;

  public Movie() {
  }

  public Movie(Integer idMovie) {
    this.idMovie = idMovie;
  }

  public Movie(Integer idMovie, String movieName) {
    this.idMovie = idMovie;
    this.movieName = movieName;
  }

  public Integer getIdMovie() {
    return idMovie;
  }

  public void setIdMovie(Integer idMovie) {
    this.idMovie = idMovie;
  }

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  public Date getMovieDate() {
    return movieDate;
  }

  public void setMovieDate(Date movieDate) {
    this.movieDate = movieDate;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idMovie != null ? idMovie.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Movie)) {
      return false;
    }
    Movie other = (Movie) object;
    if ((this.idMovie == null && other.idMovie != null) || (this.idMovie != null && !this.idMovie.equals(other.idMovie))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Movie[ idMovie=" + idMovie + " ]";
  }
  
}
