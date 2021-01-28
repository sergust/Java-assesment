


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Menu {
    boolean exit;
    Utils utils = new Utils();
    public int showMenu() {
        int choice = -1;
        while (!exit) {
            printHeader();
            choice = utils.getInput("\nEnter your choice: ");
            exit = true;
        }
        return choice;
    }

    //display all machines with events in log file
    public void getMachineList(HashMap<String, ArrayList<Event>> events) {
        System.out.println("List of Machines with reported events");
        for(String machine: events.keySet())
            System.out.println(machine);
    }

    //produce a text file that contains all events from an input machine
    public void makeFileOfMachineEvents(String machine, HashMap<String, ArrayList<Event>> events) {
    }

    //check all events on the logfile and display all failed events
    public void showFailedEvents(HashMap<String, ArrayList<Event>> events) {
    }

    private void printHeader() {
        System.out.println("Menu");
        System.out.println("Please make a selection:");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("1. Print list of machines that have reported any events");
        System.out.println("2. Make a file reporting all events for a nominated machine");
        System.out.println("3. Print all events signifying failed situations across all machines");
        System.out.println("--------------------------------------------------------------------");
    }

    //public void runMethod(int choice, ArrayList<Event> events) {
    public void runMethod(int choice, HashMap<String, ArrayList<Event>> events) {
        switch (choice) {
            case 0:
                exit = true;
                break;
            case 1:
                getMachineList(events);
                break;
            case 2:
                System.out.println("\nEnter machine name: ");
                makeFileOfMachineEvents(utils.getString(), events);
                break;
            case 3:
                showFailedEvents(events);
        }
    }
}
