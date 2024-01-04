import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(List.of(new Item("Item1", 55), new Item("Item2", 45), new Item("Item3", 60))));
        orders.add(new Order(List.of(new Item("Item4", 40), new Item("Item5", 70), new Item("Item6", 30))));


        List<Item> expensiveItems = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .filter(item -> item.getPrice() > 50)
                .collect(Collectors.toList());

        expensiveItems.forEach(System.out::println);
    }
}

class Order {
    private final List<Item> items;

    public Order(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}

class Item {
    private final String name;
    private final int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - " + price + " грн";
    }
}
