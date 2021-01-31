import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main (String[] args) {
        boolean exit = false;
        Menu menu = new Menu();
        LogReader logReader = new LogReader();
        Utils utils = new Utils();
        HashMap<String, ArrayList<Event>> events = logReader.Parser();
        while (!exit) {
            int choice = menu.showMenu();
            exit = choice == 0;
            menu.runMethod(choice, events);
            utils.printDivider();
        }
    }
}
