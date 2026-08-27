import ada.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest{
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }

    /**
     * checks the output of toString() in Ada.Todo class
     */
    @Test
    public void TodoTest(){
        Todo t = new Todo("read book");
        assertEquals("[T][ ] read book", t.toString());
    }
}
