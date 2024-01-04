import java.util.List;

public class Main {
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {

        List<Integer> integerList = List.of(1, 2, 3, 4);
        List<String> stringList = List.of("apple", "banana", "cherry");

        System.out.println("Список цілих чисел:");
        printList(integerList);

        System.out.println("Список рядків:");
        printList(stringList);
    }
}
