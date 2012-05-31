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
@Table(name = "Role")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
  @NamedQuery(name = "Role.findByIdRole", query = "SELECT r FROM Role r WHERE r.idRole = :idRole"),
  @NamedQuery(name = "Role.findByRole", query = "SELECT r FROM Role r WHERE r.role = :role")})
public class Role implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idRole")
  private Short idRole;
  @Basic(optional = false)
  @Column(name = "role")
  private String role;

  public Role() {
  }

  public Role(Short idRole) {
    this.idRole = idRole;
  }

  public Role(Short idRole, String role) {
    this.idRole = idRole;
    this.role = role;
  }

  public Short getIdRole() {
    return idRole;
  }

  public void setIdRole(Short idRole) {
    this.idRole = idRole;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idRole != null ? idRole.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Role)) {
      return false;
    }
    Role other = (Role) object;
    if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Role[ idRole=" + idRole + " ]";
  }
  
}
