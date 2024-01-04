import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<City> cities = new ArrayList<>();
        cities.add(new City("City1", 25));
        cities.add(new City("City2", 32));
        cities.add(new City("City3", 28));
        cities.add(new City("City4", 34));
        cities.add(new City("City5", 30));

        List<City> hotCities = cities.stream()
                .filter(city -> city.getTemperature() > 30)
                .peek(city -> System.out.println("Гаряче місто: " + city.getName()))
                .collect(Collectors.toList());

        List<City> coldCities = cities.stream()
                .filter(city -> city.getTemperature() <= 30)
                .peek(city -> System.out.println("Холодне місто: " + city.getName()))
                .collect(Collectors.toList());
    }
}

class City {
    private final String name;
    private final int temperature;

    public City(String name, int temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public int getTemperature() {
        return temperature;
    }
}
