package ada;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime by;
    static final LocalTime STORAGE_DEFAULT_TIME = LocalTime.of(23, 59);

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

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

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + this.by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}