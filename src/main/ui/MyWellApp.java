package ui;

import model.Journal;
import model.JournalEntry;

import java.util.Scanner;
//credits to TellerApp from
// https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/master/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
// for providing ideas on how to structure the command line user interface

//MyWell command line journaling interface
public class MyWellApp {
    private Journal journal;
    private Scanner input;
    private String menu;

    //EFFECTS starts the journaling interface
    public MyWellApp() {
        this.menu = "main";
        journaling();
    }


    //EFFECTS processes initial user input
    public void journaling() {
        boolean keepGoing = true;
        String command = null;

        journal = new Journal();
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (acceptableInput()) {
                processInput(command);
            } else {
                System.out.println("This is not valid input, please, try again.");
            }
        }

        System.out.println("\nGoodbye!");
    }

    //EFFECTS returns true if command is acceptable for the given menu
    private boolean acceptableInput() {
        return true;
    }

    private void displayMenu() {
        switch (this.menu) {
            case "main":
                System.out.println("\nWelcome to MyWell! What would you like to do?");
                System.out.println("\t1 -> Make a journal entry");
                System.out.println("\t2 -> Review past entries");
                break;
            case "addNewEntry":
                System.out.println("\nLet's make a new journal entry.");
                System.out.println("\tWhat is the overall state of your well-being at this moment?");
                System.out.println("\t0 -> amazing");
                System.out.println("\t1 -> good");
                System.out.println("\t2 -> ok");
                System.out.println("\t3 -> bad");
                System.out.println("\t4 -> terrible");
                break;
            case "addSensation":
                System.out.println("\nAre you experiencing any physical sensation in the moment?");
                break;
            default:
                break;
        }
    }

    private void processInput(String command) {
        switch (this.menu) {
            case "main":
                switch (command) {
                    case "1":
                        this.menu = "addNewEntry";
                        break;
                    case "2":
                        this.menu = "reviewEntries";
                        break;
                    default:
                        break;
                }
                break;
            case "addNewEntry":
                String state = "";
                switch (command) {
                    case "0": state = "amazing";
                    break;
                    case "1": state = "good";
                    break;
                    case "2": state = "ok";
                    break;
                    case "3": state = "bad";
                    break;
                    case "4": state = "terrible";
                    break;
                }
                JournalEntry entry = new JournalEntry(state);
                this.menu = "addSensation";
                break;
            default:
                break;
        }

    }

}
