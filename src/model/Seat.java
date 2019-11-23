package model;
public class Seat {

    private String status, statusOnEvent;

    public Seat() {
        status = "Operative";
        statusOnEvent = "Free";
    }

    /**
     * @return String that corresponds to the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return String that corresponds to the status on event
     */
    public String getStatusOnEvent() {
        return statusOnEvent;
    }

    /**
     * @param statusOnEvent the status on event to set
     */
    public void setStatusOnEvent(String statusOnEvent) {
        this.statusOnEvent = statusOnEvent;
    }

    /**
     * @return String with the value of the attributes
     */
    public String toString() {
        return "{" +
            " status='" + getStatus() + "'" +
            "}";
    }
}
