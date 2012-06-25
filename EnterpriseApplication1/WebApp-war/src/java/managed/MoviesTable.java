package managed;

import beans.MovieFacade;
import entity.Movie;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/** 
 * MoviesListing.java
 * Purpose: class used by books.xhtml to display the books(which are stored in books list)
 * 
 * @author Matej Briskar
 * @version 1.0
 */
@ManagedBean(name = "movies")
@SessionScoped
public class MoviesTable {

    @EJB
    private MovieFacade movieFacade;
    int firstMovieDisplayed;
    private List<Movie> movies = new ArrayList<Movie>();
    
    private String name = new String();
    private String director = new String();
    private String actor = new String();
    
    
    /**
     * Constructor: Creates a new instance of BookTable 
     */
    public MoviesTable() {
    }

    @PostConstruct
    public void init() {
        firstMovieDisplayed = 0;
        movies = movieFacade.getAllMovies();
    }

    /**
     * Retrieves all movies from database.
     * 
     * @return String Specification of web page designed to interpret result.
     */
    public String loadAllMovies() {
        movies = movieFacade.getAllMovies();
        firstMovieDisplayed = 0;
        return "movies";
    }
    
    public void setDirector(String s){
        director = s;
    }
    public void setName(String s){
        name = s;
    }
    public void setActor(String s){
        actor = s;
    }
    public String getDirector(){
        return director;
    }
    public String getActor(){
        return actor;
    }
    public String getName(){
        return name;
    }
    public List<Movie> getMovies(){
        return movies;
    }
    public String findMovies(){
        
        Set<Movie> byName = movieFacade.findByName(name);
        Set<Movie> byDirector = new HashSet<Movie>();    //movieFacade.findByDirector(director);
        Set<Movie> byActor = movieFacade.findByActor(actor);
        
        System.out.println("BBB " + byName.size() + " bActor " + byActor.size() );
        
        Set<Movie> nonEmpty = new HashSet<Movie>();
        if( !byName.isEmpty() ){
            nonEmpty = byName;
        } else if (!byDirector.isEmpty()){
            nonEmpty = byDirector;
        } else if (!byActor.isEmpty()){
            nonEmpty = byActor;
        }
        
        if( nonEmpty.isEmpty() && 
                (name.equals("") && actor.equals("") && director.equals(""))
          )
        {
            loadAllMovies();
            
        } else {
            if( name.equals("")){
                byName = nonEmpty;
            }
            if( actor.equals("")){
                byActor = nonEmpty;
            }
            if( director.equals("")){
                byDirector = nonEmpty;
            }

           // System.out.println("all " + byName.size() + " " + byDirector.size() + "  " + byActor.size() );
            // intersect sets
            byDirector.retainAll(byActor);
            byName.retainAll(byDirector);
            
         //   System.out.println("all " + byName.size() + " " + byDirector.size() + "  " + byActor.size() );
            
            movies = new ArrayList<Movie>(byName);
            System.out.println("AAAAAAAAAAfound " + movies.size() + " movies");
        }
        return "movies";
    }

    /**
     * Retrieves random movies from database.
     * 
     * @param int Number of expected movies
     * @return List List of random movies
     */
    public List<Movie> getRandomMovies(int number) {       
        return movieFacade.getRandomMovies(number);
    }
    
    public List<Movie> loadAll(){
        movies = getAllMovies();
        return movies;
    }
    
    /**
     * Retrieves all movies from database.
     *      
     * @return List List of all movies
     */
    public List<Movie> getAllMovies() {       
        return movieFacade.getAllMovies();
    }
    
    public void resetFilter(){
        this.init();
    }
    
    public String deleteMovie(Integer id){
        movieFacade.deleteMovie(id);
        resetFilter();
        return "movies";
    }
    
}
