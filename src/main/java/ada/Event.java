package ada;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event‑type task with a description, start datetime and end datetime.
 * Subclass of {@code Task}. Supports formatted display and file persistence
 * for event start‑and‑end time information.
 */
public class Event extends Task {

    private final LocalDateTime start;
    private final LocalDateTime end;
    /**
     * Default fallback time used when input only provides a date without time.
     */
    private static final LocalTime STORAGE_DEFAULT_TIME = LocalTime.of(23, 59);

    /**
     * Constructs an Event task with description, start and end date‑time.
     *
     * @param description text content describing the event
     * @param start starting date‑time of this event
     * @param end ending date‑time of this event
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    private String formatDateTime(LocalDateTime dt) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

        StringBuilder sb = new StringBuilder();
        sb.append(dt.toLocalDate().format(dateFormatter));
        if (!dt.toLocalTime().equals(STORAGE_DEFAULT_TIME)) {
            sb.append(" ").append(dt.format(timeFormatter));
        }
        return sb.toString();
    }

    /**
     * Returns console‑friendly string representation for this event.
     * Prefixes "[E]" and shows formatted start‑to‑end time range.
     *
     * @return formatted string for UI output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(this.start)
                + " to: " + formatDateTime(this.end) + ")";
    }

    /**
     * Produces storage‑compatible line for saving event to file.
     * Uses "E | " prefix and stores raw ISO datetime values.
     *
     * @return formatted text line for persistent file storage
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + this.start
                + " | " + this.end;
    }
}