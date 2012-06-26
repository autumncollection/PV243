package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-26T05:45:36")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Date> birthdate;
    public static volatile SingularAttribute<Person, String> surname;
    public static volatile SingularAttribute<Person, String> image;
    public static volatile SingularAttribute<Person, Integer> idPerson;

}