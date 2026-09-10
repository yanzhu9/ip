package ada;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "---------------------------------------------------";
    private static final String BANNER =
            "    _     ____      _    \n"
                    + "   / \\   |  _ \\    / \\   \n"
                    + "  / _ \\  | | | |  / _ \\  \n"
                    + " / ___ \\ | |_| | / ___ \\ \n"
                    + "/_/   \\_\\\\____/ /_/   \\_\\\n";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    private void showSection(String text) {
        System.out.println(LINE);
        System.out.println(text);
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.print(BANNER);
        System.out.println(" Hello, I'm Ada.Ada.");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showBye() {
        showSection(getByeText());
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    public void showMarkDone(Task task) {
        showSection(getMarkDoneText(task));
    }

    public void showMarkUndone(Task task) {
        showSection(getMarkUndoneText(task));
    }

    public void showAddTask(Task task, int total) {
        showSection(getAddTaskText(task, total));
    }

    public void showDeleteTask(Task task, int total) {
        showSection(getDeleteTaskText(task, total));
    }

    /**
     * Displays the list of tasks matched by find‑keyword search.
     *
     * @param matchedTasks list of tasks that match keyword
     */
    public void showFindResult(List<Task> matchedTasks) {
        showSection(getFindResultText(matchedTasks));
    }

    public void showUnknownCommand() {
        showSection(getUnknownCommandText());
    }

    public void showDateTimeErrorDeadline() {
        System.out.println("Oops! Invalid date format.");
        System.out.println("Please use format yyyy-MM-dd (e.g. 2026-08-27) or yyyy-MM-dd HHmm (e.g. 2026-08-27 1800)");
    }

    public void showDateTimeErrorEvent() {
        System.out.println("Oops! Invalid date format.");
        System.out.println("Please use format yyyy-MM-dd or yyyy-MM-dd HHmm (e.g. 2026-08-27 1800) for /from.");
        System.out.println("Please use format yyyy-MM-dd HHmm (e.g. 2026-08-27 1800) or HHmm (e.g. 1800) for /to");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Returns the welcome message.
     *
     * @return welcome message
     */
    public String getWelcomeText() {
        return " Hello, I'm Ada.\n"
                + " What can I do for you?";
    }

    /**
     * Returns the goodbye message.
     *
     * @return goodbye message
     */
    public String getByeText() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a formatted task list.
     *
     * @param tasks tasks to display
     * @return formatted task list
     */
    public String getTaskListText(List<Task> tasks) {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i));
        }
        return result.toString();
    }

    /**
     * Returns the response for marking a task as done.
     *
     * @param task marked task
     * @return response message
     */
    public String getMarkDoneText(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Returns the response for marking a task as not done.
     *
     * @param task unmarked task
     * @return response message
     */
    public String getMarkUndoneText(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns the response for adding a task.
     *
     * @param task added task
     * @param total total number of tasks
     * @return response message
     */
    public String getAddTaskText(Task task, int total) {
        return "Got it. I've added this task:\n  "
                + task
                + "\nNow you have "
                + total
                + " tasks in the list.";
    }

    /**
     * Returns the response for deleting a task.
     *
     * @param task deleted task
     * @param total total number of remaining tasks
     * @return response message
     */
    public String getDeleteTaskText(Task task, int total) {
        return "Noted. I've removed this task:\n  "
                + task
                + "\nNow you have "
                + total
                + " tasks in the list.";
    }

    /**
     * Returns a formatted list of matching tasks.
     *
     * @param matchedTasks matching tasks
     * @return formatted search result
     */
    public String getFindResultText(List<Task> matchedTasks) {
        if (matchedTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            result.append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(matchedTasks.get(i));
        }
        return result.toString();
    }

    /**
     * Returns the unknown-command message.
     *
     * @return unknown-command message
     */
    public String getUnknownCommandText() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Returns the deadline date-format error message.
     *
     * @return deadline error message
     */
    public String getDateTimeErrorDeadlineText() {
        return "Oops! Invalid date format.\n"
                + "Please use yyyy-MM-dd or yyyy-MM-dd HHmm.";
    }

    /**
     * Returns the event date-format error message.
     *
     * @return event error message
     */
    public String getDateTimeErrorEventText() {
        return "Oops! Invalid date format.\n"
                + "Use yyyy-MM-dd or yyyy-MM-dd HHmm for /from, "
                + "and yyyy-MM-dd HHmm or HHmm for /to.";
    }
}
