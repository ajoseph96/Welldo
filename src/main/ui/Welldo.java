package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;


//represents the UI of the application

public class Welldo {
    private static String JSON_STORE;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private MoodScore mood;

    private Scanner insert;

    private int instruction;

    private String stringInstruction;

    private SadTasks sad;
    private HappyTasks happy;
    private AngryTasks angry;
    private CompletedTasks completed;


    // EFFECTS: constructor for Welldo class, creates initial fields and calls runWelldo() method.
    public Welldo() throws FileNotFoundException {

        sad = new SadTasks();
        happy = new HappyTasks();
        angry = new AngryTasks();
        completed = new CompletedTasks();
        runWelldo();


    }

    // EFFECTS: allows the ui to run and provides initial values for variables relating to initial menu
    public void runWelldo() {
        boolean continueRunning = true;
        instruction = 0;
        stringInstruction = null;
        initialize();
        menuInterface();
        while (continueRunning) {
            stringInstruction = insert.nextLine();
            if (Integer.parseInt(stringInstruction) == 00) {
                continueRunning = false;
            } else {
                takeInstruction(Integer.parseInt(stringInstruction));
            }
        }
    }

    // EFFECTS: provides initial parameters for menu to run
    public void initialize() {
        mood = new MoodScore();
        insert = new Scanner(System.in);
    }

    // EFFECTS: prints out options related to initial menu
    public void menuInterface() {
        System.out.println("\n Welcome to Welldo! Your personalized Mental Health Task Tracker");
        System.out.println("\tHow are you feeling today? Please rate your mood on a scale of 1-10.");
        System.out.println("\tenter 00 to quit");
    }

    //REQUIRES: input entered be an integer that ranges from 1-10
    //MODIFIES: this
    // EFFECTS: sets initial mood score and receives and passes initial emotion to moodNavigator() method
    public void takeInstruction(int instruction) {
        if (instruction == 1 || instruction == 2 || instruction == 3
                || instruction == 4 || instruction == 5 || instruction == 6
                || instruction == 7 || instruction == 8 || instruction == 9 || instruction == 10) {
            mood.setInitialMoodNum(instruction);
            System.out.println("Thank-you for sharing, I'm here to help");
            System.out.println("Next can you tell me what mood you're primarily feeling? (Only if possible)");
            System.out.println("\nSelect from:");
            System.out.println("\ts -> sad");
            System.out.println("\th -> happy");
            System.out.println("\ta -> angry");
            stringInstruction = insert.nextLine();
            moodNavigator(stringInstruction);
        } else {
            System.out.println("input not valid...");
        }
    }

    //REQUIRES: emotional input from takeInstruction() method
    //MODIFIES: this
    // EFFECTS: provides list of helpful tasks based on emotional input from takeInstruction method.
    @SuppressWarnings("methodlength")
    public void moodNavigator(String s) {

        if (s.equals("s")) {
            System.out.println("I see you are feeling sad, lets see if we can help manage these difficult feelings, ");
            System.out.println("in these cases here are some tasks we suggest:");
            JSON_STORE = "./data/sadTasks.json";
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonReader = new JsonReader(JSON_STORE);
            System.out.println(sad.getTaskList());
            taskAdder(sad);
        } else if (s.equals("h")) {
            System.out.println("I see you are feeling happy, I'm so glad,");
            System.out.println("in these cases here are some tasks we suggest to help maintain this mood:");
            System.out.println(happy.getTaskList());
            JSON_STORE = "./data/happyTasks.json";
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonReader = new JsonReader(JSON_STORE);
            taskAdder(happy);
        } else if (s.equals("a")) {
            System.out.println("I see you are feeling angry, lets see if we can help manage these difficult feelings");
            System.out.println("in these cases here are some tasks we suggest:");
            System.out.println(angry.getTaskList());
            JSON_STORE = "./data/angryTasks.json";
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonReader = new JsonReader(JSON_STORE);
            taskAdder(angry);
        } else {
            System.out.println("Selection not valid...please restart!");
            System.exit(0);

        }
    }

    //REQUIRES: emotional input from moodNavigator()
    //MODIFIES: this
    // EFFECTS: allows user to add tasks to emotion specific task lists, save them and prints them out for review
    @SuppressWarnings("methodlength")
    public void taskAdder(Tasks t) {
        System.out.println("\nI understand that these choices are fairly limited. Based on your selected emotion");
        System.out.println("is there another task you find helps you most during these times?");
        System.out.println("If you would like to add a task please enter a (for add).");
        System.out.println("If you would like to load a previous task list please enter l (for load).");
        System.out.println("You cannot add new tasks if load is selected.");
        System.out.println("If you are not interested in adding any please enter p (for pass).");
        stringInstruction = insert.nextLine();
        if (stringInstruction.equals("a")) {
            boolean addTasks = true;
            System.out.println("I see you would like to add a task, please enter a task you would like to add below,");
            System.out.println("if you are done adding tasks please enter p.");
            while (addTasks) {
                stringInstruction = insert.nextLine();
                if (stringInstruction.equals("p")) {
                    addTasks = false;
                    System.out.println("No additional tasks to add at this time.");
                    System.out.println("Would you like to save these added tasks?");
                    completedTasks();
                } else if (stringInstruction.equals("s")) {
                    saveTasks(t);
                    System.out.println("Task saved!");
                    completedTasks();
                } else {
                    t.addTaskList(stringInstruction);
                    System.out.println("Task added! Here are the tasks we now suggest");
                    System.out.println("based on your previously selected emotion.");
                    System.out.println(t.getTaskList());
                    System.out.println("To add another task enter it below,");
                    System.out.println("otherwise please enter s (for save)/to permanently add these tasks");
                    System.out.println("or please enter p (for pass)/to move to the next step.");
                }
            }
        }
        if (stringInstruction.equals("p")) {
            System.out.println("No task to add at this time.");
            completedTasks();
        } else if (stringInstruction.equals("l")) {
            Tasks newt;
            newt = loadWorkRoom(t);
            System.out.println(newt.getTaskList());
            completedTasks();
        } else {
            System.out.println("Selection not valid...please restart!");
            System.exit(0);

        }

    }

    //MODIFIES: this
    //EFFECTS: allows user to add tasks they have completed to a completed task list
    @SuppressWarnings("methodlength")
    public void completedTasks() {
        System.out.println("\nDid you complete any of the previously mentioned tasks today?");
        System.out.println("If so please enter y (for yes). If not please enter n (for no).");
        stringInstruction = insert.nextLine();
        if (stringInstruction.equals("y")) {
            boolean addCompleted = true;
            System.out.println("Please enter a task you completed below.");
            while (addCompleted) {
                stringInstruction = insert.nextLine();
                if (stringInstruction.equals("n")) {
                    addCompleted = false;
                    System.out.println("No additional tasks to add at this time.");
                    newMood();
                } else {
                    completed.addTaskList(stringInstruction);
                    System.out.println("Task added! To add another task you completed enter it below,");
                    System.out.println("otherwise please enter n (for no)/to move to the next step.");
                }
            }

        }
        if (stringInstruction.equals("n")) {
            System.out.println("No task to add at this time.");
            newMood();
        } else {
            System.out.println("Selection not valid...please restart!");
            System.exit(0);
        }
    }

    //REQUIRES: input entered to be an integer that ranges from 1-10
    //MODIFIES: this
    // EFFECTS: sets final mood score
    public void newMood() {
        System.out.println("\nAs we are now coming to the end of the day please tell us how your mood has changed.");
        System.out.println("Once again please rate your current mood on a scale of 1-10.");
        stringInstruction = insert.nextLine();
        int score = Integer.parseInt(stringInstruction);
        if (score == 1 || score == 2 || score == 3
                || score == 4 || score == 5 || score == 6
                || score == 7 || score == 8 || score == 9 || score == 10) {
            mood.setFinalMoodNum(score);
            summaryStats();
        } else {
            System.out.println("Selection not valid...please restart!");
            System.exit(0);
        }
    }


    //MODIFIES: this
    // EFFECTS: calculates mood score difference and provides a summary for the day.
    @SuppressWarnings("methodlength")
    public void summaryStats() {
        if (mood.calculateMoodNumDifference()) {
            if (((completed.getTaskList()).isEmpty())) {
                System.out.println("It seems like your mood changed for the better! I'm so glad.");
                System.out.println("Your mood score improved by" + " " + Math.abs(mood.getMoodNumDifference()));
                System.out.println("It seems no tasks were completed today but regardless your mood improved!");
                System.out.println("Thank-you for using Welldo, I hope this information was helpful.");
                System.out.println("Hope you continue to have a great day!");
                System.exit(0);
            } else {
                System.out.println("It seems like your mood changed for the better! I'm so glad.");
                System.out.println("Your mood score improved by" + " " + Math.abs(mood.getMoodNumDifference()));
                System.out.println("and these task(s) seemed to help:" + completed.getTaskList());
                System.out.println("Thank-you for using Welldo, I hope this information was helpful.");
                System.out.println("Hope you continue to have a great day!");
                System.exit(0);
            }
        } else {
            System.out.println("It seems like your mood did not improve on this day");
            System.out.println("That's ok! Maybe you were already feeling great or");
            System.out.println("today was just a tougher day, regardless I am so proud you tried your best.");
            System.out.println("Maybe the type of tasks completed today");
            System.out.println("or any difficulty completing them contributed to this.");
            System.out.println("There is always tomorrow, I'll see you then!");
            System.exit(0);
        }

    }

    // EFFECTS: saves the workroom to file
    private void saveTasks(Tasks t) {
        try {
            jsonWriter.open();
            jsonWriter.write(t);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tasks from file
    private Tasks loadWorkRoom(Tasks ta) {
        try {
            ta = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
            return ta;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        return ta;
    }

}



