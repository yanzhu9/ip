package ada;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline‑type task that stores a description and a due‑date time.
 * Subclass of {@code Task}. Displays and persists the "by" deadline information.
 */
public class Deadline extends Task {

    static final LocalTime STORAGE_DEFAULT_TIME = LocalTime.of(23, 59);
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with given description and due datetime.
     *
     * @param description text content of the deadline task
     * @param by the due date‑time for this task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert by != null : "A deadline must have a due date and time";
        this.by = by;
    }

    /**
     * Returns human‑readable string for console display.
     * Prefixes "[D]" and appends formatted due‑date information.
     * Omits time if it equals {@code STORAGE_DEFAULT_TIME}.
     *
     * @return formatted string shown to users
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

        StringBuilder output = new StringBuilder();
        output.append(this.by.toLocalDate().format(dateFmt));

        if (!this.by.toLocalTime().equals(STORAGE_DEFAULT_TIME)) {
            output.append(" ").append(this.by.format(timeFmt));
        }

        return "[D]" + super.toString() + " (by: " + output + ")";
    }

    /**
     * Generates a storage‑compatible text line for saving into file.
     * Uses "D | " prefix and ISO‑8601 format for the deadline datetime.
     *
     * @return formatted line for persistent storage
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + this.by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
