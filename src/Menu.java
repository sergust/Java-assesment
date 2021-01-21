import org.omg.Messaging.SyncScopeHelper;

import java.io.File;
import java.io.PrintWriter;
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

    public void getMachineList(ArrayList<Event> events) {
        ArrayList<String> machineList = new ArrayList<>();

        for(Event e: events){
            if (machineList.contains(e.getMachineName()) == false){
                machineList.add(e.getMachineName());
            }
        }

        System.out.println("List of Machines with reported events");
        for(String event: machineList)
            System.out.println(event);
        // Show event list

        showMenu();
    }

    public void makeFileOfMachineEvents(String machine, ArrayList<Event> events) {
        ArrayList<Event> machineEventsList = new ArrayList();
        for(Event e: events){
            if (e.getMachineName().equalsIgnoreCase(machine)){
                machineEventsList.add(e);
            }
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(machine + "-report.txt");
            for (Event m : machineEventsList) {
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
        }catch(Exception ex){
            System.out.println("ERROR: " + ex);
        }
        // Find machine and retrieve all event
    }

    public void showFailedEvents(ArrayList<Event> events) {
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
