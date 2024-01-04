import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        System.out.print("Enter your mark: ");
        int mark = myObj.nextInt();

        if (0 <= mark && mark < 20) {
            System.out.println('F');
        }
        else if (20 <= mark && mark < 40) {
            System.out.println('D');
        }
        else if (40 <= mark && mark < 60) {
            System.out.println('C');
        }
        else if (60 <= mark && mark < 80) {
            System.out.println('B');
        }
        else if (80 <= mark && mark <= 100) {
            System.out.println('A');
        }

    }

}