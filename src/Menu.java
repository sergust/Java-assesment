import java.util.ArrayList;
import java.util.Scanner;

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

    public void getEventList(ArrayList<Event> events) {
        System.out.println("event list");
        // Show event list
    }

    public void makeFileOfMachineEvents(String machine, ArrayList<Event> events) {
        // Find machine and retrieve all event
    }

    public void makeFileOfFailedEvent(ArrayList<Event> events) {
        // iterate through all events and file failed
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

    public void runMethod(int choice, ArrayList<Event> events) {
        switch (choice) {
            case 0:
                exit = true;
                break;
            case 1:
                getEventList(events);
                break;
            case 2:
                System.out.println("\nEnter machine name: ");
                makeFileOfMachineEvents(utils.getString(), events);
                break;
            case 3:
                makeFileOfFailedEvent(events);
        }
    }
}
