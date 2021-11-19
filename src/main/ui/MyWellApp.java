package ui;

import model.Feeling;
import model.Journal;
import model.JournalEntry;
import model.Sensation;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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
    private static final String JSON_STORE = "./data/journal.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean journalLoaded;

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
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        journalLoaded = loadJournal();
        printWelcome();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                saveSession();
                keepGoing = false;
            } else if (acceptableInput()) {
                processInput(command);
            } else {
                System.out.println("This is not valid input, please, try again.");
            }
        }

        System.out.println("\nGoodbye!");
    }

    //EFFECTS prints out a welcome message
    private void printWelcome() {
        System.out.println("\nWelcome to MyWell!");
        if (journalLoaded) {
            System.out.println("\nYour previous entries were loaded from storage.");
        }
    }

    //This remains to be completed
    //EFFECTS returns true if command is acceptable for the given menu
    private boolean acceptableInput() {
        return true;
    }

    //MODIFIES this
    //EFFECTS displays menu options for each menu
    @SuppressWarnings("methodlength")
    private void displayMenu() {
        switch (this.menu) {
            case "main":
                System.out.println("\nWhat would you like to do?");
                System.out.println("\t1 -> Make a journal entry");
                System.out.println("\t2 -> Review past entries");
                System.out.println("\t3 -> Save progress");
                System.out.println("\tq -> Quit session");
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
                System.out.println("\ty - yes");
                System.out.println("\tn - no");
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
                printEntry(this.entry);
                System.out.println("\nWhat would you like to do?");
                System.out.println("\tm -> go to main menu");
                System.out.println("\tq -> quit the app");
                break;
            case "viewEntries":
                viewSummary();
                System.out.println("\nWould you like to read all entries?");
                System.out.println("\ty - yes");
                System.out.println("\tn - no");
            default:
                break;
        }
    }

    //EFFECTS handles user input for each menu
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

    private boolean loadJournal() {
        try {
            this.journal = jsonReader.readJournal();
            return true;
        } catch (IOException e) {
            System.out.println("Exception");
            return false;
        }
    }

    //MODIFIES this
    //EFFECTS handles input for the main menu and changes menu for the next prompt
    private void handleMain(String command) {
        switch (command) {
            case "1":
                this.menu = "addNewEntry";
                break;
            case "2":
                this.menu = "viewEntries";
                break;
            case "3":
                saveSession();
                break;
            default:
                break;
        }
    }

    //MODIFIES this
    //EFFECTS adds new entry and changes menu for the next prompt
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

    //EFFECTS creates a prompt for entering sensation depending on whether or not a sensation was already entered
    private String addSensationPrompt() {
        String prompt = "Are you experiencing any physical sensation in the moment?";
        if (entry.getSensations().size() > 0) {
            prompt = "Are you experiencing any other physical sensation in the moment?";
        }
        return prompt;
    }

    //MODIFIES this
    //EFFECTS handles routing for adding sensation
    private void handleAddSensation(String command) {
        if (command.equals("y")) {
            this.menu = "addSensationBodyPart";
        } else {
            this.menu = "addFeeling";
        }
    }

    //MODIFIES this
    //EFFECTS creates a new sensation and changes menu for the next prompt
    private void handleAddSensationBodyPart(String bodyPart) {
        this.currentSensation = new Sensation(bodyPart);
        this.entry.addSensation(currentSensation);
        System.out.println(this.entry.getSensations().get(0).getBodyPart());
        this.menu = "addSensationType";
    }

    //MODIFIES this
    //EFFECTS adds type to the current sensation and changes menu for the next prompt
    private void handleAddSensationType(String type) {
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

    //MODIFIES this
    //EFFECTS adds intensity to current sensation and changes menu for the next prompt
    private void handleAddSensationIntensity(String command) {
        int intensity = Integer.parseInt(command);
        this.currentSensation.setIntensity(intensity);
        this.menu = "addSensationNote";
    }

    //MODIFIES this
    //EFFECTS adds note to the current sensation and changes menu for the next prompt
    private void handleAddSensationNote(String command) {
        this.currentSensation.setNote(command);
        this.menu = "addSensation";
    }

    //EFFECTS creates a prompt for entering feeling depending on whether or not a feeling was already entered
    private String addFeelingPrompt() {
        String prompt = "Would you like to make an entry about how you feel?";
        if (entry.getSensations().size() > 0) {
            prompt = "Would you like to make another entry about how you feel?";
        }
        return prompt;
    }

    //MODIFIES this
    //EFFECTS handles routing for adding feeling
    //        as the last step in journal entry, adds entry to the journal
    private void handleAddFeeling(String command) {
        if (command.equals("y")) {
            this.menu = "addFeelingName";
        } else {
            journal.addJournalEntry(entry);
            this.menu = "finalize";
        }
    }

    //MODIFIES this
    //EFFECTS adds a new feeling by name and changes menu for the next prompt
    private void handleAddFeelingName(String feeling) {
        this.currentFeeling = new Feeling(feeling);
        this.entry.addFeeling(currentFeeling);
        this.menu = "addFeelingIntensity";
    }

    //MODIFIES this
    //EFFECTS adds intensity to the currently logged feeling and changes menu for the next prompt
    private void handleAddFeelingIntensity(String command) {
        int intensity = Integer.parseInt(command);
        this.currentFeeling.setIntensity(intensity);
        this.menu = "addFeelingNote";
    }

    //MODIFIES this
    //EFFECTS adds note to the currently logged feeling and changes menu for the next prompt
    private void handleAddFeelingNote(String command) {
        this.currentFeeling.setNote(command);
        this.menu = "addFeeling";
    }

    //MODIFIES this
    //EFFECTS handles the user input for viewing entries and returns to main menu
    private void handleViewEntries(String command) {
        if (command.equals("y")) {
            printAllEntries();
        }
        this.menu = "main";
    }

    //EFFECTS provides overall information about journaling for the last 30 days
    private void viewSummary() {
        int entriesLastMonth = journal.countRecentEntries(30);
        System.out.println("\nYou created " + entriesLastMonth + " entries in the last 30 days");
        printStates();
    }

    //MODIFIES this
    //EFFECTS handles return to main menu
    private void handleFinalize(String command) {
        if (command.equals("m")) {
            this.menu = "main";
        }
    }

    //EFFECTS prints all entries in journal
    private void printAllEntries() {
        if (journal.size() > 0) {
            for (JournalEntry entry : journal.getEntries()) {
                printEntry(entry);
            }
        }
    }

    //EFFECTS prints the content of a journal entry
    private void printEntry(JournalEntry entry) {
        System.out.println("\n\n\nEntered on: " + entry.getDate());
        System.out.println("\tYour overall state was: " + entry.getOverallState());
        printSensations(entry);
        printFeelings(entry);
    }

    //EFFECTS prints all sensations in an entry
    private void printSensations(JournalEntry entry) {
        if (entry.getSensations().size() > 0) {
            for (Sensation sensation : entry.getSensations()) {
                System.out.println("\nYour sensation was in: " + sensation.getBodyPart());
                System.out.println("\tThis sensation felt: " + sensation.getSensationType());
                System.out.println("\tThe intensity on a 5pt scale was: " + sensation.getIntensity());
                System.out.println("\tYou left the following note: " + sensation.getNote());
            }
        } else {
            System.out.println("\nYou haven't logged any sensations.");
        }
    }

    //EFFECTS prints all feelings in an entry
    private void printFeelings(JournalEntry entry) {
        if (entry.getFeelings().size() > 0) {
            for (Feeling feeling : entry.getFeelings()) {
                System.out.println("\nYou felt: " + feeling.getFeelingName());
                System.out.println("\tThe intensity on a 5pt scale was: " + feeling.getIntensity());
                System.out.println("\tYou left the following note: " + feeling.getNote());
            }
        } else {
            System.out.println("\nYou haven't logged any feelings.");
        }
    }

    //EFFECTS prints information about past overall states
    private void printStates() {
        HashMap<String, Integer> states = journal.countEntriesForAllStates(30);
        for (String state : states.keySet()) {
            System.out.println("You felt " + state + " " + states.get(state) + " times.");
        }
    }

    private void saveSession() {
        try {
            jsonWriter.open();
            jsonWriter.write(journal);
            jsonWriter.close();
            System.out.println("Saved your journal to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
