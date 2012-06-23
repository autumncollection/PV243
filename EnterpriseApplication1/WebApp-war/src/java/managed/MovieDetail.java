package managed;

import beans.ActorAtMovieFacade;
import beans.DirectorAtMovieFacade;
import beans.MovieFacade;
import beans.PersonFacade;
import entity.Actor;
import entity.Director;
import entity.Movie;
import entity.Person;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/** 
 * MovieDetail.java
 * Purpose: class used by movie.xhtml to display details connected to given movie
 * 
 * @author Tomas Sezima
 * @version 1.0
 */
@ManagedBean(name = "detail")
@SessionScoped
public class MovieDetail {

    @EJB
    private MovieFacade movieFacade;
    @EJB
    private DirectorAtMovieFacade directorFacade;
    @EJB
    private ActorAtMovieFacade actorFacade;
    @EJB
    private PersonFacade personFacade;
    
    private int idMovie;
    private String movieName;
    private Date movieDate;
    private Integer length;
    private String description;
    private Director directorInfo;
    private Person director;
    private int directorid;
    private List<Actor> actorsInfo;    
    private List<Person> actors;
        
    
    /**
     * Constructor: Creates a new instance of MovieDetail 
     */
    public MovieDetail() {
    }

    @PostConstruct
    public void init() {        
        //movies = movieFacade.findAll();
    }

     /**
     * Retrieves info about movie specified by given ID.
     * 
     * @param idMovie ID of movie.
     * @return String Specification of web page designed to interpret result.
     */
    public String selectDetailedMovie(int idmovie) {
        Movie movie = movieFacade.find(new Integer(idmovie));
        idMovie = movie.getIdMovie();
        movieName = movie.getMovieName();
        movieDate = movie.getMovieDate();
        length = movie.getLength();
        description = movie.getDescription();
        
        directorInfo = directorFacade.getDirectorToMovie(idmovie);  
        director = personFacade.getPersonById(directorInfo.getIdPerson());
        directorid = director.getIdPerson();        
        actorsInfo = actorFacade.getActorsToMovie(idMovie);    
        actors = new ArrayList<Person>();
        for(Actor actor: actorsInfo){            
            if(actors == null) System.out.println("Actors null");
            if(personFacade == null) System.out.println("Facade null");
            if(actor == null) System.out.println("Actor null");
            
            actors.add(personFacade.getPersonById(actor.getIdPerson()));
        }        

        return "movie";
    }

    public int getDirectorid() {
        return directorid;
    }

    public ActorAtMovieFacade getActorFacade() {
        return actorFacade;
    }

    public List<Person> getActors() {                
        return actors;
    }

    public String getDescription() {
        return description;
    }

    public Person getDirector() {
        return director;
    }

    public DirectorAtMovieFacade getDirectorFacade() {
        return directorFacade;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public Integer getLength() {
        return length;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public MovieFacade getMovieFacade() {
        return movieFacade;
    }

    public String getMovieName() {
        return movieName;
    }
    
    
}
