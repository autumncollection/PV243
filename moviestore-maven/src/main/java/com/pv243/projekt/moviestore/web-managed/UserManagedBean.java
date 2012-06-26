/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import beans.RoleFacade;
import beans.UserFacade;
import entity.Role;
import entity.User;
import inc.MailClient;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;

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
  private String userRole;
  
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

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }
  
  public String logout()
  {
    setIdUser(0);
    setUserRole("");
    return "home";
  }
  
  public String userRole()
  {
    if(getUserRole() == null)
    {
      return "user";
    }
    System.err.print(getUserRole());
    return getUserRole();
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
              setUserRole(roleFacade.getUserRole(user.getIdUser()));
              user.setPassword("");

              m.addInfo("Sucesfull login");

              return "home";

          } else {
              m.addWarning("Wrong login / password");
              idUser = 0;
              return "registration";
          }

      }

      return "home";
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
     * Registers user.
     * 
     * @return String Specification of web page designed to interpret result.
     */
    public String registrationUser() {
        Message m = new Message();
        if (userFacade.existsUser(login) == true) {
            m.addWarning("Uživatel s loginem " + this.login + " již v systému existuje");
            return "false";
        }

        String s = userFacade.register(login);
        if (s.equals("false")) {
            m.addError("Existujicí uživatel s loginem " + this.login);
            return "false";
        }



        MailClient mc = new MailClient();
        try {
            mc.sendMail(login, "Vitejte v nasem E-shopu", "Vitejte v nasem E-shopu! Vaše přihlašovací údaje byly vytvoreny takto:\n"
                    + "Login:" + this.login
                    + "\nHeslo: " + s);
        } catch (MessagingException ex) {
            m.addWarning("Chyba při zasílání emailu!");
        }
        m.addInfo("Registrace proběhla v pořádku. Vaše přihlašovací údaje budou zaslány na email uvedený při registraci. Login:" + this.login + " Heslo:" + s + "");
        this.login = "";
        return "ok";
    }
    
    
    
  /**
   * Creates a new instance of UserManagedBean
   */
  public UserManagedBean() {
  }
}
