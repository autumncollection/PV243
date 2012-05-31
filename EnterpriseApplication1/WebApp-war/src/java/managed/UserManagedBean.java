/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import beans.RoleFacade;
import beans.UserFacade;
import entity.Role;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author tom
 */
@ManagedBean(name = "user")
@SessionScoped

public class UserManagedBean {
  @EJB
  private RoleFacade roleFacade;
  @EJB
  private UserFacade userFacade; 
  private Integer idUser = 0;
  private String login;
  private String name;
  private String surname;
  private String address;
  private String password;
  private String password2; 
  private Role role;
  private User user;

  public Integer getIdUser() {
    return idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword2() {
    return password2;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public RoleFacade getRoleFacade() {
    return roleFacade;
  }

  public void setRoleFacade(RoleFacade roleFacade) {
    this.roleFacade = roleFacade;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public UserFacade getUserFacade() {
    return userFacade;
  }

  public void setUserFacade(UserFacade userFacade) {
    this.userFacade = userFacade;
  }
  
  /**
     * Tries to log user in.
     * 
     * @return String Specification of web page designed to interpret result.
     */
  public String isLogin() {
      if (login.length() > 1) {
          Message m = new Message();
          Object a = userFacade.isLogin(login, password);

          if (a != null) {
              idUser = Integer.parseInt(a.toString());
              // reading attributes from user
              User user = (entity.User) userFacade.find(a);
              login = user.getLogin();
              password = "";
              user.setPassword("");

              m.addInfo("Sucesfull login");

              return "books";

          } else {
              m.addWarning("Wrong login / password");
              idUser = 0;
              return "home";
          }

      }

      return "logged";
  }
  
  /**
  * Update of users password
  * 
  * @return String Specification of web page designed to interpret result.
  */
  public String updatePassword() {
    Message m = new Message();

    try {
        if (this.password.length() < 5) {
            throw new Exception("Heslo musí být delší než 5 znaků");
        }
        if (!this.password.equals(this.password2)) {
            throw new Exception("Rozdílná hesla");
        }
        String back = userFacade.updatePassword(this.idUser, this.password);
        if (back.equals("ok")) {
            m.addOk();
            return "ok";
        }

        m.addError("ERROR");
        return "false";
    } catch (Exception e) {
        m.addWarning(e.getMessage());
        return "false";
    }
}  
      /**
     * Finds out wheter user is loged in.
     * 
     * @return True on is loged in, False otherwise.
     */
    public boolean userIsLogIn() {
        if (this.idUser > 0) {
            return true;
        }
        return false;
    }
     /**
     * Retrieves role of user.
     * 
     * @return String String represetation of users role.
     */
    public String getRole() {
        entity.User user = (entity.User) userFacade.find(idUser);
        short roleId = user.getIdRole();
        entity.Role role = roleFacade.find(roleId);
        return role.getRole();
    }   
    /**
     * Finds out wheter loged user is admin.
     * 
     * @return True on is admin, False otherwise.
     */
    public boolean userIsAdmin() {
        return "admin".equalsIgnoreCase(getRole());
    }
 
  /**
   * Creates a new instance of UserManagedBean
   */
  public UserManagedBean() {
  }
}
