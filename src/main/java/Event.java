import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {

    private final LocalDateTime start;
    private final LocalDateTime end;
    private static final LocalTime STORAGE_DEFAULT_TIME = LocalTime.of(23, 59);

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    private String formatDateTime(LocalDateTime dt) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a",Locale.ENGLISH);

        StringBuilder sb = new StringBuilder();
        sb.append(dt.toLocalDate().format(dateFormatter));
        if (!dt.toLocalTime().equals(STORAGE_DEFAULT_TIME)) {
            sb.append(" ").append(dt.format(timeFormatter));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(this.start)
                + " to: " + formatDateTime(this.end) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + this.start
                + " | " + this.end;
    }
}