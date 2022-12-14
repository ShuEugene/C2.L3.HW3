package animal;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.util.Arrays;

public class WalkingBird extends Bird {

    private static int walkingBirdsCount = 0;
    private static WalkingBird[] walkingBirds = new WalkingBird[0];

    protected static boolean add(WalkingBird walkingBird) {
        if (walkingBird == null) {
            return false;
        }
        if (walkingBirds == null) {
            walkingBirds = new WalkingBird[0];
            walkingBirdsCount = 0;
        }
        for (WalkingBird current :
                walkingBirds) {
            if (current != null && current.equals(walkingBird)) {
                System.out.println(ALREADY_EXIST.replace(" БД.", " Перечень нелетающих птиц."));
                return false;
            }
        }
        walkingBirds = Arrays.copyOf(walkingBirds, ++walkingBirdsCount);
        walkingBirds[walkingBirdsCount - 1] = walkingBird;
        return true;
    }

    private static boolean delete(WalkingBird walkingBird) {
        if (walkingBird == null) {
            return false;
        }
        if (walkingBirds == null) {
            System.out.println(LIST_IS_EMPTY.replace(" животных ", " нелетающих птиц "));
            return false;
        }
        int matchPointer = DataService.getFirstMatchIndex(walkingBirds, walkingBird);
        if (matchPointer >= 0) {
            walkingBirds[matchPointer] = null;
            walkingBirds = DataService.getNotNullObjects(walkingBirds);
            --walkingBirdsCount;
            return true;
        }
        return false;
    }

    public static void show() {
        System.out.println("\nСписок всех нелетающих птиц:");
        TextService.printList(walkingBirds, TextService.PrintModes.NUMBERED_LIST_PM);
    }


    private String moveType;


    public WalkingBird(String title) {
        this(title, 0);
    }

    public WalkingBird(String title, int age) {
        this(title, age, null);
    }

    public WalkingBird(String title, int age, String habitat) {
        this(title, age, habitat, 0);
    }

    public WalkingBird(String title, int age, String habitat, float moveSpeed) {
        this(title, age, habitat, moveSpeed, null);
    }

    public WalkingBird(String title, int age, String habitat, float moveSpeed, String name) {
        super(title, age, habitat, moveSpeed, name);
    }


    public void walk() {
        System.out.println("\nГуляю...");
    }

    @Override
    public void eat() {
        System.out.println("\nЕм зерно, травку и червячков.");
    }

    @Override
    public void go() {
        System.out.println("\nБегаю «на своих двоих».");
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
        if (!(o instanceof WalkingBird)) return false;
        WalkingBird walkingBird = (WalkingBird) o;
        return this.getName().equals(walkingBird.getName())
                && this.getTitle().equals(walkingBird.getTitle())
                && this.getAge() == walkingBird.getAge();
    }
}
