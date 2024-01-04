import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 5, 7, 2, 4, 6, 7, 11, 13, 13, 17);

        long distinctPrimesCount = numbers.stream()
                .distinct()
                .filter(Main::isPrime)
                .count();

        System.out.println("Кількість різних простих чисел: " + distinctPrimesCount);
    }

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
