/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Director;
import entity.DirectorAtMovie;
import entity.Movie;
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
  
  public Director getDirectorToMovie(int idMovie){
      Query createdQuery = em.createQuery("SELECT m FROM DirectorAtMovie m WHERE m.idMovie = " + idMovie);
      DirectorAtMovie director = (DirectorAtMovie) createdQuery.getSingleResult();
      
      return em.find(Director.class, director.getIdDirector());
  
  }
  
}
