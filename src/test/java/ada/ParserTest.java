package ada;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private final Parser parserObj = new Parser();
    //bye
    @Test
    void parse_bye_returnsByeCommand() throws AdaException {
        Parser.CommandInfo ci = parserObj.parse("bye");
        assertEquals("bye", ci.command);
    }

    //list
    @Test
    void parse_list_returnsListCommand() throws AdaException {
        Parser.CommandInfo ci = parserObj.parse("list");
        assertEquals("list", ci.command);
    }

    //valid todo
    @Test
    void parse_validTodo_returnsTodoCommand() throws AdaException {
        Parser.CommandInfo ci = parserObj.parse("todo buy book");
        assertEquals("todo", ci.command);
        assertEquals("buy book", ci.description);
    }

    //todo with no task description
    @Test
    void parse_todoEmptyDesc_throwsAdaException() {
        assertThrows(AdaException.class, () -> parserObj.parse("todo "));
    }

    //valid mark
    @Test
    void parse_validMark_returnsMarkCommand() throws AdaException {
        Parser.CommandInfo ci = parserObj.parse("mark 2");
        assertEquals("mark", ci.command);
        assertEquals(2, ci.taskNum);
    }

    //mark with not number
    @Test
    void parse_markNotNumber_throwsAdaException() {
        assertThrows(AdaException.class, () -> parserObj.parse("mark abc"));
    }

    //invalid mark format
    @Test
    void parse_markInvalidFormat_throwsAdaException() {
        assertThrows(AdaException.class, () -> parserObj.parse("mark1"));
    }

    // valid deadline
    @Test
    void parse_validDeadline_returnsDeadlineCommand() throws AdaException {
        Parser.CommandInfo ci = parserObj.parse("deadline essay /by friday");
        assertEquals("deadline", ci.command);
        assertEquals("essay", ci.description);
        assertEquals("friday", ci.by);
    }

    //deadline lack /by
    @Test
    void parse_deadlineMissingBy_throwsAdaException() {
        assertThrows(AdaException.class, () -> parserObj.parse("deadline finish assignment"));
    }

    // valid event
    @Test
    void parse_validEvent_returnsEventCommand() throws AdaException {
        Parser.CommandInfo ci = parserObj.parse("event team meeting /from mon /to fri");
        assertEquals("event", ci.command);
        assertEquals("team meeting", ci.description);
        assertEquals("mon", ci.from);
        assertEquals("fri", ci.to);
    }

    // event lack /to
    @Test
    void parse_eventMissingTo_throwsAdaException() {
        assertThrows(AdaException.class, () -> parserObj.parse("event meeting /from mon"));
    }

    // valid delete
    @Test
    void parse_validDelete_returnsDeleteCommand() throws AdaException {
        Parser.CommandInfo ci = parserObj.parse("delete 3");
        assertEquals("delete", ci.command);
        assertEquals(3, ci.taskNum);
    }

    //invalid command
    @Test
    void parse_unknownCommand_returnUnknown() throws AdaException {
        Parser.CommandInfo ci = parserObj.parse("helloworld");
        assertEquals("unknown", ci.command);
    }
}
