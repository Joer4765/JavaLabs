public class Main {

    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        T max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {

        Integer[] intArray = {5, 2, 9, 1, 7};
        Integer maxInt = findMax(intArray);
        System.out.println("Максимальне ціле число: " + maxInt);

        Double[] doubleArray = {3.14, 1.618, 2.718, 2.0};
        Double maxDouble = findMax(doubleArray);
        System.out.println("Максимальне подвійне число: " + maxDouble);

        String[] stringArray = {"apple", "banana", "cherry", "date"};
        String maxString = findMax(stringArray);
        System.out.println("Максимальний рядок: " + maxString);
    }
}
