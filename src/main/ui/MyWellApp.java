package ui;

import model.Feeling;
import model.Journal;
import model.JournalEntry;
import model.Sensation;

import java.util.Scanner;
//credits to TellerApp from
// https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/master/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
// for providing ideas on how to structure the command line user interface

//MyWell command line journaling interface
public class MyWellApp {
    private Journal journal;
    private Scanner input;
    private JournalEntry entry;
    private Sensation currentSensation;
    private String menu;

    //EFFECTS starts the journaling interface at the main menu
    public MyWellApp() {
        this.menu = "main";
        journaling();
    }

    //MODIFIES this
    //EFFECTS processes user input
    public void journaling() {
        boolean keepGoing = true;
        String command;

        this.journal = new Journal();
        this.input = new Scanner(System.in).useDelimiter("\n");

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

    //EFFECTS routes execution of prompts for current menu selection
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
                System.out.println("\ty - yes");
                System.out.println("\tn - no");
                break;
            case "addSensationBodyPart":
                System.out.println("\tWhere in the body are you experiencing your sensation?");
                System.out.println("\tFor example, you can say \"left arm\" or \"chest\"...");
                break;
            case "addSensationType":
                System.out.println("\nHow does this sensation feel?");
                System.out.println("\tp - pleasant");
                System.out.println("\tu - unpleasant");
                System.out.println("\tn - neutral");
                break;
            case "addSensationIntensity":
                System.out.println("\nHow strong is this sensation?");
                System.out.println("\tEnter a value from 1 to 5, where 1 is Very Mild to 5 Very Strong");
                break;
            default:
                break;
        }
    }

    //MODIFIES this
    //EFFECTS processes input for addSensation menu
    private void handleAddSensation(String command) {
        if (command.equals("y")) {
            this.menu = "addSensationBodyPart";
        } else {
            this.menu = "addFeeling";
        }
    }

    //MODIFIES this
    //EFFECTS creates a new sensation and sets the menu to addSensationIntensity
    private void handleAddSensationBodyPart(String bodyPart) {
        this.currentSensation = new Sensation(bodyPart);
        this.entry.addSensation(currentSensation);
        System.out.println(this.entry.getSensations().get(0).getBodyPart());
        this.menu = "addSensationType";
    }

    private void handleAddSensationType(String type) {
        System.out.println("Your input was " + type);
        String sensationType;
        switch (type) {
            case "u":
                sensationType = "unpleasant";
                break;
            case "p":
                sensationType = "pleasant";
                break;
            case "n":
                sensationType = "neutral";
                break;
            default: sensationType = "neutral";
        }
        this.currentSensation.setSensationType(sensationType);
        this.menu = "addSensationIntensity";
    }

    private void handleAddSensationIntensity(String command) {
        int intensity = Integer.parseInt(command);
        this.currentSensation.setIntensity(intensity);
        this.menu = "addSensationNote";
    }

    private void handleAddSensationNote(String command) {
        this.currentSensation.setNote(command);
    }

    //EFFECTS routes menu input to the right handler
    private void processInput(String command) {
        switch (this.menu) {
            case "main":
                handleMain(command);
                break;
            case "addNewEntry":
                handleAddNewEntry(command);
                break;
            case "addSensation":
                handleAddSensation(command);
                break;
            case "addSensationBodyPart":
                handleAddSensationBodyPart(command);
                break;
            case "addSensationType":
                handleAddSensationType(command);
                break;
            case "addSensationIntensity":
                handleAddSensationIntensity(command);
                break;
            case "addSensationNote":
                handleAddSensationNote(command);
                break;
            default:
                break;
        }

    }

    //MODIFIES this
    //EFFECTS processes input for main menu
    private void handleMain(String command) {
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
    }

    //MODIFIES this
    //EFFECTS processes input for the addNewEntry menu
    private void handleAddNewEntry(String command) {
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
        this.entry = new JournalEntry(state);
        this.menu = "addSensation";
    }

}
