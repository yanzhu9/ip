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
        String[] storage = new String[100];
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
                for(int i = 0; i < index; i ++){
                    int j = i + 1;
                    System.out.println(j + ". " + storage[i]);
                }
                System.out.println("---------------------------------------------------");
            }else {
                storage[index++] = input;
                System.out.println("---------------------------------------------------");
                System.out.println(" added: " + input);
                System.out.println("---------------------------------------------------");
            }
        }
    }
}
