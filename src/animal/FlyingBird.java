package animal;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.util.Arrays;

public class FlyingBird extends Bird {

    private static int flyingBirdsCount = 0;
    private static FlyingBird[] flyingBirds = new FlyingBird[0];

    protected static boolean add(FlyingBird flyingBird) {
        if (flyingBird == null) {
            return false;
        }
        if (flyingBirds == null) {
            flyingBirds = new FlyingBird[0];
            flyingBirdsCount = 0;
        }
        for (FlyingBird current :
                flyingBirds) {
            if (current != null && current.equals(flyingBird)) {
                System.out.println(ALREADY_EXIST.replace(" БД.", " Перечень летающих птиц."));
                return false;
            }
        }
        flyingBirds = Arrays.copyOf(flyingBirds, ++flyingBirdsCount);
        flyingBirds[flyingBirdsCount - 1] = flyingBird;
        return true;
    }

    private static boolean delete(FlyingBird flyingBird) {
        if (flyingBird == null) {
            return false;
        }
        if (flyingBirds == null) {
            System.out.println(LIST_IS_EMPTY.replace(" животных ", " летающих птиц "));
            return false;
        }
        int matchPointer = DataService.getFirstMatchIndex(flyingBirds, flyingBird);
        if (matchPointer >= 0) {
            flyingBirds[matchPointer] = null;
            flyingBirds = DataService.getNotNullObjects(flyingBirds);
            --flyingBirdsCount;
            return true;
        }
        return false;
    }

    public static void show() {
        System.out.println("\nСписок всех летающих птиц:");
        TextService.printList(flyingBirds, TextService.PrintModes.NUMBERED_LIST_PM);
    }


    private String moveType;


    public FlyingBird(String title) {
        this(title, 0);
    }

    public FlyingBird(String title, int age) {
        this(title, age, null);
    }

    public FlyingBird(String title, int age, String habitat) {
        this(title, age, habitat, 0);
    }

    public FlyingBird(String title, int age, String habitat, float moveSpeed) {
        this(title, age, habitat, moveSpeed, null);
    }

    public FlyingBird(String title, int age, String habitat, float moveSpeed, String name) {
        super(title, age, habitat, moveSpeed, name);
        FlyingBird.add(this);
    }


    public void fly() {
        System.out.println("\nЛечу...");
    }

    @Override
    public void eat() {
        System.out.println("\nРазрываю пищу клювом и проглатываю.");
    }

    @Override
    public void go() {
        System.out.println("\nЛечу, куда хочу.");
    }


    public String getMoveType() {
        if (!DataService.isCorrect(moveType)) {
            return UNKNOWN;
        }
        return moveType;
    }

    public void setMoveType(String moveType) {
        if (DataService.isCorrect(moveType)) {
            this.moveType = moveType;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlyingBird)) return false;
        FlyingBird flyingBird = (FlyingBird) o;
        return this.getName().equals(flyingBird.getName())
                && this.getTitle().equals(flyingBird.getTitle())
                && this.getAge() == flyingBird.getAge();
    }
}
