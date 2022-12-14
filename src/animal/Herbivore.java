package animal;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.util.Arrays;
import java.util.Objects;

public class Herbivore extends Mammalian {

    private static int herbivoresCount = 0;
    private static Herbivore[] herbivores = new Herbivore[0];

    protected static boolean add(Herbivore herbivore) {
        if (herbivore == null) {
            return false;
        }
        if (herbivores == null) {
            herbivores = new Herbivore[0];
            herbivoresCount = 0;
        }
        for (Herbivore current :
                herbivores) {
            if (current != null && current.equals(herbivore)) {
                System.out.println(ALREADY_EXIST.replace(" БД.", " Перечень травоядных."));
                return false;
            }
        }
        herbivores = Arrays.copyOf(herbivores, ++herbivoresCount);
        herbivores[herbivoresCount - 1] = herbivore;
        return true;
    }

    private static boolean delete(Herbivore herbivore) {
        if (herbivore == null) {
            return false;
        }
        if (herbivores == null) {
            System.out.println(LIST_IS_EMPTY.replace(" животных ", " травоядных "));
            return false;
        }
        int matchPointer = DataService.getFirstMatchIndex(herbivores, herbivore);
        if (matchPointer >= 0) {
            herbivores[matchPointer] = null;
            herbivores = DataService.getNotNullObjects(herbivores);
            --herbivoresCount;
            return true;
        }
        return false;
    }

    public static void show() {
        System.out.println("\nСписок всех травоядных:");
        TextService.printList(herbivores, TextService.PrintModes.NUMBERED_LIST_PM);
    }


    private String foodType;


    public Herbivore(String title) {
        this(title, 0);
    }

    public Herbivore(String title, int age) {
        this(title, age, null);
    }

    public Herbivore(String title, int age, String habitat) {
        this(title, age, habitat, 0);
    }

    public Herbivore(String title, int age, String habitat, float moveSpeed) {
        this(title, age, habitat, moveSpeed, null);
    }

    public Herbivore(String title, int age, String habitat, float moveSpeed, String name) {
        super(title, age, habitat, moveSpeed, name);
        Herbivore.add(this);
    }


    public void eat() {
        System.out.println("\nПережёвываю и глотаю.");
    }

    public void go() {
        System.out.println("\nБегу, отталкиваясь.");
    }

    public void graze() {
        System.out.printf("\nПасусь...");
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
        if (!(o instanceof Herbivore)) return false;
        Herbivore herbivore = (Herbivore) o;
        return this.getName().equals(herbivore.getName())
                && this.getTitle().equals(herbivore.getTitle())
                && this.getAge() == herbivore.getAge();
    }
}