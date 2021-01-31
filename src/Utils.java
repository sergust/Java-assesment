import java.util.Scanner;

public class Utils {
    Scanner scan = new Scanner(System.in);
    public int getInput(String placeholder) {
        int choice = -1;
        while (choice < 0 || choice > 3) {
            try {
                System.out.print(placeholder);
                choice = Integer.parseInt(scan.nextLine());
                if(choice < 0 || choice > 3){
                    System.out.println("Please enter a value from 1 - 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a value from 1 - 3");
            }
        }
        return choice;
    }

    public String getString() {
        return scan.nextLine();
    }

    public void printDivider() {
        System.out.println("                                     ");
        System.out.println("*************************************");
        System.out.println("                                     ");
    }
}
