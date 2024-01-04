public class Main {
    public static void main(String[] args) {

        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();

        integerBox.put(42);
        int intItem = integerBox.get();
        System.out.println("Значення цілого числа: " + intItem);

        stringBox.put("Привіт, світ!");
        String strItem = stringBox.get();
        System.out.println("Рядок: " + strItem);
    }
}

class Box<T> {
    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}
