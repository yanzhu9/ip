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

    public String getDescription(){
        return this.description;
    }

    public void markDone(){
        this.isDone = true;
    }
    public void markUndone(){
        this.isDone = false;
    }
}