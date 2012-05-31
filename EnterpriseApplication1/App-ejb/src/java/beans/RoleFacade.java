/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
  
}
