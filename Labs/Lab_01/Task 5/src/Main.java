import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        Random rand = new Random();
        int randInt = rand.nextInt(101);


        for (int attempts = 0; ; attempts++) {
            System.out.print("Guess the number: ");
            int guess = myObj.nextInt();
            if (guess < randInt) {
                System.out.println("The number is greater");
            }
            else if (guess > randInt) {
                System.out.println("The number is smaller");
            }
            else {
                System.out.println("Congrats! You guessed");
                System.out.printf("You have used %d attempts", attempts);
                break;
            }
        }
    }
}