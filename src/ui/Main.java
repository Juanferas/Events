package ui;
import model.*;
import java.time.*;
import java.util.*;
public class Main {

    private Scanner lectorstr, lectorint;
    private Icesi universidad;
    
    public Main() {
        lectorstr = new Scanner(System.in);
        lectorint = new Scanner(System.in);
        universidad = new Icesi();
    }

    public static void main(String args[]) {
        Main obj = new Main();
        int ciclo = 0;
        while (ciclo==0) {
            System.out.println("\n----------MENU-----------\n1.Add event.\n2.Add the seats of an auditorium.\n3.Report faulty seat.\n4.Calculate percentage of faulty seats.\n5.Start event.\n6.Exit.");
            int op = obj.lectorint.nextInt();
            switch (op) {
                case 1:
                    obj.addEvent();
                    break;
                case 2:
                    obj.addSeatsOfAuditorium();
                    break;
                case 3:
                    obj.reportFaultySeat();
                    break;
                case 4:
                    obj.calcPercentageOfFaultySeats();
                    break;
                case 5:
                    obj.startEvent();
                    break;
                case 6:
                    ciclo = 1;
                    break;
            }
        }
    }

    public void addEvent() {
        System.out.println("\nEvent name:");
        String name = lectorstr.nextLine();
        System.out.println("Event date: (yyyy-MM-dd)");
        String pdate = lectorstr.nextLine();
        LocalDate date = LocalDate.parse(pdate);
        LocalTime start_hour = LocalTime.now();
        LocalTime end_hour = LocalTime.now();
        boolean hoursOk = false;
        while (!hoursOk) {
            System.out.println("Start hour: (hh:mm)");
            String pstart_hour = lectorstr.nextLine();
            while (Integer.valueOf(pstart_hour.split(":")[0])<7) {
                System.out.println("<<You can't reservate an auditorium before 7:00>>");
                System.out.println("Start hour: (hh:mm)");
                pstart_hour = lectorstr.nextLine();
            }
            while (Integer.valueOf(pstart_hour.split(":")[0])>18) {
                System.out.println("<<You can't reservate an auditorium after 6:00>>");
                System.out.println("Start hour: (hh:mm)");
                pstart_hour = lectorstr.nextLine();
            }
            start_hour = LocalTime.of(Integer.valueOf(pstart_hour.split(":")[0]),Integer.valueOf(pstart_hour.split(":")[1]));
            System.out.println("Event duration: (hh:mm)");
            String pevent_duration = lectorstr.nextLine();
            while (Integer.valueOf(pevent_duration.split(":")[0])<2) {
                System.out.println("<<You can't reservate an auditorium for less than 2 hours>>");
                System.out.println("Event duration: (hh:mm)");
                pevent_duration = lectorstr.nextLine();
            }
            while (Integer.valueOf(pevent_duration.split(":")[0])>12) {
                System.out.println("<<You can't reservate an auditorium for more than 12 hours>>");
                System.out.println("Event duration: (hh:mm)");
                pevent_duration = lectorstr.nextLine();
            }
            LocalTime event_duration = LocalTime.of(Integer.valueOf(pevent_duration.split(":")[0]),Integer.valueOf(pevent_duration.split(":")[1]));
            end_hour = start_hour.plusHours(Long.valueOf(event_duration.getHour())).plusMinutes(Long.valueOf(event_duration.getMinute()));
            if (end_hour.isAfter(LocalTime.parse("20:00"))) {
                System.out.println("<<Event can't end after 8 p.m.>>\n<<Please change start or end hours>>");
            }
            else {
                hoursOk = true;
            }
        }
        System.out.println("Teacher in charge:");
        String teacher = lectorstr.nextLine();
        System.out.println("Faculty:");
        String faculty = lectorstr.nextLine();
        System.out.println("Attendants:");
        int attendants = lectorint.nextInt();
        System.out.println("Auditoriums to reservate: (if more than one, separate them with hyphens)"+universidad.auditoriumsNames());
        String auditoriumsToReservate = lectorstr.nextLine();
        if (universidad.auditoriumIsFree(auditoriumsToReservate, start_hour, end_hour, date).equals("")) {
            universidad.addEvent(name, date, start_hour, end_hour, teacher, faculty, attendants, auditoriumsToReservate);
        }
        else {
            System.out.println(universidad.auditoriumIsFree(auditoriumsToReservate, start_hour, end_hour, date));
        }
    }

    public void addSeatsOfAuditorium() {
        if (!universidad.auditoriumsWithoutSeatsNames().equals("")) {
            System.out.println("\nTo which auditorium do you want to create the seats for?"+universidad.auditoriumsWithoutSeatsNames());
            int pos_aud = lectorint.nextInt();
            System.out.println("\nHow many rows does the auditorium have?");
            int num_rows = lectorint.nextInt();
            String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
            int[] seats = new int[num_rows];
            for (int i = 0; i < num_rows; i++) {
                System.out.println("How many seats does row "+alphabet[i]+" have?");
                seats[i] = lectorint.nextInt();
            }
            universidad.addSeatsOfAuditorium(pos_aud, seats);
        }
        else {
            System.out.println("All auditoriums already have seats.");
        }
    }

    public void reportFaultySeat() {
        if (!universidad.auditoriumsWithSeatsNames().equals("")) {
            System.out.println("\nIn which auditorium is the seat?"+universidad.auditoriumsWithSeatsNames());
            int pos_aud = lectorint.nextInt();
            System.out.println("\nDefect description:");
            String description = lectorstr.nextLine();
            while(description.equals("")) {
                System.out.println("\n<<Please fill the description in order to continue>>\n\nDefect description:");
                description = lectorstr.nextLine();
            }
            System.out.println("\nPlease type the letter of the row followed by the number of the seat.\n"+universidad.seatsOfAuditorium(pos_aud));
            String faulty_chair = lectorstr.nextLine();
            int f = Integer.valueOf(faulty_chair.charAt(0)-'A');
            int c = Integer.valueOf(faulty_chair.substring(1, faulty_chair.length()));
            System.out.println(universidad.reportFaultySeat(pos_aud, f, --c));
        }
        else {
            System.out.println("\n<<There are no auditoriums with seats yet>>");
        }
    }

    public void calcPercentageOfFaultySeats() {
        if (!universidad.auditoriumsWithSeatsNames().equals("")) {
            System.out.println("\nTo which auditorium do you want to calculate it?"+universidad.auditoriumsWithSeatsNames());
            int pos_aud = lectorint.nextInt();
            System.out.println(universidad.calcPercentageOfFaultySeats(pos_aud));
        }
        else {
            System.out.println("\n<<There are no auditoriums with seats yet>>");
        }
    }

    public void startEvent() {
        int event_position = 0;
        if (universidad.eventsNames().equals("\n<<There are no events registered>>")) {
            System.out.println(universidad.eventsNames());
        }
        else {
            System.out.println("\nWhich event do you want to start?"+universidad.eventsNames());
            event_position = lectorint.nextInt();
            if (universidad.canStartEvent(event_position)==true && universidad.eventAuditoriumsHaveSeats(event_position)==true) {
                System.out.println("\n<<EVENT STARTED>>");
                int cycle = 0;
                while(cycle==0) {
                    System.out.println("\n---------ACTIONS---------\n1.Ocuppy seats Randomly.\n2.End event.");
                    int action = lectorint.nextInt();
                    switch (action) {
                        case 1:
                            System.out.println("\nTo which auditorium do you want to do it?"+universidad.eventAuditoriumsNames(event_position));
                            int pos_aud = lectorint.nextInt();
                            System.out.println(universidad.ocuppySeatsRandomly(pos_aud, event_position));
                            break;
                        case 2:
                            cycle = 1;
                            break;
                    }
                }
            }
            else {
                System.out.println("\n<<CAN'T START EVENT>>\n* Maybe one of the event resevated auditoriums doesn't have seats yet.\n* Maybe today's date or time doesn't match the ones of the event.");
            }
        }
    }
}