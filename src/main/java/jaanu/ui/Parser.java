package jaanu.ui;

import jaanu.JaanuException.JaanuException;

/**
 * Parser class handles parsing of user commands.
 * Extracts command components and interprets command format.
 * Supports formats:
 * - todo: todo <description>
 * - deadline: deadline <description> /by <date>
 * - event: event <description> /from <time> /to <time>
 */
public class Parser {
    private static final String[] parsedArgs = new String[3];

    /**
     * Parses the user input and extracts command components.
     * Stores results in parsedArgs array:
     * [0] = command and description
     * [1] = from time (for events) or no date
     * [2] = to time (for events) or by date (for deadlines)
     * @param input The full user input string
     */
    public static void parseArgs(String input) {
        // Initialize defaults
        parsedArgs[0] = input;
        parsedArgs[1] = "no date";
        parsedArgs[2] = "no date";

        // Check for /by (for deadline)
        if (input.contains("/by ")) {
            int byIndex = input.indexOf("/by ");
            parsedArgs[0] = input.substring(0, byIndex).trim();
            parsedArgs[2] = input.substring(byIndex + 4).trim();
        }
        // Check for /from and /to (for event)
        else if (input.contains("/from ") && input.contains("/to ")) {
            int fromIndex = input.indexOf("/from ");
            int toIndex = input.indexOf("/to ");
            parsedArgs[0] = input.substring(0, fromIndex).trim();
            parsedArgs[1] = input.substring(fromIndex + 6, toIndex).trim();
            parsedArgs[2] = input.substring(toIndex + 4).trim();
        }
    }

    /**
     * Gets the parsed command arguments.
     * @return Array containing [command with description, from time, to/by time]
     */
    public static String[] getParsedArgs() {
        return parsedArgs;
    }

    // For now, simple parse to get command
    /**
     * Extracts the command keyword from the input.
     * @param input The user input string
     * @return The first word (command) in the input
     */
    public static String getCommand(String input) {
        return input.trim().split(" ")[0];
    }

    /**
     * Splits the input into command arguments.
     * @param input The user input string
     * @return Array of words in the input
     */
    public static String[] getCommandArgs(String input) {
        return input.trim().split(" ");
    }
}