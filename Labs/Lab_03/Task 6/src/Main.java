import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation("Дохід", 1000));
        operations.add(new Operation("Витрати", 500));
        operations.add(new Operation("Дохід", 2000));
        operations.add(new Operation("Витрати", 750));
        operations.add(new Operation("Дохід", 1500));


        Map<String, Integer> totalByCategory = operations.stream()
                .collect(Collectors.groupingBy(Operation::getCategory, Collectors.summingInt(Operation::getAmount)));

        totalByCategory.forEach((category, total) -> System.out.println(category + ": " + total + " грн"));
    }
}

class Operation {
    private final String category;
    private final int amount;

    public Operation(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }
}
