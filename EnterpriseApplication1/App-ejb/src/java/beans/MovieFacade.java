/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Movie;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tom
 */
@Stateless
public class MovieFacade extends AbstractFacade<Movie> {

    @PersistenceContext(unitName = "App-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovieFacade() {
        super(Movie.class);
    }

    public List<Movie> getRandomMovies(int numberOfMovies) {
        if (numberOfMovies <= 0) {
            return null;
        }
        
        List<Movie> randomMovies = new ArrayList<Movie>();
        Query createdQuery = em.createQuery("SELECT t FROM Movie t");
        List<Movie> movies = createdQuery.getResultList();
                

        if (movies.size() <= numberOfMovies) {
            return movies;
        } else {            
            Set<Integer> nmbrs = new HashSet<Integer>();            
            while (randomMovies.size() < numberOfMovies) {
                int rand = (int) (movies.size() * Math.random());
                if(!nmbrs.contains(rand)){
                    randomMovies.add(movies.get(rand));
                    nmbrs.add(rand);
                }                                
            }
            return randomMovies;
        }
    }

    public List<Movie> getAllMovies() {                
        Query createdQuery = em.createQuery("SELECT t FROM Movie t");
        List<Movie> movies = createdQuery.getResultList();
        
        return movies;        
    }
    
    public Set<Movie> findByActor(String actor){
        // get movie ids with such actor
        List<Long> ids = em.createNativeQuery("SELECT m.idMovie FROM Movie m, Person p, Actor a, ActorAtMovie am"
                + "                          WHERE p.surname LIKE #surname AND "
                + "                                p.idPerson = a.idPerson AND"
                + "                                a.idActor = am.idActor AND"
                + "                                am.idMovie = m.idMovie")
                 .setParameter("surname", actor)
                 .getResultList();
        
        Set<Movie> movies = new HashSet<Movie>();
        
        for( Long id: ids ){
            Movie m = (Movie) em.createQuery("SELECT m FROM Movie m WHERE m.idMovie = ?1")
                    .setParameter(1, id)
                    .getSingleResult();
            movies.add(m);
        }
        
        return movies;
               
    }
    
    public Set<Movie> findByDirector(String director){
        List<Long> ids = em.createNativeQuery("SELECT m.movieId FROM Movie m, Person p, Director d, DirectorAtMovie dm"
                + "                          WHERE p.surname LIKE #surname AND "
                + "                                p.idPerson = d.idPerson AND"
                + "                                d.idDirector = dm.idDirector AND"
                + "                                dm.idMovie = m.idMovie")
                 .setParameter("surname", director)
                 .getResultList();
        
        Set<Movie> movies = new HashSet<Movie>();
        
        for( Long id: ids ){
            Movie m = (Movie) em.createQuery("SELECT m FROM Movie m WHERE m.idMovie = ?1")
                    .setParameter(1, id)
                    .getSingleResult();
            movies.add(m);
        }
        
        return movies;
    }
    
    public Set<Movie> findByName(String movieName){
        List<Movie> movies = em.createNamedQuery("Movie.findByMovieName")
                 .setParameter("movieName", movieName)
                 .getResultList();
        
        return new HashSet<Movie>(movies);
    }
    
}
