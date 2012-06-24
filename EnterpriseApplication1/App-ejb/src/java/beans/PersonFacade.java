/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Actor;
import entity.Director;
import entity.Person;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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

  
  
  
  public String addPerson(String name, String surname, Date birthDate, Boolean isActor, Boolean isDirector)
  {
    try
    {
     Person p = new Person();
     p.setName(name);
     p.setSurname(surname);
     em.persist(p);
     em.flush();
     
     if(isActor)
     {
        Actor a = new Actor();
        a.setIdPerson(p.getIdPerson());
        em.persist(a);
     }
     if(isDirector)
     {
        Director a = new Director();
        a.setIdPerson(p.getIdPerson());
        em.persist(a);
     }
     return "ok";

    } catch(Exception e)
    {
      return e.getLocalizedMessage();
    }

  }
  
}
