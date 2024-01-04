import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4);
        List<Double> doubleList = List.of(1.5, 2.5, 3.5);

        double sumIntegers = sum(integerList);
        double sumDoubles = sum(doubleList);

        System.out.println("Сума цілих чисел: " + sumIntegers);
        System.out.println("Сума подвійних чисел: " + sumDoubles);

    }

    public static double sum(List<? extends Number> numberList) {
        double total = 0.0;
        for (Number number : numberList) {
            total += number.doubleValue();
        }
        return total;
    }
}

