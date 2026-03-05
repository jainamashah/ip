package jaanu.ui;

import jaanu.Task.Task;
import jaanu.Task.Deadline;
import jaanu.Task.Event;
import jaanu.Task.TaskList;
import jaanu.JaanuException.JaanuException;

/**
 * CommandHandler class manages all task-related commands.
 * Handles adding, marking, unmarking, and deleting tasks.
 */
public class CommandHandler {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a CommandHandler with dependencies on TaskList and Ui.
     * @param tasks The task list to operate on
     * @param ui The UI for displaying messages
     */
    public CommandHandler(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Marks a task as done.
     * @param inputString The command arguments containing the task number
     * @throws JaanuException If the task number is invalid or the task is already marked
     */
    public void markTask(String[] inputString) throws JaanuException {
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
        ui.showMark(t);
    }

    /**
     * Unmarks a task (marks as not done).
     * @param inputString The command arguments containing the task number
     * @throws JaanuException If the task number is invalid or the task is already unmarked
     */
    public void unmarkTask(String[] inputString) throws JaanuException {
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
        ui.showUnmark(t);
    }

    /**
     * Adds a new task to the task list.
     * @param command The type of task (todo, deadline, or event)
     * @param taskDescriptionLength The length of the command and description string
     * @throws JaanuException If the task format or description is invalid
     */
    public void addTask(String command, int taskDescriptionLength) throws JaanuException {
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

        String[] parsed = Parser.getParsedArgs();
        if (parsed[0].length() <= commandLength) {
            throw new JaanuException("bro u can't just say '" + command + "' and leave me hanging\n"
                    + " tell me WHAT u wanna add. example: " + command + " buy flowers for jaanu");
        }

        String description = parsed[0].substring(commandLength, taskDescriptionLength).trim();
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
            if (parsed[2].equals("no date") || parsed[2].trim().isEmpty()) {
                throw new JaanuException("oi deadlines need a date dummy. use:\n"
                        + " deadline <task> /by <date>\n"
                        + " example: deadline confess to jaanu /by tonight");
            }
            newTask = new Deadline(description, parsed[2].trim());
            break;

        case "event":
            if (parsed[1].equals("no date") || parsed[1].trim().isEmpty()) {
                throw new JaanuException("babes ur event needs a start time. format:\n"
                        + " event <what> /from <time> /to <time>\n"
                        + " example: event date with jaanu /from 7pm /to 11pm");
            }
            if (parsed[2].equals("no date") || parsed[2].trim().isEmpty()) {
                throw new JaanuException("lol ur event needs an end time too. format:\n"
                        + " event <what> /from <time> /to <time>\n"
                        + " example: event netflix with jaanu /from 8pm /to whenever ;)");
            }
            newTask = new Event(description, parsed[1].trim(), parsed[2].trim());
            break;

        default:
            throw new JaanuException("nah I can't add that: " + command);
        }

        tasks.add(newTask);
        ui.printAddConfirmationMsg(newTask, tasks.size());
    }

    /**
     * Deletes a task from the task list.
     * @param inputString The command arguments containing the task number
     * @throws JaanuException If the task number is invalid
     */
    public void deleteTask(String[] inputString) throws JaanuException {
        if (inputString.length < 2) {
            throw new JaanuException("delete WHAT babes? gimme a number\n try: delete 3");
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(inputString[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JaanuException("bruh that's not a number. try: delete 1");
        }

        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new JaanuException("lmao task " + (taskNum + 1) + " doesn't exist. u only got "
                    + tasks.size() + " task(s)");
        }

        Task removed = tasks.remove(taskNum);

        ui.showDelete(removed, tasks.size());
    }
}