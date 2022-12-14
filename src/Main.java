import animal.*;
import auxiliaryLibrary.DataService;
import auxiliaryLibrary.TextService;

public class Main {
    public static void main(String[] args) {

        Amphibian frog = new Amphibian("лягушка", 2, "болото", 0, "Квакушка");
        Amphibian frog1 = new Amphibian("лягушка", 2, "болото", 0, "Квакушка");
        Amphibian snakeFreshwater = new Amphibian("Уж Пресноводный", 1, "приводоёмье", 0);
        Herbivore gazelle = new Herbivore("Газель", 3, "степь", 80);
        Herbivore giraffe = new Herbivore("Жираф", 5, "саванна", 55);
        Herbivore horse = new Herbivore("Лошадь", 4, "степь", 60);
        Predator hyena = new Predator("Гиена", 6, "саванна", 60);
        Predator tiger = new Predator("Тигр", 9, "лес, саванна, чаща, мангровое болото, полупустыня, сопка, тайга", 65);
        Predator bear = new Predator("Медведь", 16, "лес", 50);
        WalkingBird peacock = new WalkingBird("Павлин", 3, "джунгли, заросли, берега рек", 25);
        WalkingBird penguin = new WalkingBird("Пингвин", 1, "прибрежные районы океанов", 2);
        WalkingBird dodoBird = new WalkingBird("Птица ДоДо", 2, "остров Маврикий", 0);
        FlyingBird seagull = new FlyingBird("Чайка", 1, "прибрежные районы океанов", 160);
        FlyingBird albatross = new FlyingBird("Альбатрос", 5, "ветренные прибрежные районы океанов", 50);
        FlyingBird falcon = new FlyingBird("Сокол", 3, "всюду, кроме Антарктики", 320);

        Animal.show();
    }
}