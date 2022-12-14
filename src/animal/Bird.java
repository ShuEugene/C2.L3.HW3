package animal;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.util.Arrays;

public abstract class Bird extends Animal{

    private static int birdsCount = 0;
    private static Bird[] birds = new Bird[0];

    protected static boolean add(Bird bird) {
        if (bird == null) {
            return false;
        }
        if (birds == null) {
            birds = new Bird[0];
            birdsCount = 0;
        }
        for (Bird current :
                birds) {
            if (current != null && current.equals(bird)) {
                System.out.println(ALREADY_EXIST.replace(" БД.", " Перечень птиц."));
                return false;
            }
        }
        birds = Arrays.copyOf(birds, ++birdsCount);
        birds[birdsCount - 1] = bird;
        return true;
    }

    private static boolean delete(Bird bird) {
        if (bird == null) {
            return false;
        }
        if (birds == null) {
            System.out.println(LIST_IS_EMPTY.replace(" животных ", " птиц "));
            return false;
        }
        int matchPointer = DataService.getFirstMatchIndex(birds, bird);
        if (matchPointer >= 0) {
            birds[matchPointer] = null;
            birds = DataService.getNotNullObjects(birds);
            --birdsCount;
            return true;
        }
        return false;
    }

    public static void show() {
        System.out.println("\nСписок всех птиц:");
        TextService.printList(birds, TextService.PrintModes.NUMBERED_LIST_PM);
    }


    protected Bird(String title, int age, String habitat, float moveSpeed, String name) {
        super(title, age, habitat, moveSpeed, name);
        Bird.add(this);
    }

    protected abstract void eat();

    protected abstract void go();

    public void hunt() {
        System.out.println("Охота «Воздух-Земля»!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bird)) return false;
        Bird bird = (Bird) o;
        return this.getName().equals(bird.getName())
                && this.getTitle().equals(bird.getTitle())
                && this.getAge() == bird.getAge();
    }
}