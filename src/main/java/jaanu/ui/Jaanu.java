package jaanu.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jaanu.Task.Task;
import jaanu.Task.Deadline;
import jaanu.Task.Event;
import jaanu.JaanuException.JaanuException;

import java.io.FileNotFoundException;
import java.io.FileWriter;


public class Jaanu {
    private static final int PARSED_ARGS_SIZE = 3;
    private static final String DIVIDER_LINE = "____________________________________________________________\n";

    // A-Collections: use Java Collections
    private static final List<Task> tasks = new ArrayList<>();
    private static final String[] parsedArgs = new String[PARSED_ARGS_SIZE];

    //Relative path for stored file
    private static final String filePath = "jaanu.txt";

    public static void printList() throws JaanuException {
        if (tasks.isEmpty()) {
            throw new JaanuException("bruh your list is empty lol. add something first babes");
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String doneChar = t.getStatusIcon();
            String classChar = t.getClassIcon();
            System.out.println((i + 1) + ". " + t.toString());
        }
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void loadTasks(String input) throws JaanuException{
        String[] unparsedTaskList = input.split("\n");
        String classChar, doneChar, description, input_task;
        int numOfTasks = unparsedTaskList.length;
        if (!(input.contains("\n"))) {
            numOfTasks = 0;
        }
        for (int i = 0; i < numOfTasks ; i++) {
            input_task = unparsedTaskList[i];
            Task t;
            String[] input_fields = input_task.split("/");
            //fine che
            classChar = input_fields[0];
            doneChar = input_fields[1];
            description = input_fields[2];
            switch (classChar) {
            case "T":
                t = new Task(description); // "todo "
                tasks.add(t);
                break;
            case "D":
                String by = input_fields[3];
                t = new Deadline(description, by); // "todo "
                tasks.add(t);
                break;
            case "E":
                String from = input_fields[3];
                String to = input_fields[4];
                t = new Event(description, from, to); // "todo "
                tasks.add(t);
                break;
            default:
                throw new JaanuException("logic error");
            }
            if (doneChar.equals("X")) {
                t.setAsDone();
            }
        }
    }

    private static void updateFile() throws JaanuException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                String temp;
                String lineToWrite;
                switch (tasks.get(i).getClassIcon()){
                case "T" :
                    Task t = tasks.get(i);
                    temp = "";
                    lineToWrite = t.getClassIcon() + "/" + t.getStatusIcon() + "/" + t.toStringMain() +temp +"\n" ;
                    break;
                case "D" :
                    Deadline d = (Deadline) tasks.get(i);
                    temp = "/"+d.getBy();
                    lineToWrite = d.getClassIcon() + "/" + d.getStatusIcon() + "/" + d.toStringMain() +temp +"\n" ;
                    break;
                case "E" :
                    Event e = (Event) tasks.get(i);
                    temp = "/"+e.getFrom()+"/"+e.getBy();
                    lineToWrite = e.getClassIcon() + "/" + e.getStatusIcon() + "/" + e.toStringMain() +temp +"\n" ;
                    break;
                default:
                    throw new JaanuException("internal logic error");
                }
                try {
                    fw.write(lineToWrite);
                    printFileContents(filePath);
                }
                catch (IOException e){
                    throw new JaanuException("babes there is some issue with file writing");
                }
            }
            try {
                fw.close();
            }
            catch (IOException e){
                throw new JaanuException("babes there is some issue with file writing");
            }
        }
        catch (IOException e) {
            throw new JaanuException("babes there is some issue with file writing");
        }
    }

    public static void printGreeting() {
        String greeting = DIVIDER_LINE
                + " I'm your Jaanu\n"
                + " I'll do anything, just ask and see\n"
                + DIVIDER_LINE;
        System.out.println(greeting);
    }

    public static void printBye() {
        String bye = DIVIDER_LINE
                + "hope to never see you again. bye \n"
                + DIVIDER_LINE;
        System.out.println(bye);
    }

    private static void printError(String message) {
        System.out.println(DIVIDER_LINE
                + message + "\n"
                + DIVIDER_LINE);
    }

    private static void printAddConfirmationMsg(Task addedTask) {
        String msg = DIVIDER_LINE
                + "um ok, there you go babes:";
        System.out.println(msg);
        System.out.println(addedTask.toString());

        String remainingTaskMsg = "Now you have " + tasks.size() + " tasks in the list.\n"
                + DIVIDER_LINE;
        System.out.println(remainingTaskMsg);
    }

    public static void markTask(String[] inputString) throws JaanuException {
        if (inputString.length < 2) {
            throw new JaanuException("oi babes, WHICH task u wanna mark?? gimme a number\n try: mark 2");
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(inputString[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JaanuException("that ain't even a number lol. use mark 1 or mark 2 etc");
        }

        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new JaanuException("lmao task " + (taskNum + 1) + " doesn't exist. u only got "
                    + tasks.size() + " task(s) babes");
        }

        Task t = tasks.get(taskNum);

        if (t.isDone()) {
            throw new JaanuException("bro u already marked that one. getting old?");
        }

        t.setAsDone();
        System.out.println(DIVIDER_LINE + "Attaboy, keep the grind on");
        System.out.println("  [X] " + t.toStringMain() + "\n" + DIVIDER_LINE);
    }

    public static void unmarkTask(String[] inputString) throws JaanuException {
        if (inputString.length < 2) {
            throw new JaanuException("which task u lazy dog? gimme the number\n try: unmark 2");
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(inputString[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JaanuException("bruh that's not a number. try unmark 1 or unmark 2 etc");
        }

        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new JaanuException("nice try but task " + (taskNum + 1) + " doesn't exist. u only got "
                    + tasks.size() + " task(s)");
        }

        Task t = tasks.get(taskNum);

        if (!t.isDone()) {
            throw new JaanuException("it's already unmarked genius. pay attention babes");
        }

        t.setAsNotDone();
        System.out.println(DIVIDER_LINE + "U lazy dog, go finish this task:");
        System.out.println("  [ ] " + t.toStringMain() + "\n" + DIVIDER_LINE);
    }

    public static void parseArgs(String input) {
        if (input.contains("/")) {
            int dividerPosition = input.indexOf("/");
            parsedArgs[0] = input.substring(0, dividerPosition).trim();
            String temp = input.substring(dividerPosition + 1);

            if (temp.contains("/")) {
                int secondDividerPosition = temp.indexOf("/") + dividerPosition + 1;
                parsedArgs[1] = input.substring(dividerPosition + 1, secondDividerPosition).trim();
                parsedArgs[2] = input.substring(secondDividerPosition + 1).trim();
            } else {
                parsedArgs[1] = "no from date";
                parsedArgs[2] = input.substring(dividerPosition + 1).trim();
            }
        } else {
            parsedArgs[0] = input;
            parsedArgs[1] = "no from date";
            parsedArgs[2] = "no to date";
        }
    }

    private static void addTask(String command, int taskDescriptionLength) throws JaanuException {
        int commandLength;
        switch (command) {
        case "todo":
            commandLength = 5; // "todo "
            break;
        case "deadline":
            commandLength = 9; // "deadline "
            break;
        case "event":
            commandLength = 6; // "event "
            break;
        default:
            throw new JaanuException("idk what a " + command + " is babes");
        }

        if (parsedArgs[0].length() <= commandLength) {
            throw new JaanuException("bro u can't just say '" + command + "' and leave me hanging\n"
                    + " tell me WHAT u wanna add. example: " + command + " buy flowers for jaanu");
        }

        String description = parsedArgs[0].substring(commandLength, taskDescriptionLength).trim();
        if (description.isEmpty()) {
            throw new JaanuException("umm the description can't be empty babes\n"
                    + " try: " + command + " take jaanu on a date");
        }

        Task newTask;

        switch (command) {
        case "todo":
            newTask = new Task(description);
            break;

        case "deadline":
            if (parsedArgs[2].equals("no to date") || parsedArgs[2].trim().isEmpty()) {
                throw new JaanuException("oi deadlines need a date dummy. use:\n"
                        + " deadline <task> / <date>\n"
                        + " example: deadline confess to jaanu / tonight");
            }
            newTask = new Deadline(description, parsedArgs[2].trim());
            break;

        case "event":
            if (parsedArgs[1].equals("no from date") || parsedArgs[1].trim().isEmpty()) {
                throw new JaanuException("babes ur event needs a start time. format:\n"
                        + " event <what> / <from> / <to>\n"
                        + " example: event date with jaanu / 7pm / 11pm");
            }
            if (parsedArgs[2].equals("no to date") || parsedArgs[2].trim().isEmpty()) {
                throw new JaanuException("lol ur event needs an end time too. format:\n"
                        + " event <what> / <from> / <to>\n"
                        + " example: event netflix with jaanu / 8pm / whenever ;)");
            }
            newTask = new Event(description, parsedArgs[1].trim(), parsedArgs[2].trim());
            break;

        default:
            throw new JaanuException("nah I can't add that: " + command);
        }

        tasks.add(newTask);
        printAddConfirmationMsg(newTask);
    }

    public static void manageTasks() throws JaanuException {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            try {
                input = input.trim();

                if (input.isEmpty()) {
                    throw new JaanuException("hello??? say something babes, I can't read ur mind (yet)");
                }

                String[] inputString = input.split(" ");
                String command = inputString[0];

                parseArgs(input);
                int taskDescriptionLength = parsedArgs[0].length();

                switch (command) {
                case "list":
                    printList();
                    break;
                case "mark":
                    markTask(inputString);
                    break;
                case "unmark":
                    unmarkTask(inputString);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addTask(command, taskDescriptionLength);
                    break;
                default:
                    throw new JaanuException("wht is '" + command + "'??? I don't speak that language babes\n"
                            + " try: list, todo, deadline, event, mark, unmark, delete, bye");
                }

            } catch (JaanuException e) {
                printError(e.getMessage());
            } catch (Exception e) {
                printError("yikes something broke: " + e.getMessage());
            }

            try {
                updateFile();
            }
            catch (JaanuException e){
                printError(e.getMessage());
            }

            input = in.nextLine();
        }
        in.close();
    }

    private static String readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String output = "";
        while (s.hasNext()) {
            output = output + s.nextLine()+"\n";
        }
        return output;
    }

    public static String safelyInitFile() {
        try {
            File f = new File(filePath); // create a File for the given file path

            String output = "";

            if (!(f.createNewFile())){
                output = readFileContents(filePath);
                return output;
            }
            else {
                return output;
            }
        }
        catch (IOException e){
            printError("An IO Error Occurred.");
        }
        return "";
    }

    public static void cleanFile() throws JaanuException {
        try {
            FileWriter fw = new FileWriter(filePath);

            try {
                fw.write("");
            }
            catch (IOException e) {
                throw new JaanuException("babes there is some issue with file writing");
            }
        }
        catch (JaanuException e){
            printError(e.getMessage());
        }
        catch (IOException e){
            printError("An IO Error Occurred.");
        }
    }

    public static void main(String[] args) throws JaanuException {
        loadTasks(safelyInitFile());
        printGreeting();
        manageTasks();
        printBye();
    }
}
