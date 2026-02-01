import java.util.Scanner;

public class Jaanu {
    private static final int MAX_TASKS = 100;
    private static final int PARSED_ARGS_SIZE = 3;
    private static final String DIVIDER_LINE = "____________________________________________________________\n";

    private static int numOfTasks = 0;
    private static Task[] taskList = new Task[MAX_TASKS];
    private static String[] parsedArgs = new String[PARSED_ARGS_SIZE];

    public static void printList() {
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

    public static void markTask(String[] inputString) {
        int taskNum = Integer.parseInt(inputString[1]) - 1;
        taskList[taskNum].setAsDone();
        System.out.println("Attaboy, keep the grind on");
        System.out.println("  [X] " + taskList[taskNum].description);
    }

    public static void unmarkTask(String[] inputString) {
        int taskNum = Integer.parseInt(inputString[1]) - 1;
        taskList[taskNum].setAsNotDone();
        System.out.println("U lazy dog, go finish this task:");
        System.out.println("  [ ] " + taskList[taskNum].description);
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

    private static void addTask(String command, int taskDescriptionLength) {
        switch (command) {
        case "todo":
            taskList[numOfTasks] = new Task(parsedArgs[0].substring(5, taskDescriptionLength));
            break;
        case "deadline":
            taskList[numOfTasks] = new Deadline(
                    parsedArgs[0].substring(9, taskDescriptionLength),
                    parsedArgs[2]);
            break;
        case "event":
            taskList[numOfTasks] = new Event(
                    parsedArgs[0].substring(6, taskDescriptionLength),
                    parsedArgs[1],
                    parsedArgs[2]);
            break;
        default:
            return;
        }
        printAddConfirmationMsg();
        numOfTasks++;
    }

    public static void manageTasks() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
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
                System.out.println("invalid command, try again");
                break;
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
