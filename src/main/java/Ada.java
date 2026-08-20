import java.util.Scanner;

public class Ada {
    public static void main(String[] args) {
        String banner =
                "    _     ____      _    \n" +
                        "   / \\   |  _ \\    / \\   \n" +
                        "  / _ \\  | | | |  / _ \\  \n" +
                        " / ___ \\ | |_| | / ___ \\ \n" +
                        "/_/   \\_\\\\____/ /_/   \\_\\\n";
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 0;

        System.out.println("---------------------------------------------------");
        System.out.print(banner);
        System.out.println(" Hello, I'm Ada.");
        System.out.println(" What can I do for you?");
        System.out.println("---------------------------------------------------");
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("---------------------------------------------------");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("---------------------------------------------------");
                break;
            }else if(input.equals("list")){
                System.out.println("---------------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < index; i ++){
                    int j = i + 1;
                    String done = tasks[i].isDone() ? "[X] " : "[ ] ";
                    System.out.println(j + ". " + done + tasks[i].getDescription());
                }
                System.out.println("---------------------------------------------------");
            }else if(input.startsWith("mark")){
                int num = Integer.parseInt(input.substring(5));
                tasks[num - 1].markDone();
                System.out.println("---------------------------------------------------");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("  [X] " + tasks[num - 1].getDescription());
                System.out.println("---------------------------------------------------");
            }else if(input.startsWith("unmark")){
                int num = Integer.parseInt(input.substring(7));
                tasks[num - 1].markUndone();
                System.out.println("---------------------------------------------------");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + tasks[num - 1].getDescription());
                System.out.println("---------------------------------------------------");
            }else {
                Task task = new Task(input);
                tasks[index ++ ] = task;
                System.out.println("---------------------------------------------------");
                System.out.println(" added: " + input);
                System.out.println("---------------------------------------------------");
            }
        }
    }
}
