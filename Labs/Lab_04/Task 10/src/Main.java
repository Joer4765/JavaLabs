import java.util.List;


class Animal {
    public void speak() {
        System.out.println("Тварина говорить");
    }
}

class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("Кіт нявкає");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("Собака гавкає");
    }
}

public class Main {
    public static void printSubclasses(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            if (animal instanceof Cat) {
                System.out.println("Це об'єкт класу Cat.");
            } else if (animal instanceof Dog) {
                System.out.println("Це об'єкт класу Dog.");
            } else {
                System.out.println("Це об'єкт базового класу Animal.");
            }
        }
    }

    public static void main(String[] args) {
        List<Animal> animalList = List.of(new Cat(), new Dog(), new Animal());

        printSubclasses(animalList);
    }
}


