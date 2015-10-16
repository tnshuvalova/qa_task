import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SortTest extends SortUtils {
    @Test
    public void testBubbleSortIntAsc(){
        Integer[] array = generateArray(10);
        Integer[] array2 = new Integer[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);

        Arrays.sort(array2);

        Sorter<Integer> sorter = new BubbleSort<Integer>();
        assertArrayEquals(array2, sorter.sortAsc(array));
    }

    @Test
    public void testSelectionSortIntAsc(){
        Integer[] array = generateArray(10);
        Integer[] array2 = new Integer[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);

        Arrays.sort(array2);

        Sorter<Integer> sorter = new SelectionSort<Integer>();
        assertArrayEquals(array2, sorter.sortAsc(array));
    }

    @Test
    public void testGnomeSortIntAsc(){
        Integer[] array = generateArray(10);
        Integer[] array2 = new Integer[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);

        Arrays.sort(array2);

        Sorter<Integer> sorter = new GnomeSort<Integer>();
        assertArrayEquals(array2, sorter.sortAsc(array));
    }

    @Test
    public void testQuickSortIntAsc(){
        Integer[] array = generateArray(10);
        Integer[] array2 = new Integer[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);

        Arrays.sort(array2);

        QuickSort<Integer> sorter = new QuickSort<Integer>();
        assertArrayEquals(array2, sorter.sortAsc(array, 0, array.length-1));
    }

    @Test
    public void testBubbleSortDoubleAsc(){
        Double[] array = new Double[10];
        for (int i = 0; i < 10; i++) {
            array[i] = new Double(10 - i);
        }
        Double[] array2 = new Double[10];
        for (int i = 0; i < 10; i++) {
            array2[i] = new Double(i + 1);
        }
        Sorter<Double> sorter = new BubbleSort<Double>();
        assertArrayEquals(array2, sorter.sortAsc(array));
    }

    @Test
    public void testBubbleSortStringAsc(){
        String[] array = new String[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i + "";
        }
        String[] array2 = new String[10];
        for (int i = 0; i < 10; i++) {
            array2[i] = i + "";
        }
        Sorter<String> sorter = new BubbleSort<String>();
        assertArrayEquals(array2, sorter.sortAsc(array));
    }

    @Test
    public void testFactRec() {
        Trash t = new Trash();
        assertEquals(6, t.factorial(3));
        assertEquals(120, t.factorial(5));
        assertEquals(720, t.factorial(6));
    }
}
