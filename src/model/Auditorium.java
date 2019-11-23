package model;
import java.util.*;
public class Auditorium {
    
    private String name, location, status;
    private ArrayList<Event> events;
    private Seat[][] seats;

    public Auditorium(String name, String location) {
        this.name = name;
        this.location = location;
        status = "free";
        events = new ArrayList<Event>();
    }

    /**
     * @return String that corresponds to the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String that corresponds to the location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return String that corresponds to the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return ArrayList that corresponds to the events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * @param newEvent the new event to add
     */
    public void addEvent(Event newEvent) {
        events.add(newEvent);
    }

    /**
     * @return Seat[][] that corresponds to the seats
     */
    public Seat[][] getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    /**
     * @return String with the value of the attributes
     */
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}