public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsNotDone() {
        this.isDone = false;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return "[T]" + status + " " + toStringMain();
    }

    public String toStringMain() {
        return this.description;
    }

    public String getClassIcon() {
        return "T";
    }
}
