package managed;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/** 
 * Message.java
 * Purpose: Allows programers insert text hints to displayed web pages.
 * 
 * @author Tomáš Hrabal
 * @version 1.0
 */
public class Message {

    private FacesContext fc = FacesContext.getCurrentInstance();

    public void addInfo(String message) {
        fc.addMessage("clientId", new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public void addError(String message) {
        fc.addMessage("clientId", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public void addWarning(String message) {
        fc.addMessage("clientId", new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    public void addOk() {
        fc.addMessage("clientId", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operace proběhla v pořádku", null));
    }
}