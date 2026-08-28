package ada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Serves as the main application class for Ada task‑manager.
 * Coordinates user interface, input parsing, task operations and file storage.
 * Initializes core components, loads saved‑tasks, and runs the main interactive loop
 * to process user commands until receiving the "bye" command.
 */
public class Ada {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Constructs an Ada application instance.
     * Initializes UI, storage, parser, and loads existing tasks from disk into TaskList.
     */
    public Ada() {
        ui = new Ui();
        storage = new Storage();
        ArrayList<Task> loaded = storage.load();
        taskList = new TaskList(loaded);
        parser = new Parser();
    }

    /**
     * Starts the main‑event loop of the program.
     * Continuously reads user input, parses and executes commands.
     * Handles runtime‑specific exceptions and terminates on "bye" command.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String input = ui.readCommand();
                Parser.CommandInfo cmd = parser.parse(input);

                switch (cmd.command) {
                case "bye":
                    ui.showBye();
                    return;
                case "list":
                    ui.showTaskList(taskList.getAllTasks());
                    break;
                case "mark":
                    if (cmd.taskNum < 1 || cmd.taskNum > taskList.size()) {
                        throw new AdaException("This task does not exist. Please enter a valid task number.");
                    }
                    taskList.markDone(cmd.taskNum - 1);
                    storage.save(taskList.getAllTasks());
                    ui.showMarkDone(taskList.getTask(cmd.taskNum - 1));
                    break;
                case "unmark":
                    if (cmd.taskNum < 1 || cmd.taskNum > taskList.size()) {
                        throw new AdaException("This task does not exist. Please enter a valid task number.");
                    }
                    taskList.markUndone(cmd.taskNum - 1);
                    storage.save(taskList.getAllTasks());
                    ui.showMarkUndone(taskList.getTask(cmd.taskNum - 1));
                    break;
                case "todo":
                    Todo todo = new Todo(cmd.description);
                    taskList.addTask(todo);
                    storage.save(taskList.getAllTasks());
                    ui.showAddTask(todo, taskList.size());
                    break;
                case "deadline":
                    LocalDateTime byDateTime;
                    try {
                        if (cmd.by.contains(" ")) {
                            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            byDateTime = LocalDateTime.parse(cmd.by, f);
                        } else {
                            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate d = LocalDate.parse(cmd.by, f);
                            byDateTime = LocalDateTime.of(d, Deadline.STORAGE_DEFAULT_TIME);
                        }
                        Deadline dl = new Deadline(cmd.description, byDateTime);
                        taskList.addTask(dl);
                        storage.save(taskList.getAllTasks());
                        ui.showAddTask(dl, taskList.size());
                    } catch (DateTimeException e) {
                        ui.showDateTimeErrorDeadline();
                    }
                    break;
                case "event":
                    try {
                        LocalDateTime start;
                        if (cmd.from.contains(" ")) {
                            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            start = LocalDateTime.parse(cmd.from, f);
                        } else {
                            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate d = LocalDate.parse(cmd.from, f);
                            start = LocalDateTime.of(d, Deadline.STORAGE_DEFAULT_TIME);
                        }
                        LocalDateTime end;
                        String toTrim = cmd.to.trim();
                        if (toTrim.contains("-")) {
                            end = LocalDateTime.parse(toTrim);
                        } else {
                            int hh = Integer.parseInt(toTrim.substring(0, 2));
                            int mm = Integer.parseInt(toTrim.substring(2, 4));
                            LocalTime t = LocalTime.of(hh, mm);
                            end = LocalDateTime.of(start.toLocalDate(), t);
                        }
                        Event evt = new Event(cmd.description, start, end);
                        taskList.addTask(evt);
                        storage.save(taskList.getAllTasks());
                        ui.showAddTask(evt, taskList.size());
                    } catch (DateTimeException e) {
                        ui.showDateTimeErrorEvent();
                    }
                    break;
                case "delete":
                    if (cmd.taskNum < 1 || cmd.taskNum > taskList.size()) {
                        throw new AdaException("This task does not exist. Please enter a valid task number.");
                    }
                    Task removed = taskList.removeTask(cmd.taskNum - 1);
                    storage.save(taskList.getAllTasks());
                    ui.showDeleteTask(removed, taskList.size());
                    break;
                case "find":
                    String keyword = cmd.keyword;
                    List<Task> matched = taskList.matchTasksByKeyword(keyword);
                    ui.showFindResult(matched);
                    break;
                default:
                    ui.showUnknownCommand();
                }
            } catch (AdaException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Application entry‑point.
     * Creates an Ada instance and launches the interactive program.
     *
     * @param args command‑line arguments (unused)
     */
    public static void main(String[] args) {
        new Ada().run();
    }
}