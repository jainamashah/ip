public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = null;
        if (isDone){
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return "[D]" + status + " "  + toStringMain() + "(by: " + by + ")";
    }

    public String getBy(){
        return by;
    }

    @Override
    public String getClassIcon() {
        return "D";
    }
}
