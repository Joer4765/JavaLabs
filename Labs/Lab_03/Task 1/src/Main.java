import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Filtering_Mapping(Arrays.asList("apple", "Banana", "cherry", "Date", "Fig"));
    }

    private static void Filtering_Mapping(List<String> inputStrings) {
        List<String> result = inputStrings.stream()
                .filter(s -> s.contains("a"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }
}