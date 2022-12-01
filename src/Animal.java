import dataService.DataService;

import java.time.LocalDate;

public abstract class Animal {
    private String name;
    private int birthYear;

    private static int unknownAnimalCount = 0;

    public Animal(String name, int age) {
        if (DataService.isCorrect(name)) {
            this.name = name;
        } else {
            this.name = "\"" + ++unknownAnimalCount + "-е безымянное животное\"";
        }
        setAge(age);
    }

    protected abstract void eat();

    protected abstract void go();

    public void sleep() {

    }

    public String getName() {
        if (!DataService.isCorrect(name)) {
            return "<данных нет>";
        }
        return name;
    }

    public void setName(String name) {
        if (DataService.isCorrect(name)) {
            this.name = name;
        } else {
            if (!this.name.matches("-е безымянное животное\"")) {
                this.name = "\"" + ++unknownAnimalCount + "-е безымянное животное\"";
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

    protected static void increaseUnkAnimalCount() {
        ++unknownAnimalCount;
    }

    protected static void decreaseUnkAnimalCount() {
        --unknownAnimalCount;
    }
}
