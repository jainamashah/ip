package jaanu.ui;

import java.util.List;

import jaanu.Task.Task;
import jaanu.Task.Deadline;
import jaanu.Task.Event;
import jaanu.Task.TaskList;
import jaanu.JaanuException.JaanuException;

/**
 * Jaanu is the main application class.
 * Coordinates between Storage, TaskList, Ui, and CommandHandler.
 * Manages the application lifecycle and command processing.
 */
public class Jaanu {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private CommandHandler commandHandler;

    /**
     * Constructs the Jaanu application.
     * Initializes UI, Storage, TaskList, and CommandHandler.
     * Loads existing tasks from the file.
     * @param filePath The path to the file for storing tasks
     */
    public Jaanu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JaanuException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        commandHandler = new CommandHandler(tasks, ui);
    }

    /**
     * Runs the main application loop.
     * Continuously reads user commands, processes them, and saves changes.
     * Exits when user enters \"bye\" command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                String command = Parser.getCommand(fullCommand);
                String[] args = Parser.getCommandArgs(fullCommand);
                Parser.parseArgs(fullCommand);
                String[] parsed = Parser.getParsedArgs();
                int descLen = parsed[0].length();
                switch (command) {
                    case "list":
                        ui.printList(tasks.getAll());
                        break;
                    case "mark":
                        commandHandler.markTask(args);
                        break;
                    case "unmark":
                        commandHandler.unmarkTask(args);
                        break;
                    case "find":
                        commandHandler.findTask(args[1],tasks);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        commandHandler.addTask(command, descLen);
                        break;
                    case "delete":
                        commandHandler.deleteTask(args);
                        break;
                    case "bye":
                        isExit = true;
                        break;
                    default:
                        throw new JaanuException("wht is '" + command + "'??? I don't speak that language babes\n"
                                + " try: list, todo, deadline, event, mark, unmark, delete, bye");
                }
                storage.save(tasks.getAll());
            } catch (JaanuException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showBye();
        ui.close();
    }



    /**
     * Entry point of the application.
     * @param args Command line arguments (not used)
     * @throws JaanuException If there is an error during execution
     */
    public static void main(String[] args) throws JaanuException {
        new Jaanu("jaanu.txt").run();
    }
}
