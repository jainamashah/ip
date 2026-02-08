import java.util.Scanner;

public class Jaanu {
    private static final int MAX_TASKS = 100;
    private static final int PARSED_ARGS_SIZE = 3;
    private static final String DIVIDER_LINE = "____________________________________________________________\n";

    private static int numOfTasks = 0;
    private static Task[] taskList = new Task[MAX_TASKS];
    private static String[] parsedArgs = new String[PARSED_ARGS_SIZE];

    public static void printList() throws JaanuException {
        if (numOfTasks == 0) {
            throw new JaanuException("bruh your list is empty lol. add something first babes");
        }
        for (int i = 0; i < numOfTasks; i++) {
            String doneChar = taskList[i].getStatusIcon();
            String classChar = taskList[i].getClassIcon();
            System.out.println((i + 1) + ". [" + classChar + "] [" + doneChar + "] "
                    + taskList[i].description);
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

    public static void printAddConfirmationMsg() {
        String msg = DIVIDER_LINE
                + "um ok, there you go babes:";
        System.out.println(msg);
        System.out.println(taskList[numOfTasks].toString());
        String remainingTaskMsg = "Now you have " + (numOfTasks + 1) + " tasks in the list.\n"
                + DIVIDER_LINE;
        System.out.println(remainingTaskMsg);
    }

    private static void printError(String message) {
        System.out.println(DIVIDER_LINE
                + message + "\n"
                + DIVIDER_LINE);
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

        if (taskNum < 0 || taskNum >= numOfTasks) {
            throw new JaanuException("lmao task " + (taskNum + 1) + " doesn't exist. u only got "
                    + numOfTasks + " task(s) babes");
        }

        if (taskList[taskNum].isDone) {
            throw new JaanuException("bro u already marked that one. getting old?");
        }

        taskList[taskNum].setAsDone();
        System.out.println(DIVIDER_LINE + "Attaboy, keep the grind on");
        System.out.println("  [X] " + taskList[taskNum].description + "\n" + DIVIDER_LINE);
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

        if (taskNum < 0 || taskNum >= numOfTasks) {
            throw new JaanuException("nice try but task " + (taskNum + 1) + " doesn't exist. u only got "
                    + numOfTasks + " task(s)");
        }

        if (!taskList[taskNum].isDone) {
            throw new JaanuException("it's already unmarked genius. pay attention babes");
        }

        taskList[taskNum].setAsNotDone();
        System.out.println(DIVIDER_LINE + "U lazy dog, go finish this task:");
        System.out.println("  [ ] " + taskList[taskNum].description + "\n" + DIVIDER_LINE);
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
        if (numOfTasks >= MAX_TASKS) {
            throw new JaanuException("woah slow down babes, ur list is full (" + MAX_TASKS + " max). delete something first");
        }

        // Determine the command length to extract description
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

        // Check if there's a description after the command
        if (parsedArgs[0].length() <= commandLength) {
            throw new JaanuException("bro u can't just say '" + command + "' and leave me hanging\n"
                    + " tell me WHAT u wanna add. example: " + command + " buy flowers for jaanu");
        }

        String description = parsedArgs[0].substring(commandLength, taskDescriptionLength).trim();
        if (description.isEmpty()) {
            throw new JaanuException("umm the description can't be empty babes\n"
                    + " try: " + command + " take jaanu on a date");
        }

        switch (command) {
        case "todo":
            taskList[numOfTasks] = new Task(description);
            break;

        case "deadline":
            if (parsedArgs[2].equals("no to date") || parsedArgs[2].trim().isEmpty()) {
                throw new JaanuException("oi deadlines need a date dummy. use:\n"
                        + " deadline <task> / <date>\n"
                        + " example: deadline confess to jaanu / tonight");
            }
            taskList[numOfTasks] = new Deadline(description, parsedArgs[2].trim());
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
            taskList[numOfTasks] = new Event(description, parsedArgs[1].trim(), parsedArgs[2].trim());
            break;

        default:
            throw new JaanuException("nah I can't add that: " + command);
        }

        printAddConfirmationMsg();
        numOfTasks++;
    }

    public static void manageTasks() {
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
                            + " try: list, todo, deadline, event, mark, unmark, bye");
                }

            } catch (JaanuException e) {
                printError(e.getMessage());
            } catch (Exception e) {
                printError("yikes something broke: " + e.getMessage());
            }

            input = in.nextLine();
        }

        in.close();
    }

    public static void main(String[] args) {
        printGreeting();
        manageTasks();
        printBye();
    }
}
