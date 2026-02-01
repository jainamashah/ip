    import java.util.Arrays;
    import java.util.Scanner;

    public class Jaanu {
        private static int numOfTasks = 0;
        private static final String DIVIDER_LINE = "____________________________________________________________\n";
        private static Task[] taskList = new Task[100];
        private static String[] parsedArgs = new String[3];

        public static void printList() {
            for (int i = 0; i < numOfTasks; i++) {
                String doneChar = taskList[i].getStatusIcon();
                String classChar = taskList[i].getClassIcon();
                System.out.println((i + 1) + "." + " [" + classChar + "] "+ "[" + doneChar + "] " + taskList[i].description);
            }
        }

        public static void printGreeting() {
            String greeting = DIVIDER_LINE
                    + " I'm your Jaanu\n"
                    + " I'll do anything, just ask and see\n"
                    + DIVIDER_LINE;
            System.out.println(greeting);
        }

        public static void printBye() {
            String bye = DIVIDER_LINE
                    + "hope to never see you again. bye \n"
                    + DIVIDER_LINE;
            System.out.println(bye);
        }

        public static void printAddConfirmationMsg() {
            String Msg = DIVIDER_LINE
                        + "um ok, there you go babes:";
            System.out.println(Msg);
            System.out.println(taskList[numOfTasks].toString());
            String remainingTaskMsg = "Now you have " + (numOfTasks+1) + " tasks in the list.\n"
                                    + DIVIDER_LINE;
            System.out.println(remainingTaskMsg);
        }

        public static void markTask(String[] inputString) {
            int taskNum = Integer.parseInt(inputString[1]) - 1;
            taskList[taskNum].setAsDone();
            System.out.println("Attaboy, keep the grind on");
            System.out.println("  [X]" + taskList[taskNum].description);
        }

        public static void unmarkTask(String[] inputString) {
            int taskNum = Integer.parseInt(inputString[1]) - 1;
            taskList[taskNum].setAsNotDone();
            System.out.println("U lazy dog, go finish this task:");
            System.out.println("  [ ]" + taskList[taskNum].description);
        }

        public static void parseArgs(String input){
            if (input.contains("/")) {
                int dividerPosition = input.indexOf("/");
                parsedArgs[0] = input.substring(0, dividerPosition);
                String temp = input.substring(dividerPosition + 1);
                if (temp.contains("/")) {
                    int secondDividerPosition = temp.indexOf("/")+dividerPosition+1;
                    parsedArgs[1] = input.substring(dividerPosition + 1, secondDividerPosition);
                    parsedArgs[2] = input.substring(secondDividerPosition + 1);
                } else {
                    parsedArgs[1] = "no from date";
                    parsedArgs[2] = input.substring(dividerPosition + 1);
                }
            }
            else {
                parsedArgs[0] = input;
                parsedArgs[1] = "no from date";
                parsedArgs[2] = "no to date";
            }
        }

        public static void manageTasks() {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] inputString;
            inputString = input.split(" ");
            int taskDescriptionLength;
            while (!input.contains("bye")) {
                parseArgs(input);
                taskDescriptionLength = (parsedArgs[0]).length();
                if (input.equals("list")) {
                    printList();
                } else if ((inputString[0]).equals("mark")) {
                    markTask(inputString);
                } else if ((inputString[0]).equals("unmark")) {
                    unmarkTask(inputString);
                } else if ((inputString[0]).equals("todo")){
                    taskList[numOfTasks] = new Task((parsedArgs[0]).substring(5,taskDescriptionLength));
                    printAddConfirmationMsg();
                    numOfTasks++;
                }
                else if ((inputString[0]).equals("deadline")){
                    taskList[numOfTasks] = new Deadline((parsedArgs[0]).substring(9,taskDescriptionLength),parsedArgs[2]);
                    printAddConfirmationMsg();
                    numOfTasks++;
                }
                else if ((inputString[0]).equals("event")){
                    taskList[numOfTasks] = new Event((parsedArgs[0]).substring(6,taskDescriptionLength),parsedArgs[1],parsedArgs[2]);
                    printAddConfirmationMsg();
                    numOfTasks++;
                }
                else {
                    System.out.println("invalid command, try again");
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
