package jaanu.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class manages a collection of tasks.
 * Provides operations to add, remove, retrieve, and query tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     * @param tasks The initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * @param t The task to add
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the list.
     * @param index The index of the task to remove
     * @return The removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task at the specified index.
     * @param index The index of the task
     * @return The task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    public int find(String keyword) {
        String[] task_words;
        for (int i = 0; i < size(); i++){
            Task cur_task = tasks.get(i);
            task_words = (cur_task.description).split(" ");
            for (int j = 0; j < task_words.length; j++){
                if ((task_words[j]).equals(keyword)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Gets the number of tasks in the list.
     * @return The size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets all tasks in the list.
     * @return A list of all tasks
     */
    public List<Task> getAll() {
        return tasks;
    }
}