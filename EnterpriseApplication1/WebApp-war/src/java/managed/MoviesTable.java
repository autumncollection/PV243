package managed;

import beans.MovieFacade;
import entity.Movie;
import java.util.ArrayList;
import java.util.List;
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
    List<Movie> movies = new ArrayList<Movie>();
    
    /**
     * Constructor: Creates a new instance of BookTable 
     */
    public MoviesTable() {
    }

    @PostConstruct
    public void init() {
        firstMovieDisplayed = 0;
        //movies = movieFacade.findAll();
    }

    /**
     * Retrieves all movies from database.
     * 
     * @return String Specification of web page designed to interpret result.
     */
    public String loadAllMovies() {
        movies = movieFacade.findAll();
        firstMovieDisplayed = 0;
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
    
    /**
     * Retrieves all movies from database.
     *      
     * @return List List of all movies
     */
    public List<Movie> getAllMovies() {       
        return movieFacade.getAllMovies();
    }
    
}
