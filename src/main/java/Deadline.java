public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return "[D]" + status + " " + toStringMain() + " (by: " + by + ")";
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getClassIcon() {
        return "D";
    }
}
