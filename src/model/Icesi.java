package model;
import java.util.*;
import java.time.*;
import java.text.*;
public class Icesi {

    private Auditorium[] allAuditoriums;
    private ArrayList<Event> allEvents;

    public Icesi() {
        allEvents = new ArrayList<Event>();
        allAuditoriums = new Auditorium[6];
        allAuditoriums[0] = new Auditorium ("Manuelita S.A.", "Auditoriums");
        allAuditoriums[1] = new Auditorium ("Cementos Argos", "Auditoriums");
        allAuditoriums[2] = new Auditorium ("SIDOC", "Auditoriums");
        allAuditoriums[3] = new Auditorium ("Banco de Occidente", "Building E");
        allAuditoriums[4] = new Auditorium ("Varela", "Building D");
        allAuditoriums[5] = new Auditorium ("Delima", "Building L");
    }

    /**
     * @return Auditorium[] that corresponds to the total auditoriums
     */
    public Auditorium[] getAllAuditoriums() {
        return this.allAuditoriums;
    }

    /**
     * @param allAuditoriums the total auditoriums to set
     */
    public void setAllAuditoriums(Auditorium[] allAuditoriums) {
        this.allAuditoriums = allAuditoriums;
    }

    /**
     * @return ArrayList that corresponds to the total events
     */
    public ArrayList<Event> getAllEvents() {
        return this.allEvents;
    }

    /**
     * @param allEvents the total events to set
     */
    public void setEvents(ArrayList<Event> allEvents) {
        this.allEvents = allEvents;
    }

    /**
     * @return String with the value of the attributes
     */
    public String toString() {
        return "{" +
            " allAuditoriums='" + getAllAuditoriums() + "'" +
            ", events='" + getAllEvents() + "'" +
            "}";
    }

    /**
     * This method receives the attributes of an event in order to create a new event and add it to its respectives auditoriums.
     * <b>pre:</b> An arrayList to store all the events has already been created.
     * <b>post:</b> A new event has been created and added to its correspondent container.
     * @param name String that corresponds to the name of the event.
     * @param date LocalDate that corresponds to the date of the event.
     * @param start_hour LocalTime that corresponds to the star hour of the event.
     * @param end_hour LocalTime that corresponds to the end hour of the event.
     * @param teacher String that corresponds to the teacher in charge of the event.
     * @param faculty String that corresponds to the faculty that organizes the event.
     * @param attendants int that corresponds to the attendants time of the event.
     * @param auditoriumsToReservate String that correspond to the auditoriums ro reservate for the event.
     */
    public void addEvent(String name, LocalDate date, LocalTime start_hour, LocalTime end_hour, String teacher, String faculty, int attendants, String auditoriumsToReservate) {
        Event newEvent = new Event(name, date, start_hour, end_hour, teacher, faculty, attendants);
        ArrayList<Auditorium> eventAuds = new ArrayList<Auditorium>();
        if (auditoriumsToReservate.contains("-")) {
            String[] auds = auditoriumsToReservate.split("-");
            for (int i = 0; i <auds.length; i++) {
                allAuditoriums[Integer.valueOf(auds[i])-1].addEvent(newEvent);
                eventAuds.add(allAuditoriums[Integer.valueOf(auds[i])-1]);
            }
        }
        else {
            allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].addEvent(newEvent);
            eventAuds.add(allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1]);
        }
        allEvents.add(newEvent);
        newEvent.setAuditoriums(eventAuds);
    }

    /**
     * This method concatenates the names of all auditoriums in a String.
     * <b>pre:</b> An array that stores all the auditoriums has already been created.
     * <b>post:</b> The names of all auditoriums have been concatenated into a String.
     * @return String with the names of all auditoriums.
     */
    public String auditoriumsNames() {
        String msj = "";
        for (int i = 0; i <allAuditoriums.length; i++) {
            msj += "\n"+(i+1)+". "+allAuditoriums[i].getName();
        }
        return msj;
    }

    /**
     * This method concatenates the names of all auditoriums without seats in a String.
     * <b>pre:</b> An array that stores all the auditoriums has already been created.
     * <b>post:</b> The names of all auditoriums without seats have been concatenated into a String.
     * @return String with the names of all auditoriums without seats.
     */
    public String auditoriumsWithoutSeatsNames() {
        String msj = "";
        int cont = 1;
        for (int i = 0; i <allAuditoriums.length; i++) {
            if (allAuditoriums[i].getSeats()==null) {
                msj += "\n"+cont+". "+allAuditoriums[i].getName();
                cont++;
            }
        }
        return msj;
    }

    /**
     * This method concatenates the names of all auditoriums with seats in a String.
     * <b>pre:</b> An array that stores all the auditoriums has already been created.
     * <b>post:</b> The names of all auditoriums with seats have been concatenated in a String.
     * @return String with the names of all auditoriums with seats.
     */
    public String auditoriumsWithSeatsNames() {
        String msj = "";
        int cont = 1;
        for (int i = 0; i <allAuditoriums.length; i++) {
            if (allAuditoriums[i].getSeats()!=null) {
                msj += "\n"+cont+". "+allAuditoriums[i].getName();
                cont++;
            }
        }
        return msj;
    }

    /**
     * This method concatenates the names of all auditoriums associated to an event in a String.
     * <b>pre:</b> An arrayList that stores all the events has already been created as well as an Array that stores all the auditoriums.
     * <b>post:</b> The names of all auditoriums associated to an event have been concatenated in a String.
     * @param event_position int that corresponds to the position of the event.
     * @return String with the names of all auditoriums associated to an event.
     */
    public String eventAuditoriumsNames(int event_position) {
        String msj = "";
        for (int i = 0; i <allEvents.get(event_position-1).getAuditoriums().size(); i++) {
            msj += "\n"+(i+1)+". "+allEvents.get(event_position-1).getAuditoriums().get(i).getName();
        }
        return msj;
    }

    /**
     * This method concatenates the names of all events in a String.
     * <b>pre:</b> An arrayList that stores all the events has already been created.
     * <b>post:</b> The names of all events have been concatenated in a String.
     * @return String with the names of all events.
     */
    public String eventsNames() {
        String msj = "";
        for (int i = 0; i <allEvents.size(); i++) {
            msj += "\n"+(i+1)+". "+allEvents.get(i).getName();
        }
        if (allEvents.size()==0) {
            msj = "\nThere are no events registered.";
        }
        return msj;
    }

    /**
     * This method compares the start and end hour of an event with the ones of the other events in order to know if they cross.
     * <b>pre:</b> An array that stores all the auditoriums has already been created.
     * <b>post:</b> It is known if any of the auditoriums associated to the event are taken or not.
     * @param date LocalDate that corresponds to the date of the event.
     * @param start_hour LocalTime that corresponds to the star hour of the event.
     * @param end_hour LocalTime that corresponds to the end hour of the event.
     * @param auditoriumsToReservate String that correspond to the auditoriums ro reservate for the event.
     * @return String that indicates if any of the auditoriums associated to the event are taken by the time it is gonna take place.
     */
    public String auditoriumIsFree(String auditoriumsToReservate, LocalTime start_hour, LocalTime end_hour, LocalDate date) {
        String msj = "";
        if (auditoriumsToReservate.contains("-")) {
            String[] auds = auditoriumsToReservate.split("-");
            for (int i = 0; i <auds.length; i++) {
                if (allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().size()!=0) {
                    for (int j = 0; j <allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().size(); j++) {
                        if ((allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().get(j).getDate().equals(date)) && ((start_hour.isBefore(allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().get(j).getStart_hour())) || (start_hour.isAfter(allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().get(j).getStart_hour())&&start_hour.isBefore(allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().get(j).getEnd_hour()))) && ((end_hour.isAfter(allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().get(j).getStart_hour())&&end_hour.isBefore(allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().get(j).getEnd_hour())) || (end_hour.isAfter(allAuditoriums[Integer.valueOf(auds[i])-1].getEvents().get(j).getEnd_hour())))) {
                            if (!(msj.contains(allAuditoriums[Integer.valueOf(auds[i])-1].getName()))) {
                                msj += "\n"+allAuditoriums[Integer.valueOf(auds[i])-1].getName()+" is already taken by that time.";
                            }
                        }
                    }
                }
            }
        }
        else {
            if (allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().size()!=0) {
                for (int j = 0; j <allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().size(); j++) {
                    if ((allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().get(j).getDate().equals(date)) && ((start_hour.isBefore(allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().get(j).getStart_hour())) || (start_hour.isAfter(allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().get(j).getStart_hour())&&start_hour.isBefore(allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().get(j).getEnd_hour()))) && ((end_hour.isAfter(allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().get(j).getStart_hour())&&end_hour.isBefore(allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().get(j).getEnd_hour())) || (end_hour.isAfter(allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getEvents().get(j).getEnd_hour())))) {
                        if (!(msj.contains(allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getName()))) {
                            msj = "\n"+allAuditoriums[Integer.valueOf(auditoriumsToReservate)-1].getName()+" is already taken by that time.";
                        }
                    }
                }
            }       
        }
        return msj;
    }

    public void addSeatsOfAuditorium(int pos_aud, int[] seats) {
        ArrayList<Auditorium> audsWithoutSeats = new ArrayList<Auditorium>();
        for (int i = 0; i<allAuditoriums.length; i++) {
            if (allAuditoriums[i].getSeats()==null) {
                audsWithoutSeats.add(allAuditoriums[i]);
            }
        }
        boolean endFor = false;
        for (int i = 0; i<allAuditoriums.length && !endFor; i++) {
            if (audsWithoutSeats.get(pos_aud-1).equals(allAuditoriums[i])) {
                pos_aud = i;
                endFor = true;
            }
        }
        int mayor = seats[0];
        for(int i = 0; i < seats.length; i++) {
            if (seats[i] > mayor){
                mayor = seats[i];
            }
        }
        Seat[][] auditoriumSeats = new Seat[seats.length][mayor];
        for (int f = 0; f<auditoriumSeats.length; f++) {
            for (int c=0; c<seats[f]; c++) {
                auditoriumSeats[f][c] = new Seat();
            }
        }
        allAuditoriums[pos_aud].setSeats(auditoriumSeats);
    }

    public String seatsOfAuditorium(int pos_aud) {
        ArrayList<Auditorium> audsWithSeats = new ArrayList<Auditorium>();
        for (int i = 0; i<allAuditoriums.length; i++) {
            if (allAuditoriums[i].getSeats()!=null) {
                audsWithSeats.add(allAuditoriums[i]);
            }
        }
        boolean endFor = false;
        for (int i = 0; i<allAuditoriums.length && !endFor; i++) {
            if (audsWithSeats.get(pos_aud-1).equals(allAuditoriums[i])) {
                pos_aud = i;
                endFor = true;
            }
        }
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String msj = "";
        for(int f = 0; f < allAuditoriums[pos_aud].getSeats().length; f++){
            for(int c = 0; c < allAuditoriums[pos_aud].getSeats()[0].length; c++){
                if (c==0) {
                    msj += alphabet[f]+" ["+(c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatus()+"), ";
                }
                else if (c==(allAuditoriums[pos_aud].getSeats()[0].length-1) && allAuditoriums[pos_aud].getSeats()[f][c]!=null) {
                    msj += (c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatus()+")]\n";
                }
                else if (c==(allAuditoriums[pos_aud].getSeats()[0].length-1) && allAuditoriums[pos_aud].getSeats()[f][c]==null) {
                    msj += "null]\n";
                }
                else {
                    if (allAuditoriums[pos_aud].getSeats()[f][c]==null) {
                        msj += "null, ";
                    }
                    else {
                        msj += (c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatus()+"), ";
                    }
                }
            }
        }
        return msj;
    }
    
    public String seatsOfAuditoriumOnEvent(int pos_aud) {
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String msj = "";
        for(int f = 0; f < allAuditoriums[pos_aud].getSeats().length; f++){
            for(int c = 0; c < allAuditoriums[pos_aud].getSeats()[0].length; c++){
                if (c==0) {
                    if (!allAuditoriums[pos_aud].getSeats()[f][c].getStatus().equals("Faulty")) {
                        msj += alphabet[f]+" ["+(c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatusOnEvent()+"), ";
                    }
                    else {
                        msj += alphabet[f]+" ["+(c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatus()+"), ";
                    }
                }
                else if (c==(allAuditoriums[pos_aud].getSeats()[0].length-1) && allAuditoriums[pos_aud].getSeats()[f][c]!=null) {
                    if (!allAuditoriums[pos_aud].getSeats()[f][c].getStatus().equals("Faulty")) {
                        msj += (c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatusOnEvent()+")]\n";
                    }
                    else {
                        msj += (c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatus()+")]\n";
                    }
                }
                else if (c==(allAuditoriums[pos_aud].getSeats()[0].length-1) && allAuditoriums[pos_aud].getSeats()[f][c]==null) {
                    msj += "null]\n";
                }
                else {
                    if (allAuditoriums[pos_aud].getSeats()[f][c]==null) {
                        msj += "null, ";
                    }
                    else {
                        if (!allAuditoriums[pos_aud].getSeats()[f][c].getStatus().equals("Faulty")) {
                            msj += (c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatusOnEvent()+"), ";
                        }
                        else {
                            msj += (c+1)+"("+allAuditoriums[pos_aud].getSeats()[f][c].getStatus()+"), ";
                        }
                    }
                }
            }
        }
        return msj;
    }

    public String reportFaultySeat(int pos_aud, int f, int c) {
        ArrayList<Auditorium> audsWithSeats = new ArrayList<Auditorium>();
        for (int i = 0; i<allAuditoriums.length; i++) {
            if (allAuditoriums[i].getSeats()!=null) {
                audsWithSeats.add(allAuditoriums[i]);
            }
        }
        boolean endFor = false;
        for (int i = 0; i<allAuditoriums.length && !endFor; i++) {
            if (audsWithSeats.get(pos_aud-1).equals(allAuditoriums[i])) {
                pos_aud = i;
                endFor = true;
            }
        }
        String msj = "";
        if (allAuditoriums[pos_aud].getSeats()[f][c]!=null && !allAuditoriums[pos_aud].getSeats()[f][c].getStatus().equals("Faulty")) {
            allAuditoriums[pos_aud].getSeats()[f][c].setStatus("Faulty");
            msj = "\n<<Seat reported correctly>>";
        }
        else if (allAuditoriums[pos_aud].getSeats()[f][c]==null) {
            msj = "\n<<There is no seat in that location>>";
        }
        else {
            msj = "\n<<That seat has already been reported>>";
        }
        return msj;
    }

    public String calcPercentageOfFaultySeats(int pos_aud) {
        ArrayList<Auditorium> audsWithSeats = new ArrayList<Auditorium>();
        for (int i = 0; i<allAuditoriums.length; i++) {
            if (allAuditoriums[i].getSeats()!=null) {
                audsWithSeats.add(allAuditoriums[i]);
            }
        }
        boolean endFor = false;
        for (int i = 0; i<allAuditoriums.length && !endFor; i++) {
            if (audsWithSeats.get(pos_aud-1).equals(allAuditoriums[i])) {
                pos_aud = i;
                endFor = true;
            }
        }
        double totalSeats = 0;
        double faultySeats = 0;
        for (int f = 0; f<allAuditoriums[pos_aud].getSeats().length; f++) {
            for (int c = 0; c<allAuditoriums[pos_aud].getSeats()[0].length; c++) {
                if (allAuditoriums[pos_aud].getSeats()[f][c]!=null) {
                    totalSeats += 1;
                }
                if (allAuditoriums[pos_aud].getSeats()[f][c]!=null && allAuditoriums[pos_aud].getSeats()[f][c].getStatus().equals("Faulty")) {
                    faultySeats += 1;
                }
            }
        }
        double percentageOfFaultySeats = (faultySeats/totalSeats)*100;
        DecimalFormat df = new DecimalFormat("#.00");
        String msj = "";
        if (percentageOfFaultySeats==0) {
            msj = "\n0% of the seats are faulty.";
        }
        else {
            msj = "\n"+df.format(percentageOfFaultySeats)+"% of the seats are faulty.";
        }
        return msj;
    }

    public boolean canStartEvent(int event_position) {
        boolean canStartEvent = false;
        if (((LocalTime.now().equals(allEvents.get(event_position-1).getStart_hour())) || (LocalTime.now().isAfter(allEvents.get(event_position-1).getStart_hour()) && LocalTime.now().isBefore(allEvents.get(event_position-1).getEnd_hour()))) && (LocalDate.now().equals(allEvents.get(event_position-1).getDate()))) {
            canStartEvent = true;
        }
        return canStartEvent;
    }

    public int[] seatsArray(int pos_aud) {
        int[] seats = new int[allAuditoriums[pos_aud].getSeats().length];
        for (int f = 0; f <allAuditoriums[pos_aud].getSeats().length; f++) {
            for (int c = 0; c <allAuditoriums[pos_aud].getSeats()[0].length; c++) {
                if (allAuditoriums[pos_aud].getSeats()[f][c]!=null) {
                    seats[f] += 1;
                }
            }
        }
        return seats;
    }

    public boolean eventAuditoriumsHaveSeats(int event_position) {
        boolean auditoriumsHaveSeats = false;
        ArrayList<String> audsSeats = new ArrayList<String>();
        for (int i = 0; i <allEvents.get(event_position-1).getAuditoriums().size(); i++) {
            if (allEvents.get(event_position-1).getAuditoriums().get(i).getSeats()!=null) {
                audsSeats.add("True");
            }
            else {
                audsSeats.add("False");
            }
        }
        if (!audsSeats.contains("False")) {
            auditoriumsHaveSeats = true;
        }
        return auditoriumsHaveSeats;
    }

    public String ocuppySeatsRandomly(int pos_aud, int event_position) {
        for (int i = 0; i<allAuditoriums.length; i++) {
            if (allEvents.get(event_position-1).getAuditoriums().get(pos_aud-1).getName().equals(allAuditoriums[i].getName())) {
                pos_aud = i;
            }
        }
        int[] seats = seatsArray(pos_aud);
        for (int f = 0; f <allAuditoriums[pos_aud].getSeats().length; f++) {
            for (int c = (int)Math.floor(Math.random()*seats[f]); c <(int)Math.floor(Math.random()*seats[f]); c++) {
                if (allAuditoriums[pos_aud].getSeats()[f][c]!= null && !allAuditoriums[pos_aud].getSeats()[f][c].getStatus().equals("Faulty")) {
                    allAuditoriums[pos_aud].getSeats()[f][c].setStatusOnEvent("Ocuppied");
                }
            }
        }
        return seatsOfAuditoriumOnEvent(pos_aud);
    }
}