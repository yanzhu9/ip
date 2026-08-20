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
        System.out.println("---------------------------------------------------");
        System.out.print(banner);
        System.out.println("Hello, I'm Ada.");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------");
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("---------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("---------------------------------------------------");
                break;
            }
            System.out.println("---------------------------------------------------");
            System.out.println(input);
            System.out.println("---------------------------------------------------");
        }
    }
}
