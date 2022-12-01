package dataService;

import java.util.Arrays;

public class DataService {

    static public boolean isCorrect(String parameter) {
        return parameter != null && !parameter.isBlank() && !parameter.isEmpty();
    }

    static public boolean isCorrect(int parameter) {
        return parameter > 0;
    }

    static public boolean isCorrect(float parameter) {
        return parameter > 0;
    }

    static public boolean isCorrect(Object parameter) {
        return parameter != null;
    }

    static public int getValidObjectsNumber(Object[] objects) {
        if (objects == null) return 0;
        int validRecordsNumber = objects.length;
        for (Object curObj :
                objects) {
            if (curObj == null) --validRecordsNumber;
        }
        return validRecordsNumber;
    }

    static public int getValidObjectsNumber(Object[][] objects) {
        if (objects == null) return 0;
        int validRecordsNumber = objects.length;
        for (Object[] object : objects) {
            if (object[0] == null) {
                --validRecordsNumber;
            }
        }
        return validRecordsNumber;
    }

    static public <T> T[] getValidObjects(T[] objects) {
        int validObjectsNumber = getValidObjectsNumber(objects);
        if (validObjectsNumber <= 0) return null;
        T[] validObjects = Arrays.copyOf(objects, validObjectsNumber);
        int validObjectIndex = -1;
        for (T curObj : objects) {
            if (curObj != null) validObjects[++validObjectIndex] = curObj;
        }
        return validObjects;
    }

    static public String[][] getValidObjects(String[][] objects) {
        int validObjectsNumber = getValidObjectsNumber(objects);
        if (validObjectsNumber <= 0) return null;
        String[][] validObjects = new String[validObjectsNumber][objects[0].length];
        int validObjectIndex = -1;
        for (String[] object : objects) {
            if (object[0] != null) {
                validObjects[++validObjectIndex] = Arrays.copyOf(object, object.length);
            }
        }
        return validObjects;
    }

    static public boolean isLetters(String string) {
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
}
