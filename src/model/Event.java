package model;
import java.util.*;
import java.time.*;
public class Event {

    private String name, teacher, faculty;
    private LocalDate date;
    private LocalTime start_hour, end_hour;
    private int attendants;
    private ArrayList<Auditorium> auditoriums;

    public Event(String name, LocalDate date, LocalTime start_hour, LocalTime end_hour, String teacher, String faculty, int attendants) {
        this.name = name;
        this.date = date;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
        this.teacher = teacher;
        this.faculty = faculty;
        this.attendants = attendants;
        auditoriums = new ArrayList<Auditorium>();
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
     * @return LocalDate that corresponds to the date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return LocalTime that corresponds to the start hour
     */
    public LocalTime getStart_hour() {
        return this.start_hour;
    }

    /**
     * @param start_hour the start hour to set
     */
    public void setStart_hour(LocalTime start_hour) {
        this.start_hour = start_hour;
    }

    /**
     * @return LocalTime that corresponds to the end hour
     */
    public LocalTime getEnd_hour() {
        return this.end_hour;
    }

    /**
     * @param end_hour the end hour to set
     */
    public void setEnd_hour(LocalTime end_hour) {
        this.end_hour = end_hour;
    }

    /**
     * @return String that corresponds to the teacher
     */
    public String getTeacher() {
        return this.teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     * @return String that corresponds to the faculty
     */
    public String getFaculty() {
        return this.faculty;
    }

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * @return int that corresponds to the attendants
     */
    public int getAttendants() {
        return this.attendants;
    }

    /**
     * @param attendants the attendants to set
     */
    public void setAttendants(int attendants) {
        this.attendants = attendants;
    }

    /**
     * @return ArrayList that corresponds to the auditoriums
     */
    public ArrayList<Auditorium> getAuditoriums() {
        return auditoriums;
    }
    
    /**
     * @param auditoriums the auditoriums to set
     */
    public void setAuditoriums(ArrayList<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    /**
     * @return String with the value of the attributes
     */
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", date='" + getDate() + "'" +
            ", star_hour='" + getStart_hour() + "'" +
            ", end_hour='" + getEnd_hour() + "'" +
            ", teacher='" + getTeacher() + "'" +
            ", faculty='" + getFaculty() + "'" +
            ", attendants='" + getAttendants() + "'" +
            "}";
    }

}