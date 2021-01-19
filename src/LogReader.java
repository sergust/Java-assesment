import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogReader {
    private File getFile() {
        Utils utils = new Utils();
        boolean exit = false;
        File file = null;
        while (!exit) {
            try {
                System.out.println("Enter name of file: ");
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

    public ArrayList<Event> Parser() {
        Scanner scan = null;
        File log = null;
        ArrayList<Event> eventLog = new ArrayList<>();

        String eventArray[] = {};

        String eventType;

        try{
            log = getFile();
            scan = new Scanner(log);
            String numberOfEvents = scan.nextLine();
            Event e = null;
            while(scan.hasNext()){
                String event = scan.nextLine();
                eventArray = event.split(" ");
                eventType = eventArray[1];

                if(eventType.equalsIgnoreCase("SOFTWAREUPDATE")){
                    e = new SoftwareUpdate(eventArray[0], eventArray[1], eventArray[2], eventArray[3], eventArray[4]);
                    eventLog.add(e);
                }else if(eventType.equalsIgnoreCase("POLICY")){
                    e = new Policy(eventArray[0], eventArray[1], eventArray[2], eventArray[3], eventArray[4]);
                    eventLog.add(e);
                }else{
                    e = new Inventory(eventArray[0], eventArray[1], eventArray[2], eventArray[3], eventArray[4]);
                    eventLog.add(e);
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }

        for(Event e:eventLog){
            if(e.getEventType().equalsIgnoreCase("INVENTORY")){
                e.printEvent();
                System.out.println(((Inventory)e).getInventoryType());
            }
        }
        return eventLog;
    }
}
