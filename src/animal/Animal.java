package animal;

import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public abstract class Animal {

    protected static final String UNKNOWN = "<не известно>";
    protected static final String UNTITLED_ANIMAL_ADDED = "\nВ БД внесена запись о неизвестном животном.";
    protected static final String UNTITLED_ANIMALS_IS_OUT = "\nНа данный момент в БД отсутствуют неизвестные животные.";
    protected static final String ALREADY_EXIST = "\nДанное животное уже внесено в БД.";
    protected static final String COUNT_IS_NULL = "\nСчётчик уже равен нулю. Уменьшение исключено.";
    protected static final String LIST_IS_EMPTY = "\nПеречень животных пуст.";
    protected static final String CAN_NOT_DELETE = "\nУдаление исключено.";

    private static int animalsCount = 0;
    private static Animal[] animals = new Animal[0];

    private static int untitledAnimalsCount = 0;
    protected static Animal[] untitledAnimals;
    private static int unnamedAnimalsCount = 0;
    protected static Animal[] unnamedAnimals;


    protected static boolean add(Animal animal) {
        if (animal == null) {
            return false;
        }
        if (animals == null) {
            animals = new Animal[0];
            animalsCount = 0;
        }
        for (Animal current :
                animals) {
            if (current != null && current.equals(animal)) {
                System.out.println(ALREADY_EXIST);
                return false;
            }
        }
        animals = Arrays.copyOf(animals, ++animalsCount);
        animals[animalsCount - 1] = animal;
        return true;
    }

    protected static boolean delete(Animal animal) {
        if (animal == null) {
            return false;
        }
        if (!DataService.isCorrect(animals)) {
            System.out.println(LIST_IS_EMPTY);
            System.out.println(CAN_NOT_DELETE);
            return false;
        }
        int matchPointer = DataService.getFirstMatchIndex(animals, animal);
        if (matchPointer >= 0) {
            animals[matchPointer] = null;
            animals = DataService.getNotNullObjects(animals);
            return true;
        }
        return false;
    }

    public static void show() {
        System.out.println("\nСписок всех животных:");
        TextService.printList(animals, TextService.PrintModes.NUMBERED_LIST_PM);
    }

    protected static void addUntitledAnimal(Animal untitledAnimal) {
        if (untitledAnimal == null) {
            return;
        }
        if (untitledAnimals == null) {
            untitledAnimals = new Animal[0];
        }
        untitledAnimals = Arrays.copyOf(untitledAnimals, ++untitledAnimalsCount);
        untitledAnimals[untitledAnimalsCount - 1] = untitledAnimal;
        System.out.println(UNTITLED_ANIMAL_ADDED);
    }

    protected static void delUntitledAnimal(Animal titledAnimal) {
        if (titledAnimal == null) {
            return;
        }
        if (!DataService.isCorrect(untitledAnimals)) {
            System.out.println(UNTITLED_ANIMALS_IS_OUT);
            return;
        }
        for (int index = 0; index < untitledAnimals.length; ++index) {
            if (untitledAnimals[index] == titledAnimal) {
                untitledAnimals[index] = null;
                untitledAnimals = DataService.getNotNullObjects(untitledAnimals);
                System.out.println("\n" + titledAnimal.getTitle() + " удалён(-а) из Списка неизвестных.");
                renameUntitledAnimals();
                if (--untitledAnimalsCount < 1) {
                    System.out.println(UNTITLED_ANIMALS_IS_OUT.replace("На данный момент ", "Теперь "));
                }
                return;
            }
        }
    }

    protected static void renameUntitledAnimals() {
        if (DataService.isCorrect(untitledAnimals)) {
            for (int index = 0; index < untitledAnimals.length; ++index) {
                if (untitledAnimals[index] != null) {
                    untitledAnimals[index].setTitle("<" + (index + 1) + "-е неизвестное животное>");
                }
            }
        }
    }

    protected static void addUnnamedAnimal(Animal unnamedAnimal) {
        if (unnamedAnimal == null) {
            return;
        }
        if (unnamedAnimals == null) {
            unnamedAnimals = new Animal[0];
        }
        unnamedAnimals = Arrays.copyOf(unnamedAnimals, ++unnamedAnimalsCount);
        unnamedAnimals[unnamedAnimalsCount - 1] = unnamedAnimal;
        System.out.println(UNTITLED_ANIMAL_ADDED.replace(" неизвестном ", " безымянном "));
    }

    protected static void delUnnamedAnimal(Animal namedAnimal) {
        if (namedAnimal == null) {
            return;
        }
        if (!DataService.isCorrect(unnamedAnimals)) {
            System.out.println(UNTITLED_ANIMALS_IS_OUT.replace(" неизвестные ", " безымянные "));
            return;
        }
        for (int index = 0; index < unnamedAnimals.length; ++index) {
            if (unnamedAnimals[index] == namedAnimal) {
                unnamedAnimals[index] = null;
                unnamedAnimals = DataService.getNotNullObjects(unnamedAnimals);
                System.out.println("\n" + namedAnimal.getName() + " удалён(-а) из Списка безымянных.");
                renameUnnamedAnimals();
                if (--unnamedAnimalsCount < 1) {
                    System.out.println(UNTITLED_ANIMALS_IS_OUT.replace("На данный момент ", "Теперь "));
                }
                return;
            }
        }
    }

    protected static void renameUnnamedAnimals() {
        if (DataService.isCorrect(unnamedAnimals)) {
            for (int index = 0; index < unnamedAnimals.length; ++index) {
                if (unnamedAnimals[index] != null) {
                    unnamedAnimals[index].setName("<" + (index + 1) + "-е безымянное животное>");
                }
            }
        }
    }

//  static'get'n'setters not used?
/*
    public static int getAnimalsCount() {
        return animalsCount;
    }

    public static void increaseAnimalsCount() {
        ++Animal.animalsCount;
    }

    public static void decreaseAnimalsCount() {
        if (animalsCount < 1) {
            System.out.println(COUNT_IS_NULL.replace("Счётчик ", "Счётчик животных "));
            return;
        }
        --Animal.animalsCount;
    }

    public static Animal[] getAnimals() {
        if (animals == null) {
            animals = new Animal[0];
        }
        return animals;
    }

    public static void setAnimals(Animal[] animals) {
        if (DataService.isCorrect(animals)) {
            Animal.animals = animals;
        }
    }

    public static int getUntitledAnimalsCount() {
        return untitledAnimalsCount;
    }

    public static void increaseUntitledAnimalsCount() {
        ++Animal.untitledAnimalsCount;
    }

    public static void decreaseUntitledAnimalsCount() {
        if (unnamedAnimalsCount < 1) {
            System.out.println(COUNT_IS_NULL.replace("Счётчик ", "Счётчик неизвестных животных "));
            return;
        }
        --Animal.untitledAnimalsCount;
    }

    public static Animal[] getUntitledAnimals() {
        if (untitledAnimals == null) {
            untitledAnimals = new Animal[0];
        }
        return untitledAnimals;
    }

    public static void setUntitledAnimals(Animal[] untitledAnimals) {
        if (DataService.isCorrect(untitledAnimals)) {
            Animal.untitledAnimals = untitledAnimals;
        }
    }

    public static int getUnnamedAnimalsCount() {
        return unnamedAnimalsCount;
    }

    public static void increaseUnnamedAnimalsCount() {
        ++Animal.unnamedAnimalsCount;
    }

    public static void decreaseUnnamedAnimalsCount() {
        if (unnamedAnimalsCount < 1) {
            System.out.println(COUNT_IS_NULL.replace("Счётчик ", "Счётчик безымянных животных "));
            return;
        }
        --Animal.unnamedAnimalsCount;
    }
*/


    private String title, name;
    private int birthYear;

    private String habitat;
    private float moveSpeed;
    protected String moveSpeedUnit = "км/ч";

    protected Animal(String title, int age, String habitat, float moveSpeed, String name) {
        setTitle(title);
        setAge(age);
        setHabitat(habitat);
        setMoveSpeed(moveSpeed);
        setName(name);
        Animal.add(this);
    }

    protected abstract void eat();

    protected abstract void go();

    protected void sleep() {
        System.out.println("\nХр-ррр-рр.... Хр-рр-ррр...");
    }


    public String getName() {
        if (!DataService.isCorrect(name)) {
            return UNKNOWN;
        }
        return name;
    }

    public void setName(String name) {
        if (DataService.isCorrect(name)) {
            if (isUnnamedAnimal()) {
                delUnnamedAnimal(this);
            }
            this.name = name;
        } else {
            this.name = "<" + ++unnamedAnimalsCount + "-е безымянное животное>";
        }
    }

    private boolean isUntitledAnimal() {
        if (!DataService.isCorrect(untitledAnimals)) {
            return false;
        }
        for (Animal current :
                untitledAnimals) {
            if (current != null && current.equals(this)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUnnamedAnimal() {
        if (!DataService.isCorrect(unnamedAnimals)) {
            return false;
        }
        for (Animal current :
                unnamedAnimals) {
            if (current != null && current.equals(this)) {
                return true;
            }
        }
        return false;
    }

    public String getTitle() {
        if (!DataService.isCorrect(title)) {
            return "<данных нет>";
        }
        return title;
    }

    public void setTitle(String title) {
        if (DataService.isCorrect(title)) {
            if (isUntitledAnimal()) {
                delUntitledAnimal(this);
            }
            this.title = title;
        } else {
            if (!isUntitledAnimal()) {
                addUntitledAnimal(this);
                this.title = "<" + untitledAnimalsCount + "-е неизвестное животное>";
            }
        }
    }

    public int getAge() {
        if (birthYear < LocalDate.now().getYear()) {
            return LocalDate.now().getYear() - birthYear;
        } else {
            return 0;
        }
    }

    public void setAge(int age) {
        if (age > 0) {
            this.birthYear = LocalDate.now().getYear() - age;
        }
    }

    public String getHabitat() {
        if (!DataService.isCorrect(habitat)) {
            return UNKNOWN;
        }
        return habitat;
    }

    public void setHabitat(String habitat) {
        if (DataService.isCorrect(habitat)) {
            this.habitat = habitat;
        }
    }

    public String getStrMoveSpeed() {
        if (moveSpeed <= 0) {
            return UNKNOWN;
        }
        return moveSpeed + " " + moveSpeedUnit;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        if (moveSpeed > 0) {
            this.moveSpeed = moveSpeed;
        }
    }

    protected String getSpecies() {
        String speciesTitle;
        switch (getClass().getSimpleName()) {
            case "Amphibian":
                speciesTitle = "земноводное";
                break;
            case "WalkingBird":
                speciesTitle = "нелетающая птица";
                break;
            case "FlyingBird":
                speciesTitle = "летающая птица";
                break;
            case "Herbivore":
                speciesTitle = "травоядное";
                break;
            case "Predator":
                speciesTitle = "хищник";
                break;
            default:
                speciesTitle = "<неизвестный тип>";
        }
        return speciesTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getTitle().equals(animal.getTitle()) && Objects.equals(getName(), animal.getName()) && getAge() == animal.getAge() && Objects.equals(getHabitat(), animal.getHabitat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getName(), getAge(), getHabitat(), getMoveSpeed());
    }

    @Override
    public String toString() {
        String title = getTitle();
        if (!title.contains(" неизвестное ")) {
            title = TextService.startWithCapLetter(title);
        }
        String name = getName();
        if (!name.contains(" безымянное ")) {
            name = TextService.startWithCapLetter(name);
        }
        return String.format("%s %s (возраст: %d ; вид: %s; среда обитания: %s; скорость передвижения: %s)",
                title, name, getAge(), getSpecies(), getHabitat(), getStrMoveSpeed());
    }
}
