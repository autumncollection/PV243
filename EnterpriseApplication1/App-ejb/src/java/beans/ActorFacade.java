/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Actor;
import entity.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tom
 */
@Stateless
public class ActorFacade extends AbstractFacade<Actor> {
  @PersistenceContext(unitName = "App-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ActorFacade() {
    super(Actor.class);
  }
    
  public String addPersonAsActor(int id)
  {
      Actor a = new Actor();
      a.setIdPerson(id);
      em.persist(a);
      return "ok";
  }
  
  public List<Person> getAll()
  {
    Query q = em.createQuery("SELECT p FROM Person p, Actor d WHERE p.idPerson = d.idPerson");
    List<Person> l = q.getResultList();
    return l;
  }

}
