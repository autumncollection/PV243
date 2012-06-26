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
 * PersonDetail.java Purpose: class used by person.xhtml to display details
 * connected to given movie
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
    private Boolean isDirector;
    private Boolean isActor;
    private String image;
    private List<Movie> directedMovies;
    private List<Movie> actedMovies;

    /**
     * Constructor: Creates a new instance of MovieDetail
     */
    public PersonDetail() {
    }

    public Boolean getIsActor() {
        return isActor;
    }

    public void setIsActor(Boolean isActor) {
        this.isActor = isActor;
    }

    public Boolean getIsDirector() {
        return isDirector;
    }

    public void setIsDirector(Boolean isDirector) {
        this.isDirector = isDirector;
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
       
        Person person = personFacade.getPersonById(idPerson);
        this.idPerson = person.getIdPerson();
        name = person.getName();
        surname = person.getSurname();
        birthDate = person.getBirthdate();
        image = person.getImage();

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

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public PersonFacade getPersonFacade() {
        return personFacade;
    }

    public String getSurname() {
        return surname;
    }
    
    public String getImage(){
        return image;
    }
    
    public void setImage(String image){
        this.image = image;
    }

    public String addPerson() {
        Message m = new Message();
        String rslt = personFacade.addPerson(getName(), getSurname(), getImage(), getBirthDate(), getIsActor(), getIsDirector());
        if (rslt == null) {
            m.addError("err");
            return "actors";
        }
        if (!rslt.equals("ok")) {
            m.addError(rslt);
            return "actors";
        }
        return "actors";
    }
}
