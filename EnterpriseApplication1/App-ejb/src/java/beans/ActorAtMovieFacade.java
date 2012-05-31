/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.ActorAtMovie;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tom
 */
@Stateless
public class ActorAtMovieFacade extends AbstractFacade<ActorAtMovie> {
  @PersistenceContext(unitName = "App-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ActorAtMovieFacade() {
    super(ActorAtMovie.class);
  }
  
}
