/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Actor;
import entity.ActorAtMovie;
import entity.Director;
import java.util.ArrayList;
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

    public List<Actor> getActorsToMovie(int idMovie) {
      Query createdQuery = em.createQuery("SELECT m FROM ActorAtMovie m WHERE m.idMovie = " + idMovie);
      List<ActorAtMovie> actorsAtMovie = createdQuery.getResultList();
      List<Actor> actors = new ArrayList<Actor>();
      
      for(ActorAtMovie actor: actorsAtMovie){
          actors.add(em.find(Actor.class, actor.getIdActor()));
      }
      
      return actors;
    }
  
}
