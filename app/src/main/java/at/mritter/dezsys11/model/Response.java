package at.mritter.dezsys11.model;

/**
 * Model class that represents a response from a rest api call.
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class Response {

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
