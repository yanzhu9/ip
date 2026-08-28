package ada;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/Ada.txt";

    public Storage() {
        File dataFolder = new File("./data");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    /**
     * read from the disk then return the task list
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return tasks;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseLineToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("File cannot be loaded. Starting with empty task list.");
        }
        return tasks;
    }

    /**
     * write the current task list into the disk
     */
    public void save(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task t : tasks) {
                fw.write(t.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Warning: fail to save tasks to file.");
        }
    }

    Task parseLineToTask(String line) {
        // use '|' to split the String
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (type.equals("T")) {
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markDone();
            }
            return todo;
        } else if (type.equals("D")) {
            String byStr = parts[3];
            LocalDateTime by = LocalDateTime.parse(byStr);
            Deadline d = new Deadline(description, by);
            if (isDone) {
                d.markDone();
            }
            return d;
        } else if (type.equals("E")) {
            String startStr = parts[3].trim();
            String endStr = parts[4].trim();
            LocalDateTime start = LocalDateTime.parse(startStr);
            LocalDateTime end = LocalDateTime.parse(endStr);
            Event e = new Event(description, start, end);
            if (isDone) {
                e.markDone();
            }
            return e;
        }
        return null;
    }
}