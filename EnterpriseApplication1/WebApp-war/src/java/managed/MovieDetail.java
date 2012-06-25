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
    private List directors;
    private List actors_;

  public List getDirectors() {
    return directors;
  }

  public void setDirectors(List directors) {
    this.directors = directors;
  }

  public List getActors_() {
    return actors_;
  }

  public void setActors_(List actors_) {
    this.actors_ = actors_;
  }
    
    
    
  public List<Actor> getActorsInfo() {
    return actorsInfo;
  }

  public void setActorsInfo(List<Actor> actorsInfo) {
    this.actorsInfo = actorsInfo;
  }

  public Director getDirectorInfo() {
    return directorInfo;
  }

  public void setDirectorInfo(Director directorInfo) {
    this.directorInfo = directorInfo;
  }

  public void setActors(List<Person> actors) {
    this.actors = actors;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDirector(Person director) {
    this.director = director;
  }

  public void setDirectorid(int directorid) {
    this.directorid = directorid;
  }

  public void setIdMovie(int idMovie) {
    this.idMovie = idMovie;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public void setMovieDate(Date movieDate) {
    this.movieDate = movieDate;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }
     
    
    
    /**
     * Constructor: Creates a new instance of MovieDetail 
     */
    public MovieDetail() {
    }

    @PostConstruct
    public void init() {        
        //movies = movieFacade.findAll();
    }
    
    private Integer selectedActor;
    private Integer selectedDirector;
      
    public void setSelectedActor(Integer actorId){
        selectedActor = actorId;
    }
    public void setSelectedDirector(Integer directorId){
        selectedDirector = directorId;
    }
    public Integer getSelectedActor(){
        return selectedActor;
    }
    public Integer getSelectedDirector(){
        return selectedDirector;
    }
    
    public String editSave(){
        
        // check no field is empty 
        if(actors.size() < 1 ){
            Message err = new Message();
            err.addError("vypln vsetky polia");
            
            return "edit_movie";
        }  
           
        List<String> newActors = new ArrayList<String>();
        for( Person a: actors){
            newActors.add( String.valueOf(a.getIdPerson()));
        }
        
        if(selectedDirector == null){
            selectedDirector = director.getIdPerson();
        }
        List<String> newDirectors = new ArrayList<String>();
        newDirectors.add( String.valueOf(selectedDirector));
        
        movieFacade.deleteMovie(idMovie); // deletes movie, actoratmovie,directoratmovie
        movieFacade.addMovie(getMovieName(), getDescription(), getLength(), newDirectors, newActors);
             
        return "movies";
    }

    public String editMovie(Integer idMovie){
        selectDetailedMovie(idMovie);
        return "edit_movie";
    }
    
    public String addSelectedActor(){
        
       actors.add(personFacade.getPersonById( selectedActor ));
       return "edit_movie";
    }
    
    public String removeLastActor(){
        
        if( actors.size() > 0 ){
            actors.remove( actors.size() - 1);
        }
        return "edit_movie";
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
    
    public String addMovie()
    {
      Message m = new Message();
      String rslt = movieFacade.addMovie(getMovieName(), getDescription(), getLength(), getDirectors(), getActors_());
      if(rslt == null)
      {
        m.addError("err");
      }
      if(!rslt.equals("ok"))
      {    
        m.addError(rslt);
      }
      return "actors";
    }
    
}
