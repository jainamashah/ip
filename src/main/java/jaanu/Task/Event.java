package jaanu.Task;

/**
 * Represents an event that spans a time interval.
 * Stores both a start time ({@code from}) and an end time ({@code to}, inherited as {@code by}).
 */
public class Event extends Deadline {
    protected String from;

    /**
     * Returns the start time of this event.
     *
     * @return event start time
     */
    public String getFrom() {
        return from;
    }

    /**
     * Creates a new event task.
     *
     * @param description description of the event
     * @param from start time of the event
     * @param to end time of the event
     */
    public Event(String description, String from, String to) {
        super(description, to);
        this.from = from;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return "[E]" + status + " " + toStringMain() + " (from: " + getFrom() + " to: " + getBy() + ")";
    }

    @Override
    public String getClassIcon() {
        return "E";
    }
}
