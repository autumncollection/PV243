/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tom
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> {
  @PersistenceContext(unitName = "App-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public PersonFacade() {
    super(Person.class);
  }
  
}
