package animal;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.util.Arrays;
import java.util.Objects;

public class Amphibian extends Animal {

    private static int amphibiansCount = 0;
    private static Amphibian[] amphibians = new Amphibian[0];

    protected static boolean add(Amphibian amphibian) {
        if (amphibian == null) {
            return false;
        }
        if (amphibians == null) {
            amphibians = new Amphibian[0];
            amphibiansCount = 0;
        }
        for (Amphibian current :
                amphibians) {
            if (current != null && current.equals(amphibian)) {
                System.out.println(ALREADY_EXIST.replace(" БД.", " Перечень земноводных."));
                return false;
            }
        }
        amphibians = Arrays.copyOf(amphibians, ++amphibiansCount);
        amphibians[amphibiansCount - 1] = amphibian;
        return true;
    }

    private static boolean delete(Amphibian amphibian) {
        if (amphibian == null) {
            return false;
        }
        if (amphibians == null) {
            System.out.println(LIST_IS_EMPTY.replace(" животных ", " земноводных "));
            System.out.println(CAN_NOT_DELETE);
            return false;
        }
        int matchPointer = DataService.getFirstMatchIndex(amphibians, amphibian);
        if (matchPointer >= 0) {
            amphibians[matchPointer] = null;
            amphibians = DataService.getNotNullObjects(amphibians);
            --amphibiansCount;
            return true;
        }
        return false;
    }

    public static void show() {
        System.out.println("\nСписок всех земноводных:");
        TextService.printList(amphibians, TextService.PrintModes.NUMBERED_LIST_PM);
    }


    public Amphibian(String title) {
        this(title, 0);
    }

    public Amphibian(String title, int age) {
        this(title, age, null);
    }

    public Amphibian(String title, int age, String habitat) {
        this(title, age, habitat, 0);
    }

    public Amphibian(String title, int age, String habitat, float moveSpeed) {
        this(title, age, habitat, moveSpeed, null);
    }

    public Amphibian(String title, int age, String habitat, float moveSpeed, String name) {
        super(title, age, habitat, moveSpeed, name);
        Amphibian.add(this);
    }


    public void eat() {
        System.out.println("\nПроглатываю, не пережёвывая...");
    }

    public void go() {
        String moveType;
        switch (getTitle()) {
            case "лягушка":
            case "жаба":
                moveType = "Прыгаю.";
                break;
            default:
                moveType = "Ползу.";
        }
        System.out.println(moveType + "...");
    }

    public void hunt() {
        System.out.println("\nЗемноводная охота!");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amphibian)) return false;
        Amphibian amphibian = (Amphibian) o;
        return this.getName().equals(amphibian.getName())
                && this.getTitle().equals(amphibian.getTitle())
                && this.getAge() == amphibian.getAge();
    }
}
