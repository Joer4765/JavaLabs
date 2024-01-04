import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book1", 2000));
        books.add(new Book("Book2", 1990));
        books.add(new Book("Book3", 2010));
        books.add(new Book("Book4", 1980));
        books.add(new Book("Book5", 2020));
        List<Book> result = books.stream()
                .sorted(Comparator.comparingInt(Book::getYear))
                .limit(3)
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}

class Book {
    private final String title;
    private final int year;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
}
