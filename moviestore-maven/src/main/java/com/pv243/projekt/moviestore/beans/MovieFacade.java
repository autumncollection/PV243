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

import javax.inject.Inject;
import org.jboss.solder.logging.Logger;

/**
 *
 * @author tom
 */
@Stateless
public class MovieFacade extends AbstractFacade<Movie> {

    @PersistenceContext(unitName = "App-ejbPU")
    private EntityManager em;
    
    @Inject
    private Logger log;

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
        
        log.info("getRandomMovies() called");

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
        
        log.info("getAllMovies() called");

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
        log.info("getMoviesPlayedBy " + idPerson);

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
        log.info("findByActor (" + actor + ")");  
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
    
    public String addMovie(String movieName, String image, String description, int length, List<String> directors,  List<String> actors)
    {
      Movie m = new Movie();
      m.setMovieName(movieName);
      m.setDescription(description);
      m.setLength(length);
      m.setImage(image);
      em.persist(m);
      em.flush();
      
      for (int i = 0; i < directors.size(); i++)
      {
        Query q = em.createNamedQuery("Director.findByIdPerson");
        q.setParameter("idPerson", Integer.parseInt(directors.get(i)));
        List<Director> d_list = q.getResultList();
        if( !d_list.isEmpty() )
        {
            Director d = d_list.get(0);
            DirectorAtMovie dam = new DirectorAtMovie();
            dam.setIdMovie(m.getIdMovie());
            dam.setIdDirector(d.getIdDector());
            em.persist(dam);
        }
        
      }
      
      for (int i = 0; i < actors.size(); i++)
      {
        Query q = em.createNamedQuery("Actor.findByIdPerson");
        //System.err.print(directors.get(i));
        q.setParameter("idPerson", Integer.parseInt(actors.get(i)));
        List<Actor> a_list = q.getResultList();
        if( !a_list.isEmpty() )
        {
            Actor a = a_list.get(0);
            ActorAtMovie dam = new ActorAtMovie();
            dam.setIdMovie(m.getIdMovie());
            dam.setIdActor(a.getIdActor());
            em.persist(dam);
        }
      }
      
      return "ok";
    }
    
    public String deleteMovie(Integer id){
        
        
        System.out.println("deleting " + id);
        
        Movie m = (Movie) em.createNamedQuery("Movie.findByIdMovie").setParameter("idMovie",id).getSingleResult();
        List<ActorAtMovie> am_list = em.createNamedQuery("ActorAtMovie.findByIdMovie").setParameter("idMovie",id).getResultList();
        List<DirectorAtMovie> dm_list =  em.createNamedQuery("DirectorAtMovie.findByIdMovie").setParameter("idMovie",id).getResultList();
        
        em.remove(m);
        for( ActorAtMovie am: am_list){
            em.remove(am);
        }
        for( DirectorAtMovie dm: dm_list){
            em.remove(dm);
        }
                     
        System.out.println("smazane");
        log.info("deleted a movie with id: " + id);
        return "";
    }
}
