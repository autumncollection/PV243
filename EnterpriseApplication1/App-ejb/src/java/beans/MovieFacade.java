/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
                if (!nmbrs.contains(rand)) {
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

    public List<Movie> getMoviesPlayedBy(int idPerson) {
        List<Movie> result = new ArrayList<Movie>();
        try {
            Query createdQuery = em.createQuery("SELECT t FROM Actor t WHERE t.idPerson = " + idPerson);
            Actor actor = (Actor) createdQuery.getSingleResult();
            createdQuery = em.createQuery("SELECT m FROM ActorAtMovie m WHERE m.idActor = " + actor.getIdActor());
            List<ActorAtMovie> movies = createdQuery.getResultList();

            for (ActorAtMovie movie : movies) {
                result.add(em.find(Movie.class, movie.getIdMovie()));
            }
        } catch (NoResultException ex) {
            System.err.println("Person with id " + idPerson + "havent play in a movie yet.");
        }

        return result;
    }

    public List<Movie> getMoviesDirectedBy(int idPerson) {
        List<Movie> result = new ArrayList<Movie>();
        try {
            Query createdQuery = em.createQuery("SELECT t FROM Director t WHERE t.idPerson = " + idPerson);
            System.err.println("Looking for director with id " + idPerson);
            Director director = (Director) createdQuery.getSingleResult();
            createdQuery = em.createQuery("SELECT m FROM DirectorAtMovie m WHERE m.idDirector = " + director.getIdDector());
            List<DirectorAtMovie> movies = createdQuery.getResultList();
            
            for (DirectorAtMovie movie : movies) {
                result.add(em.find(Movie.class, movie.getIdMovie()));
            }
        }catch(NoResultException ex){
            System.err.println("Person with id " + idPerson + "havent directed a movie yet.");
        }

        return result;
    }

    public Movie getMovieById(int idMovie) {
        Query createdQuery = em.createQuery("SELECT t FROM Movie t WHERE t.idMovie =" + idMovie);
        Movie movie = (Movie) createdQuery.getSingleResult();

        return movie;
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
