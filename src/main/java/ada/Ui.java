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
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
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
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(LINE);
    }

    public void showMarkUndone(Task task) {
        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println(LINE);
    }

    public void showAddTask(Task task, int total) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + total + " tasks in the list");
        System.out.println(LINE);
    }

    public void showDeleteTask(Task task, int total) {
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + total + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * Displays the list of tasks matched by find‑keyword search.
     *
     * @param matchedTasks list of tasks that match keyword
     */
    public void showFindResult(List<Task> matchedTasks) {
        System.out.println(LINE);
        if (matchedTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            System.out.println(LINE);
            return;
        }

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchedTasks.get(i));
        }
        System.out.println(LINE);
    }

    public void showUnknownCommand() {
        System.out.println(LINE);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(LINE);
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
}
