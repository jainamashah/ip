import java.util.Arrays;
import java.util.Scanner;

public class Jaanu {
    private static int numOfTasks = 0;
    private static String[] tasks = new String[100];
    private static final String dividerLine = "____________________________________________________________\n";

    public static void printList(){
        for (int i = 0; i< numOfTasks ;i++){
            System.out.println((i+1)+"."+tasks[i]);
        }
    }

    public static void printGreeting(){
        String Greeting = dividerLine
                + " I'm your Jaanu\n"
                + " I'll do anything, just ask and see \n"
                + dividerLine;
        System.out.println(Greeting);
    }

    public static void printBye(){
        String Bye = dividerLine+
                "hope to never see you again. bye \n"
                + dividerLine;
        System.out.println(Bye);
    }

    public static void manageTasks(){
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (!input.contains("bye")){
            tasks[numOfTasks] = input;
        }
        else{
            return;
        }
        while (!input.contains("bye")){
            if (!input.equals("list")) {
                tasks[numOfTasks] = input;
                numOfTasks++;
                System.out.println(dividerLine + "added: " + input + "\n" + dividerLine + "\n");
            }
            else {
                printList();
            }
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        printGreeting();
        manageTasks();
        printBye();
    }
}
