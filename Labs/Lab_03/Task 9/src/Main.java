import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Product1", 25.0),
                new Product("Product2", 50.0),
                new Product("Product3", 75.0),
                new Product("Product4", 10.0),
                new Product("Product5", 60.0),
                new Product("Product6", 90.0)
        );

        Map<String, List<Product>> productsByPriceRange = products.stream()
                .collect(new PriceRangeCollector());

        productsByPriceRange.forEach((range, productList) -> {
            System.out.println("Ціновий діапазон: " + range);
            productList.forEach(product -> System.out.println("    " + product.getName()));
        });
    }
}

class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class PriceRangeCollector implements

        Collector<Product,
                Map<String, List<Product>>,
                Map<String, List<Product>>> {
    @Override
    public Supplier<Map<String, List<Product>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, List<Product>>, Product> accumulator() {
        return (map, product) -> {
            String range = getPriceRange(product.getPrice());
            map.computeIfAbsent(range, key -> new ArrayList<>()).add(product);
        };
    }

    @Override
    public BinaryOperator<Map<String, List<Product>>> combiner() {
        return (map1, map2) -> {
            map2.forEach((key, value) -> map1.merge(key, value, (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            }));
            return map1;
        };
    }

    @Override
    public Function<Map<String, List<Product>>, Map<String, List<Product>>> finisher() {
        return map -> map;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.IDENTITY_FINISH);
    }

    private String getPriceRange(double price) {
        if (price <= 30.0) {
            return "Дешевий";
        } else if (price <= 60.0) {
            return "Помірний";
        } else {
            return "Дорогий";
        }
    }
}
