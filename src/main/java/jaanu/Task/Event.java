package jaanu.Task;

public class Event extends Deadline {
    protected String from;

    public Event(String description, String from, String to) {
        super(description, to);
        this.from = from;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return "[E]" + status + " " + toStringMain() + " (from: " + from + " to: " + getBy() + ")";
    }

    @Override
    public String getClassIcon() {
        return "E";
    }
}
