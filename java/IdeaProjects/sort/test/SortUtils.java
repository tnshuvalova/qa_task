import java.util.Random;

public class SortUtils {
    protected Integer[] generateArray(int size) {
        Integer[] array = new Integer[size];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(10);
        }
        return array;
    }
}
