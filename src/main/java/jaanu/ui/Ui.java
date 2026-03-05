package jaanu.ui;

import java.util.List;
import java.util.Scanner;

import jaanu.Task.Task;
import jaanu.JaanuException.JaanuException;
import jaanu.Task.TaskList;

/**
 * Ui class handles all user interface interactions.
 * Responsible for displaying messages to the user and reading user input.
 */
public class Ui {
    private static final String DIVIDER_LINE = "____________________________________________________________\n";
    private Scanner in;

    /**
     * Constructs a Ui object and initializes the Scanner for input.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        String greeting = DIVIDER_LINE
                + " I'm your Jaanu\n"
                + " I'll do anything, just ask and see\n"
                + DIVIDER_LINE;
        System.out.println(greeting);
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showBye() {
        String bye = DIVIDER_LINE
                + "hope to never see you again. bye \n"
                + DIVIDER_LINE;
        System.out.println(bye);
    }

    /**
     * Displays an error message with formatting.
     * @param message The error message to display
     */
    public void showError(String message) {
        System.out.println(DIVIDER_LINE
                + message + "\n"
                + DIVIDER_LINE);
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Reads a command from user input.
     * @return The command string entered by the user
     */
    public String readCommand() {
        return in.nextLine();
    }

    public void showLoadingError() {
        showError("Error loading tasks from file.");
    }

    public void printList(List<Task> tasks) throws JaanuException {
        if (tasks.isEmpty()) {
            throw new JaanuException("bruh your list is empty lol. add something first babes");
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + ". " + t.toString());
        }
    }

    public void printAddConfirmationMsg(Task addedTask, int size) {
        String msg = DIVIDER_LINE
                + "um ok, there you go babes:";
        System.out.println(msg);
        System.out.println(addedTask.toString());

        String remainingTaskMsg = "Now you have " + size + " tasks in the list.\n"
                + DIVIDER_LINE;
        System.out.println(remainingTaskMsg);
    }

    public void showMark(Task t) {
        System.out.println(DIVIDER_LINE + "Attaboy, keep the grind on");
        System.out.println("  [X] " + t.toStringMain() + "\n" + DIVIDER_LINE);
    }

    public void showUnmark(Task t) {
        System.out.println(DIVIDER_LINE + "U lazy dog, go finish this task:");
        System.out.println("  [ ] " + t.toStringMain() + "\n" + DIVIDER_LINE);
    }

    public void showTask(int i, TaskList tasks) {
        if (i > -1){
            Task t = tasks.get(i);
            System.out.println(DIVIDER_LINE + "i gotchu:");
            System.out.println((i + 1) + ". " + t.toString());
            System.out.println("\n" + DIVIDER_LINE);
        }
        else {
            System.out.println(DIVIDER_LINE + "There is nothing there babes" + "\n" + DIVIDER_LINE);
        }
    }

    public void showDelete(Task removed, int size) {
        System.out.println(DIVIDER_LINE + "Noted. I've removed this task:");
        System.out.println("  " + removed.toString());
        System.out.println("Now you have " + size + " tasks in the list.\n" + DIVIDER_LINE);
    }

    /**
     * Closes the scanner resource.
     */
    public void close() {
        in.close();
    }
}