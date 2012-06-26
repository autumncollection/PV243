package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-26T05:45:36")
@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile SingularAttribute<Movie, String> movieName;
    public static volatile SingularAttribute<Movie, String> description;
    public static volatile SingularAttribute<Movie, Date> movieDate;
    public static volatile SingularAttribute<Movie, Integer> length;
    public static volatile SingularAttribute<Movie, String> image;
    public static volatile SingularAttribute<Movie, Integer> idMovie;

}