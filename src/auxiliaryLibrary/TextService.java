package auxiliaryLibrary;

public class TextService {

    protected static final String NO_DATA_TO_OUTPUT = "\nДанные для вывода отсутствуют.";

    public enum PrintModes {NO_PUNCTUATION, SIMPLE_LIST_PM, BULLETED_LIST, NUMBERED_LIST_PM;}

//  withLetters
    public static boolean isLetters(String string) {
        int noLetterMatchesCount = 0;
        char[] chars = string.toCharArray();
        for (char curSymbol :
                chars) {
            if (!Character.isLetter(curSymbol)) {
                ++noLetterMatchesCount;
            }
        }
        return noLetterMatchesCount == 0;
    }

    public static String startWithCapLetter(String string) {
        if (!DataService.isCorrect(string)) {
            return string;
        }
        if (string.length() < 2) {
            return string.toUpperCase();
        }
        int index;
        String firstLetter = null;
        for (index = 0; index < string.length(); ++index) {
            firstLetter = string.substring(index, index + 1);
            if (isLetters(firstLetter)) {
                break;
            }
        }
        firstLetter = firstLetter.toUpperCase();
        if (index == 0) {
            return firstLetter + string.substring(1);
        } else {
            String firstSubstring = string.substring(0, index);
            return firstSubstring + firstLetter + string.substring(index + 1);
        }
    }

//  output
    public static void printList(Object[] objects) {
        printList(objects, null);
    }

    public static void printList(Object[] objects, PrintModes printMode) {
        if (!DataService.isCorrect(objects)) {
            System.out.println(NO_DATA_TO_OUTPUT);
            return;
        }
        if (printMode == null) {
            printMode = PrintModes.NO_PUNCTUATION;
        }
        String listItem;
        char itemSeparator = ';';
        int indexOfLastNotNullObject = DataService.getIndexOfLastNotNullObject(objects);
        for (int index = 0; index < objects.length; index++) {
            if (objects[index] != null) {
                if (index == indexOfLastNotNullObject) {
                    itemSeparator = '.';
                }
                switch (printMode) {
                    case SIMPLE_LIST_PM:
                        listItem = String.format("%s%c", objects[index], itemSeparator);
                        break;
                    case BULLETED_LIST:
                        listItem = String.format("* %s%c", objects[index], itemSeparator);
                        break;
                    case NUMBERED_LIST_PM:
                        listItem = String.format("%d. %s%c", index + 1, objects[index], itemSeparator);
                        break;
                    default:
                        listItem = String.format("%s", objects[index]);
                }
                System.out.println(listItem);
            }
        }
    }
}
