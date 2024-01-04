import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj  = new Scanner(System.in);

        System.out.println("Уведіть h:");
        int h = myObj.nextInt();
        System.out.println("Уведіть w:");
        Integer w = myObj.nextInt();
        System.out.println("Уведіть l:");
        Integer l = myObj.nextInt();

        int P_base = 2 * (l + w);
        int S_side = P_base * h;
        int S_base = l * w;
        int S = S_side + 2 * S_base;
        int V = S_base * h;

        System.out.println("Площа поверхні призми: " + S);
        System.out.println("Об’єм призми: " + V);


    }
}