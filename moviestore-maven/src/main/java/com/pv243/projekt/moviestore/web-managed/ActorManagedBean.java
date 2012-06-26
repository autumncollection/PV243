

import beans.ActorFacade;
import managed.*;
import beans.DirectorAtMovieFacade;
import beans.DirectorFacade;
import beans.PersonFacade;
import entity.Director;
import entity.Person;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/** 
 * MovieDetail.java
 * Purpose: class used by movie.xhtml to display details connected to given movie
 * 
 * @author Tomas Sezima
 * @version 1.0
 */
@ManagedBean(name = "actor")
@SessionScoped
public class ActorManagedBean implements Serializable {

    @EJB
    private ActorFacade actorFacade;    
    @EJB
    private PersonFacade personFacade;     
    private List<Person> persons;
    private List<Director> directors;


    public List<Person> actors() {
      persons = actorFacade.getAll();
      return persons;
    }

    
    /**
     * Constructor: Creates a new instance of MovieDetail 
     */
    public ActorManagedBean() {
    }

}
