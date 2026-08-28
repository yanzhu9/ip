package ada;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> initialTasks) {
        this.tasks = initialTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markDone(int index) {
        tasks.get(index).markDone();
    }

    public void markUndone(int index) {
        tasks.get(index).markUndone();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns list of tasks whose description contains the given keyword (case‑insensitive).
     *
     * @param keyword search keyword from find command
     * @return matched task list
     */
    public List<Task> matchTasksByKeyword(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Task task : tasks) {
            String taskDesc = task.getDescription().toLowerCase();
            if (taskDesc.contains(lowerKeyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}