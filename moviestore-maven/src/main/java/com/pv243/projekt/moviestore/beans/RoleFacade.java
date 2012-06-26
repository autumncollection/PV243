/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tom
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> {
  @PersistenceContext(unitName = "App-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public RoleFacade() {
    super(Role.class);
  }
  
  public String getUserRole(int idUser)
  {
    Query q = em.createQuery("SELECT r FROM Role r, User u WHERE r.idRole = u.idRole and u.idUser = :idUser");
    q.setParameter("idUser", idUser);
    Role r = (Role) q.getSingleResult();
    return r.getRole();
  }
  
}
