import java.util.Scanner;

public class Utils {
    Scanner scan = new Scanner(System.in);
    public int getInput(String placeholder) {
        int choice = -1;
        while (choice < 0 || choice > 3) {
            try {
                System.out.print(placeholder);
                choice = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection! Please try again!");
            }
        }
        return choice;
    }

    public String getString() {
        return scan.nextLine();
    }
}
