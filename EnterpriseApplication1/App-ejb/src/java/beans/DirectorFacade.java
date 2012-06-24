/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Director;
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
public class DirectorFacade extends AbstractFacade<Director> {
  @PersistenceContext(unitName = "App-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public DirectorFacade() {
    super(Director.class);
  }
    
  public void addPersonAsDirector(Person p)
  {
    Director a = new Director();
    a.setIdPerson(p.getIdPerson());
    em.persist(a);
  }
  
  public List<Person> getAll()
  {
    Query q = em.createQuery("SELECT p FROM Person p, Director d WHERE p.idPerson = d.idPerson");
    List<Person> l = q.getResultList();
    return l;
  }
  
}
