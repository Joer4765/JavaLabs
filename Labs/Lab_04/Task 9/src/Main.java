import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Object> data = new ArrayList<>();
        
        data.add(42);               
        data.add("Hello, world!");  
        data.add(3.14);             
        
        Map<String, Object> map = new HashMap<>();
        
        map.put("integer", data.get(0));  
        map.put("string", data.get(1));   
        map.put("double", data.get(2));   
        
        System.out.println("Значення в ArrayList:");
        for (Object item : data) {
            System.out.println(item);
        }

        System.out.println("\nЗначення в HashMap:");
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }

        data.set(1, "New value");  

        System.out.println("\nЗмінені значення в ArrayList:");
        for (Object item : data) {
            System.out.println(item);
        }
    }
}
