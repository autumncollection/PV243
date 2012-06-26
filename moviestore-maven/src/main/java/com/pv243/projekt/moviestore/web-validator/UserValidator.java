package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import managed.Message;

/** 
 * UsersValidator.java
 * Purpose: Validates users.
 * 
 * @author Tomáš Hrabal.
 * @version 1.0
 */
public class UserValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String enteredEmail = (String) value;
        //Set the email pattern string
        System.err.print(value);
        String pattern = ".+@.+\\.[a-z]{1,5}";
        Pattern p = Pattern.compile(pattern);

        //Match the given string with the pattern
        Matcher m = p.matcher(enteredEmail);

        //Check whether match is found
        boolean matchFound = m.matches();
        Message mess = new Message();

        if (!matchFound) {
            mess.addWarning("Login není validní email");
            FacesMessage message = new FacesMessage();
            throw new ValidatorException(message);
        }

    }
}