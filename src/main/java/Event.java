public class Event extends Deadline {

    protected String from;

    public Event(String description, String from, String to) {
        super(description, to);
        this.from = from;
    }

    @Override
    public String toString() {
        String status = null;
        if (isDone){
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return "[E]" + status + " "  + toStringMain() + " (from: " + from + " to:" +getBy() + ")";
    }

    @Override
    public String getClassIcon() {
        return "E";
    }

}
