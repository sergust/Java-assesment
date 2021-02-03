
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LogReader {

    private File getFile() {
        Utils utils = new Utils();
        boolean exit = false;
        File file = null;
        while (!exit) {
            try {
                System.out.println("Enter name of log file to read: ");
                String fileName = utils.getString();
                file = new File(fileName);
                new Scanner(file);
                exit = true;
            } catch (FileNotFoundException e) {
                System.out.println("File is not found");
            }
        }
        return file;
    }

    public HashMap<String, ArrayList<Event>> Parser() {
        Scanner scan;
        File log;
        ArrayList<Event> eventLog = new ArrayList<>();
        HashMap <String, ArrayList<Event>> machineList = null;

        String eventArray[] = {};

        String eventType;

        try{
            log = getFile();
            scan = new Scanner(log);
            String numberOfEvents = scan.nextLine();
            Event e = null;
            machineList = new HashMap<String, ArrayList<Event>>();
            while(scan.hasNext()){

                String event = scan.nextLine();
                eventArray = event.split(" ");
                //eventArray[0] Event Time
                //eventArray[1] Machine Name
                //eventArray[2] Event Type
                //eventArray[3] Event Code
                //eventArray[4] Event Status
                String machineName = eventArray[1].toLowerCase();
                if(!machineList.containsKey(machineName)){
                    machineList.putIfAbsent(machineName, new ArrayList<>());
                    machineList.get(machineName).add(checkInstance(eventArray[0],eventArray[1],eventArray[2],eventArray[3],eventArray[4]));
                }else{
                    machineList.get(machineName).add(checkInstance(eventArray[0],eventArray[1],eventArray[2],eventArray[3],eventArray[4]));
                }
            }

        } catch(Exception e){
            System.out.println("ERROR" + e);
        }

        return machineList;
    }

    //checks and creates the corresponding object type
    public Event checkInstance(String eventTime, String machineName, String eventType, String eventCode, String eventStatus){
        Event e;
        if(eventType.equalsIgnoreCase("SOFTWAREUPDATES")){
            e = new SoftwareUpdate(eventTime, machineName, eventType, eventCode, eventStatus);
            return e;
        }else if(eventType.equalsIgnoreCase("POLICY")){
            e = new Policy(eventTime, machineName, eventType, eventCode, eventStatus);
            return e;
        }else{
            e = new Inventory(eventTime, machineName, eventType, eventCode, eventStatus);
            return e;
        }
    }
}
