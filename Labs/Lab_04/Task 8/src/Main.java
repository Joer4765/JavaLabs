interface Validator<T> {
    boolean validate(T item);
}

class StringValidator implements Validator<String> {
    @Override
    public boolean validate(String item) {
        return item != null && !item.isEmpty();
    }
}

class IntegerValidator implements Validator<Integer> {
    @Override
    public boolean validate(Integer item) {
        return item >= 0;
    }
}

public class Main {
    public static void main(String[] args) {

        Validator<String> stringValidator = new StringValidator();
        Validator<Integer> integerValidator = new IntegerValidator();

        String str = "Hello, world!";
        Integer number = -42;

        if (stringValidator.validate(str)) {
            System.out.println("Рядок є дійсним.");
        } else {
            System.out.println("Рядок не є дійсним.");
        }

        if (integerValidator.validate(number)) {
            System.out.println("Ціле число є дійсним.");
        } else {
            System.out.println("Ціле число не є дійсним.");
        }
    }
}
