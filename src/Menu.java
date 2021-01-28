


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


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

        if (!events.containsKey(machine)){
            System.out.println("Invalid machine name");
            runMethod(2, events);
        }else {
            PrintWriter pw;
            try {
                pw = new PrintWriter(machine + "-report.txt");
                for (Event m : events.get(machine)) {
                    if (m instanceof Inventory) {
                        pw.println(m.getEventTime() + " " + m.getMachineName() + " " + m.getEventType() + " " + ((Inventory) m).getInventoryType() + " " + ((Inventory) m).getInventoryStatus());
                    } else if (m instanceof Policy) {
                        pw.println(m.getEventTime() + " " + m.getMachineName() + " " + m.getEventType() + " " + ((Policy) m).getPolicyId() + " " + ((Policy) m).getPolicyStatus());
                    } else {
                        pw.println(m.getEventTime() + " " + m.getMachineName() + " " + m.getEventType() + " " + ((SoftwareUpdate) m).getSoftwareUpdateId() + " " + ((SoftwareUpdate) m).getSoftwareUpdateStatus());
                    }
                }
                pw.println("End of events");
                pw.close();
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex);
            }
        }
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
