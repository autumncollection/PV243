/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.DirectorAtMovie;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tom
 */
@Stateless
public class DirectorAtMovieFacade extends AbstractFacade<DirectorAtMovie> {
  @PersistenceContext(unitName = "App-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public DirectorAtMovieFacade() {
    super(DirectorAtMovie.class);
  }
  
}
