package jaanu.Task;

/**
 * Represents a task that must be completed by a specific date or time.
 * Extends {@link Task} with an additional {@code by} field.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new deadline task.
     *
     * @param description description of the task
     * @param by textual representation of the deadline (e.g. date or time)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return "[D]" + status + " " + toStringMain() + " (by: " + by + ")";
    }

    /**
     * Returns the stored deadline value.
     *
     * @return deadline value as a string
     */
    public String getBy() {
        return by;
    }

    @Override
    public String getClassIcon() {
        return "D";
    }
}
