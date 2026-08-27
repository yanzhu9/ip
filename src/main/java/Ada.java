import java.util.Scanner;
import java.util.ArrayList;

public class Ada {
    public static void main(String[] args) {
        String banner =
                "    _     ____      _    \n" +
                        "   / \\   |  _ \\    / \\   \n" +
                        "  / _ \\  | | | |  / _ \\  \n" +
                        " / ___ \\ | |_| | / ___ \\ \n" +
                        "/_/   \\_\\\\____/ /_/   \\_\\\n";
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        ArrayList<Task> tasks = storage.load();

        System.out.println("---------------------------------------------------");
        System.out.print(banner);
        System.out.println(" Hello, I'm Ada.");
        System.out.println(" What can I do for you?");
        System.out.println("---------------------------------------------------");
        while(true) {
            try {
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println("---------------------------------------------------");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("---------------------------------------------------");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("---------------------------------------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        int j = i + 1;
                        System.out.println(j + ". " + tasks.get(i).toString());
                    }
                    System.out.println("---------------------------------------------------");
                } else if (input.startsWith("mark")) {
                    String afterMark = input.substring(4).trim();
                    if(afterMark.isEmpty()){
                        throw new AdaException("Please provide a task number.");
                    }
                    if(input.charAt(4) != ' '){
                        throw new AdaException("Wrong format. Use exactly one space: mark <>");
                    }
                    int num;
                    try{
                        num = Integer.parseInt(afterMark);
                    }catch(NumberFormatException e){
                        throw new AdaException("Please input a valid integer number.");
                    }
                    if(num < 1 || num > tasks.size()){
                        throw new AdaException("This task does not exist. Please enter a valid task number.");
                    }
                    tasks.get(num - 1).markDone();
                    storage.save(tasks);
                    System.out.println("---------------------------------------------------");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(num - 1).toString());
                    System.out.println("---------------------------------------------------");
                } else if (input.startsWith("unmark")) {
                    String afterUnmark = input.substring(6).trim();
                    if(afterUnmark.isEmpty()){
                        throw new AdaException("Please provide a task number.");
                    }
                    if(input.charAt(6) != ' '){
                        throw new AdaException("Wrong format. Use exactly one space: unmark <>");
                    }
                    int num;
                    try{
                        num = Integer.parseInt(afterUnmark);
                    }catch(NumberFormatException e){
                        throw new AdaException("Please input a valid integer number.");
                    }
                    if(num < 1 || num > tasks.size()){
                        throw new AdaException("This task does not exist. Please enter a valid task number.");
                    }
                    tasks.get(num - 1).markUndone();
                    storage.save(tasks);
                    System.out.println("---------------------------------------------------");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(num - 1).toString());
                    System.out.println("---------------------------------------------------");
                } else if (input.startsWith("todo")) {
                    String description = input.substring(4).trim();
                    if(description.isEmpty()){
                        throw new AdaException("Todo description can not be empty. Please input task content.");
                    }
                    if(input.charAt(4) != ' '){
                        throw new AdaException("Wrong format. Use exactly one space: todo <>");
                    }
                    Task todo = new Todo(description);
                    tasks.add(todo);
                    storage.save(tasks);
                    System.out.println("---------------------------------------------------");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + todo.toString());
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list");
                    System.out.println("---------------------------------------------------");
                } else if (input.startsWith("deadline")) {
                    String content = input.substring(8).trim();
                    if(content.isEmpty()){
                        throw new AdaException("Deadline can not be empty. Please input task content.");
                    }
                    if(input.charAt(8) != ' '){
                        throw new AdaException("Wrong format. Use exactly one space: deadline <>");
                    }
                    int byIndex = content.indexOf(" /by");
                    if(byIndex == -1){
                        throw new AdaException("Missing marker '/by'. Please follow format: deadline xxx /by xxx.");
                    }
                    String description = content.substring(0, byIndex);
                    if(description.isEmpty()){
                        throw new AdaException("Deadline description can not be empty. Please input task description.");
                    }
                    String by = content.substring(byIndex + 4).trim();
                    if(by.isEmpty()){
                        throw new AdaException("Deadline time can not be empty. If you are not sure about the due date, enter 'not know' is also valid.");
                    }
                    Task deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    storage.save(tasks);
                    System.out.println("---------------------------------------------------");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + deadline.toString());
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list");
                    System.out.println("---------------------------------------------------");
                } else if (input.startsWith("event")) {
                    String content = input.substring(5).trim();
                    if(content.isEmpty()){
                        throw new AdaException("Event can not be empty. Please input task content.");
                    }
                    if(input.charAt(5) != ' '){
                        throw new AdaException("Wrong format. Use exactly one space: event <>");
                    }
                    int fromIndex = content.indexOf(" /from");
                    if(fromIndex == -1){
                        throw new AdaException("Missing marker '/from'. Format: event xxx /from xxx /to xxx");
                    }
                    int toIndex = content.indexOf(" /to");
                    if(toIndex == -1){
                        throw new AdaException("Missing marker '/to'. Format: event xxx /from xxx /to xxx");
                    }
                    if(fromIndex > toIndex){
                        throw new AdaException("Wrong format.Marker order should be: event xxx /from xxx /to xxx");
                    }
                    String description = content.substring(0, fromIndex);
                    if(description.isEmpty()){
                        throw new AdaException("Event description can not be empty. Please input task description.");
                    }
                    String from = content.substring(fromIndex + 6, toIndex).trim();
                    if(from.isEmpty()){
                        throw new AdaException("Starting time can not be empty. If you are not sure about the due date, enter 'not know' is also valid.");
                    }
                    String to = content.substring(toIndex + 4).trim();
                    if(to.isEmpty()){
                        throw new AdaException("Ending time can not be empty. If you are not sure about the due date, enter 'not know' is also valid.");
                    }
                    Task event = new Event(description, from, to);
                    tasks.add(event);
                    storage.save(tasks);
                    System.out.println("---------------------------------------------------");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + event.toString());
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list");
                    System.out.println("---------------------------------------------------");
                } else if(input.startsWith("delete")){
                    String afterDelete = input.substring(6).trim();
                    if(afterDelete.isEmpty()){
                        throw new AdaException("Please provide a task number.");
                    }
                    if(input.charAt(6) != ' '){
                        throw new AdaException("Wrong format. Use exactly one space: delete <>");
                    }
                    int num;
                    try{
                        num = Integer.parseInt(afterDelete);
                    }catch(NumberFormatException e){
                        throw new AdaException("Please input a valid integer number.");
                    }
                    if(num < 1 || num > tasks.size()){
                        throw new AdaException("This task does not exist. Please enter a valid task number.");
                    }
                    Task taskT = tasks.get(num - 1);
                    tasks.remove(num - 1);
                    storage.save(tasks);
                    System.out.println("---------------------------------------------------");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("  " + taskT.toString());
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list");
                    System.out.println("---------------------------------------------------");

                } else {
                    System.out.println("---------------------------------------------------");
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("---------------------------------------------------");
                }
            }catch(AdaException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
