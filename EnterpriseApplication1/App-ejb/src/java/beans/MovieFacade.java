/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Movie;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
}
