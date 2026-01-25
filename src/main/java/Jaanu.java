import java.util.Arrays;
import java.util.Scanner;

public class Jaanu {
    private static int numOfTasks = 0;
    private static String[] tasks = new String[100];
    private static final String dividerLine = "____________________________________________________________\n";
    private static boolean[] tasksDone = new boolean[100];

    public static void printList(){
        for (int i = 0; i< numOfTasks ;i++){
            String doneChar;
            if (tasksDone[i]){
                doneChar = "X";
            }
            else {
                doneChar = " ";
            }
            System.out.println((i+1)+"."+"["+doneChar+"] "+tasks[i]);
        }
        return;
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

    public static void markTask(String[] inputString){
        int taskNum = Integer.parseInt(inputString[1])-1;
        tasksDone[taskNum] = true;
        System.out.println("Attaboy, keep the grind on");
        System.out.println("  [X]"+tasks[taskNum]);
    }

    public static void unmarkTask(String[] inputString){
        int taskNum = Integer.parseInt(inputString[1])-1;
        tasksDone[taskNum] = false;
        System.out.println("U lazy dog, go finish this task:");
        System.out.println("  [ ]"+tasks[taskNum]);
    }

    public static void manageTasks(){
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inputString;
        if (!input.contains("bye")){
            tasks[numOfTasks] = input;
        }
        else{
            return;
        }
        inputString = input.split(" ");
        while (!input.contains("bye")){
            if (input.equals("list")) {
                printList();
            }
            else if ((inputString[0]).equals("mark")){
                markTask(inputString);
            }
            else if ((inputString[0]).equals("unmark")){
                unmarkTask(inputString);
            }
            else {
                tasks[numOfTasks] = input;
                numOfTasks++;
                System.out.println(dividerLine + "added: " + input + "\n" + dividerLine + "\n");
            }
            input = in.nextLine();
            inputString = input.split(" ");
        }
    }

    public static void main(String[] args) {
        printGreeting();
        manageTasks();
        printBye();
    }
}
