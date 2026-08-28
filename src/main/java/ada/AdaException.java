package ada;

<<<<<<< HEAD
/**
 * Represents application‑specific checked exceptions for the Ada task manager.
 * Used to signal application‑level errors such as invalid user input.
 */
public class AdaException extends Exception{
    /**
     * Constructs an AdaException with the given error message.
     *
     * @param message the detail error message shown to users
     */
    public AdaException(String message){
=======
public class AdaException extends Exception {
    public AdaException(String message) {
>>>>>>> branch-Level-9
        super(message);
    }
}