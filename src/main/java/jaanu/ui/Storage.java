package jaanu.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jaanu.Task.Task;
import jaanu.Task.Deadline;
import jaanu.Task.Event;
import jaanu.JaanuException.JaanuException;

/**
 * Storage class handles file operations for loading and saving tasks.
 * Manages persistent storage of the task list to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with a specified file path.
     * @param filePath The path to the file for storing tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * @return A list of tasks loaded from the file
     * @throws JaanuException If there is an error loading or parsing the file
     */
    public List<Task> load() throws JaanuException {
        String content = safelyInitFile();
        List<Task> tasks = new ArrayList<>();
        loadTasks(content, tasks);
        return tasks;
    }

    /**
     * Saves tasks to the file.
     * @param tasks The list of tasks to save
     * @throws JaanuException If there is an error writing to the file
     */
    public void save(List<Task> tasks) throws JaanuException {
        updateFile(tasks);
    }

    private String safelyInitFile() {
        try {
            File f = new File(filePath);
            String output = "";
            if (!(f.createNewFile())) {
                output = readFileContents(filePath);
                return output;
            } else {
                return output;
            }
        } catch (IOException e) {
            // For now, return empty
            return "";
        }
    }

    private String readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String output = "";
        try {
            while (s.hasNext()) {
                output = output + s.nextLine() + "\n";
            }
        } finally {
            s.close();
        }
        return output;
    }

    private void loadTasks(String input, List<Task> tasks) throws JaanuException {
        if (input == null || input.trim().isEmpty()) {
            return;
        }
        String[] unparsedTaskList = input.split("\n");
        String classChar, doneChar, description, input_task;
        int numOfTasks = unparsedTaskList.length;
        if (!(input.contains("\n"))) {
            numOfTasks = 1;
        }
        for (int i = 0; i < numOfTasks; i++) {
            input_task = unparsedTaskList[i].trim();
            if (input_task.isEmpty()) continue;
            Task t = null;
            String[] input_fields = input_task.split("/");
            if (input_fields.length < 3) continue; // skip invalid
            classChar = input_fields[0].trim();
            doneChar = input_fields[1].trim();
            description = input_fields[2].trim();
            switch (classChar) {
                case "T":
                    t = new Task(description);
                    tasks.add(t);
                    break;
                case "D":
                    if (input_fields.length < 4) continue;
                    String by = input_fields[3].trim();
                    t = new Deadline(description, by);
                    tasks.add(t);
                    break;
                case "E":
                    if (input_fields.length < 5) continue;
                    String from = input_fields[3].trim();
                    String to = input_fields[4].trim();
                    t = new Event(description, from, to);
                    tasks.add(t);
                    break;
                default:
                    // skip unknown task types
            }
            if (t != null && doneChar.equals("X")) {
                t.setAsDone();
            }
        }
    }

    private void updateFile(List<Task> tasks) throws JaanuException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                String temp = "";
                String lineToWrite;
                Task task = tasks.get(i);
                switch (task.getClassIcon()) {
                    case "T":
                        lineToWrite = task.getClassIcon() + "/" + task.getStatusIcon() + "/" + task.toStringMain() + temp + "\n";
                        break;
                    case "D":
                        Deadline d = (Deadline) task;
                        temp = "/" + d.getBy();
                        lineToWrite = d.getClassIcon() + "/" + d.getStatusIcon() + "/" + d.toStringMain() + temp + "\n";
                        break;
                    case "E":
                        Event e = (Event) task;
                        temp = "/" + e.getFrom() + "/" + e.getBy();
                        lineToWrite = e.getClassIcon() + "/" + e.getStatusIcon() + "/" + e.toStringMain() + temp + "\n";
                        break;
                    default:
                        throw new JaanuException("internal logic error");
                }
                try {
                    fw.write(lineToWrite);
                } catch (IOException e) {
                    throw new JaanuException("babes there is some issue with file writing");
                }
            }
            try {
                fw.close();
            } catch (IOException e) {
                throw new JaanuException("babes there is some issue with file writing");
            }
        } catch (IOException e) {
            throw new JaanuException("babes there is some issue with file writing");
        }
    }
}