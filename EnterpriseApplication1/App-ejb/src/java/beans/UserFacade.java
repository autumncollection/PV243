/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author tom
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
  @PersistenceContext(unitName = "App-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public UserFacade() {
    super(User.class);
  }
  
  /**
    * Determines wheather is user logged in, or not.
    * 
    * @param login Login of user.
    * @param password Password of user.
    * @return Object Logged user or null.
    */
  public Object isLogin(String login, String password) {
      try {
          Query q = em.createNamedQuery("User.findByLogin");
          q.setParameter("login", login);
          Object u = q.getSingleResult();
          User user = (User) u;

          System.err.print(user.getIdUser() + " - ");
          if (user.getIdUser() < 1) {
              return null;
          }

          password = hash(salting(password, user.getSalt())); // ---- osoleni hesla
          System.err.print(password);
          q = em.createNamedQuery("User.isLogIn");
          q.setParameter("login", login);
          q.setParameter("password", password);
          List results = q.getResultList();
          if (!results.isEmpty()) {
              u = results.get(0);
          } else {
              u = null;
          }

          return u;
      } catch (NoResultException e) {
          return null;
      }
  }

    /**
    * Generates random 'salt'.
    * 
    * @return String Salt.
    */
  private String getSalt() {
      String text = "abcdefgijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String text2 = "!@#$%^&*()_";
      Random r = new Random();

      char[] salt = new char[15];
      for (int i = 0; i < 15; i++) {
          salt[i] = text.charAt(r.nextInt(text.length() - 1));
      }

      salt[r.nextInt(15)] = text2.charAt(r.nextInt(text2.length() - 1));

      return new String(salt);
  }

  /*
    * Hashes text into MD5 cipher.
    * 
    * @param String Input string.
    * @return String MD5 cipher of input text.
    */
  private String hash(String s) {
      try {

          MessageDigest m = MessageDigest.getInstance("MD5");
          m.update(s.getBytes(), 0, s.length());
          return new BigInteger(1, m.digest()).toString(16);
      } catch (NoSuchAlgorithmException ex) {

      }

      return "";
  }

  /**
    * 'Salts' given password
    * 
    * @param password Password.
    * @param salt Salt.
    * @return String Salted password.
    */
  private String salting(String password, String salt) {
    System.err.println(salt);
      return password + salt.substring(0, 7) + password + salt.substring(8, 14);
  }

  /**
    * This method updates users password.
    * 
    * @param idUser Id od user.
    * @param password New Password.
    * @return String Ok on success, False otherwise.
    */
  public String updatePassword(int idUser, String password) {
      try {
          User u = em.find(User.class, idUser);

          String salt = this.getSalt();
          String hash = this.hash(this.salting(password, salt));

          System.err.print(hash + " " + salt);


          if (u.getPassword() != hash) {
              u.setPassword(hash);
              u.setSalt(salt);
          }

          em.flush();

      } catch (PersistenceException e) {
          System.err.print(e.getLocalizedMessage());
          return "false";
      }
      return "ok";
  }
    
  public void persistUser(User user){       
      
      System.out.println(user.getLogin());       
      em.persist(user);
      
  }
  
  public User getUserById(int id) {
      User user = em.find(User.class, id);
      return user;
  }    
}
