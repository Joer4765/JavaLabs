import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
                "The quick brown fox jumps over the lazy dog",
                "The sun is shining and the birds are singing",
                "This is a sample sentence with some common words"
        );

        List<String> stopWords = Arrays.asList("the", "and", "is", "a");

        System.out.println("Унікальні слова:");
        List<String> uniqueWords = sentences.stream()
                .map(sentence -> sentence.toLowerCase().split("\\s+"))
                .flatMap(Arrays::stream)
                .filter(word -> !stopWords.contains(word))
                .distinct()
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
