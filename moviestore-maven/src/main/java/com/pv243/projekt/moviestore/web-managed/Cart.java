/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import beans.MovieFacade;
import entity.Movie;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author tomas
 */
@ManagedBean(name = "cart")
@SessionScoped
public class Cart implements Serializable{

    @EJB
    private MovieFacade movieFacade;
    private Map<Movie, Integer> toPurchase;

    @PostConstruct
    public void init() {
        toPurchase = new HashMap<Movie, Integer>();
    }

    public String addMovieToCart(int idMovie) {
        Movie toAdd = movieFacade.getMovieById(idMovie);

        if (toPurchase.containsKey(toAdd)) {
            toPurchase.put(toAdd, toPurchase.get(toAdd) + 1);
        } else {
            toPurchase.put(toAdd, 1);
        }

        return "cart";
    }

    public String removeMovieFromCart(int idMovie) {
        Movie toRemove = movieFacade.getMovieById(idMovie);

        if (toPurchase.containsKey(toRemove) && toPurchase.get(toRemove) > 1) {
            toPurchase.put(toRemove, toPurchase.get(toRemove) - 1);
        } else {
            toPurchase.remove(toRemove);
        }

        return "cart";
    }

    public Map<Movie, Integer> getMovies() {
        return toPurchase;
    }
}
