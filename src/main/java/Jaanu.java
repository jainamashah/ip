import java.util.Arrays;
import java.util.Scanner;

public class Jaanu {
    public static void main(String[] args) {
        String dividerLine = "____________________________________________________________\n";
        String Greeting = dividerLine
                + " I'm your Jaanu\n"
                + " I'll do anything for you my love, just ask and see ;-) \n"
                + dividerLine;
        String Bye = dividerLine+
                " how dare you, hope to never see you again. bye :-( \n"
                + dividerLine;
        Scanner in = new Scanner(System.in);
        System.out.println(Greeting);
        String input = in.nextLine() +"\n";
        while (!input.contains("bye")){
            System.out.println(dividerLine+input+dividerLine);
            input = in.nextLine()+"\n";
        }
        System.out.println(Bye);
    }
}
