import java.io.File;
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
        exit = false;
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
        PrintWriter pw = null;

        if (events.containsKey(machine)) {
            try {
                File file = new File("C:\\Users\\Jake Antonio\\Documents\\AA School Stuff\\02. Masters in IT\\[Term 1] ICT711 - Programming and Algorithms\\Java-assesment-main\\" + machine + "-report.txt");
                pw = new PrintWriter(file);
                pw.println("Events for machine '" + machine + "'\n");
                for(Event ev: events.get(machine)) {
                    if (ev.getEventType().equalsIgnoreCase("INVENTORY")) {
                        Inventory iEv = (Inventory) ev;
                        pw.println(iEv.getEventTime() + " " + iEv.getMachineName() + " " + iEv.getEventType() + " " + iEv.getInventoryType() + " " + iEv.getInventoryStatus() + "\n");
                    }
                    else if (ev.getEventType().equalsIgnoreCase("POLICY")) {
                        Policy pEv = (Policy) ev;
                        pw.println(pEv.getEventTime() + " " + pEv.getMachineName() + " " + pEv.getEventType() + " " + pEv.getPolicyId() + " " + pEv.getPolicyStatus() + "\n");
                    }
                    else if (ev.getEventType().equalsIgnoreCase("SOFTWAREUPDATES")) {
                        SoftwareUpdate sEv = (SoftwareUpdate) ev;
                        pw.println(sEv.getEventTime() + " " + sEv.getMachineName() + " " + sEv.getEventType() + " " +  sEv.getSoftwareUpdateId() + " " + sEv.getSoftwareUpdateStatus() + "\n");
                    }
                }
                pw.println("End of events.");
                pw.close();
            }
            catch (Exception e) {
                System.out.println("ERROR FOUND.");
            }
        }
        else {
            System.out.println("!! INVALID MACHINE NAME !! ");
            System.out.println("Enter machine name: ");
            makeFileOfMachineEvents(utils.getString(), events);
        }
    }

    //check all events on the logfile and display all failed events
    public void showFailedEvents(HashMap<String, ArrayList<Event>> events) {
        ArrayList<String> failedEvents = new ArrayList<>();
        Iterator it = events.entrySet().iterator();
        try{
            System.out.println("\nSoftware updates that Failed:");
            while(it.hasNext()){
                Map.Entry pair = (Map.Entry) it.next();
                for(Event e: events.get(pair.getKey())){
                    if(e instanceof SoftwareUpdate){
                        if(((SoftwareUpdate) e).getSoftwareUpdateStatus().equalsIgnoreCase("Failed")){
                            System.out.println(((SoftwareUpdate) e).getMachineName() + ": update " +((SoftwareUpdate) e).getSoftwareUpdateId());
                        }
                    }
                    else if(e instanceof Inventory){
                        if(((Inventory) e).getInventoryStatus().equalsIgnoreCase("interrupted"))
                            failedEvents.add( ((Inventory) e).getMachineName() + ": " + ((Inventory) e).getInventoryType() + " Inventory");
                    }
                }

                it.remove();
            }
            if(!failedEvents.isEmpty()){
                System.out.println ("\nInventory actions that Failed to complete:");
                for(String failed: failedEvents){
                    if(failed.contains("INVENTORY"))
                        System.out.println(failed);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void printHeader() {
        System.out.println("Menu");
        System.out.println("Please make a selection:");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("1. Print list of machines that have reported any events");
        System.out.println("2. Make a file reporting all events for a nominated machine");
        System.out.println("3. Print all events signifying failed situations across all machines");
        System.out.println("0. Exit");
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
