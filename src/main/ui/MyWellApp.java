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
    private Feeling currentFeeling;
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
    @SuppressWarnings("methodlength")
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
                System.out.println(addSensationPrompt());
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
            case "addSensationNote":
                System.out.println("\nPlease, leave a note associated with this sensation.");
                System.out.println("\tYou can leave it blank by pressing enter");
                break;
            case "addFeeling":
                System.out.println(addFeelingPrompt());
                break;
            case "addFeelingName":
                System.out.println("\nPlease, enter the feeling you're experiencing.");
                break;
            case "addFeelingIntensity":
                System.out.println("\nOn a scale from 1 to 5, how strong is your feeling?");
                break;
            case "addFeelingNote":
                System.out.println("\nPlease, leave a note associated with this feeling.");
                System.out.println("\tYou can leave it blank by pressing enter");
                break;
            case "finalize":
                System.out.println("\nYou have successfully created an entry");
                break;
            default:
                break;
        }
    }

    private String addSensationPrompt() {
        String prompt = "Are you experiencing any physical sensation in the moment?";
        if (entry.getSensations().size() > 0) {
            prompt = "Are you experiencing any other physical sensation in the moment?";
        }
        return prompt;
    }

    private String addFeelingPrompt() {
        String prompt = "Would you like to make an entry about how you feel?";
        if (entry.getSensations().size() > 0) {
            prompt = "Would you like to make another entry about how you feel?";
        }
        return prompt;
    }

    private void handleAddFeelingIntensity(String command) {
        int intensity = Integer.parseInt(command);
        this.currentFeeling.setIntensity(intensity);
        this.menu = "addFeelingNote";
    }

    private void handleAddFeelingNote(String command) {
        this.currentFeeling.setNote(command);
        this.menu = "finalize";
    }

    private void handleFinalize(String command) {

    }

    private void handleViewEntries(String command) {

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
    //EFFECTS processes input for addFeeling menu
    private void handleAddFeeling(String command) {
        if (command.equals("y")) {
            this.menu = "addFeelingName";
        } else {
            this.menu = "finalize";
        }
    }

    //MODIFIES this
    //EFFECTS processes input for addFeelingName menu
    private void handleAddFeelingName(String feeling) {
        this.currentFeeling = new Feeling(feeling);
        this.entry.addFeeling(currentFeeling);
        this.menu = "addFeelingIntensity";
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
        this.menu = "addSensation";
    }

    //EFFECTS routes menu input to the right handler
    @SuppressWarnings("methodlength")
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
            case "addFeeling":
                handleAddFeeling(command);
                break;
            case "addFeelingName":
                handleAddFeelingName(command);
                break;
            case "addFeelingIntensity":
                handleAddFeelingIntensity(command);
                break;
            case "addFeelingNote":
                handleAddFeelingNote(command);
                break;
            case "finalize":
                handleFinalize(command);
                break;
            case "viewEntries":
                handleViewEntries(command);
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
