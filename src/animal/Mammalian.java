package animal;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.util.Arrays;

public abstract class Mammalian extends Animal {

    private static int mammaliansCount = 0;
    private static Mammalian[] mammalians = new Mammalian[0];

    protected static boolean add(Mammalian mammalian) {
        if (mammalian == null) {
            return false;
        }
        if (mammalians == null) {
            mammalians = new Mammalian[0];
            mammaliansCount = 0;
        }
        for (Mammalian current :
                mammalians) {
            if (current != null && current.equals(mammalian)) {
                System.out.println(ALREADY_EXIST.replace(" БД.", " Перечень млекопитающих."));
                return false;
            }
        }
        mammalians = Arrays.copyOf(mammalians, ++mammaliansCount);
        mammalians[mammaliansCount - 1] = mammalian;
        return true;
    }

    private static boolean delete(Mammalian mammalian) {
        if (mammalian == null) {
            return false;
        }
        if (mammalians == null) {
            System.out.println(LIST_IS_EMPTY.replace(" животных ", " млекопитающих "));
            System.out.println(CAN_NOT_DELETE);
            return false;
        }
        int matchPointer = DataService.getFirstMatchIndex(mammalians, mammalian);
        if (matchPointer >= 0) {
            mammalians[matchPointer] = null;
            mammalians = DataService.getNotNullObjects(mammalians);
            --mammaliansCount;
            return true;
        }
        return false;
    }

    public static void show() {
        System.out.println("\nСписок всех млекопитающих:");
        TextService.printList(mammalians, TextService.PrintModes.NUMBERED_LIST_PM);
    }


    protected Mammalian(String title, int age, String habitat, float moveSpeed, String name) {
        super(title, age, habitat, moveSpeed, name);
        Mammalian.add(this);
    }


    protected void walk() {
        System.out.println("\nЯ гуляю...");
    }

    @Override
    protected abstract void eat();

    @Override
    protected abstract void go();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mammalian)) return false;
        Mammalian mammalian = (Mammalian) o;
        return this.getName().equals(mammalian.getName())
                && this.getTitle().equals(mammalian.getTitle())
                && this.getAge() == mammalian.getAge();
    }
}
