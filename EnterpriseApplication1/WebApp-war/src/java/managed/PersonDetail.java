package managed;

import beans.MovieFacade;
import beans.PersonFacade;
import entity.Movie;
import entity.Person;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/** 
 * PersonDetail.java
 * Purpose: class used by person.xhtml to display details connected to given movie
 * 
 * @author Tomas Sezima
 * @version 1.0
 */
@ManagedBean(name = "personDetail")
@SessionScoped
public class PersonDetail {

    @EJB
    private PersonFacade personFacade;    
    @EJB
    private MovieFacade movieFacade;    
    
    private int idPerson;
    private String name;
    private String surname;
    private Date birthDate;
    
    private List<Movie> directedMovies;
    private List<Movie> actedMovies;
        
    
    /**
     * Constructor: Creates a new instance of MovieDetail 
     */
    public PersonDetail() {
    }

    @PostConstruct
    public void init() {        
        //movies = movieFacade.findAll();
    }

     /**
     * Retrieves info about person specified by given ID.
     * 
     * @param idPerson ID of person.
     * @return String Specification of web page designed to interpret result.
     */
    public String selectDetailedPerson(int idPerson) {                
        
        System.err.println("Id is " + idPerson); 
        
        Person person = personFacade.getPersonById(idPerson);
        this.idPerson = person.getIdPerson();
        name = person.getName();
        surname = person.getSurname();
        birthDate = person.getBirthdate();

        directedMovies = movieFacade.getMoviesDirectedBy(idPerson);
        actedMovies = movieFacade.getMoviesPlayedBy(idPerson);
        
        return "person";
    }

    public List<Movie> getActedMovies() {
        return actedMovies;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<Movie> getDirectedMovies() {
        return directedMovies;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public MovieFacade getMovieFacade() {
        return movieFacade;
    }

    public String getName() {
        return name;
    }

    public PersonFacade getPersonFacade() {
        return personFacade;
    }

    public String getSurname() {
        return surname;
    }

    
}
