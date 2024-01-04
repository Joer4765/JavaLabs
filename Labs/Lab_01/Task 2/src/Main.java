import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String s = "See? He's her lobster.";
        int l = s.length();
        boolean containsSubstring = s.contains("her; banana");
        String rep = "qwrtpsdfghjklzxcvbnm";
        String sWOConsonants = s.replaceAll("[" + rep + rep.toUpperCase() + "]", "");

        System.out.println("string length: " + l);
        System.out.println("contains her; banana: " + containsSubstring);
        System.out.println("string w/o consonants: " + sWOConsonants);
        System.out.println("result: " + s);
    }
}