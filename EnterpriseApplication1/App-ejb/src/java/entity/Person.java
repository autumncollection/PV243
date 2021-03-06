/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "Person")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
  @NamedQuery(name = "Person.findByIdPerson", query = "SELECT p FROM Person p WHERE p.idPerson = :idPerson"),
  @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
  @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE p.surname = :surname"),
  @NamedQuery(name = "Person.findByBirthdate", query = "SELECT p FROM Person p WHERE p.birthdate = :birthdate")})
public class Person implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idPerson")
  private Integer idPerson;
  @Basic(optional = false)
  @Column(name = "name")
  @Length(min=1, max=255)
  private String name;
  @Basic(optional = false)
  @Column(name = "surname")
  @Length(min=1, max=255)
  private String surname;
  @Column(name = "birthdate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date birthdate;
  @Column(name = "image")
  private String image;

  public Person() {
  }

  public Person(Integer idPerson) {
    this.idPerson = idPerson;
  }

  public Person(Integer idPerson, String name, String surname) {
    this.idPerson = idPerson;
    this.name = name;
    this.surname = surname;
  }

  public Integer getIdPerson() {
    return idPerson;
  }

  public void setIdPerson(Integer idPerson) {
    this.idPerson = idPerson;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }
  
  public String getImage(){
      return this.image;
  }
  
  public void setImage(String image){
      this.image = image;              
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idPerson != null ? idPerson.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Person)) {
      return false;
    }
    Person other = (Person) object;
    if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Person[ idPerson=" + idPerson + " ]";
  }
  
}
