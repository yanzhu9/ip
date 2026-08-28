package ada;

import java.util.ArrayList;

/**
 * Maintains the in‑memory collection of tasks.
 * Provides operations to add, remove, retrieve and update task completion status.
 * Returns a copy of internal task list when exporting all tasks to prevent external modification.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList using an existing list of initial tasks.
     *
     * @param initialTasks list of tasks loaded from storage
     */
    public TaskList(ArrayList<Task> initialTasks) {
        this.tasks = initialTasks;
    }

    /**
     * Appends a new task to the task collection.
     *
     * @param task the task object to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index zero‑based position of target task
     * @return the removed Task instance
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task stored at given index.
     *
     * @param index zero‑based position of target task
     * @return the Task at that position
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets total number of tasks stored.
     *
     * @return count of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks the task at given index as completed.
     *
     * @param index zero‑based position of target task
     */
    public void markDone(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks the task at given index as unfinished.
     *
     * @param index zero‑based position of target task
     */
    public void markUndone(int index) {
        tasks.get(index).markUndone();
    }

    /**
     * Returns a brand‑new copy of the internal task list.
     * External code cannot modify the original stored collection through this returned list.
     *
     * @return copied ArrayList containing all tasks
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}