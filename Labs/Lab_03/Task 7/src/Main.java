import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "fig");


        String concatenatedString = words.stream()
                .collect(Collectors.joining(", "));


        System.out.println(concatenatedString);
    }
}
