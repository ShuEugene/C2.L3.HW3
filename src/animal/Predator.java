package animal;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.util.Arrays;

public class Predator extends Mammalian {

    private static int predatorsCount = 0;
    private static Predator[] predators = new Predator[0];

    protected static boolean add(Predator predator) {
        if (predator == null) {
            return false;
        }
        if (predators == null) {
            predators = new Predator[0];
            predatorsCount = 0;
        }
        for (Predator current :
                predators) {
            if (current != null && current.equals(predator)) {
                System.out.println(ALREADY_EXIST.replace(" БД.", " Перечень хищников."));
                return false;
            }
        }
        predators = Arrays.copyOf(predators, ++predatorsCount);
        predators[predatorsCount - 1] = predator;
        return true;
    }

    private static boolean delete(Predator predator) {
        if (predator == null) {
            return false;
        }
        if (predators == null) {
            System.out.println(LIST_IS_EMPTY.replace(" животных ", " хищников "));
            return false;
        }
        int matchPointer = DataService.getFirstMatchIndex(predators, predator);
        if (matchPointer >= 0) {
            predators[matchPointer] = null;
            predators = DataService.getNotNullObjects(predators);
            --predatorsCount;
            return true;
        }
        return false;
    }

    public static void show() {
        System.out.println("\nСписок всех хищников:");
        TextService.printList(predators, TextService.PrintModes.NUMBERED_LIST_PM);
    }


    private String foodType;


    public Predator(String title) {
        this(title, 0);
    }

    public Predator(String title, int age) {
        this(title, age, null);
    }

    public Predator(String title, int age, String habitat) {
        this(title, age, habitat, 0);
    }

    public Predator(String title, int age, String habitat, float moveSpeed) {
        this(title, age, habitat, moveSpeed, null);
    }

    public Predator(String title, int age, String habitat, float moveSpeed, String name) {
        super(title, age, habitat, moveSpeed, name);
        Predator.add(this);
    }


    @Override
    protected void eat() {
        System.out.println("\nРазрываю на части и проглатываю.");
    }

    @Override
    protected void go() {
        System.out.println("\nБегу прыжками, цепляясь когтями.");
    }

    public void hunt() {
        System.out.println("\nПодстерегаю добычу в засаде и бросаюсь на неё, впиваясь зубами и когтями");
    }


    public String getFoodType() {
        if (!DataService.isCorrect(foodType)) {
            return UNKNOWN;
        }
        return foodType;
    }

    public void setFoodType(String foodType) {
        if (DataService.isCorrect(foodType)) {
            this.foodType = foodType;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Predator)) return false;
        Predator predator = (Predator) o;
        return this.getName().equals(predator.getName())
                && this.getTitle().equals(predator.getTitle())
                && this.getAge() == predator.getAge();
    }
}