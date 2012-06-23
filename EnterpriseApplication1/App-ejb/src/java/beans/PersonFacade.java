/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
  
  public Person getPersonById(int idPerson){
      Query q = em.createQuery("SELECT t FROM Person t WHERE t.idPerson = " + idPerson);
      Person person = (Person) q.getSingleResult();            
      
      return person;      
  }
  
}
