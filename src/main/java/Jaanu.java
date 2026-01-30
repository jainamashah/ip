import java.util.Arrays;
import java.util.Scanner;

public class Jaanu {
    private static int numOfTasks = 0;
    private static final String DIVIDER_LINE = "____________________________________________________________\n";
    private static Task[] taskList = new Task[100];

    public static void printList() {
        for (int i = 0; i < numOfTasks; i++) {
            String doneChar = taskList[i].getStatusIcon();
            System.out.println((i + 1) + "." + "[" + doneChar + "] " + taskList[i].description);
        }
    }

    public static void printGreeting() {
        String greeting = DIVIDER_LINE
                + " I'm your Jaanu\n"
                + " I'll do anything, just ask and see \n"
                + DIVIDER_LINE;
        System.out.println(greeting);
    }

    public static void printBye() {
        String bye = DIVIDER_LINE
                + "hope to never see you again. bye \n"
                + DIVIDER_LINE;
        System.out.println(bye);
    }

    public static void markTask(String[] inputString) {
        int taskNum = Integer.parseInt(inputString[1]) - 1;
        taskList[taskNum].setAsDone();
        System.out.println("Attaboy, keep the grind on");
        System.out.println("  [X]" + taskList[taskNum].description);
    }

    public static void unmarkTask(String[] inputString) {
        int taskNum = Integer.parseInt(inputString[1]) - 1;
        taskList[taskNum].setAsNotDone();
        System.out.println("U lazy dog, go finish this task:");
        System.out.println("  [ ]" + taskList[taskNum].description);
    }

    public static void manageTasks() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inputString;
        inputString = input.split(" ");
        while (!input.contains("bye")) {
            if (input.equals("list")) {
                printList();
            } else if ((inputString[0]).equals("mark")) {
                markTask(inputString);
            } else if ((inputString[0]).equals("unmark")) {
                unmarkTask(inputString);
            } else {
                taskList[numOfTasks] = new Task(input);
                numOfTasks++;
                System.out.println(DIVIDER_LINE + "added: " + input + "\n" + DIVIDER_LINE + "\n");
            }
            input = in.nextLine();
            inputString = input.split(" ");
        }
    }

    public static void main(String[] args) {
        printGreeting();
        manageTasks();
        printBye();
    }
}
