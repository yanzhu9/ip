public class Task{
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone(){
        return this.isDone;
    }
    public void markDone(){
        this.isDone = true;
    }
    public void markUndone(){
        this.isDone = false;
    }

    public String toString(){
        String done = isDone() ? "[X] " : "[ ] ";
        return done + this.description;
    }

    public String toFileFormat(){
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }
}