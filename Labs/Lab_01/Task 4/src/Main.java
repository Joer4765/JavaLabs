import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        System.out.print("Уведіть назву місяця: ");
        String month = myObj.nextLine();

        switch(month.toLowerCase()) {
            case "січень":
                System.out.println(31);
                break;
            case "лютий":
                System.out.println(28);
                break;
            case "березень":
                System.out.println(31);
                break;
            case "квітень":
                System.out.println(30);
                break;
            case "травень":
                System.out.println(28);
                break;
            case "червень":
                System.out.println(30);
                break;
            case "липень":
                System.out.println(31);
                break;
            case "серпень":
                System.out.println(31);
                break;
            case "вересень":
                System.out.println(30);
                break;
            case "жовтень":
                System.out.println(31);
                break;
            case "листопад":
                System.out.println(30);
                break;
            case "грудень":
                System.out.println(31);
                break;
        }

    }
}