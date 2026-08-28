package ada;

/**
 * Parses raw user‑input strings and converts them into structured {@code Parser.CommandInfo} objects.
 * Detects command type, extracts arguments and throws {@code AdaException} for format‑violating input.
 */
public class Parser {

    /**
     * Data‑holding inner class that stores parsed result of one user command.
     * Contains command name and corresponding extracted argument fields.
     */
    public static class CommandInfo {
        public String command;
        public int taskNum;
        public String description;
        public String by;
        public String from;
        public String to;

        /**
         * Creates a CommandInfo holding only the command name.
         * Other fields remain uninitialized and should be set manually if needed.
         *
         * @param command name of the recognized user command
         */
        public CommandInfo(String command) {
            this.command = command;
        }
    }

    /**
     * Parses one line of user input into a {@code CommandInfo}.
     * Validates input syntax and extracts relevant command arguments.
     *
     * @param input raw text line entered by the user
     * @return structured parsed command information
     * @throws AdaException when input format is invalid or required arguments are missing
     */
    public CommandInfo parse(String input) throws AdaException {
        if (input.equals("bye")) {
            return new CommandInfo("bye");
        }
        if (input.equals("list")) {
            return new CommandInfo("list");
        }

        if (input.startsWith("mark")) {
            String after = input.substring(4).trim();
            if (after.isEmpty()) throw new AdaException("Please provide a task number.");
            if (input.charAt(4) != ' ') throw new AdaException("Wrong format. Use exactly one space: mark <>");
            int num;
            try {
                num = Integer.parseInt(after);
            } catch (NumberFormatException e) {
                throw new AdaException("Please input a valid integer number.");
            }
            CommandInfo ci = new CommandInfo("mark");
            ci.taskNum = num;
            return ci;
        }

        if (input.startsWith("unmark")) {
            String after = input.substring(6).trim();
            if (after.isEmpty()) throw new AdaException("Please provide a task number.");
            if (input.charAt(6) != ' ') throw new AdaException("Wrong format. Use exactly one space: unmark <>");
            int num;
            try {
                num = Integer.parseInt(after);
            } catch (NumberFormatException e) {
                throw new AdaException("Please input a valid integer number.");
            }
            CommandInfo ci = new CommandInfo("unmark");
            ci.taskNum = num;
            return ci;
        }

        if (input.startsWith("todo")) {
            String desc = input.substring(4).trim();
            if (desc.isEmpty())
                throw new AdaException("Ada.Todo description can not be empty. Please input task content.");
            if (input.charAt(4) != ' ') throw new AdaException("Wrong format. Use exactly one space: todo <>");
            CommandInfo ci = new CommandInfo("todo");
            ci.description = desc;
            return ci;
        }

        if (input.startsWith("deadline")) {
            String content = input.substring(8).trim();
            if (content.isEmpty()) throw new AdaException("Ada.Deadline can not be empty. Please input task content.");
            if (input.charAt(8) != ' ') throw new AdaException("Wrong format. Use exactly one space: deadline <>");
            int byIdx = content.indexOf(" /by");
            if (byIdx == -1)
                throw new AdaException("Missing marker '/by'. Please follow format: deadline xxx /by xxx.");
            String desc = content.substring(0, byIdx);
            if (desc.isEmpty())
                throw new AdaException("Ada.Deadline description can not be empty. Please input task description.");
            String by = content.substring(byIdx + 4).trim();
            if (by.isEmpty())
                throw new AdaException("Ada.Deadline time can not be empty. If you are not sure about the due date, enter 'not know' is also valid.");
            CommandInfo ci = new CommandInfo("deadline");
            ci.description = desc;
            ci.by = by;
            return ci;
        }

        if (input.startsWith("event")) {
            String content = input.substring(5).trim();
            if (content.isEmpty()) throw new AdaException("Ada.Event can not be empty. Please input task content.");
            if (input.charAt(5) != ' ') throw new AdaException("Wrong format. Use exactly one space: event <>");
            int fromIdx = content.indexOf(" /from");
            int toIdx = content.indexOf(" /to");
            if (fromIdx == -1) throw new AdaException("Missing marker '/from'. Format: event xxx /from xxx /to xxx");
            if (toIdx == -1) throw new AdaException("Missing marker '/to'. Format: event xxx /from xxx /to xxx");
            if (fromIdx > toIdx)
                throw new AdaException("Wrong format.Marker order should be: event xxx /from xxx /to xxx");
            String desc = content.substring(0, fromIdx);
            if (desc.isEmpty())
                throw new AdaException("Ada.Event description can not be empty. Please input task description.");
            String from = content.substring(fromIdx + 6, toIdx).trim();
            if (from.isEmpty())
                throw new AdaException("Starting time can not be empty. If you are not sure about the due date, enter 'not know' is also valid.");
            String to = content.substring(toIdx + 4).trim();
            if (to.isEmpty())
                throw new AdaException("Ending time can not be empty. If you are not sure about the due date, enter 'not know' is also valid.");
            CommandInfo ci = new CommandInfo("event");
            ci.description = desc;
            ci.from = from;
            ci.to = to;
            return ci;
        }

        if (input.startsWith("delete")) {
            String after = input.substring(6).trim();
            if (after.isEmpty()) throw new AdaException("Please provide a task number.");
            if (input.charAt(6) != ' ') throw new AdaException("Wrong format. Use exactly one space: delete <>");
            int num;
            try {
                num = Integer.parseInt(after);
            } catch (NumberFormatException e) {
                throw new AdaException("Please input a valid integer number.");
            }
            CommandInfo ci = new CommandInfo("delete");
            ci.taskNum = num;
            return ci;
        }

        return new CommandInfo("unknown");
    }
}