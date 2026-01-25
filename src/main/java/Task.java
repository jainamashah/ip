public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String Description) {
        this.description =Description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setAsDone(){
        this.isDone = true;
    }

    public void setAsNotDone(){
        this.isDone = false;
    }

    public void setDescription(String text){
        this.description = text;
    }
}
