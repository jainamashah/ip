package jaanu.Task;

/**
 * Represents a generic task in the Jaanu task manager.
 * Tracks a description and whether the task is completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     * The task is initially marked as not done.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon used when printing the task.
     *
     * @return {@code "X"} if the task is done, otherwise a single space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void setAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks whether this task is done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Updates the description of this task.
     *
     * @param text new description text
     */
    public void setDescription(String text) {
        this.description = text;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return "[T]" + status + " " + toStringMain();
    }

    /**
     * Returns the main text representation of this task without type or status.
     *
     * @return description text of the task
     */
    public String toStringMain() {
        return this.description;
    }

    /**
     * Returns the class icon used to identify this task type in storage.
     *
     * @return {@code "T"} for a generic task
     */
    public String getClassIcon() {
        return "T";
    }
}
