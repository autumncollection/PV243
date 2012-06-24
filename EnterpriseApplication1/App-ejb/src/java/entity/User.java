/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
  @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
  @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
  @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
  @NamedQuery(name = "User.findByIdRole", query = "SELECT u FROM User u WHERE u.idRole = :idRole"),
  @NamedQuery(name = "User.findBySalt", query = "SELECT u FROM User u WHERE u.salt = :salt"),
  @NamedQuery(name = "User.isLogIn", query = "SELECT u.idUser FROM User u WHERE u.login = :login AND u.password = :password")})
public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idUser")
  private Integer idUser;
  @Basic(optional = false)
  @Column(name = "login")
  @Length(min=5, max=255)
  private String login;
  @Basic(optional = false)
  @Column(name = "password")
  @Length(min=5, max=255)
  private String password;
  @Basic(optional = false)
  @Column(name = "idRole")
  private short idRole;
  @Column(name = "salt")
  @Length(min=0, max=255)
  private String salt;

  public User() {
  }

  public User(Integer idUser) {
    this.idUser = idUser;
  }

  public User(Integer idUser, String login, String password, short idRole) {
    this.idUser = idUser;
    this.login = login;
    this.password = password;
    this.idRole = idRole;
  }

  public Integer getIdUser() {
    return idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public short getIdRole() {
    return idRole;
  }

  public void setIdRole(short idRole) {
    this.idRole = idRole;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idUser != null ? idUser.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.User[ idUser=" + idUser + " ]";
  }
  
}
