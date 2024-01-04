class Pair<T> {
    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public boolean isEqual(Pair<?> otherPair) {
        return this.first.equals(otherPair.first) && this.second.equals(otherPair.second);
    }
}

public class Main {
    public static void main(String[] args) {
        Pair<Integer> intPair1 = new Pair<>(1, 2);
        Pair<Integer> intPair2 = new Pair<>(1, 2);
        Pair<String> stringPair = new Pair<>("hello", "world");

        boolean intPairsEqual = intPair1.isEqual(intPair2);
        System.out.println("Пари цілих чисел рівні: " + intPairsEqual);

        boolean stringPairsEqual = intPair1.isEqual(stringPair);
        System.out.println("Пари рядків рівні: " + stringPairsEqual);
    }
}